/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP6.Punto7;

import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class Ferry{
    private int capacidad=3;
    private int espOcupados=0;
    private boolean destino=false;
    private boolean origen=true;
    
    public synchronized void subir(int sumar){
        while(espOcupados+sumar>capacidad || !origen){
            try {
                System.out.println(Thread.currentThread().getName()+" intenta entrar");
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Ferry.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        espOcupados+=sumar;
        System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra, ocupados:" +this.espOcupados);
        this.notifyAll();
    }
    public synchronized void arrancar(){
        while(this.capacidad>espOcupados){
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Ferry.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("ARRANCANDO");
    }    
    
    public synchronized void llegarDestino(){
        destino=true;
        origen=false;
        this.notifyAll();
        System.out.println(Color.YELLOW+"LLEGO A DESTINO");
    }    
    
    public synchronized void bajar(int restar){
        while(!destino){
            try {
                System.out.println(Thread.currentThread().getName()+" intenta bajar");
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Ferry.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        espOcupados-=restar;
        System.out.println(Color.YELLOW+Thread.currentThread().getName()+" se bajó en destino");
        if(this.espOcupados==0){
            this.notifyAll();
        }
    }
    
    public synchronized void regresar(){
        while(espOcupados!=0){
            try {
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(Ferry.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        System.out.println("REGRESANDO");
    }
    
    public synchronized void llegarOrigen(){
        origen=true;
        destino=false;
        this.notifyAll();
        System.out.println(Color.GREEN+"LLEGO A ORIGEN");
    }
}
