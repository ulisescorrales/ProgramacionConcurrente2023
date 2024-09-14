/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.ParcialPunto1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Transporte extends Thread{
    private Almacen almacen;

    public Transporte(Almacen almacen, String name) {
        super(name);
        this.almacen = almacen;
    }
    public void run(){
        while(true){
            almacen.vaciarAlmacen();
            try {
                //Viajar
                Thread.sleep(5000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Transporte.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
