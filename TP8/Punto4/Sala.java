/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto4;

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
    Lock lock=new ReentrantLock();
    Condition revista=lock.newCondition();
    Condition televisor=lock.newCondition();
    int cantRevistas=5;
    int camillasDisponibles=4;
    
    public void entrar(){
        lock.lock();
        while(camillasDisponibles==0){
            try {
                while(cantRevistas==0){
                    try {
                        System.out.println(Thread.currentThread().getName()+" mira televisión");
                        televisor.await();
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                cantRevistas--;
                System.out.println(Thread.currentThread().getName()+" lee una revista");
                revista.await();
                cantRevistas++;
            } catch (InterruptedException ex) {
                Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        camillasDisponibles--;
        System.out.println(Color.GREEN+Thread.currentThread().getName()+" entra al centro");
        lock.unlock();
    }
    public void salir(){
        lock.lock();
        System.out.println(Color.RED+Thread.currentThread().getName()+" sale");
        camillasDisponibles++;
        revista.signal();
        televisor.signal();
        lock.unlock();
    }
}
