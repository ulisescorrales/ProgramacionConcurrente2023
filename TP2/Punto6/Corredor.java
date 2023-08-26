/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP2.Punto6;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Corredor extends Thread{
    private static final byte MAXIMO=100;
    private byte distanciaRecorrida=0;
    String nombre;
    int maximoPaso=0;
    
    public Corredor(String nombre){
        super(nombre);
        this.nombre=nombre;
    }
    
    public void run(){
        Random num= new Random();
        int paso;
        System.out.println("Corredor "+this.nombre+" empieza la carrera");
        while(this.distanciaRecorrida<MAXIMO){
            paso=num.nextInt(10);            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Corredor.class.getName()).log(Level.SEVERE, null, ex);
            }            
            if(paso>maximoPaso)
            this.maximoPaso=paso;
                this.distanciaRecorrida+=paso;
            System.out.println(this.nombre+" recorre "+this.distanciaRecorrida+" pasos");
        }
        System.out.println("FINALIZÓ el corredor "+this.nombre);
    }
    public int getMaximoPaso(){
        return maximoPaso;
    }
    
}
