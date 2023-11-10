/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto5Sem;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Canibal extends Thread{
    Olla olla;

    public Canibal(Olla olla, String name) {
        super(name);
        this.olla = olla;
    }
    public void run(){
        try {
            this.olla.comer();
        } catch (InterruptedException ex) {
            Logger.getLogger(Canibal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
