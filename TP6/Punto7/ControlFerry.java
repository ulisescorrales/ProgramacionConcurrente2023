/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP6.Punto7;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class ControlFerry extends Thread{
    Ferry ferry;

    public ControlFerry(Ferry ferry, String name) {
        super(name);
        this.ferry = ferry;
    }
    public void run(){
        while(true){
            try {
                ferry.arrancar();
                Thread.sleep(100);
                ferry.llegarDestino();
                ferry.regresar();
                Thread.sleep(100);
                ferry.llegarOrigen();
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlFerry.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
