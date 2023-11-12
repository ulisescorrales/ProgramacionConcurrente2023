/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto6;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
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
    private Semaphore puedeDespegar = new Semaphore(1, true);
    //private Semaphore limiteAterrizajes = new Semaphore(limite);
    private int esperandoDespegar = 0;
    private Semaphore mutex = new Semaphore(1);
    private int esperandoYArriba = 0;
    private Semaphore usarPista = new Semaphore(1, true);
    private int contAterrizajes = 0;

    private Lock despegar = new ReentrantLock();

    public void permitirAterrizar() {
        try {
            mutex.acquire();
            esperandoAterrizar++;
            esperandoYArriba++;
            System.out.println(Thread.currentThread().getName() + " pide aterrizar");
            if (esperandoYArriba == 1) {
                System.out.println("SE BLOQUEA DESPEGAR");
                //Debe bloquearlo para que no hayan despegues en medio de aterrizajes consecutivos
                puedeDespegar.acquire();
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

            if (esperandoYArriba == 1) {
                System.out.println("SE BLOQUEA ATERRIZAR");
                if (contAterrizajes < limite) {
                    puedeAterrizar.acquire(this.limite - contAterrizajes);
                } else {
                    puedeAterrizar.acquire();
                }
            }
            //Como es un solo permiso se autobloquea
            mutex.release();

            puedeDespegar.acquire();
            usarPista.acquire();
            mutex.acquire();
            contAterrizajes = 0;
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
            contAterrizajes++;
            if (contAterrizajes < limite) {
                if (esperandoAterrizar == 0) {
                    if (esperandoDespegar > 0) {
                        //switch
                        puedeAterrizar.acquire(limite - contAterrizajes);
                        puedeDespegar.release();
                    } else {
                        puedeDespegar.release();
                    }
                }
                //Si hay esperandoA, no hacer nada
            } else {
                if (esperandoDespegar > 0) {
                    System.out.println("Aterizaje libera un despegar");
                    System.out.println(puedeAterrizar.availablePermits());
                    puedeDespegar.release();
                } else if (esperandoAterrizar > 0) {
                    puedeAterrizar.release();
                } else {
                    System.out.println("Aterizaje libera ambos");
                    this.getPermisos();
                    puedeDespegar.release();

                    puedeAterrizar.release();
                }
            }
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
                puedeAterrizar.release(this.limite);
            } else if (esperandoDespegar > 0) {
                contAterrizajes = 0;
                puedeDespegar.release();
            } else {
                puedeAterrizar.release(this.limite);
                puedeDespegar.release();
            }
            usarPista.release();
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void getPermisos() {
        System.out.println("puedeAterrizar:" + this.puedeAterrizar.availablePermits() + ", puedeDespegar:" + this.puedeDespegar.availablePermits());
    }
}
