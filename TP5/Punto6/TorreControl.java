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
    private int esperandoYAterrizando = 0;
    private int limite = 3;
    private int esperandoDespegar = 0;
    private Semaphore mutex = new Semaphore(1);

    public void permitirAterrizar() {
        try {
            mutex.acquire();
            esperandoYAterrizando++;
            System.out.println(Thread.currentThread().getName() + " pide aterrizar");
            mutex.release();

            puedeAterrizar.acquire();
            mutex.acquire();
            System.out.println(Color.CYAN + Thread.currentThread().getName() + " está aterrizando");
            esperandoYAterrizando--;
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
            System.out.println(Thread.currentThread().getName() + " pide despegar");
            //Si hay aviones esperando a aterrizar, cederles lugar
            if (esperandoDespegar==1 && esperandoYAterrizando > 0 && contAterrizajes < limite) {
                System.out.println("DESPEGAR BLOQUEADOS");
                puedeDespegar.acquire();                
            }
            mutex.release();
            
            puedeDespegar.acquire();
            mutex.acquire();            
            System.out.println(Color.GREEN + Thread.currentThread().getName() + " está despegando");
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
            if (contAterrizajes >= limite && esperandoDespegar > 0) {
                puedeDespegar.release();
                contAterrizajes = 0;
            } else if (esperandoYAterrizando > 0) {
                puedeAterrizar.release();
            } else if (esperandoDespegar > 0) {
                puedeDespegar.release();
            } else {
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
            esperandoDespegar--;
            System.out.println(Color.RED + Thread.currentThread().getName() + " termina de aterrizar");            
            if (esperandoYAterrizando > 0) {
                puedeAterrizar.release();
            } else if (esperandoDespegar > 0) {
                puedeDespegar.release();
            } else {
                puedeDespegar.release();
                puedeAterrizar.release();
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
