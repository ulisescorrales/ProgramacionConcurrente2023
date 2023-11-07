/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto2Sem;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Discapacitado extends Visitante{
    
    public Discapacitado(Observatorio observatorio, String name) {
        super(observatorio, name);
    }
    
    public void run(){
        try {
            this.observatorio.entrarDiscapacitado();
            Thread.sleep(500);
            this.observatorio.salirDiscapacitado();
        } catch (InterruptedException ex) {
            Logger.getLogger(Discapacitado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
