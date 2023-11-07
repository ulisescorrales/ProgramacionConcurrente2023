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
public class Observador extends Thread{
    Observatorio observatorio;

    public Observador(Observatorio observatorio, String name) {
        super(name);
        this.observatorio = observatorio;
    }
    public void run(){
        try {
            this.observatorio.entrarObservador();
            Thread.sleep(500);
            this.observatorio.salirObservador();
        } catch (InterruptedException ex) {
            Logger.getLogger(Observador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
