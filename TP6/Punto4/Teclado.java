/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP6.Punto4;

import java.util.Scanner;

/**
 *
 * @author ulisescorrales
 */
public class Teclado extends Thread{
    private Almacen almacen;
    private Scanner scan=new Scanner(System.in);

    public Teclado(Almacen almacen) {
        this.almacen = almacen;
    }
    
    public void run(){
        char opcion;
        while(true){
            System.out.println("Introduzca 'c' o 'p'");
            opcion=scan.next().charAt(0);
            switch(opcion){
                case 'c' :
                    new Consumidor(almacen).start();
                    break;
                case 'p' :
                    new Productor(almacen).start();
                    break;
            }
        }
    }
}
