/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP3.Punto2;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Sanador extends Thread{
    Energia e;

    public Sanador(Energia e, String name) {
        super(name);
        this.e = e;
    }
    
    public void run(){
        Random num=new Random();
        while(true){
            try {
                e.revitalizar();
                Thread.sleep(num.nextInt(1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(Sanador.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
