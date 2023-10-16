/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto8;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class Cuerda {    
    private Semaphore ladoA=new Semaphore(5);
    private Semaphore ladoB=new Semaphore(5);
    private Semaphore mutex1=new Semaphore(1);
    //private Semaphore mutex2=new Semaphore(1);
    //private Semaphore lectores=new Semaphore(1);
    private int cont=0;    
    private int contA=0;
    private int contB=0;
    
    
    public void subirA(){
        try {                
            mutex1.acquire();
            System.out.println(Color.YELLOW+Thread.currentThread().getName()+"quiere entrar");
            cont++;
            contA++;
            if(cont==1){
                //El primero que llega bloquea al otro
                ladoB.acquire(5);                
            }            
            mutex1.release();    
            ladoA.acquire();            
            //Escribir
            System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra");
        } catch (InterruptedException ex) {
            Logger.getLogger(Cuerda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void subirB(){
        try {
            mutex1.acquire();
            System.out.println(Color.YELLOW+Thread.currentThread().getName()+"quiere entrar");
            cont++; 
            contB++;
            if(cont==1){
                //El primero que llega bloquea al otro
                ladoA.acquire(5);
            }            
            mutex1.release();
            ladoB.acquire();  
            System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra");
        } catch (InterruptedException ex) {
            Logger.getLogger(Cuerda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public void bajarA(){
        try {
            mutex1.acquire();
            System.out.println(Color.RED+Thread.currentThread().getName()+" sale");   
            cont--;
            contA--;
            ladoA.release();
            if(contA==0){
                if(contB>0){
                    ladoA.acquire(5);
                }
                ladoB.release(5);                
            }                     
            mutex1.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Cuerda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void bajarB(){
        try {
            mutex1.acquire();
            System.out.println(Color.RED+Thread.currentThread().getName()+" sale");            
            cont--;
            contB--;
            ladoB.release();
            if(contB==0){
                if(contA>0){
                    ladoB.acquire(5);
                }
                ladoA.release(5);
            }            
            mutex1.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Cuerda.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
