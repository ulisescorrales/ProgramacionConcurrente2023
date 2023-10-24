/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto6;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class TorreControl {

    private Semaphore usarPista = new Semaphore(1);
    private Semaphore puedeDespegar = new Semaphore(0);
    private Semaphore puedeAterrizar=new Semaphore(1);
    private int contAterrizajes = 0;
    private int esperandoAterrizar = 0;
    private int esperandoDespegar = 0;
    private Semaphore mutex=new Semaphore(1);
    
    public void permitirAterrizar() {
        try {
            mutex.acquire();
            esperandoAterrizar++;            
            System.out.println(Thread.currentThread().getName()+" pide aterrizar");
            mutex.release();
            //Se bloquea si hubieron 10 aterrizajes y un avión quiere despegar            
            puedeAterrizar.acquire();
            usarPista.acquire();
            System.out.println(Color.CYAN+Thread.currentThread().getName()+" está aterrizando");
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void permitirDespegar() {
        try {
            //Antes de pedir la pista, espera a que los aviones aterricen
            mutex.acquire();
            esperandoDespegar++;
            System.out.println(Thread.currentThread().getName()+" pide despegar");
            //Si hay aviones esperando a aterrizar, cederles lugar
            if (esperandoAterrizar > 0 && contAterrizajes < 10) {
                mutex.release();
                puedeDespegar.acquire();
                mutex.acquire();
            }
            usarPista.acquire();
            System.out.println(Color.GREEN+Thread.currentThread().getName()+" está despegando");
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void terminarAterrizar() {
        try {
            mutex.acquire();
            System.out.println(Color.RED+Thread.currentThread().getName()+" termina de aterrizar");
            usarPista.release();
            esperandoAterrizar--;
            contAterrizajes++;
            System.out.println(contAterrizajes);
            puedeAterrizar.release();
            /*
            if(contAterrizajes<10 || esperandoDespegar==0){
                puedeAterrizar.release();
            }*/
            //Si ya terminaron de aterrizar y había un avión esperando a despegar
            if (esperandoAterrizar == 0 && esperandoDespegar > 0) {
                puedeDespegar.release();
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }  

    public void terminarDespegar() {
        try {
            mutex.acquire();
            System.out.println(Color.RED+Thread.currentThread().getName()+" termina de despegar");
            esperandoDespegar--;
            mutex.release();
            usarPista.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
