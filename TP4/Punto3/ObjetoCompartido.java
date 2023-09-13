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
public class ObjetoCompartido {
    Semaphore sem1=new Semaphore(1);
    Semaphore sem2=new Semaphore(0);
    Semaphore sem3=new Semaphore(0);
    
    
    public void comenzarP1(){
        try {
            sem1.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(ObjetoCompartido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void comenzarP2(){
        try {
            sem2.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(ObjetoCompartido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void comenzarP3(){
        try {
            sem3.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(ObjetoCompartido.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    //Terminar
    public void terminarP1(){
        sem2.release();
    }
    public void terminarP2(){
        sem3.release();
    }
    public void terminarP3(){
        sem1.release();
    }
}
