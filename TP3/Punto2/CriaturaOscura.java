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
public class CriaturaOscura extends Thread{
    private Energia e;

    public CriaturaOscura(Energia e, String name) {
        super(name);
        this.e = e;
    }            
    public void run(){
        Random num=new Random();
        while(true){
            try {
                e.drenar();
                Thread.sleep(num.nextInt(1000));
            } catch (InterruptedException ex) {
                Logger.getLogger(CriaturaOscura.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}