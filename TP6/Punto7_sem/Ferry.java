/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP6.Punto7_sem;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class Ferry{
    int capacidad=10;
    Semaphore mutex=new Semaphore(1);
    Semaphore arrancarA=new Semaphore(0);
    Semaphore bajarB=new Semaphore(0);
    Semaphore arrancarB=new Semaphore(0);
    Semaphore entrarA=new Semaphore(capacidad);
    int cantAdentro=0;
    
    public void subir(){
        try {
            entrarA.acquire();
            System.out.println(Thread.currentThread().getName()+"pide entrar");
            mutex.acquire();
            cantAdentro++;
            System.out.println(Color.GREEN+Thread.currentThread().getName()+" entra");
            mutex.release();
            arrancarA.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ferry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void arrancar(){
        try {
            arrancarA.acquire(capacidad);
            System.out.println(Color.RED+Thread.currentThread().getName()+" arranca");
        } catch (InterruptedException ex) {
            Logger.getLogger(Ferry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void llegarDestino(){
        bajarB.release(capacidad);
        System.out.println(Color.RED+Thread.currentThread().getName()+" llega a destino");
    }
    public void bajar(){
        try {
            bajarB.acquire();
            mutex.acquire();
            System.out.println(Color.YELLOW+Thread.currentThread().getName()+" baja");
            mutex.release();
            arrancarB.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Ferry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void llegarOrigen(){
        System.out.println(Color.RED+Thread.currentThread().getName()+" llega a origen");
        entrarA.release(capacidad);
    }
    public void regresar(){
        try {
            arrancarB.acquire(capacidad);
            System.out.println(Color.RED+Thread.currentThread().getName()+" empieza a regresar");
        } catch (InterruptedException ex) {
            Logger.getLogger(Ferry.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
