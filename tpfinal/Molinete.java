/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacionconcurrente2023.tpfinal;

/**
 *
 * @author ulises.corrales
 */
public class Molinete {
    private int contador=0;
    
    
    public synchronized void sumarContador(){
        this.contador++;
    }

    public synchronized int getContador() {
        return contador;
    }
    
}