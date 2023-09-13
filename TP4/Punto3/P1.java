/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto3;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class P1 extends Thread{
    ObjetoCompartido oc;    

    public P1(ObjetoCompartido oc, String name) {
        super(name);
        this.oc = oc;
    }

    public void run(){
        while(true){
            oc.comenzarP1();
            System.out.println("Comienza P1");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(P1.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Termina P1");
            oc.terminarP1();
        }
    }
    
    
    
}
