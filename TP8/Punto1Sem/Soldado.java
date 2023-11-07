/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto1Sem;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Soldado extends Thread {

    Sala sala;

    public Soldado(Sala sala, String name) {
        super(name);
        this.sala = sala;
    }

    public void run() {
        Random num = new Random();
        int x;
        sala.entrar();
        sala.servirseAlmuerzo();
        try {
            Thread.sleep(num.nextInt(2000));
        } catch (InterruptedException ex) {
            Logger.getLogger(Soldado.class.getName()).log(Level.SEVERE, null, ex);
        }
        sala.dejarServirseAlmuerzo();
        x=num.nextInt(2);
        System.out.println(x);
        if (x == 1) {
            sala.servirseGaseosa();
            try {
                Thread.sleep(num.nextInt(2000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Soldado.class.getName()).log(Level.SEVERE, null, ex);
            }
            sala.dejarServirseGaseosa();
        }
        if (num.nextInt(2) == 1) {
            sala.servirsePostre();
            try {
                Thread.sleep(num.nextInt(2000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Soldado.class.getName()).log(Level.SEVERE, null, ex);
            }
            sala.dejarServirsePostre();
        }
        //comer
        System.out.println(this.getName()+" ESTA COMIENDO");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Soldado.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(this.getName()+" TERMINA");
        sala.salir();
    }
}
