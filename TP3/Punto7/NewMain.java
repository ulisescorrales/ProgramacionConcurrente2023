/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP3.Punto7;

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
        Turno turnos=new Turno();
        Imprimidor a=new Imprimidor("AA",turnos,"Hilo a",0);
        Imprimidor b=new Imprimidor("BBB",turnos,"Hilo b",1);
        Imprimidor c=new Imprimidor("CCCC",turnos,"Hilo c",2);
        a.start();
        b.start();
        c.start();
    }
    
}
