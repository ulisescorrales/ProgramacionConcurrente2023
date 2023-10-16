/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.LectoresEscritores;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Libro {
    private int dato=5;
    private Semaphore leer;
    private Semaphore escribir;
    private int cantLectores;
    private Semaphore mutexE;
    private int cantEscritores=0;
    private Semaphore mutex2=new Semaphore(1);
    private Semaphore escritores=new Semaphore(1);

    public Libro(int cantLectores) {
        this.cantLectores=cantLectores;
        leer=new Semaphore(cantLectores);
    }        
    
    public void empezarLeer(){        
        try {            
            leer.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public int getDato(){
        return this.dato;
    }
    public void terminarLeer(){
        leer.release();
    }
    
    public void empezarEscribir(){
        try {
            mutex2.acquire();
            cantEscritores++;
            if(cantEscritores==1){
                leer.acquire(cantLectores);
            }
            mutex2.release();
            escritores.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public void escribir(int dato){
        try {
            
            mutexE.acquire();
            this.dato=dato;
            mutexE.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void dejarEscribir(){
        //Ya terminó de escribir
        escritores.release();
        mutex2.acquire();
        cantEscritores--;
        if(cantEscritores==0){
            leer.release(cantLectores);
        }
        mutex2.release();
    }
}
