/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Mozo extends Thread{
    
    private Lugar lugar;

    public Mozo(Lugar lugar, String name) {
        super(name);
        this.lugar = lugar;
    }
    public void run(){
        while(true){
            try {
                lugar.buscarPlato();              
                System.out.println("Mozo busca el plato");
                Thread.sleep(2000);
                System.out.println("Mozo sirve el plato");
                lugar.servir();
                lugar.volverAEsperar();
                System.out.println("Mozo Vuelve a esperar");
            } catch (InterruptedException ex) {
                Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
    }
}
