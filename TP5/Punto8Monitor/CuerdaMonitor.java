/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto8Monitor;

import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class CuerdaMonitor {

    private int contA = 0;
    private int contB = 0;
    private int arribaA = 0;
    private int arribaB = 0;
    private int turnoA = 1;
    private int turnoB = 1;
    private int capacidad = 5;
    private int esperandoA=0;
    private int esperandoB=0;
    

    public synchronized void subirA() {        
        esperandoA++;
        int turnoAsignado=esperandoA;
        System.out.println(Thread.currentThread().getName() + " pide entrar");        
        while ((arribaB > 0 || arribaA >= capacidad) || !(turnoAsignado==turnoA)) {
            try {
                System.out.println(Thread.currentThread().getName() + " espera");                
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(CuerdaMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        turnoA++;
        System.out.println(Color.CYAN + Thread.currentThread().getName() + " entra");
        arribaA++;
    }

    public synchronized void bajarA() {
        arribaA--;
        System.out.println(Color.YELLOW + Thread.currentThread().getName() + " sale");
        this.notifyAll();
    }

    public synchronized void subirB() {
        esperandoB++;
        int turnoAsignado=esperandoB;
        System.out.println(Thread.currentThread().getName() + " intenta entrar");        
        while ((arribaA > 0 || arribaB >= capacidad)|| !(turnoAsignado==turnoB)) {
            try {
                System.out.println(Thread.currentThread().getName() + " espera");
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(CuerdaMonitor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        turnoB++;
        System.out.println(Color.CYAN + Thread.currentThread().getName() + " entra");
        arribaB++;
    }

    public synchronized void bajarB() {
        arribaB--;
        System.out.println(Color.YELLOW + Thread.currentThread().getName() + " sale");
        this.notifyAll();
    }
}
