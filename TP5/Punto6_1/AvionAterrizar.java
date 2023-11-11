/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto6_1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class AvionAterrizar extends Thread{
    TorreControl torre;

    public AvionAterrizar(TorreControl torre, String name) {
        super(name);
        this.torre = torre;
    }
        
    public void run(){
        torre.permitirAterrizar();
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(AvionAterrizar.class.getName()).log(Level.SEVERE, null, ex);
        }
        torre.terminarAterrizar();
    }
}
