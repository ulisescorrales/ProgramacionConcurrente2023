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
    public void terminarLeer(){
        
    }
    
    public void empezarEscribir(int dato2){
        try {
            escribir.acquire();
            leer.acquire(cantLectores);
        } catch (InterruptedException ex) {
            Logger.getLogger(Libro.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
}
