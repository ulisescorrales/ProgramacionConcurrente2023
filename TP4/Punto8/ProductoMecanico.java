/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto8;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class ProductoMecanico extends Thread{
    private ControladorProduccion produccion;

    public ProductoMecanico(ControladorProduccion produccion, String name) {
        super(name);
        this.produccion = produccion;
    }
    
    public void run(){
        produccion.llegaMecanico();
        System.out.println("Llega "+this.getName());
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductoMecanico.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Sale "+this.getName());
        produccion.sale();
    }
}
