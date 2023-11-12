/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.ParqueEj;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int cantPersonas=15;
        Parque parque=new Parque(5);
        Vecino[] vecinos=new Vecino[cantPersonas];
        Visitante[] visitantes=new Visitante[cantPersonas];
        
        for (int i = 0; i < cantPersonas; i++) {
            vecinos[i]=new Vecino(parque,"Vecino "+(i+1));
            vecinos[i].start();
            visitantes[i]=new Visitante(parque,"Visitante"+(i+1));
            visitantes[i].start();
        }
    }
    
}
