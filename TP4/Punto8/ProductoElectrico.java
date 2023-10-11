/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto8;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class ProductoElectrico extends Thread{
    private ControladorProduccion produccion;

    public ProductoElectrico(ControladorProduccion produccion, String name) {
        super(name);
        this.produccion = produccion;
    }
    
    public void run(){
        Random num=new Random();
        produccion.llegaElectrico();        
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(ProductoElectrico.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("Sale "+this.getName());
        produccion.saleElectrico();
    }
}
