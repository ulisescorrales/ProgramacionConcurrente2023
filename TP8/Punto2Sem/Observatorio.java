/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto2Sem;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class Observatorio {

    Semaphore mutex = new Semaphore(1);
    Semaphore capacidad = new Semaphore(50);    
    private int esperandoArriba = 0;
    private int cantVisitantes = 0;
    Semaphore mantenimiento = new Semaphore(1, true);
    Semaphore observadores = new Semaphore(1, true);
    private int cantDisc = 0;    
    private int esperandoDisc = 0;  
    private Semaphore residuo=new Semaphore(21);//Indica que existen 29 personas dentro o menos
    private Semaphore espacioTotal=new Semaphore(50);//No afectado por nuevo límite
    private Semaphore puedeEntrarD=new Semaphore(1);
    private int cont=0;

    public void entrarVisitante() {
        try {
            mutex.acquire();
            esperandoArriba++;
            if (esperandoArriba == 1) {                
                mantenimiento.acquire();
                observadores.acquire();
            }
            mutex.release();
            
            capacidad.acquire();                   
            mutex.acquire();                    
            cantVisitantes++;     
            System.out.println(Color.CYAN + Thread.currentThread().getName() + " entra, adentro: " + (this.cantDisc + this.cantVisitantes)+", permisos disponibles: "+this.capacidad.availablePermits());
            if(cantVisitantes+cantDisc==30){
                System.out.println("Bloquear a discapacitados");
                puedeEntrarD.acquire();
            }            
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void entrarDiscapacitado() {
        try {
            mutex.acquire();
            esperandoArriba++;
            esperandoDisc++;
            if (esperandoArriba == 1) {
                System.out.println("BLOQUEO A MANTENIMIENTO Y OBSERVADOR");
                mantenimiento.acquire();
                observadores.acquire();
            }         
            mutex.release();
                        
            puedeEntrarD.acquire();
            capacidad.acquire();
            mutex.acquire();                           
            if (cantDisc == 0) {     
                mutex.release();
                System.out.println("TOMAR 20 PERMISOS");
                capacidad.acquire(20);
            }else{          
                mutex.release();
                capacidad.acquire();                  
            }
            mutex.acquire();
            esperandoDisc--;            
            cantDisc++;
            System.out.println(Color.GREEN + Thread.currentThread().getName() + " entra, adentro: " + (this.cantDisc + this.cantVisitantes));            
            if(cantDisc+cantVisitantes<30){
                System.out.println("Liberar permiso a discapacitados");
                puedeEntrarD.release();
            }            
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salir() {
        try {
            mutex.acquire();
            System.out.println(Color.YELLOW + Thread.currentThread().getName() + " sale, adentro:" + (cantDisc + cantVisitantes));
            if(cantDisc+cantVisitantes==30){
                System.out.println("Visitane libera permiso a discapacitados");
                puedeEntrarD.release();
            }
            esperandoArriba--;
            cantVisitantes--;
            capacidad.release();
            //espacioTotal.release();
            if (cantVisitantes == 0 && cantDisc == 0) {
                System.out.println("DESBLOQUEO A TODOS");
                mantenimiento.release();
                observadores.release();
            }            
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salirDiscapacitado() {
        try {
            mutex.acquire();
            if(cantVisitantes+cantDisc==30){
                System.out.println("Discapacitado libera permiso a discapacitados");
                puedeEntrarD.release();
            }
            esperandoArriba--;
            cantDisc--;            
            //espacioTotal.release();
            System.out.println(Color.YELLOW + Thread.currentThread().getName() + " sale, adentro:" + (cantDisc + cantVisitantes));            
            if (cantDisc == 0) {
                System.out.println("LIBERO 20 PERMISOS");
                capacidad.release(21);
                if (cantVisitantes == 0) {
                    System.out.println("LIBERO A TODOS");
                    mantenimiento.release();
                    observadores.release();
                }
            } else {
                capacidad.release();
            }            
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Observatorio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
