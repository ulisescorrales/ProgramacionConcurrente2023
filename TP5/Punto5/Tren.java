/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacionconcurrente2023.TP5.Punto5;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

public class Tren {
    private int capacidad;    
    private Semaphore puedeViajar=new Semaphore(0,true);
    private Semaphore puedeBajar=new Semaphore(0,true);
    private Semaphore puedeSubir;
    private Semaphore semSubir= new Semaphore(0,true);
    private Semaphore mutexBajar=new Semaphore(1,true);
    public Tren(int capacidad) {
        this.capacidad = capacidad;
        puedeSubir=new Semaphore(capacidad,true);
    }
    
    public void subir(){
        /*Método que realiza la verificacion para que un pasajero pueda subir
        * Si la capacidad del tren está completa el tren puede
        * arrancar y ningun otro pasajero puede subir hasta que este se detenga
        */
        try {
            //No pueden subir y bajar al mismo tiempo            
            puedeSubir.acquire();
            //mutexSubir.acquire();
            System.out.println(Color.RED+"Pasajero "+Thread.currentThread().getName()+" sube");            
            puedeViajar.release();
            
            //mutexSubir.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public void bajar(){
        /*Método que realiza la verificacion para que un pasajero pueda bajar
        * Si el tren se detuvo los pasajeros pueden bajar
        * Si todos los pasajeros bajaron del tren, pueden subir nuevos pasajeros
        */
        try {            
            puedeBajar.acquire();
            mutexBajar.acquire();
            System.out.println(Color.CYAN+"Pasajero "+Thread.currentThread().getName()+" baja");   
            System.out.println("semSubir: "+semSubir.availablePermits());
            mutexBajar.release();
            semSubir.release();            
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void habilitarSubir(){
        try {
            //Cuando ya se han bajado todos, pueden subir
            semSubir.acquire(capacidad);
            System.out.println(Color.GREEN+"Pueden subir");
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }
        puedeSubir.release(capacidad);
    }
    public void arrancar(){
        //Método que realiza la verificacion para que el tren pueda arrancar       
        try {
            puedeViajar.acquire(capacidad);
            System.out.println("El tren arranca");
        } catch (InterruptedException ex) {
            Logger.getLogger(Tren.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void parar(){
        //Método que realiza la verificacion para que el tren se detenga    
        System.out.println("El tren para");
        puedeBajar.release(capacidad);
    }
}
