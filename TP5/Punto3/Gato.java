/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Gato extends Thread{
    Comedero comedero;

    public Gato(Comedero comedero, String name) {
        super(name);
        this.comedero = comedero;
    }
    
    public void run(){
        Object plato;
        try {
            plato=comedero.comerGato();
            Thread.sleep(2000);
            comedero.salirGato(plato);
        } catch (InterruptedException ex) {
            Logger.getLogger(Gato.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
