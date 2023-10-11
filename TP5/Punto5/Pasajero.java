/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacionconcurrente2023.TP5.Punto5;

public class Pasajero extends Thread{
    private Tren tren;
    private MaquinaExpendedora me;

    public Pasajero(Tren tren, MaquinaExpendedora me, String name) {
        super(name);
        this.tren = tren;
        this.me = me;
    }
      
    
    public void run(){
    /*Los pasajeros pueden subir repetidamente al tren, para ello antes
    *deben comprar un ticket por cada viaje*/
        while(true){
            me.comprarTicket();
            tren.subir();        
            tren.bajar();        
        }        
    }
   
}
