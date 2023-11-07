/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto2Sem;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class Observatorio {
    Semaphore mutex=new Semaphore(1);
    Semaphore capacidad=new Semaphore(50);
    Semaphore observador=new Semaphore(1);
    private int esperaVisitante=0;
    private int esperandoArriba=0;
    private int esperandoObservador=0;
    private int limite=50;
    private int visitantesAdentro=0;    

    public void entrarVisitante(){
        try {
            mutex.acquire();
            esperandoArriba++;
            esperaVisitante++;
            if(esperandoArriba==1){
                observador.acquire();
            }
            mutex.release();
            capacidad.acquire();
            mutex.acquire();
            esperaVisitante--;
            visitantesAdentro++;
            System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra");
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public void entrarObservador(){
        try {
            mutex.acquire();
            esperandoArriba++;
            esperandoObservador++;
            if(esperandoArriba==1){
                capacidad.acquire(limite-visitantesAdentro);
            }
            mutex.release();
            
            observador.acquire();
            mutex.acquire();            
            System.out.println(Color.GREEN+Thread.currentThread().getName()+" entra");
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public void salir(){
        try {
            mutex.acquire();
            esperandoArriba--;
            visitantesAdentro--;
            if(esperandoObservador==0){
                capacidad.release();
            }else if(visitantesAdentro==0){
                observador.release();
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
        }               
    }
    public void salirObservador(){
        try {
            mutex.acquire();
            esperandoArriba--;            
            if(esperandoObservador>0){
                observador.release();
            }else{
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}
