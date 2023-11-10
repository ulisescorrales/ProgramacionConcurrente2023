/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto5Sem;

import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class Olla {

    Semaphore raciones = new Semaphore(10);
    Stack colRaciones = new Stack();
    Semaphore mutex = new Semaphore(1);
    Semaphore cocinar = new Semaphore(0);
    int cantRaciones;

    public Olla(int cantRaciones) {
        for (int i = 0; i < cantRaciones; i++) {
            colRaciones.push(new Object());

        }
        this.cantRaciones = cantRaciones;
    }

    public Object comer() throws InterruptedException {
        Object retornar;
        raciones.acquire();
        mutex.acquire();        
        System.out.println(Color.GREEN+Thread.currentThread().getName()+" toma una porción");
        retornar = colRaciones.pop();
        if(colRaciones.isEmpty()){
            System.out.println("Solicitar COCINERO");
            cocinar.release();
        }
        mutex.release();
        return retornar;
    }
    public void cocinar(){
        try {
            cocinar.acquire();
            mutex.acquire();
            System.out.println(Color.YELLOW+Thread.currentThread().getName()+" está cocinando");
            for (int i = 0; i < cantRaciones; i++) {
                colRaciones.push(new Object());                
            }
            raciones.release(cantRaciones);
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Olla.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
