/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto8;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Control extends Thread{
    private ControladorProduccion produccion;

    public Control(ControladorProduccion produccion, String name) {
        super(name);
        this.produccion = produccion;
    }
    
    public void run(){
        Random num=new Random();
        while(true){
            try {
                Thread.sleep(num.nextInt(5000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Control.class.getName()).log(Level.SEVERE, null, ex);
            }            
            this.produccion.alternarLineas();
        }
    }
}
