/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP6.Punto4;

import java.util.LinkedList;
import java.util.Queue;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Almacen {
    private Queue buffer=new LinkedList();
    private int cant=0;
    private int limite=5;
    
    public synchronized void producir(){        
        while(cant>=limite){
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cant++;
        System.out.println("PRODUCIR: "+this.cant+" elementos");
        this.notify();
    }
    public synchronized void consumir(){
        while(cant==0){
            try {
                System.out.println("No hay elementos");
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cant--;
        System.out.println("CONSUMIR: "+this.cant+" elementos");        
        this.notify();
    }
}
