/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
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
public class Almacen {
    //Objeto compartido entre transporte y empaquetador
    private Lock almacen=new ReentrantLock();
    private Condition empaquetador=almacen.newCondition();
    private Condition transporte=almacen.newCondition();
    private int cantCajas=0;
    
    //Método para empquetador
    public void ponerCaja(){
        almacen.lock();
        while(cantCajas==10){
            try {
                empaquetador.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cantCajas++;
        System.out.println("              Empaquetador coloca caja, cant. cajas:"+this.cantCajas);
        if(cantCajas==10){
            transporte.signal();
        }
        almacen.unlock();
    }
    //Método para transportador
    public void vaciarAlmacen(){
        almacen.lock();
        while(cantCajas<10){
            try {
                transporte.await();
            } catch (InterruptedException ex) {
                Logger.getLogger(Almacen.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        cantCajas=0;
        System.out.println("            Transporte vacía el almacén");
        empaquetador.signal();
    }
}
