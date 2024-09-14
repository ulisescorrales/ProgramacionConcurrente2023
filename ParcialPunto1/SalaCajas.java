/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.ParcialPunto1;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class SalaCajas {
    //Objeto compartido entre embotelladores y el empaquetador
    private Lock cajaAgua=new ReentrantLock();
    private Lock cajaVino=new ReentrantLock();   
    private Lock empaquetadorLock=new ReentrantLock();
    private Condition agua=cajaAgua.newCondition();
    private Condition vino=cajaVino.newCondition();
    private Condition empaquetador=empaquetadorLock.newCondition();
    private int contAgua=0;
    private int contVino=0;
    private boolean hayCajaAgua=true;
    private boolean hayCajaVino=true;
    
    //Método de embotelladora de agua saborizada
    public void ponerAgua(){
        cajaAgua.lock();
        while(contAgua==10 || !hayCajaAgua){
            try {
                agua.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(SalaCajas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        contAgua++;
        System.out.println("Embotellador pone botella de agua");
        if(contAgua==10){
            //Para enviar un signal a una condición de otro lock primero habría que adquirirlo
            empaquetadorLock.lock();            
            empaquetador.signal();
            empaquetadorLock.unlock();
        }
        cajaAgua.unlock();
    }
    //Método para embotelladora de vino
    public void ponerVino(){
        cajaVino.lock();
        while(contVino==10 || !hayCajaVino){
            try {
                vino.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(SalaCajas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        contVino++;
        System.out.println("Embotellador pone botella de vino");
        if(contAgua==10){
            //Para enviar un signal a una condición de otro lock primero habría que adquirirlo
            empaquetadorLock.lock();
            empaquetador.signal();
            empaquetadorLock.unlock();
        }
        cajaVino.unlock();
    }
    //Método para embotellador
    public void sacarCaja(){            
        empaquetadorLock.lock();
        //Tomaría estos dos lock para tener acceso con exclusión mutua a contAgua y contVino
        cajaAgua.lock();
        cajaVino.lock();
        while(contVino<10 && contAgua<10){
            try {                
                cajaAgua.unlock();
                cajaVino.unlock();                
                empaquetador.await();
                //Vover a tomar ambos locks para tener exclusión mutua sobre contVino y contAgua
                cajaAgua.lock();
                cajaVino.lock();
            } catch (InterruptedException ex) {
                Logger.getLogger(SalaCajas.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
        //Sacar la caja
        if(contVino==10){
            contVino=0;
            hayCajaVino=false;
            System.out.println("    Empaquetador saca la caja de vino");
        }else{
            contAgua=0;
            hayCajaAgua=false;
            System.out.println("    Empaquetador saca la caja de agua");
        }
        cajaAgua.unlock();
        cajaVino.unlock();
        empaquetadorLock.unlock();
    }
    //Método para embotellador
    public void reponerCaja(){
        empaquetadorLock.lock();
        if(!hayCajaVino){    
            cajaVino.lock();
            hayCajaVino=true; 
            System.out.println("    Empaquetador repone caja de vino");
            vino.signalAll();
            cajaVino.unlock();
        }
        if(!hayCajaAgua){
            cajaAgua.lock();
            hayCajaAgua=true;
            System.out.println("    Empaquetador repone caja de agua");
            agua.signalAll();
            cajaAgua.unlock();
        }
        empaquetadorLock.unlock();
    }
}

