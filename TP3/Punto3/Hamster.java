/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP3.Punto3;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Hamster extends Thread{
    private Jaula jaula;

    public Hamster(Jaula jaula, String name) {
        super(name);
        this.jaula = jaula;
    }
    
    public void run(){
        Random num=new Random();
        int accion;
        int duracion;
        while(true){
            try {
                accion=num.nextInt(3);
                duracion=num.nextInt(1000);
                switch(accion){
                    case 0:
                        jaula.empezarUsarRueda();
                        Thread.sleep(duracion);
                        break;
                    case 1:
                        jaula.empezarComer();
                        Thread.sleep(duracion);
                        jaula.terminarComer();
                        break;
                    case 2:
                        jaula.empezarUsarHamaca();
                        Thread.sleep(duracion);
                        jaula.terminarUsarHamaca();
                        break;
                }
            } catch (InterruptedException ex) {
                Logger.getLogger(Hamster.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
