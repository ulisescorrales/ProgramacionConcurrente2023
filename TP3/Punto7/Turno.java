/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP3.Punto7;

/**
 *
 * @author ulisescorrales
 */
public class Turno {
    private int turno=0;
    
    public synchronized boolean getTurno(int turno){
        boolean exito=false;
        if(this.turno==turno){
            exito=true;
            this.turno++;        
            this.turno=this.turno%3;
        }
        return exito;
    }
}