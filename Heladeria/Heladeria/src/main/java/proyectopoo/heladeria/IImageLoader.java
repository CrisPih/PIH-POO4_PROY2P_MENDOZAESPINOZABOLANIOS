/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectopoo.heladeria;

import javafx.scene.image.Image;
import java.io.FileInputStream;
import java.io.IOException;

public interface IImageLoader {
    Image loadImage(String path) throws IOException;
}