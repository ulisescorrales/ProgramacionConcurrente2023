/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto8;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class ControladorProduccion {
    private char lineaActiva='e';//e es electrico,m es mecanico
    private Semaphore mutex=new Semaphore(1);
    private Semaphore mecanico=new Semaphore(0);
    private Semaphore electrico=new Semaphore(1);
    
    public void alternarLineas(){
        try {
            mutex.acquire();
            if(lineaActiva=='e'){
                lineaActiva='m';
                electrico.acquire();
                mecanico.release();
            }else{
                lineaActiva='e';
                mecanico.acquire();
                electrico.release();                
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void llegaElectrico(){
        try {
            electrico.acquire();
            mutex.acquire();
            electrico.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void llegaMecanico(){
        try {
            mecanico.acquire();
            mutex.acquire();
            mecanico.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void sale(){
        mutex.release();
    }
}
