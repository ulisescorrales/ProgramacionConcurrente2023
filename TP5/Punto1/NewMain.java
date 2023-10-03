/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto1;

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
        Mozo mozo1=new Mozo(lugar,"Mozo1");
        Mozo mozo2=new Mozo(lugar,"Mozo2");
        Empleado[] empleados=new Empleado[3];
        
        for (int i = 0; i < cantEmpleados; i++) {
            empleados[i]=new Empleado(lugar,"Empleado "+(i+1));
            empleados[i].start();
        }
        mozo1.start();
        mozo2.start();
    }
    
}
