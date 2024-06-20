/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;

import proyectopoo.heladeria.SaborService;
import proyectopoo.heladeria.PedidoService;
import Modelo.IncompleteStageException;
import Modelo.Local;
import Modelo.ManejoArchivos;
import Modelo.Pedido;
import Modelo.Sabor;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Nahin
 */
public class VentanaSaboresController implements Initializable {
    private final SaborService saborService;
    private PedidoService pedidoService;
 
    @FXML
    private VBox VBox1Sabores;

    @FXML
    private VBox VBoxSabor1;
    @FXML
    private VBox VBox2Sabores;
    @FXML
    private VBox VBoxSabor2;
    @FXML
    private ComboBox<Sabor> cbsabor1;
    @FXML
    private ComboBox<Sabor> cbsabor2;
    @FXML
    private Button botonContinuarSabores;
    @FXML
    private HBox HBox1Sabores;
    @FXML
    private HBox HBox2Sabores;
    @FXML
    private HBox HBox3Sabores;
    @FXML
    private Label totalsabores;
    @FXML
    private ImageView imgvsabor;

    public static double totalpago;
 
    ArrayList<Sabor> listasabores = new ArrayList<>();

    public static Sabor sabor1;

    public static Sabor sabor2;
    
    public VentanaSaboresController(SaborService saborService, PedidoService pedidoService) {
       this.saborService = saborService;
       this.pedidoService = pedidoService;
    }

    /**
     * Metodo para inicializar el controller
     *
     * @param url se utiliza para especificar la ubicación del archivo FXML
     * @param rb maneja los recursos locales
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        totalpago = 0;
        try (FileInputStream f = new FileInputStream(ManejoArchivos.rutaImagenes + "bases3.png")) {
            Image i = new Image(f);
            imgvsabor.setImage(i);
        } catch (IOException i) {
            System.out.println("Error al cargar imagen");
        }
        cargarsabores();
        cargarcombo();
        cbsabor1.valueProperty().addListener((observable, oldValue, newValue) -> actualizarTotal());
        cbsabor2.valueProperty().addListener((observable, oldValue, newValue) -> actualizarTotal());
    }

    /**
     * Metodo para cargar los datos de un archivo de texto en una lista de
     * sabores
     */
    public void cargarsabores() {
        listasabores.addAll(saborService.obtenerSabores());
    }

    /**
     * Metodo para ordenar la lista de sabores en orden albetico
     *
     * @param listaSabores La lista de sabores que se quiere ordenar
     */
    public void ordenarSabores(ArrayList<Sabor> listaSabores) {
        Collections.sort(listaSabores); // Utiliza el comparador predefinido
    }

    /**
     * Metodo para actualizar el label "totalsabores" y se muestre en escena el
     * precio actulizado segun las selecciones que se hagan
     */
    private void actualizarTotal() {
        sabor1 = cbsabor1.getValue();
        sabor2 = cbsabor2.getValue();
        totalpago = App.pedidoactual.getBase1().getPrecioBase();
        if (sabor1 != null) {
            totalpago += sabor1.getPrecioSabor();
        }
        if (sabor2 != null) {
            totalpago += sabor2.getPrecioSabor();
        }

        totalsabores.setText("$ " + totalpago);
    }

    /**
     * Metodo para cargar la lista de sabores al combo box
     */
    public void cargarcombo() {
        ArrayList<Sabor> listaordenada = new ArrayList(listasabores);
        ordenarSabores(listaordenada);
        totalsabores.setText("$ " + App.pedidoactual.getBase1().getPrecioBase());
        cbsabor1.getItems().setAll(listaordenada);
        cbsabor2.getItems().setAll(listaordenada);
    }

    /**
     * Metodo implementado para enviar los datos de los sabores seleccionados al
     * pedido actual y pasar a la siguiente escena
     *
     * @param event Evento que se activara cuando clicken el boton
     */
    @FXML

    private void botoncontinuar(ActionEvent event) {
        try {
            boolean alMenosUnoSeleccionado = (cbsabor1.getValue() != null || cbsabor2.getValue() != null);
            if (alMenosUnoSeleccionado) {
                Sabor sabor1 = cbsabor1.getValue();
                Sabor sabor2 = cbsabor2.getValue();

                ArrayList<Sabor> selecsabores = new ArrayList<>();
                if (sabor1 != null) {
                    selecsabores.add(sabor1);
                }
                if (sabor2 != null) {
                    selecsabores.add(sabor2);
                }

                App.pedidoactual.setListasabores(selecsabores);
                // Actualiza el pedido actual utilizando el servicio de pedidos
                pedidoService.actualizarPedido(App.pedidoactual);

                try {
                    App.setRoot("VentanaToppings");
                } catch (IOException ioe) {
                    System.out.println("Ocurrió un error al intentar cambiar a la escena de toppings");
                }
            } else {
                System.out.println("Ningún ComboBox tiene algo seleccionado.");
                throw new IncompleteStageException("Debe escoger al menos un sabor");
            }
        } catch (IncompleteStageException e) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Se ha producido un error:\n" + e.getMessage());
            alert.showAndWait();
        }
    }
}
