/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.ProblemasClasicos.elBarBeroDormilon;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Silla {
    private Semaphore sentarse=new Semaphore(1);
    private Semaphore cortar=new Semaphore(0);
    private Semaphore salir=new Semaphore(0);
    
    public void sentarse(){
        try {
            sentarse.acquire();
            cortar.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Silla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void salir(){
        try {
            salir.acquire();
            sentarse.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Silla.class.getName()).log(Level.SEVERE, null, ex);
        }        
    }
    public void empezarACortar(){
        try {
            cortar.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Silla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void terminarCortar(){
        salir.release();        
    }
}
