/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto6;

/**
 *
 * @author ulisescorrales
 */
public class Pasajero extends Thread{
    private Taxi taxi;

    public Pasajero(Taxi taxi, String name) {
        super(name);
        this.taxi = taxi;
    }
    
    public void run(){
        taxi.subirAlTaxi();
        System.out.println(this.getName()+" se toma el taxi");
        taxi.bajarDelTaxi();
        System.out.println(this.getName()+" se baja del taxi");
    }
}
