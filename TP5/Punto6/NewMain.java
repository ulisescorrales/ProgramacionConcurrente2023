/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto6;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int cantDespegar=20;
        int cantAterrizar=11;
        
        TorreControl torre=new TorreControl();
        AvionAterrizar[] aterrizar=new AvionAterrizar[cantAterrizar];
        AvionDespegar[] despegar=new AvionDespegar[cantDespegar];
        
        for (int i = 0; i < cantAterrizar; i++) {            
            aterrizar[i]=new AvionAterrizar(torre,"AviónA "+(i+1));
            aterrizar[i].start();            
        }
        for (int i = 0; i < cantDespegar; i++) {
            despegar[i]=new AvionDespegar(torre,"AviónD "+(i+1));
            despegar[i].start();
        }
    }
    
}
