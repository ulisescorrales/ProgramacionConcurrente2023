/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto4Sem;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int cantPersonas=30;
        Persona[] personas=new Persona[cantPersonas];
        Sala sala=new Sala();
        
        for (int i = 0; i < cantPersonas; i++) {
            personas[i]=new Persona(sala,"Persona "+(i+1));
            personas[i].start();            
        }
        
    }
    
}
