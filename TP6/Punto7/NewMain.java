/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP6.Punto7;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Ferry ferry=new Ferry();
        int cantPersonas=11;
        int cantAutos=5;
        Persona[] p=new Persona[cantPersonas];
        Automovil[] a=new Automovil[cantPersonas];
        for (int i = 0; i < cantPersonas; i++) {
            p[i]=new Persona(ferry,"Persona "+(i+1));
            p[i].start();
            a[i]=new Automovil(ferry,"Auto "+(i+1));
            a[i].start();
        }
        
        ControlFerry control=new ControlFerry(ferry,"Control");
        control.start();
    }
    
}
