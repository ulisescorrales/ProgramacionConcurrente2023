/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto3;

import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class Comedero {

    private Stack platos;
    private int cantPlatos;
    private Semaphore mutex = new Semaphore(1);
    private Semaphore gato;
    private Semaphore perro;    
    private int gatosComiendo = 0;
    private int perrosComiendo = 0;    
    private int gatosEsperando=0;
    private int perrosEsperando=0;
    private int animalesComiendo=0;

    public Comedero(int cantPlatos) {
        this.cantPlatos = cantPlatos;
        gato = new Semaphore(cantPlatos);
        perro = new Semaphore(cantPlatos);
        platos=new Stack();
        for (int i = 0; i < cantPlatos; i++) {
            platos.push(new Object());
        }
    }

    public Object comerGato() throws InterruptedException {
        Object platoElegido;

        mutex.acquire();        
        System.out.println(Thread.currentThread().getName()+" intenta entrar");         
        gatosEsperando++;
        if (gatosEsperando == 1 && animalesComiendo==0) {
            System.out.println(Color.RED+"Perros se bloquean");
            perro.acquire(cantPlatos);
        }
        mutex.release();

        //Consumir
        gato.acquire();
        
        mutex.acquire();
        System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra");
      //  gatosComiendo++;   
        animalesComiendo++;
        gatosEsperando--;
        platoElegido = platos.peek();
        platos.pop();

        mutex.release();
        return platoElegido;
    }
    public Object comerPerro() throws InterruptedException {
        Object platoElegido;

        mutex.acquire();
        
        System.out.println(Thread.currentThread().getName()+" intenta entrar");              
        perrosEsperando++;        
        if (perrosEsperando == 1 && animalesComiendo==0) {
            System.out.println(Color.RED+"Gatos se bloquean");
            gato.acquire(cantPlatos);
        }
        mutex.release();

        //Consumir
        perro.acquire();
        mutex.acquire();
        System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra");
       // perrosComiendo++; 
        perrosEsperando--;
        animalesComiendo++;
        platoElegido = platos.peek();
        platos.pop();

        mutex.release();
        return platoElegido;
    } 
    public void salirGato(Object plato) throws InterruptedException{
        mutex.acquire();
       // gatosComiendo--;
        animalesComiendo--;
        System.out.println(Color.YELLOW+Thread.currentThread().getName()+" sale");
        platos.push(plato);
        if(animalesComiendo==0){
            perro.release(cantPlatos);
        }        
        gato.release();
        mutex.release();
    }
    public void salirPerro(Object plato) throws InterruptedException{
        mutex.acquire();
       // perrosComiendo--;
        animalesComiendo--;
        System.out.println(Color.YELLOW+Thread.currentThread().getName()+" sale");
        platos.push(plato);
        if(animalesComiendo==0){
            gato.release(cantPlatos);
        }        
        perro.release();
        mutex.release();
    }     

}
