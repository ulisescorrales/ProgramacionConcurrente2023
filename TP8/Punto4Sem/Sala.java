/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto4Sem;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class Sala {

    Semaphore camillas = new Semaphore(4);
    Semaphore revistas = new Semaphore(5);
    Semaphore television = new Semaphore(0);
    Semaphore mutex = new Semaphore(1);
    int esperando = 0;

    public void entrar() {
        try {
            mutex.acquire();
            if (!camillas.tryAcquire()) {
                if (!revistas.tryAcquire()) {
                    esperando++;
                    System.out.println(Thread.currentThread().getName() + " mira television");
                    mutex.release();
                    television.acquire();
                }
                System.out.println(Thread.currentThread().getName() + " toma una revista");
                mutex.release();
                camillas.acquire();

            } else {
                mutex.release();
            }
            mutex.acquire();
            System.out.println(Color.GREEN + Thread.currentThread().getName() + " entra");
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salir() {
        try {
            mutex.acquire();
            camillas.release();
            System.out.println(Color.RED + Thread.currentThread().getName() + " sale");
            if (esperando > 0) {
                television.release();
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
