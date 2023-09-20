/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP3.Punto7;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Imprimidor extends Thread {

    private String letra;
    private Turno turno;//Objeto compartido
    private int turnoPropio;

    public Imprimidor(String letra, Turno turno, String name, int turnoPropio) {
        super(name);
        this.letra = letra;
        this.turno = turno;
        this.turnoPropio = turnoPropio;
    }

    public void run() {
        while (true) {
            if (turno.getTurno(this.turnoPropio)) {

                System.out.print(this.letra);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Imprimidor.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
