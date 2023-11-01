/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP6.Punto4;

/**
 *
 * @author ulisescorrales
 */
public class Productor extends Thread{
    Almacen almacen;

    public Productor(Almacen almacen) {
        this.almacen = almacen;
    }
    public void run(){
        almacen.producir();
    }
}
