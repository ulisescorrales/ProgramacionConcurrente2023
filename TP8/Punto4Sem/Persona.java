/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto4Sem;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Persona extends Thread{
    Sala sala;

    public Persona(Sala sala, String name) {
        super(name);
        this.sala = sala;
    }
    public void run(){
        try {
            Random num=new Random();
            sala.entrar();
            Thread.sleep(num.nextInt(2000));
            sala.salir();
        } catch (InterruptedException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
