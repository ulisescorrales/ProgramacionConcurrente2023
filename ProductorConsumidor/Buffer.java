/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.ProductorConsumidor;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Buffer {

    private Queue cola = new LinkedList();
    private int tamanio;
    private Semaphore producir = new Semaphore(tamanio);
    //Comienza en cero
    private Semaphore consumir = new Semaphore(0);
    private Semaphore mutex=new Semaphore(1);

    public void producir(Object o) {
        try {
            producir.acquire();
            mutex.acquire();            
            cola.add(o);            
            System.out.println("Productor agrega elemento Nº "+cola.size());
            mutex.release();
            consumir.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void consumir() {
        try {
            consumir.acquire();
            mutex.acquire();
            cola.poll();
            System.out.println("Productor elimina elemento, quedan: "+cola.size());
            mutex.release();
            producir.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Buffer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
