/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacionconcurrente2023.TP8.Punto2;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulises.corrales
 */
public class Mantenimiento extends Thread {

    Observatorio observatorio;

    public Mantenimiento(Observatorio observatorio, String name) {
        super(name);
        this.observatorio = observatorio;
    }

    public void run() {
        while (true) {
            try {                
                observatorio.entrarMantenimiento();
                Thread.sleep(2500);
                this.observatorio.salirMantenimiento();
                Thread.sleep(1500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mantenimiento.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }
}
