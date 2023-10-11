/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto3;

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
    private Semaphore mutex= new Semaphore(1,true);
    //Semáforo genérico
    private Semaphore comederos;
    //Semáforos binarios
    private Semaphore puedeComerPerro = new Semaphore(1);
    private Semaphore puedeComerGato = new Semaphore(0);

    public void comerPerro() {
        try {
            //Entra un perro al comedero
            mutex.acquire();
            perrosEsperando++;
            mutex.release();
            //Si es el turno de los perros
            puedeComerPerro.acquire();            
            //Toma un plato
            mutex.acquire();
            //Se ocupan los platos, si se llenan los platos esperan aquí?
            comederos.acquire();
            perrosEsperando--;
            perrosComiendo++;
            contadorPerros++;                        
            //Quedaría un solo perro esperando adentro como máximo
            if (contadorPerros < limitePerros || gatosEsperando == 0) {
                //Pasa el siguiente perro
                puedeComerPerro.release();
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void dejarComerPerro() {
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedero.class.getName()).log(Level.SEVERE, null, ex);
        }
        comederos.release();
        perrosComiendo--;
        //Si el contador supera el límite, hay gatos esperando para comer y ya se desocuparon todos lso platos,
        //se cambia el turno para los gatos
        if ((contadorPerros >= limitePerros) && (gatosEsperando != 0) && (perrosComiendo==0)) {
                cambiarAnimal();                
        }
        mutex.release();
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
