/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP7.Punto1;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        int cant=15;
        Sala sala=new Sala();
        Persona[] p=new Persona[cant];
        Jubilado[]j=new Jubilado[cant];
        
        for (int i = 0; i < cant; i++) {
            p[i]=new Persona(sala,"Persona "+(i+1));
            p[i].start();
            j[i]=new Jubilado(sala,"Jubilado "+(i+1));
            j[i].start();
        }
    }
    
}
