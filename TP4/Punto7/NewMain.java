/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto7;

import programacionconcurrente2023.TP5.Punto1.Mozo;
import programacionconcurrente2023.TP5.Punto1.Lugar;
import programacionconcurrente2023.TP5.Punto1.Empleado;

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
        int cantEmpleados=3;
        Lugar lugar=new Lugar();
        Mozo mozo=new Mozo(lugar,"Mozo");
        Empleado[] empleados=new Empleado[3];
        
        for (int i = 0; i < cantEmpleados; i++) {
            empleados[i]=new Empleado(lugar,"Empleado "+(i+1));
            empleados[i].start();
        }
        mozo.start();
    }
    
}
