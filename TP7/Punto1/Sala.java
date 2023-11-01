/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP7.Punto1;

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
    private int temperatura=30;
    private int capacidad1=5;
    private int capacidad2=10;
    private int contPersonas=0;
    private int esperaPersona=0;
    private int esperaJubilado=0;
    
    Lock lock=new ReentrantLock();
    Condition hayEspacio=lock.newCondition();
    Condition hayEspacioJubilado=lock.newCondition();
    
    public void entrar(){
        lock.lock();
        try {            
            while(esperaJubilado>0 || this.estaLleno()){
                try {
                    System.out.println(Thread.currentThread().getName()+" espera");
                    hayEspacio.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
                }
            }            
            contPersonas++;
            System.out.println(Color.GREEN+Thread.currentThread().getName()+" entra");
        } finally {
            lock.unlock();
        }
    }
    public void entrarJubilado(){        
        try {
            lock.lock();
            esperaJubilado++;
            while(this.estaLleno()){
                try {
                    System.out.println(Thread.currentThread().getName()+" espera");
                    hayEspacioJubilado.await();
                } catch (InterruptedException ex) {
                    Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } finally {
            esperaJubilado--;
            contPersonas++;
            System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra");
            lock.unlock();
        }
    }
    public void salir(){
        lock.lock();
        try {
            contPersonas--;
            System.out.println(Color.YELLOW+Thread.currentThread().getName()+" sale");
            if(esperaJubilado>0){
                hayEspacioJubilado.signal();
            }else{
                hayEspacio.signal();
            }
        } finally {
            lock.unlock();
        }
    }
    
    private boolean estaLleno(){
        boolean estaLleno=false;
        if(temperatura>30 && contPersonas>=capacidad2){
            estaLleno=true;
        }else if(contPersonas>=capacidad1){
            estaLleno=true;
        }
        return estaLleno;
    }
            
}
