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
    private boolean estaOcupado=false;
    private int numImpresora;

    public Impresora(int numImpresora) {
        this.numImpresora = numImpresora;
    }
    
    public int getNumImpresora(){
        return this.numImpresora;
    }
    public void usar(){
        estaOcupado=true;
    }
    public void dejarUsar(){
        estaOcupado=false;
    }
}
