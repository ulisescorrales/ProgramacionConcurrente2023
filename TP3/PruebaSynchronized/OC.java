/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP3.PruebaSynchronized;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class OC {
    int dato=0;
    
    public void noSincronizado(){
        while(true){
            System.out.println(Thread.currentThread().getName()+" está usando el m. no sincronizado");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public synchronized void sincronizado(){
        while(true){
            System.out.println(Thread.currentThread().getName()+" está usando el m. sincronizado");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    public synchronized void sincronizado2(){
        while(true){
            System.out.println(Thread.currentThread().getName()+" está usando el m. sincronizado 2");
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(OC.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
