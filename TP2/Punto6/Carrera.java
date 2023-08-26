/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP2.Punto6;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Carrera {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int cantCorredores=10;
        Corredor[] corredores=new Corredor[cantCorredores];        
        int i;
        for(i=0;i<cantCorredores;i++){
            corredores[i]=new Corredor(String.valueOf(i+1));
        }
        for(i=0;i<cantCorredores;i++){
            corredores[i].start();
        }
        for(i=0;i<cantCorredores;i++){
            try {
                corredores[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(Carrera.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Corredor max=corredores[0];
        for(i=1;i<cantCorredores;i++){
            if(corredores[i].getMaximoPaso()>max.getMaximoPaso())
                max=corredores[i];
        }
        System.out.println("El corredor con el mayor paso fue el Nº: "+max.getName());
    }
    
}
