/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto6;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int cantAviones = 6;

        TorreControl torre = new TorreControl();
        AvionAterrizar[] aterrizar = new AvionAterrizar[cantAviones];
        AvionDespegar[] despegar = new AvionDespegar[cantAviones];
        
        for (int i = 0; i < cantAviones; i++) {
            aterrizar[i] = new AvionAterrizar(torre, "AviónA " + (i + 1));
            aterrizar[i].start();            
        }        
        for (int i = 0; i < cantAviones; i++) {
            despegar[i] = new AvionDespegar(torre, "AviónD " + (i + 1));
            despegar[i].start();
            
        }
        for (int i = 0; i < cantAviones; i++) {
            
            try {
                despegar[i].join();
                aterrizar[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            }
            
        }
        torre.getPermisos();
        
    }

}
