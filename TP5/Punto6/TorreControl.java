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

    private int esperandoAterrizar = 0;
    private int limite = 3;
    private Semaphore puedeAterrizar = new Semaphore(this.limite);
    private Semaphore puedeDespegar = new Semaphore(limite);
    //private Semaphore limiteAterrizajes = new Semaphore(limite);
    private int esperandoDespegar = 0;
    private Semaphore mutex = new Semaphore(1);
    private int esperandoYArriba = 0;
    private Semaphore usarPista = new Semaphore(1, true);
    private int contAterrizajes=0;

    public void permitirAterrizar() {
        try {
            mutex.acquire();
            esperandoAterrizar++;
            esperandoYArriba++;
            System.out.println(Thread.currentThread().getName() + " pide aterrizar");
            if (esperandoYArriba == 1) {
                System.out.println("SE BLOQUEA DESPEGAR");
                puedeDespegar.acquire(this.limite);
            }
            mutex.release();

            puedeAterrizar.acquire();
            usarPista.acquire();
            mutex.acquire();
            System.out.println(Color.CYAN + Thread.currentThread().getName() + " está aterrizando");
            esperandoAterrizar--;
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void permitirDespegar() {
        try {
            //Antes de pedir la pista, espera a que los aviones aterricen
            mutex.acquire();
            esperandoDespegar++;
            esperandoYArriba++;
            System.out.println(Thread.currentThread().getName() + " pide despegar");
            //Si hay aviones esperando a aterrizar, cederles lugar
            if (esperandoYArriba == 1) {
                System.out.println("SE BLOQUEA ATERRIZAR");
                puedeAterrizar.acquire(this.limite);
            }
            mutex.release();

            puedeDespegar.acquire(this.limite);            
            System.out.println(Thread.currentThread().getName() + " pide la pista");
            usarPista.acquire();
            mutex.acquire();
            contAterrizajes=0;
            System.out.println(Color.GREEN + Thread.currentThread().getName() + " está despegando");
            esperandoDespegar--;
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminarAterrizar() {
        try {
            mutex.acquire();
            System.out.println(Color.RED + Thread.currentThread().getName() + " termina de aterrizar");
            esperandoYArriba--;            
            if (esperandoAterrizar > 0) {
                //Libera un límite y deja que aterize el otro
                //Como puedeDespegar está primero, si se llega al límite el avión a Despegar lo toma primero
                System.out.println("Esperando aterrizar:" + this.esperandoAterrizar);
                System.out.println("Permisos despegar:" + (puedeDespegar.availablePermits() + 1));
                
                puedeDespegar.release();

            } else if (esperandoDespegar > 0) {
                System.out.println("ContAterrizajes:"+contAterrizajes);
                System.out.println("Permisos despegar:" + (puedeDespegar.availablePermits() + this.limite));
                puedeDespegar.release(this.limite-contAterrizajes);

            } else {
                System.out.println("LIBERADO AMBOS");
                System.out.println("Permisos despegar:" + (puedeDespegar.availablePermits() + this.limite));
                System.out.println("Permisos aterrizar:" + (puedeAterrizar.availablePermits() + 1));
                puedeDespegar.release(this.limite);
                puedeAterrizar.release();
            }
            contAterrizajes++;
            usarPista.release();
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void terminarDespegar() {
        try {
            mutex.acquire();
            esperandoYArriba--;
            System.out.println(Color.RED + Thread.currentThread().getName() + " termina de despegar");
            if (esperandoAterrizar > 0) {
                System.out.println("Permisos aterrizar:" + (puedeAterrizar.availablePermits() + this.limite));
                if (esperandoAterrizar > 2) {
                    puedeAterrizar.release(this.limite);
                } else {
                    puedeAterrizar.release(esperandoAterrizar);
                }
            } else if (esperandoDespegar > 0) {
                System.out.println("Permisos despegar:" + (puedeDespegar.availablePermits() + this.limite));
                puedeDespegar.release(this.limite);
            } else {
                System.out.println("Permisos despegar:" + (puedeDespegar.availablePermits() + this.limite));
                System.out.println("Permisos aterrizar:" + (puedeAterrizar.availablePermits() + this.limite));
                puedeDespegar.release(this.limite);
                puedeAterrizar.release(this.limite);
            }
            usarPista.release();
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
