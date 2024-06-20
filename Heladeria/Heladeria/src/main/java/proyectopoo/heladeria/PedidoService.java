/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package proyectopoo.heladeria;

import Modelo.Pedido;

public class PedidoService {

    public Pedido obtenerPedidoActual() {
       
        return App.pedidoactual;  
    }

    public void actualizarPedido(Pedido pedido) {
     
        App.pedidoactual = pedido;  
    }    

}