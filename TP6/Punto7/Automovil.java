/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP6.Punto7;

/**
 *
 * @author ulisescorrales
 */
public class Automovil extends Thread{
    Ferry ferry;
    int espacio=4;

    public Automovil(Ferry ferry, String name) {
        super(name);
        this.ferry = ferry;
    }
    public void run(){
        this.ferry.subir(espacio);
        this.ferry.bajar(espacio);
    }
}
