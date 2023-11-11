/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto6_1;

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
public class TorreControl {

    private int esperandoAterrizar = 0;
    private int limite = 3;    
    //private Semaphore limiteAterrizajes = new Semaphore(limite);
    private int esperandoDespegar = 0;


    private Lock lock=new ReentrantLock();
    private Condition aterrizar=lock.newCondition();
    private Condition despegar=lock.newCondition();
    private boolean enUso=false;
    private int contAterrizajes=0;

    public void permitirAterrizar() {        
        lock.lock();
        esperandoAterrizar++;
        while(enUso||(contAterrizajes>=limite && esperandoDespegar>0)){
            try {
                System.out.println(Thread.currentThread().getName()+" espera");
                aterrizar.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        esperandoAterrizar--;
        contAterrizajes++;
        enUso=true;
        System.out.println(Color.YELLOW+Thread.currentThread().getName()+" entra");
        lock.unlock();
    }

    public void permitirDespegar() {
        lock.lock();
        esperandoDespegar++;
        while(enUso||(contAterrizajes>=limite && esperandoAterrizar>0 && contAterrizajes<limite)){
            try {
                System.out.println(Thread.currentThread().getName()+" espera");
                despegar.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        esperandoDespegar--;    
        enUso=true;
        contAterrizajes=0;
        System.out.println(Color.YELLOW+Thread.currentThread().getName()+" entra");
        lock.unlock();
    }

    public void terminarAterrizar() {
        lock.lock();
        enUso=false;
        System.out.println(Color.RED+Thread.currentThread().getName()+" termina");
        if(contAterrizajes<limite && esperandoAterrizar>0){
            aterrizar.signal();
        }else{
            despegar.signal();
        }
        lock.unlock();
    }

    public void terminarDespegar() {
        lock.lock();
        enUso=false;
        System.out.println(Color.RED+Thread.currentThread().getName()+" termina");
        if(esperandoAterrizar>0){
            aterrizar.signal();
        }else{
            despegar.signal();
        }
        lock.unlock();
    } 
}
