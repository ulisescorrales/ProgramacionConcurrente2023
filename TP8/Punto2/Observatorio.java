/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto2;

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
public class Observatorio {

    private int capacidad2 = 30;
    private int limite = this.capacidad1;
    private int capacidad1 = 50;
    private int cantAdentro = 0;
    private Lock lock = new ReentrantLock();
    private int cantSillaRuedas = 0;
    private Condition limiteC = lock.newCondition();
    private int cantMantenimiento = 0;
    private boolean observando = false;
    private int esperandoObservador = 0;
    private Condition observador = lock.newCondition();

    public void entrarVisitante() {
        lock.lock();
        while (cantAdentro == limite || this.observando || esperandoObservador > 0 || cantMantenimiento > 0) {
            try {
                limiteC.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cantAdentro++;
        System.out.println(Color.GREEN + Thread.currentThread().getName() + " entra, cantAdentro:" + this.cantAdentro);
        lock.unlock();
    }

    public void entrarDiscapacitado() {
        lock.lock();
        while (cantAdentro >= capacidad2 || observando || esperandoObservador > 0 || cantMantenimiento > 0) {
            try {
                limiteC.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cantSillaRuedas++;
        limite = capacidad2;
        cantAdentro++;
        System.out.println(Color.GREEN + Thread.currentThread().getName() + " entra, cantAdentro:" + this.cantAdentro);
        lock.unlock();
    }

    public void salir() {
        lock.lock();
        cantAdentro--;
        if (esperandoObservador > 0) {
            observador.signal();
        } else {
            limiteC.signal();
        }
        System.out.println(Color.YELLOW + Thread.currentThread().getName() + " sale, cantPersonas:" + this.cantAdentro);
        lock.unlock();
    }

    public void salirDiscapacitado() {
        lock.lock();
        cantAdentro--;
        cantSillaRuedas--;
        if (esperandoObservador > 0) {
            observador.signal();
        } else if (cantSillaRuedas == 0) {
            System.out.println("CAMBIAR LÍMITE");
            limite = capacidad1;
            limiteC.signalAll();
        } else {
            limiteC.signal();
        }

        System.out.println(Color.YELLOW + Thread.currentThread().getName() + " sale, cantPersonas:" + this.cantAdentro);
        lock.unlock();
    }

    public void entraObservador() {
        lock.lock();
        esperandoObservador++;
        while (cantAdentro > 0 || cantMantenimiento > 0 || observando) {
            try {
                this.observador.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        esperandoObservador--;
        this.observando = true;
        System.out.println(Color.PURPLE + Thread.currentThread().getName() + " entra");
        lock.unlock();
    }

    public void salirObservador() {
        lock.lock();
        System.out.println(Color.RED + Thread.currentThread().getName() + " sale");
        observando = false;
        if (esperandoObservador > 0) {
            observador.signal();
        } else {
            limiteC.signalAll();
        }
        lock.unlock();
    }

}
