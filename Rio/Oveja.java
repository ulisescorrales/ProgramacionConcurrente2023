/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.Rio;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Oveja extends Thread{
    Rio rio;

    public Oveja(Rio rio, String name) {
        super(name);
        this.rio = rio;
    }
    public void run(){
        try {
            rio.entrarOveja();
            Thread.sleep(1000);
            rio.salirOveja();
        } catch (InterruptedException ex) {
            Logger.getLogger(Oveja.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
}
