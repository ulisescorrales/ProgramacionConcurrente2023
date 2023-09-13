/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto3;

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
        ObjetoCompartido oc=new ObjetoCompartido();
        P1 p1=new P1(oc,"P1");
        P2 p2=new P2(oc,"P2");
        P3 p3=new P3(oc,"P3");
        
        p1.start();
        p2.start();
        p3.start();
    }
    
}
