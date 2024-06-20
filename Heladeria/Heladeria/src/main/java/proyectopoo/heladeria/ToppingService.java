/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectopoo.heladeria;

/**
 *
 * @author crisp
 */
import Modelo.ManejoArchivos;
import Modelo.Topping;
import java.util.ArrayList;

public class ToppingService implements IToppingService {
    @Override
    public ArrayList<Topping> loadToppings() {
        return ManejoArchivos.listaToppings();
    }
}