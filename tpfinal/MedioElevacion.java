/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacionconcurrente2023.tpfinal;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulises.corrales
 */
public class MedioElevacion {
    //Objeto compartido entre esquiadores
    private Semaphore puedeElevarse= new Semaphore(0,true);
    private Semaphore puedeDescender=new Semaphore(0,true);
    private Semaphore puedeSubir;
    private Semaphore puedeBajar;
    private Semaphore mutex=new Semaphore(1,true);
    
    private Molinete[] molinetes;
    int capacidad;
    private static byte HORA;
    
    public MedioElevacion(int capacidad, int cantMolinetes) throws Exception {
        puedeSubir=new Semaphore(capacidad,true);
        this.capacidad=capacidad;
        if(cantMolinetes>4 || cantMolinetes<0){
            throw new Exception("Cantidad de molinetes no puede ser menor a 0 ni mayor a 4");
        }
    }
    //Métodos para Esquiador
    public void subir(){
        try {
            mutex.acquire();
            puedeSubir.acquire();
            puedeElevarse.release();
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(MedioElevacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void bajar(){
        try {
            mutex.acquire();
            puedeBajar.acquire();
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(MedioElevacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //Métodos para ControlMedioElevacion
    public void elevar(){
        try {
            puedeElevarse.acquire(this.capacidad);
        } catch (InterruptedException ex) {
            Logger.getLogger(MedioElevacion.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void llegarCima(){
        puedeBajar.release(this.capacidad);
    }
    public void descender(){
        try {
            puedeDescender.acquire(this.capacidad);
        } catch (InterruptedException ex) {
            Logger.getLogger(MedioElevacion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void llegarOrigen(){
        puedeSubir.release(this.capacidad);
    }
    
    public int getCantidadEsquiadores(){
        int longitud=this.molinetes.length;
        int sumatoria=0;
        for (int i = 0; i < longitud; i++) {
            sumatoria+=this.molinetes[i].getContador();
        }
        return sumatoria;
    }
}
