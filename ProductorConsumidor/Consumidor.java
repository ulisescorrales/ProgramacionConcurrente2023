/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.ProductorConsumidor;
import java.util.Random;

/**
 *
 * @author ulisescorrales
 */
public class Consumidor extends Thread {

    private Buffer buffer;

    public Consumidor(Buffer buffer, String name) {
        super(name);
        this.buffer = buffer;
    }

    public void run() {
        Object l = new Object();
        Random num = new Random();
        while (true) {
            buffer.consumir();
            try {
                Thread.sleep(num.nextInt(5000));
            } catch (InterruptedException ex) {
                java.util.logging.Logger.getLogger(Consumidor.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
            }
        }

    }
}
