/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto1Locks;

import programacionconcurrente2023.TP8.Punto1Sem.Soldado;
import programacionconcurrente2023.TP8.Punto1Sem.Sala;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int cantSoldados=20;
        Sala sala=new Sala();
        Soldado[] soldados=new Soldado[cantSoldados];
        for (int i = 0; i < cantSoldados; i++) {
            soldados[i]=new Soldado(sala,"Soldado "+(i+1));
            soldados[i].start();
        }
    }
    
}
