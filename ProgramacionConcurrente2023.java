/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023;

import programacionconcurrente2023.TP2.Punto1.Recurso;

/**
 *
 * @author ulisescorrales
 */
public class ProgramacionConcurrente2023 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Recurso unRecurso= new Recurso();
        Thread t=new Thread(unRecurso);        
        
    }
    
}
