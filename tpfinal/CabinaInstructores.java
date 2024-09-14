/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacionconcurrente2023.tpfinal;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulises.corrales
 */
public class CabinaInstructores {
    private int cantInstructores=5;
    private byte alumnosEsperando=0;
    private Lock lock;
    private Condition hayInstructores=lock.newCondition();
    private Condition haySuficientesAlumnos=lock.newCondition();
    private int tiempoMax;
    
    public void entrarClase(){
        lock.lock();
        alumnosEsperando++;
        if(cantInstructores==0){
            
            try {
                this.wait(5000);
                
            } catch (InterruptedException ex) {
                Logger.getLogger(CabinaInstructores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        lock.unlock();
    }
    public void dejarClase(){
        alumnosEsperando--;
    }
    
    public void comenzarEnseniar(){
        cantInstructores--;
        alumnosEsperando=0;
    }
    
    public void terminarEnseniar(){
        cantInstructores++;
    }
}
