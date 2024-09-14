/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.ParcialPunto1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class EmbotelladoraAgua extends Thread{
    private SalaCajas salaCajas;

    public EmbotelladoraAgua(SalaCajas salaCajas, String name) {
        super(name);
        this.salaCajas = salaCajas;
    }
    
    public void run(){
        while(true){
            try {
                //fabricar una botella
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(EmbotelladoraAgua.class.getName()).log(Level.SEVERE, null, ex);
            }
            salaCajas.ponerAgua();            
        }
    }
}
