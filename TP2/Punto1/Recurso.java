/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP2.Punto1;

/**
 *
 * @author ulisescorrales
 */
public class Recurso implements Runnable{

    public static void uso(){
        Thread t = Thread.currentThread();
        System.out.println("en Recurso: Soy" + t.getName());
    }
    public void run(){
        
    }
}
