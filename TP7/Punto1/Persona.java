/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP7.Punto1;

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
            sala.entrar();
            Thread.sleep(2000);
            sala.salir();
        } catch (InterruptedException ex) {
            Logger.getLogger(Persona.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
