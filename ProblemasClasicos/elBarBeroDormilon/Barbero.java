/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.ProblemasClasicos.elBarBeroDormilon;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Barbero extends Thread{
    
    private Silla silla;

    public Barbero(Silla silla, String name) {
        super(name);
        this.silla = silla;
    }
    public void run(){
        while(true){
            silla.empezarACortar();//Duerme mientras espera que llegue un cliente
            System.out.println("Babero empieza a cortar");
            try {
                Thread.sleep(2000);//Cortar
            } catch (InterruptedException ex) {
                Logger.getLogger(Barbero.class.getName()).log(Level.SEVERE, null, ex);
            }
            System.out.println("Barbero termina de cortar");
            silla.terminarCortar();
        }
    }
}
