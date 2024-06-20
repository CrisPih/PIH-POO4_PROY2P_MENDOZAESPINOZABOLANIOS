/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectopoo.heladeria;


import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.IOException;

public class ImageLoader implements IImageLoader {
    @Override
    public Image loadImage(String path) throws IOException {
        try (FileInputStream input = new FileInputStream(path)) {
            return new Image(input);
        }
    }
}
