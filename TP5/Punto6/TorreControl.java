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

    private Semaphore puedeDespegar = new Semaphore(1);
    private Semaphore puedeAterrizar = new Semaphore(1);    
    private int contAterrizajes = 0;
    private int esperandoAterrizando = 0;
    private int limite = 3;
    private Semaphore limiteAterrizajes = new Semaphore(limite);
    private int esperandoYDespegando = 0;
    private Semaphore mutex = new Semaphore(1);
    private int esperandoYArriba=0;
    private boolean enUso = false;

    public void permitirAterrizar() {
        try {
            mutex.acquire();
            esperandoAterrizando++;
            esperandoYArriba++;
            System.out.println(Thread.currentThread().getName() + " pide aterrizar");
            if(esperandoYArriba==1){
                puedeDespegar.acquire();
            }
            mutex.release();

            puedeAterrizar.acquire();
            mutex.acquire();
            System.out.println(Color.CYAN + Thread.currentThread().getName() + " está aterrizando");            
            enUso = true;
            esperandoAterrizando--;
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void permitirDespegar() {
        try {
            //Antes de pedir la pista, espera a que los aviones aterricen
            mutex.acquire();
            esperandoYDespegando++;
            esperandoYArriba++;
            System.out.println(Thread.currentThread().getName() + " pide despegar");
            //Si hay aviones esperando a aterrizar, cederles lugar
            if(esperandoYArriba==1){
                puedeAterrizar.acquire();
                mutex.release();
                puedeDespegar.acquire();
            }else if(esperandoAterrizando>0){
                mutex.release();
                System.out.println("ESPERANDO EL LÍMITE");
                limiteAterrizajes.acquire(this.limite);
                puedeAterrizar.acquire();
            }else{
                mutex.release();
                puedeDespegar.acquire();
            }            
            
            //puedeDespegar.acquire();
            mutex.acquire();
            System.out.println(Color.GREEN + Thread.currentThread().getName() + " está despegando");
            enUso = true;
            esperandoYDespegando--;
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminarAterrizar() {
        try {
            mutex.acquire();
            System.out.println(Color.RED + Thread.currentThread().getName() + " termina de aterrizar");
            contAterrizajes++;            
            esperandoYArriba--;
            enUso = false;
            limiteAterrizajes.release();
            if (esperandoAterrizando > 0) {
                puedeAterrizar.release();
            } else if (esperandoYDespegando > 0) {
                puedeDespegar.release();
            } else {
                System.out.println("LIBERADO AMBOS");
                puedeDespegar.release();
                puedeAterrizar.release();
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminarDespegar() {
        try {
            mutex.acquire();
            enUso = false;
            esperandoYArriba--;            
            System.out.println(Color.RED + Thread.currentThread().getName() + " termina de aterrizar");
            if (esperandoAterrizando > 0) {
                puedeAterrizar.release();
            } else if (esperandoYDespegando > 0) {
                limiteAterrizajes.release(this.limite);
                puedeDespegar.release();
            } else {
                System.out.println("LIBERADO AMBOS");
                puedeDespegar.release();
                puedeAterrizar.release();
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
