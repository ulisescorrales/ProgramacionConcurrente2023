/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto7;

/**
 *
 * @author ulisescorrales
 */
public class Encargado extends Thread{
    private Mirador mirador;

    public Encargado(Mirador mirador, String name) {
        super(name);
        this.mirador = mirador;
    }
    public void run(){
        while(true){
            mirador.habilitarToboganes();           
        }
    }
}
