/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto4;

import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class GestorImpresora{
    private final Impresora[] colImpresoras;
    private Stack<Impresora> impresorasLibres;        
    private final Semaphore hayDisponible=new Semaphore(1);
    private final Semaphore mutexTerminar=new Semaphore(1);

    public GestorImpresora(Impresora[] colImpresoras) {
        this.colImpresoras = colImpresoras;        
        int cantImpresoras=colImpresoras.length;
        this.impresorasLibres=new Stack<Impresora>();
        //Apilar todas las impresoras como libres
        for (int i = 0; i < cantImpresoras; i++) {
            impresorasLibres.add(colImpresoras[i]);            
        }
    }
    
    public Impresora imprimir(){        
        Impresora impresoraAUsar;
        try {
            hayDisponible.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorImpresora.class.getName()).log(Level.SEVERE, null, ex);
        }                        
        //Usar la última impresora en la pila de impresoras libres
        impresoraAUsar=impresorasLibres.peek();
        //Eliminar del tope la impresora a usar
        impresorasLibres.pop();
        //Si ya no quedan impresoras, no se libera el semáforo
        if(!impresorasLibres.empty()){
            hayDisponible.release();
        }
        impresoraAUsar.usar();
        return impresoraAUsar;
    }
    public void terminarImprimir(Impresora impresoraUsada){             
        try {
            mutexTerminar.acquire();            
            impresoraUsada.dejarUsar();
            impresorasLibres.push(impresoraUsada);
            //Si estaban todas las impresoras ocupadas, se libera el permiso para imprimir a los que esperaban
            if(impresorasLibres.size()==1){
                hayDisponible.release();
            }
            mutexTerminar.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorImpresora.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
