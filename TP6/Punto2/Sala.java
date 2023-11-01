/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP6.Punto2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class Sala {
    private int cantMesas=5;
    private Semaphore usarMesa=new Semaphore(this.cantMesas,true);
    private Semaphore mutex=new Semaphore(1);
    
    public void entrar(){
        try {
            mutex.acquire();
            System.out.println(Thread.currentThread().getName()+" pide entrar");
            mutex.release();
            usarMesa.acquire();
            mutex.acquire();
            System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra");            
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void salir(){
        try {
            mutex.acquire();
            System.out.println(Color.YELLOW+Thread.currentThread().getName()+" deja de usar la mesa");
            mutex.release();
            usarMesa.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
