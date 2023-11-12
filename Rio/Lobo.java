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
public class Lobo extends Thread{
    Rio rio;

    public Lobo(Rio rio, String name) {
        super(name);
        this.rio = rio;
    }
    public void run(){
        rio.entrarLobo();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Lobo.class.getName()).log(Level.SEVERE, null, ex);
        }
        rio.salirLobo();
    }
}
