/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto2;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Comedero {
    
    private int limiteGatos;
    private int contComederos;
    private int limitePerros;
    private int contadorPerros = 0;
    private int gatosEsperando = 0;
    private int perrosEsperando = 0;
    private int perrosComiendo=0;
    private int contadorGatos = 0;
    private char animalActivo = 'p';//g para gato    
    //Semáforo genérico
    private Semaphore comederos;
    //Semáforos binarios
    private Semaphore puedeComerPerro = new Semaphore(1);
    private Semaphore puedeComerGato = new Semaphore(0);

    public void comerPerro() {
        try {
            //Entra un perro al comedero
            perrosEsperando++;
            //Si es el turno de los perros
            puedeComerPerro.acquire();            
            //Toma un plato
            comederos.acquire();
            contComederos--;
            perrosEsperando--;
            contadorPerros++;
            perrosComiendo++;
            //Quedaría un solo perro esperando adentro como máximo
            if (contadorPerros < limitePerros || gatosEsperando == 0) {
                //Pasa el siguiente perro
                puedeComerPerro.release();
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void dejarComerPerro() {
        comederos.release();
        perrosComiendo--;
        //Si el contador supera el límite, hay gatos esperando para comer y ya se desocuparon todos lso platos,
        //se cambia el turno para los gatos
        if ((contadorPerros >= limitePerros) && (gatosEsperando != 0) && (perrosComiendo==0)) {
                cambiarAnimal();                
        }
    }

    public void cambiarAnimal() {
        if (animalActivo == 'p') {
            contadorPerros=0;
            puedeComerGato.release();
            animalActivo = 'g';
        } else {
            contadorGatos=0;
            puedeComerPerro.release();
            animalActivo = 'p';
        }
    }
    

    public void comerGato() {
        try {
            puedeComerGato.acquire();
            comederos.acquire();
            puedeComerGato.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
