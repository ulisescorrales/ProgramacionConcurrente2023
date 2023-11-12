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
    Semaphore semVecino = new Semaphore(1);
    Semaphore semVisitantes = new Semaphore(1);
    boolean liberadoV = true;
    boolean bloqVecino = false;
    int esperandoAdentro = 0;

    public Parque(int cap) {
        this.cap = cap;
        capacidad = new Semaphore(cap);
    }

    public void entrarVisitante() {
        try {
            mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " pide entrar");
            esperandoAdentro++;
            esperandoVisitante++;
            if (esperandoVisitante + esperandoVecino == 1 && cont<cap) {
                System.out.println("BLOQUEAR VECINO");
                semVecino.acquire();
            }
            mutex.release();

            semVisitantes.acquire();
            capacidad.acquire();
            mutex.acquire();
            System.out.println(Color.GREEN + Thread.currentThread().getName() + " entra");
            //a esta altura quedan en (0,0)
            esperandoVisitante--;
            cont++;
            if (cont < cap) {
                if (esperandoVecino > 0) {
                    System.out.println("    libera vecino");
                    semVecino.release();
                } else if (esperandoVisitante > 0) {
                    System.out.println("    libera visitante");
                    semVisitantes.release();
                } else {
                    System.out.println("    libera ambos");
                    this.getPermisos();
                    semVisitantes.release();
                    semVecino.release();
                }
            }

            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Parque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void entrarVecino() {
        try {
            mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " pide entrar");
            esperandoAdentro++;
            esperandoVecino++;
            if (esperandoVisitante + esperandoVecino == 1 && cont<cap) {
                System.out.println("BLOQUEAR VISITANTE");
                semVisitantes.acquire();
            }
            mutex.release();

            semVecino.acquire();
            capacidad.acquire();
            mutex.acquire();
            System.out.println(Color.CYAN + Thread.currentThread().getName() + " entra");
            esperandoVecino--;
            cont++;
            if (cont < cap) {
                if (esperandoVecino > 0) {
                    System.out.println("    libera vecino");
                    semVecino.release();                   
                } else if (esperandoVisitante > 0) {
                    System.out.println("    libera visitante");
                    semVisitantes.release();
                } else {
                    System.out.println("    libera ambos");
                    this.getPermisos();
                    semVisitantes.release();
                    semVecino.release();
                }
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Parque.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void salir() {
        try {
            mutex.acquire();
            esperandoAdentro--;
            if (cont == cap) {
                System.out.println("--");
                if (esperandoVecino > 0) {
                    System.out.println("    libera vecino");
                    semVecino.release();
                } else if (esperandoVisitante > 0) {
                    System.out.println("    libera visitante");
                    semVisitantes.release();
                } else {
                    System.out.println("    libera ambos");
                    this.getPermisos();
                    semVisitantes.release();
                    semVecino.release();
                }
            }
            cont--;
            System.out.println(Color.YELLOW + Thread.currentThread().getName() + " sale");
            capacidad.release();
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Vecino.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void getPermisos(){
        System.out.println("semVisitantes: "+(semVisitantes.availablePermits()+1)+", semVecinos: "+(semVecino.availablePermits()+1));
    }
}
