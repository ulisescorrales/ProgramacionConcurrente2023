/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.ParqueEj;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Visitante extends Thread{
    Parque parque;

    public Visitante(Parque parque, String name) {
        super(name);
        this.parque = parque;
    }
    public void run(){
        try {
            parque.entrarVisitante();
            Thread.sleep(500);
            parque.salir();
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
