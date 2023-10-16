/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto8;

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
        // TODO code application logic here
        int cantBabuinos=7;
        int i,j;
        BabuinoA[] a=new BabuinoA[cantBabuinos];
        BabuinoB[] b=new BabuinoB[cantBabuinos];
        Cuerda cuerda=new Cuerda();
        
        for (i = 0; i < cantBabuinos; i++) {
            a[i]=new BabuinoA(cuerda,"BabuinoA "+(i+1));
            a[i].start();
            b[i]=new BabuinoB(cuerda,"BabuinoB "+(i+1));
            b[i].start();
        }
        try {
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        BabuinoA a1=new BabuinoA(cuerda,"BabuinoA "+(8));
        a1.start();
        BabuinoA a2=new BabuinoA(cuerda,"BabuinoA "+(9));
        a2.start();
    }
    
}
