/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto5Sem;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int cantCanibales=20;
        Olla olla=new Olla(10);
        Cocinero cocinero=new Cocinero(olla,"Cocinero");
        Canibal[] canibales=new Canibal[cantCanibales];
        for (int i = 0; i < cantCanibales; i++) {
            canibales[i]=new Canibal(olla,"Canibal "+(i+1));
            canibales[i].start();
        }
        cocinero.start();
        
    }
    
}
