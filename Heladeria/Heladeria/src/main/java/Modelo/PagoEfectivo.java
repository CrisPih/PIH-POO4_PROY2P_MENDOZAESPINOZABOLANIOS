/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

public class PagoEfectivo implements TipodePago {
    @Override
    public double calcularTotalPago(double precioBase) {
        return precioBase;
    }
}