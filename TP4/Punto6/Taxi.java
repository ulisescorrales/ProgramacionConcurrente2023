/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto6;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Taxi {
    Semaphore puedeViajar=new Semaphore(1);
    Semaphore hayPasajero=new Semaphore(0);
    Semaphore puedeBajar=new Semaphore(0);
    
    //S
    public void empezarRecorrido(){
        try {
            hayPasajero.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
        }
    }    
    public void subirAlTaxi(){
        try {
            puedeViajar.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
        }
        hayPasajero.release();//Despertar al taxi        
    }
    //
    public void bajarDelTaxi(){
        try {
            puedeBajar.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Taxi.class.getName()).log(Level.SEVERE, null, ex);
        }
        puedeViajar.release();//Una vez que bajó, el taxista puede empezar otro viaje
    }
    public void terminarRecorrido(){
        puedeBajar.release();        
    }
}
