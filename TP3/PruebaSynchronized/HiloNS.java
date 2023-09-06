/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP3.PruebaSynchronized;

/**
 *
 * @author ulisescorrales
 */
public class HiloNS extends Thread{
    
    OC objeto;

    public HiloNS(OC objeto, String name) {
        super(name);
        this.objeto = objeto;
    }
    
    public void run(){
        objeto.noSincronizado();
    }
}
