/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author tomas
 */
public class Sabor {
    private String nombreSabor;
    private double precioSabor;

    public Sabor(String nombreSabor, double precioSabor) {
        this.nombreSabor = nombreSabor;
        this.precioSabor = precioSabor;
    }


    public String getNombreSabor() {
        return nombreSabor;
    }

    public void setNombreSabor(String nombreSabor) {
        this.nombreSabor = nombreSabor;
    }

    public double getPrecioSabor() {
        return precioSabor;
    }

    public void setPrecioSabor(double precioSabor) {
        this.precioSabor = precioSabor;
    }
    
    
    
}
