/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto8Monitor;

import programacionconcurrente2023.TP5.Punto8.*;


/**
 *
 * @author ulisescorrales
 */
public class BabuinoB extends Thread {

    CuerdaMonitor cuerda;

    public BabuinoB(CuerdaMonitor cuerda, String name) {
        super(name);
        this.cuerda = cuerda;
    }

    public void run() {
        try {
            cuerda.subirB();
            Thread.sleep(2000);
            cuerda.bajarB();
        } catch (InterruptedException ex) {
            java.util.logging.Logger.getLogger(BabuinoB.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
