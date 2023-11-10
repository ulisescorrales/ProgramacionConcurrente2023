/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto2Sem;

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
        try {
            int cantVisitantes=100;
            Visitante[] visitantes= new Visitante[cantVisitantes];
            Observatorio observatorio=new Observatorio();
         //   Observador observador=new Observador(observatorio,"Observador");
            int cantDiscapacitados=10;
           // observador.start();
            Discapacitado[] discapacitados=new Discapacitado[cantDiscapacitados];
            for (int i = 0; i < cantDiscapacitados; i++) {
                discapacitados[i]=new Discapacitado(observatorio,"Discapacitado "+(i+1));
                discapacitados[i].start();                
            }
            for (int i = 0; i < cantVisitantes; i++) {
                visitantes[i]= new Visitante(observatorio,"Visitante "+(i+1));
                visitantes[i].start();
            }
            Thread.sleep(250);
            (new Discapacitado(observatorio,"Discapacitadox")).start();
           // Observador observador2=new Observador(observatorio,"Observador2");
          //  observador2.start();
        } catch (InterruptedException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
