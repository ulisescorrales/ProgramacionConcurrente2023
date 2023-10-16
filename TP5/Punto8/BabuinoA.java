/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto8;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class BabuinoA extends Thread{
    Cuerda cuerda;

    public BabuinoA(Cuerda cuerda, String name) {
        super(name);
        this.cuerda = cuerda;
    }
    
    public void run(){
        try {
            cuerda.subirA();
            Thread.sleep(2000);
            cuerda.bajarA();
        } catch (InterruptedException ex) {
            Logger.getLogger(BabuinoA.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
