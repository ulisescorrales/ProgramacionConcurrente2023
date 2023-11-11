/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto3;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 *
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int cantAnimales = 7;
        Perro[] perros = new Perro[cantAnimales];
        Gato[] gatos = new Gato[cantAnimales];

        Comedero comedero = new Comedero(5, 6);

//Se prueba con inicializar y comenzar los hilos de ambas especies intercaladamente para verificar el bloqueo entre ambas especies
        for (int i = 0; i < cantAnimales; i++) {
            perros[i] = new Perro(comedero, "Perro " + (i + 1));
            perros[i].start();                        
            gatos[i] = new Gato(comedero, "Gato" + (i + 1));
            gatos[i].start();        
        }
                

    }

}
