/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto7;

import java.util.LinkedList;
import java.util.Random;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Mirador {

    private int capacidad;
    private Semaphore semCapacidad = new Semaphore(0);
    private Semaphore puedeSubir = new Semaphore(this.capacidad);
    private Semaphore mutex1 = new Semaphore(1);

    private Semaphore puedeBajar = new Semaphore(0);
    private Semaphore tobogan1 = new Semaphore(1);
    private Semaphore tobogan2 = new Semaphore(1);
    private int cantPersonas = 0;
    private int cantToboganes;
    private Object[] toboganes;
    private Object toboganHabilitado;
    private Semaphore[] colToboganes;
    private Semaphore semToboganes;
    private Semaphore puedeSeleccionar = new Semaphore(1);
    private LinkedList toboganesLibres = new LinkedList();

    public Mirador(int capacidad, int cantToboganes) {
        this.capacidad = capacidad;
        this.cantToboganes = cantToboganes;
        toboganes = new Object[cantToboganes];
        for (int i = 0; i < cantToboganes; i++) {
            toboganes[i] = new Object();
            colToboganes[i] = new Semaphore(1);
            toboganesLibres.addFirst(new Object());
        }
        semToboganes = new Semaphore(this.cantToboganes);
    }

    //Método de visitante
    public void subir() {
        try {
            puedeSubir.acquire();
            mutex1.acquire();
            cantPersonas++;
            mutex1.release();
            semCapacidad.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Mirador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Encargado
    public void habilitarToboganes() {
        try {
            semCapacidad.acquire(this.capacidad);            
        } catch (InterruptedException ex) {
            Logger.getLogger(Mirador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Encargado
    public void seleccionarTobogan() {
        try {
            Random num = new Random();
            puedeSeleccionar.acquire();            
            //Elegir entre los toboganes libres
            semToboganes.acquire();
            mutex1.acquire();
            toboganHabilitado = toboganesLibres.get(num.nextInt(toboganesLibres.size()));
            toboganesLibres.remove(toboganHabilitado);
            mutex1.release();
            puedeBajar.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Mirador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //Visitante
    public Object empezarBajar() throws InterruptedException {

        Object retornar;
        puedeBajar.acquire();
        mutex1.acquire();
        retornar = toboganHabilitado;
        mutex1.release();
        puedeSeleccionar.release();

        return retornar;
    }

    //Visitante
    public void terminarBajar(Object toboganRetornar) {        
        try {
            mutex1.acquire();
            //Retornar a la lista de toboganes libres
            toboganesLibres.addFirst(toboganRetornar);
            semToboganes.release();
            puedeBajar.release();
            mutex1.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Mirador.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
