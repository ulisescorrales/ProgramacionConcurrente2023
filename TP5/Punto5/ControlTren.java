/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacionconcurrente2023.TP5.Punto5;

import java.util.logging.Level;
import java.util.logging.Logger;

public class ControlTren extends Thread{
    private Tren tren;

    public ControlTren(Tren tren, String name) {
        super(name);
        this.tren = tren;
    }
    
    public void run(){
        //Hilo que controla el arranque y detencion del tren
        while(true){
            tren.arrancar();            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlTren.class.getName()).log(Level.SEVERE, null, ex);
            }            
            tren.parar();
            tren.habilitarSubir();
        }
    }
}
