/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto3;

import java.util.Stack;
import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class Comedero {

//Estructura de pila para representar los platos libres
    private Stack platos;
    private int cantPlatos;
    private Semaphore mutex = new Semaphore(1);
    private Semaphore gato;
    private Semaphore perro;    
    private int gatosEsperando = 0;
    private int perrosEsperando = 0;
    private int animalesComiendo = 0;
    //Contador de veces seguidas que un perro y gato entran a comer, cuando la otra especie come se reinicia en cero
    private int contGatos = 0;
    private int contPerros = 0;
//Limite de xx veces seguidaS que puede comer un animal hasta que de deba cambiar el permiso hacia la otra especie en caso de que pida comer
    private int limite;

    public Comedero(int cantPlatos, int limite) {
        this.cantPlatos = cantPlatos;
        gato = new Semaphore(cantPlatos);
        perro = new Semaphore(cantPlatos);
        platos = new Stack();
        for (int i = 0; i < cantPlatos; i++) {
            platos.push(new Object());
        }
this.limite=limite;
    }

    public Object comerGato() throws InterruptedException {
//Método para comer un plato, el objecto retornado representaría el plato asignado que se saca de los platos libres
        Object platoElegido;

        mutex.acquire();
        System.out.println(Thread.currentThread().getName() + " intenta entrar");
        gatosEsperando++;
//El primer animal que llega cuando nadie esta comiendo es quien bloquea a la otra especie
        if (gatosEsperando == 1 && (perrosEsperando == 0 || animalesComiendo==0)) {
//Bloquear a todos los perros
            System.out.println(Color.RED + "Perros se bloquean");
            perro.acquire(cantPlatos);
            contPerros = 0;
        }
        mutex.release();

        //Consumir
        gato.acquire();

        mutex.acquire();
        System.out.println(Color.CYAN + Thread.currentThread().getName() + " entra");
        //  gatosComiendo++;   
        animalesComiendo++;
        contGatos++;
        System.out.println("ContGatos=" +contGatos);
        if (contGatos >= limite && perrosEsperando > 0) {
            //cederle lugar a los perros si hay perros esperando después de xx gatos
            System.out.println("Cederle lugar a los GATOS");
            gato.acquire(gato.availablePermits());
        }
        gatosEsperando--;
        platoElegido = platos.peek();
        platos.pop();

        mutex.release();
        return platoElegido;
    }

    public Object comerPerro() throws InterruptedException {
//Método para comer un plato, el objecto retornado representaría el plato asignado que se saca de los platos libres
        Object platoElegido;

        mutex.acquire();

        System.out.println(Thread.currentThread().getName() + " intenta entrar");
        perrosEsperando++;
        if (perrosEsperando == 1 && (animalesComiendo == 0 || gatosEsperando==0)) {
            System.out.println(Color.RED + "Gatos se bloquean");
            gato.acquire(cantPlatos);
            contGatos = 0;
        }
        mutex.release();

        //Consumir
        perro.acquire();
        mutex.acquire();
        System.out.println(Color.CYAN + Thread.currentThread().getName() + " entra");
        // perrosComiendo++; 
        perrosEsperando--;
        animalesComiendo++;
        platoElegido = platos.peek();
        platos.pop();

        contPerros++;
        System.out.println("ContPerros=" +contPerros);
        if (contPerros >= limite && gatosEsperando > 0) {
            //cederle lugar a los gatos si hay gatos esperando  despues de xx  perros
            System.out.println("Cederle lugar a los perros");
            perro.acquire(perro.availablePermits());
        }
        mutex.release();
        return platoElegido;
    }

    public void salirGato(Object plato) throws InterruptedException {
//Método que devuelve el plato que llega por parámetro a la pila de platos libres
        mutex.acquire();
        animalesComiendo--;
        System.out.println(Color.YELLOW + Thread.currentThread().getName() + " sale");
        platos.push(plato);
//Si xx cantidad de gatos ya han comido y hay perros esperando, no liberar un permiso para los otros gatos
        if (!(contGatos >= limite && perrosEsperando > 0)) {
            gato.release();
        }
//Desbloquear los permisos para los perros si no hay nadie comiendo
        if (animalesComiendo == 0) {
            perro.release(cantPlatos);
            if(perrosEsperando>0){
                contPerros=0;
            }
        }
        mutex.release();
    }

    public void salirPerro(Object plato) throws InterruptedException {
        mutex.acquire();
        animalesComiendo--;
        System.out.println(Color.YELLOW + Thread.currentThread().getName() + " sale");
        platos.push(plato);
//Si xx cantidad de perros han comido y hay gatos esperando entonces no seguir liberando permisos para los perros
        if (!(contPerros >= limite && gatosEsperando > 0)) {
            perro.release();
        }
//Desbloquear a los gatos si nadie esta comiendo
        if (animalesComiendo == 0) {
            gato.release(cantPlatos);
            if(gatosEsperando>0){
                contGatos=0;
            }
        }
        mutex.release();
    }

}
