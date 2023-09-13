/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto6;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Taxista extends Thread{
    
    private Taxi taxi;

    public Taxista(Taxi taxi, String name) {
        super(name);
        this.taxi = taxi;
    }
    public void run(){
        while(true){
            try {
                taxi.empezarRecorrido();//Dormir has que un pasajero pida viajar
                System.out.println("Empieza el viaje");
                Thread.sleep(2000);//Viajar
                System.out.println("Termina el viaje");
                taxi.terminarRecorrido();
            } catch (InterruptedException ex) {
                Logger.getLogger(Taxista.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
