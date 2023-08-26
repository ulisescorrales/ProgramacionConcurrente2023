/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP3.Punto2;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Energia e=new Energia();
        
        CriaturaOscura co=new CriaturaOscura(e,"Criatura Oscura");
        Sanador s=new Sanador(e,"Sanador");
        
        co.start();
        s.start();
    }
    
}
