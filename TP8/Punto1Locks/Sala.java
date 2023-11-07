/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto1Locks;

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
    int disponible=10;
    int mostradorAlmuerzo=5;
    int mostradorGaseosa=10;
    int mostradorPostre=3;
    Lock lock=new ReentrantLock();
    Condition almuerzo=lock.newCondition();
    Condition gaseosa=lock.newCondition();
    Condition postre=lock.newCondition();
    Condition capacidad=lock.newCondition();
    
    public void entrar(){
        lock.lock();
        try {
            while(disponible==0){
                try {
                    System.out.println(Color.GREEN+Thread.currentThread().getName()+" espera para ENTRAR");
                    capacidad.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println(Thread.currentThread().getName()+" ENTRA");
        } finally {
            disponible--;
            lock.unlock();
        }
    }
    public void salir(){
        lock.lock();
        try {
            disponible++;
            capacidad.signal();
            System.out.println(Color.RED+Thread.currentThread().getName()+" SALE");
        } finally {
            lock.unlock();
        }
    }
    public void servirseAlmuerzo(){
        lock.lock();
        try {
            while(mostradorAlmuerzo==0){
                try {
                    System.out.println("    "+Thread.currentThread().getName()+" espera para comer");
                    almuerzo.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            System.out.println(Color.WHITE+Thread.currentThread().getName()+" entra a comer");
        } finally {
            mostradorAlmuerzo--;
            lock.unlock();
        }
    }
    public void dejarServirseAlmuerzo(){
        lock.lock();
        try {
            mostradorAlmuerzo++;
            almuerzo.signal();
            System.out.println(Thread.currentThread().getName()+" deja de servirse almuerzo");
        } finally {
            lock.unlock();
        }
    }
    public void servirseGaseosa(){
        lock.lock();
        try {
            while(mostradorGaseosa==0){
                try {
                    System.out.println("    "+Thread.currentThread().getName()+" espera para servirse gaseosa");
                    gaseosa.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            mostradorGaseosa--;
            System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra a servirse gaseosa");
        } finally {
            lock.unlock();
        }
    }
    public void dejarServirseGaseosa(){
        lock.lock();
        try {
            mostradorGaseosa++;
            gaseosa.signal();
            System.out.println(Thread.currentThread().getName()+" deja de servirse gaseosa");
        } finally {
            lock.unlock();
        }
    }
    public void servirsePostre(){
        lock.lock();
        try {
            while(mostradorPostre==0){
                try {
                    System.out.println("    "+Thread.currentThread().getName()+" espera para servirse postre");
                    postre.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
            mostradorPostre--;
            System.out.println(Thread.currentThread().getName()+" se sirve postre");
        } finally {
            lock.unlock();
        }
    }
    public void dejarServirsePostre(){
        lock.lock();
        try {
            mostradorPostre++;
            postre.signal();
            System.out.println(Thread.currentThread().getName()+" deja de servirse postre");
        } finally {
            lock.unlock();
        }
    }
}
