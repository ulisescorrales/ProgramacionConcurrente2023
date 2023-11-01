/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP7.Punto1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Jubilado extends Persona{

    public Jubilado(Sala sala, String name) {
        super(sala, name);
    }
    
    public void run(){
        try {
            this.sala.entrarJubilado();
            Thread.sleep(2000);
            this.sala.salir();
        } catch (InterruptedException ex) {
            Logger.getLogger(Jubilado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
