/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto6;

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
        int cantPasajeros=5;
        Pasajero[] colPasajeros=new Pasajero[cantPasajeros];
        Taxi taxi=new Taxi();
        Taxista taxista=new Taxista(taxi,"Taxista");
        int i;
        for (i = 0; i < cantPasajeros; i++) {
            colPasajeros[i]=new Pasajero(taxi,"Pasajero "+(i+1));
        }
        for (i = 0; i < cantPasajeros; i++) {
            colPasajeros[i].start();
        }
        taxista.start();
    }
    
}
