/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto4;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Cliente extends Thread{
    GestorImpresora gi;

    public Cliente(GestorImpresora gi, String name) {
        super(name);
        this.gi = gi;
    }
    
    
    public void run(){
        int impresoraAsignada;
        impresoraAsignada=gi.imprimir();
        System.out.println(this.getName()+" está imprimiendo");
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Cliente.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(this.getName()+" termina de imprimir");
        gi.terminarImprimir(impresoraAsignada);
    }
}
