/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto2Sem;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Visitante extends Thread{
    Observatorio observatorio;

    public Visitante(Observatorio observatorio, String name) {
        super(name);
        this.observatorio = observatorio;
    }
    public void run(){
        try {
            observatorio.entrarVisitante();
            Thread.sleep(500);
            observatorio.salir();
        } catch (InterruptedException ex) {
            Logger.getLogger(Visitante.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
