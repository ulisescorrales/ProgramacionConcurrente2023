/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto7;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Empleado extends Thread{
    private Lugar lugar;

    public Empleado(Lugar lugar, String name) {
        super(name);
        this.lugar = lugar;
    }
    public void run(){
        lugar.pedirPlato();
        System.out.println(this.getName()+" pide el plato");
        lugar.comer();
        System.out.println(this.getName()+" empieza a comer");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Empleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(this.getName()+" termina de comer");
        lugar.terminarComer();        
    }
}
