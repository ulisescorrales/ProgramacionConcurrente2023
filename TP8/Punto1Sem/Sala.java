/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto1Sem;

import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class Sala {

    Semaphore disponible = new Semaphore(20, true);
    Semaphore mostradorAlmuerzo = new Semaphore(5, true);
    Semaphore mostradorGaseosa = new Semaphore(10, true);
    Semaphore mostradorPostre = new Semaphore(3, true);
    Semaphore mutex = new Semaphore(1, true);

    public void entrar() {
        try {
            disponible.acquire();
            mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " ENTRA");
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void salir() {
        try {
            mutex.acquire();
            disponible.release();
            System.out.println(Color.RED + Thread.currentThread().getName() + " SALE");
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void servirseAlmuerzo() {
        try {
            mostradorAlmuerzo.acquire();
            mutex.acquire();
            System.out.println(Color.WHITE + Thread.currentThread().getName() + " entra a comer");
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void dejarServirseAlmuerzo() {
        mostradorAlmuerzo.release();
        System.out.println(Thread.currentThread().getName() + " deja de servirse Almuerzo");
    }

    public void servirseGaseosa() {
        try {
            mostradorGaseosa.acquire();
            mutex.acquire();
            System.out.println(Color.CYAN + Thread.currentThread().getName() + " entra a servirse gaseosa");
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void dejarServirseGaseosa() {
        mostradorGaseosa.release();
        System.out.println(Thread.currentThread().getName() + " deja de servirse gaseosa");
    }

    public void servirsePostre() {
        try {
            mostradorPostre.acquire();
            mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " se sirve postre");
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void dejarServirsePostre() {
        mostradorPostre.release();
        System.out.println(Thread.currentThread().getName() + " deja de servirse postre");
    }
}
