/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP3.Punto3;

/**
 *
 * @author ulisescorrales
 */
public class Jaula {
    private boolean plato=true;
    private boolean hamaca=true;
    private boolean rueda=true;
    
    public synchronized boolean empezarComer(){
        boolean exito=false;
        if(plato){
            plato=false;
            exito=true;
        }
        return exito;
    }
    public synchronized void terminarComer(){
        plato=true;
    }
    public synchronized boolean empezarUsarRueda(){
        boolean exito=false;
        if(rueda){
            rueda=false;
            exito=true;
        }
        return exito;
    }
    public synchronized void terminarUsarRueda(){
        rueda=true;
    }
    public synchronized boolean empezarUsarHamaca(){
        boolean exito=false;
        if(hamaca){
            hamaca=false;
            exito=true;
        }
        return exito;
    }
    public synchronized void terminarUsarHamaca(){
        hamaca=false;
    }
}
