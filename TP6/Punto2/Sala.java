/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP6.Punto2;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class Sala {
    private int cantMesas=10;
    private int mesasOcupadas=0;
    private Queue cola=new LinkedList();
    
    public synchronized void entrar(Estudiante est){       
        cola.add(est);
        while(mesasOcupadas>=cantMesas||cola.peek()!=est ){
            try {
                //Si todas las mesas están ocupadas
                System.out.println(Thread.currentThread().getName()+" espera");
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Sala.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        cola.remove();
        mesasOcupadas++;
        System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra");
    }
    public synchronized void salir(){
        mesasOcupadas--;
        System.out.println(Color.YELLOW+Thread.currentThread().getName()+" desocupa una mesa");
        this.notifyAll();
    }
}
