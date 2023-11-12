/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.ParqueEj;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class Parque {
    int cap;
    Semaphore capacidad;
    Semaphore vecino = new Semaphore(1);
    Semaphore visitantes = new Semaphore(1);
    Semaphore mutex = new Semaphore(1);
    int cont = 0;
    int esperandoVisitante = 0;
    int esperandoVecino = 0;
    Semaphore semVecino=new Semaphore(1);
    boolean liberadoV=true;

    public Parque(int cap) {
        this.cap = cap;
        capacidad = new Semaphore(cap);
    }

    public void entrarVisitante() {
        try {
            mutex.acquire();
            esperandoVisitante++;
            System.out.println(Thread.currentThread().getName()+" intenta entrar");
            mutex.release();      
                        
            visitantes.acquire();
            capacidad.acquire();
            mutex.acquire();
            System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra");
            esperandoVisitante--;
            cont++;
            if (cont < cap) {
                //Pueden seguir entrando vecinos
                visitantes.release();
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Vecino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void entrarVecino() {
        try {
            mutex.acquire();
            esperandoVecino++;
            System.out.println(Thread.currentThread().getName()+" intenta entrar");
            if (esperandoVecino == 1 && cont<cap  && esperandoVisitante!=1) {
                System.out.println("intenta bloquear a visitantes");
                visitantes.acquire();
                System.out.println("visitanes bloqueados");
            }
            mutex.release();
            
            capacidad.acquire();
            mutex.acquire();
            System.out.println(Color.GREEN+Thread.currentThread().getName()+" entra");
            cont++;
            esperandoVecino--;
            if (cont < cap && esperandoVecino == 0) {
                System.out.println("    Libera visitantes");
                visitantes.release();
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Vecino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void salir(){
        try {
            mutex.acquire();
            if(cont==cap){
                if(esperandoVecino==0)
                    visitantes.release();
            }
            cont--;
            System.out.println(Color.YELLOW+Thread.currentThread().getName()+" sale");
            capacidad.release();
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Vecino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
