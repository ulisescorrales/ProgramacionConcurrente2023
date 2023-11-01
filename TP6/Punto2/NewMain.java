/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP6.Punto2;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        int cantEstudiantes=20;
        Estudiante[] estudiantes=new Estudiante[cantEstudiantes];
        Sala sala=new Sala();
        
        for (int i = 0; i < cantEstudiantes; i++) {
            estudiantes[i]=new Estudiante(sala,"Estudiante "+(i+1));
            estudiantes[i].start();
        }
    }
    
}
