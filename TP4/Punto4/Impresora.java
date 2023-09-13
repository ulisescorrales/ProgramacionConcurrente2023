/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto4;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Impresora {    
    Semaphore usar=new Semaphore(1);
    
    public boolean intentarUsar(){
        return usar.tryAcquire();
    }
    public void dejarUsar(){
        usar.release();
    }
    public void esperar(){
        try {
            usar.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Impresora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
