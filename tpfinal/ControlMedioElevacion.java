/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacionconcurrente2023.tpfinal;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulises.corrales
 */
public class ControlMedioElevacion extends Thread{
    private MedioElevacion me;
    
    public void run(){
        while(true){
            try {
                //Empieza su operación desde abajo e irá elevándose y bajando constantemente
                me.elevar();
                Thread.sleep(2000);
                me.llegarCima();
                me.descender();
                Thread.sleep(2000);
                me.llegarOrigen();
            } catch (InterruptedException ex) {
                Logger.getLogger(ControlMedioElevacion.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
