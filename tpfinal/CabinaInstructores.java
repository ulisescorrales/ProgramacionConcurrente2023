/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacionconcurrente2023.tpfinal;

import java.util.concurrent.TimeUnit;
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
    private Condition puedeEntrarProfesor=lock.newCondition();
    private Condition puedeEntrarAlumno=lock.newCondition();
    private Condition dejarClase=lock.newCondition();
    private int segundosMax=5;
    
    public boolean entrarClase(){
        boolean consiguioInstructor=false;
        try {
            lock.lock();
            alumnosEsperando++;
            puedeEntrarProfesor.notify();
            consiguioInstructor=puedeEntrarAlumno.await(segundosMax, TimeUnit.SECONDS);
            alumnosEsperando--;
            lock.unlock();
        } catch (InterruptedException ex) {
            Logger.getLogger(CabinaInstructores.class.getName()).log(Level.SEVERE, null, ex);
        }
        return consiguioInstructor;
    }
    public void dejarClase(){
        try {
            dejarClase.await();
            //Avisarle al siguiente que espera
            dejarClase.notify();
        } catch (InterruptedException ex) {
            Logger.getLogger(CabinaInstructores.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void comenzarEnseniar(){
        lock.lock();
        while(alumnosEsperando<4){
            try {
                puedeEntrarProfesor.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(CabinaInstructores.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        alumnosEsperando-=4;
        lock.unlock();
    }
    
    public void terminarEnseniar(){
        cantInstructores++;
    }
}
