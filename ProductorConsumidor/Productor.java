/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.ProductorConsumidor;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Productor extends Thread{
    private Buffer buffer;

    public Productor(Buffer buffer, String name) {
        super(name);
        this.buffer = buffer;
    }
    public void run(){
        Object l=new Object();
        Random num=new Random();
        while(true){
            buffer.producir(l);
            try {
                Thread.sleep(num.nextInt(5000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Productor.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }        
}
