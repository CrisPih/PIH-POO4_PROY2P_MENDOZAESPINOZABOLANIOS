    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package proyectopoo.heladeria;

import Modelo.Topping;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import proyectopoo.heladeria.IToppingService;
import proyectopoo.heladeria.IImageLoader;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class VentanaToppingsController implements Initializable {

    public static int numPedido = 9999;
    public static double total = VentanaSaboresController.totalpago;

    @FXML
    private VBox root_toppings;
    @FXML
    private HBox HBox1Toppings;
    @FXML
    private HBox HBox2Toppings;
    @FXML
    private HBox HBox3Toppings;
    @FXML
    private VBox VBoxtoppings;
    @FXML
    private Label totaltoppings;
    @FXML
    private Button botonContinuarToppings;
    @FXML
    private ImageView imgvtoppings;
    @FXML
    private ImageView imgvgif;

    private ArrayList<Topping> listatoppings = new ArrayList<>();
    private ArrayList<Topping> toppingselec = new ArrayList<>();

    private IToppingService toppingService;
    private IImageLoader imageLoader;

    public VentanaToppingsController(IToppingService toppingService, IImageLoader imageLoader) {
        this.toppingService = toppingService;
        this.imageLoader = imageLoader;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        total = VentanaSaboresController.totalpago;
        try {
            imgvtoppings.setImage(imageLoader.loadImage("rutaImagenes/bases3.png"));
            imgvgif.setImage(imageLoader.loadImage("rutaImagenes/L.gif"));
        } catch (IOException e) {
            System.out.println("Error al cargar imagen: " + e.getMessage());
        }
        totaltoppings.setText(String.valueOf(total));
        listatoppings = toppingService.loadToppings();
        crearCheckbox();
    }

    private void crearCheckbox() {
        for (Topping tp : listatoppings) {
            CheckBox chb = new CheckBox(tp.toString());
            VBoxtoppings.getChildren().addAll(chb);
            chb.setOnAction(event -> recuperarToppings(chb, tp));
        }
    }

    private void recuperarToppings(CheckBox ch, Topping tp) {
        if (ch.isSelected()) {
            total += tp.getPrecioTopping();
            toppingselec.add(tp);
        } else {
            total -= tp.getPrecioTopping();
            toppingselec.remove(tp);
        }
        totaltoppings.setText("$ " + total);
    }

    @FXML
    private void botonContinuar(ActionEvent e) {
        App.pedidoactual.setListatopping(toppingselec);
        try {
            App.setRoot("Resumen");
        } catch (IOException ioe) {
            System.out.println("No se ha podido cambiar la ventana");
        }
    }
}