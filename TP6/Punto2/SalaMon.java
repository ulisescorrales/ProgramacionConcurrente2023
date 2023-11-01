/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP6.Punto2;

import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class SalaMon {
    private int cantMesas=10;
    private int mesasOcupadas=0;
    private int turnoActivo=1;
    private int esperando=0;
    
    public synchronized void entrar(){
        int turno;        
        esperando++;
        turno=esperando;
        while(mesasOcupadas>=cantMesas || turno!=turnoActivo){
            try {
                //Si todas las mesas están ocupadas
                System.out.println(Thread.currentThread().getName()+" espera");
                this.wait();
            } catch (InterruptedException ex) {
                Logger.getLogger(SalaMon.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        turnoActivo++;
        mesasOcupadas++;
        System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra");
    }
    public synchronized void salir(){
        mesasOcupadas--;
        System.out.println(Color.YELLOW+Thread.currentThread().getName()+" desocupa una mesa");
        this.notifyAll();
    }
}
