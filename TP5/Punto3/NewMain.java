/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto3;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int cantGatos=7,cantPerros=7;
        Perro[] perros=new Perro[cantPerros];
        Gato[] gatos=new Gato[cantGatos];
        
        Comedero comedero=new Comedero(5);
        
        for (int i = 0; i < cantPerros; i++) {
            perros[i]=new Perro(comedero,"Perro "+(i+1));
            perros[i].start();
            gatos[i]=new Gato(comedero,"Gato"+(i+1));
            gatos[i].start();
        }
        /*for (int i = 0; i < cantGatos; i++) {
            gatos[i]=new Gato(comedero,"Gato"+(i+1));
            gatos[i].start();
        }*/
    }
    
}
