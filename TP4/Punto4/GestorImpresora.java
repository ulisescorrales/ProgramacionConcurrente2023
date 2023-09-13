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
public class GestorImpresora{
    private Impresora[] colImpresoras;
    private int cantImpresoras;
    private int cantUsados=0;
    private Semaphore hayDisponible=new Semaphore(1);

    public GestorImpresora(Impresora[] colImpresoras) {
        this.colImpresoras = colImpresoras;
        cantImpresoras=colImpresoras.length;
    }
    
    public int imprimir(){        
        try {
            hayDisponible.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(GestorImpresora.class.getName()).log(Level.SEVERE, null, ex);
        }
        boolean encontrado=false;
        int i=0;
        int numDeImpresora;
        
        //Buscar una impresora desocupada
        while(!encontrado && i<cantImpresoras){
            encontrado=colImpresoras[i].intentarUsar();
            i++;
        }
        numDeImpresora=i-1;
        cantUsados++;
        //Mientras sigan habiendo impresoras disponibles, liberar el permiso
        if(cantUsados<cantImpresoras){
            hayDisponible.release();
        }        
        return numDeImpresora;
    }
    public void terminarImprimir(int impresoraUsada){
        colImpresoras[impresoraUsada].dejarUsar();
        cantUsados--;      
        //Si estaban todas las impresoras ocupadas, se libera un permiso
        if(cantImpresoras-1==cantUsados){
            hayDisponible.release();
        }          
    }
}
