package proyectopoo.heladeria;

import Modelo.Local;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import proyectopoo.heladeria.IImageLoader;
import proyectopoo.heladeria.ImageLoader;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class VentanaUbicacionController implements Initializable {

    @FXML
    private Pane root1;
    @FXML
    private ImageView iv;

    private ILocalService localService;
    private IImageLoader imageLoader;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Inicialización del servicio de locales e imágenes
        this.localService = new LocalService();
        this.imageLoader = new ImageLoader();
        
        cargarFondo();
        cargarImagenes();
    }

    private void cargarFondo() {
        try {
            iv.setImage(imageLoader.loadImage("rutaImagenes/mapa.png"));
            iv.setFitHeight(800);
            iv.setFitWidth(820);
        } catch (IOException e) {
            System.out.println("Error al cargar imagen: " + e.getMessage());
        }
    }

    @FXML
    private void cargarImagenes() {
        Thread t = new Thread(() -> {
            localService.loadLocales().forEach(local -> {
                Platform.runLater(() -> cargarLocales(local.getEjex(), local.getEjey(), local.getLugar(), local.getHorario()));
                try {
                    Thread.sleep((int) (Math.random() * 10 + 1) * 1000);
                } catch (InterruptedException ex) {
                    ex.printStackTrace();
                }
            });
        });
        t.start();
    }

    private void cargarLocales(Double ejex, Double ejey, String direccion, String horarioslocal) {
        try {
            Image imgLocal = imageLoader.loadImage("rutaImagenes/heladomapa2.png");

            ImageView imageView1 = new ImageView(imgLocal);
            imageView1.setFitHeight(50);
            imageView1.setFitWidth(50);
            imageView1.setLayoutX(ejex);
            imageView1.setLayoutY(ejey);

            Local local = new Local(ejex, ejey, direccion, horarioslocal);
            imageView1.setUserData(local);

            imageView1.setOnMouseClicked(event -> mostrarDetalleLocal(event, local.getHorario(), local.getLugar()));

            root1.getChildren().addAll(imageView1);
        } catch (IOException e) {
            System.err.println("Error al cargar los locales: " + e.getMessage());
        }
    }

    private void mostrarDetalleLocal(MouseEvent event, String nombreLocal, String horariosLocal) {
        Label contenidoPopup = new Label("Detalles del local:\nDireccion: " + nombreLocal + "\nHorarios: " + horariosLocal);
        VBox pane = new VBox();
        VBox pane2 = new VBox();
        pane.setAlignment(Pos.CENTER);
        pane.setBackground(new Background(new BackgroundFill(Color.DARKKHAKI, CornerRadii.EMPTY, Insets.EMPTY)));
        pane.getChildren().add(contenidoPopup);
        pane2.setAlignment(Pos.CENTER_RIGHT);
        pane.getChildren().add(pane2);

        Stage popupStage = new Stage();
        popupStage.setTitle("Detalle del Local");
        popupStage.setScene(new Scene(pane, 230, 100));

        double x = event.getScreenX();
        double y = event.getScreenY();
        popupStage.setX(x);
        popupStage.setY(y);
        popupStage.show();

        Thread contadorThread = new Thread(() -> {
            try {
                for (int segundos = 5; segundos > 0; segundos--) {
                    Thread.sleep(1000);
                    final int segundosRestantes = segundos;
                    Platform.runLater(() -> {
                        contenidoPopup.setText("Detalles del local:\nNombre: " + nombreLocal + "\nHorarios: " + horariosLocal + "\nCerrando en " + segundosRestantes + " segundos");
                    });
                }
                Platform.runLater(popupStage::close);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        contadorThread.start();

        Button botoncerrar = new Button("Cerrar Ventana");
        pane2.getChildren().addAll(botoncerrar);
        botoncerrar.setOnAction(e -> popupStage.close());
    }
}