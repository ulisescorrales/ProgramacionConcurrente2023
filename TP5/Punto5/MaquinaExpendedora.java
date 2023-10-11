/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacionconcurrente2023.TP5.Punto5;

public class MaquinaExpendedora {
    private int cantVendidos=0;
    
    public synchronized void comprarTicket(){
    //Método sincronizado que lleva la cuenta del total de tickets vendidos        
        cantVendidos++;
        System.out.println("Pasajero"+Thread.currentThread().getName()+" compra el ticket Nº" +cantVendidos);
    }
}
