/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.ParcialPunto1;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Empaquetador extends Thread{
    private SalaCajas salaCajas;
    private Almacen almacen;

    public Empaquetador(SalaCajas salaCajas, Almacen almacen, String name) {
        super(name);
        this.salaCajas = salaCajas;
        this.almacen = almacen;
    }
    
    public void run(){
        while(true){
            salaCajas.sacarCaja();
            try {
                //
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Empaquetador.class.getName()).log(Level.SEVERE, null, ex);
            }
            almacen.ponerCaja();
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Empaquetador.class.getName()).log(Level.SEVERE, null, ex);
            }
            salaCajas.reponerCaja();
        }
    }
}
