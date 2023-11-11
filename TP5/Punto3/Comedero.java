/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto3;

import java.util.Stack;
import java.util.concurrent.Semaphore;
import programacionconcurrente2023.Color;

/**
 *
 *
 */
public class Comedero {

    //Estructura de pila para representar los platos libres
    private Stack platos;
    private int cantPlatos;
    private Semaphore mutex = new Semaphore(1);
    private Semaphore gato;
    private Semaphore perro;
    private Semaphore plato;
    private int gatosEsperando = 0;
    private int perrosEsperando = 0;
    private int animalesComiendo = 0;
    //Contador de veces seguidas que un perro y gato entran a comer, cuando la otra especie come se reinicia en cero
    private int contGatos = 0;
    private int contPerros = 0;
    //Limite de xx veces seguidaS que puede comer un animal hasta que de deba cambiar el permiso hacia la otra especie en caso de que pida comer
    private int limite;
    boolean liberadoGato;
    boolean liberadoPerro;

    public Comedero(int cantPlatos, int limite) {
        this.cantPlatos = cantPlatos;
        gato = new Semaphore(limite);
        perro = new Semaphore(limite);
        plato = new Semaphore(cantPlatos);
        platos = new Stack();
        for (int i = 0; i < cantPlatos; i++) {
            platos.push(new Object());
        }
        this.limite = limite;
    }

    public Object comerGato() throws InterruptedException {
        //Método para comer un plato, el objecto retornado representaría el plato asignado que se saca de los platos libres
        Object platoElegido;

        mutex.acquire();
        System.out.println(Thread.currentThread().getName() + " intenta entrar");
        gatosEsperando++;
        //El primer animal que llega cuando nadie esta comiendo es quien bloquea a la otra especie
        if (gatosEsperando == 1 && (perrosEsperando + animalesComiendo == 0)) {
            //Bloquear a todos los perros
            System.out.println(Color.RED + "Perros se bloquean");
            if (contPerros >= limite) {
                //Aqui solo tendría un permiso
                System.out.println("Se bloquea un solo permiso");
                System.out.println(perro.availablePermits());
                perro.acquire();
            } else {                
                System.out.println("Se bloquean "+(limite - contPerros)+" permisos");
                System.out.println(perro.availablePermits());
                perro.acquire(limite - contPerros);
            }
            contPerros = 0;
            contGatos = 0;
        }
        mutex.release();

        //Consumir
        gato.acquire();
        plato.acquire();
        mutex.acquire();
        System.out.println(Color.CYAN + Thread.currentThread().getName() + " entra");
        gatosEsperando--;
        animalesComiendo++;
        contGatos++;
        contPerros = 0;
        System.out.println("ContGatos=" + contGatos);
        //Se verifica de a un 
        liberadoGato=false;
        if (contGatos >= limite && perrosEsperando == 0) {
            //cederle lugar a los perros si hay perros esperando después de xx gatos
            System.out.println("Pueden seguir entrando gatos");
            gato.release();
            liberadoGato=true;
        } else if (contPerros >= limite && liberadoGato) {
            System.out.println("Tomar permiso remanente de PERRO: "+perro.availablePermits());
            perro.acquire();            
        }
        contPerros = 0;
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
        if (perrosEsperando == 1 && (animalesComiendo + gatosEsperando) == 0) {
            System.out.println(Color.RED + "Gatos se bloquean");
            if (contGatos >= limite) {
                System.out.println("Se bloquea un solo permiso");
                System.out.println(gato.availablePermits());
                gato.acquire();
            } else {
                System.out.println("Se bloquean" +(limite - contGatos)+" permisos");
                System.out.println(perro.availablePermits());
                gato.acquire(limite - contGatos);
            }
            contGatos = 0;
            contPerros = 0;
        }
        mutex.release();

        //Consumir
        perro.acquire();
        plato.acquire();
        mutex.acquire();
        System.out.println(Color.CYAN + Thread.currentThread().getName() + " entra");
        // perrosComiendo++; 
        perrosEsperando--;
        animalesComiendo++;
        platoElegido = platos.peek();
        platos.pop();
        contPerros++;
        liberadoPerro=false;
        System.out.println("ContPerros=" + contPerros);
        if (contPerros >= limite && gatosEsperando == 0) {
            //cederle lugar a los gatos si hay gatos esperando  despues de xx  perros
            System.out.println("Pueden seguir entrando perros");
            perro.release();
            liberadoPerro=true;
        } else if (contGatos >= limite && liberadoPerro) {
            System.out.println("Tomar permiso remanente de GATO: "+gato.availablePermits());
            gato.acquire();            
        }
        contGatos = 0;
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
        if (animalesComiendo == 0 && (gatosEsperando + perrosEsperando) == 0) {
            perro.release(this.limite);
            //Gato tendría todos sus permisos disponibles
        } else if (contGatos < limite) {
            if (gatosEsperando == 0) {
                if (perrosEsperando > 0 && animalesComiendo == 0) {
                    //switch a perros
                    gato.acquire(limite - contGatos);
                    perro.release(limite);                    
                }
                //si no hay gatos esperando, que sigan consumiendo sus permisos
            }
        } else {
            if (perrosEsperando > 0 && animalesComiendo == 0) {
                //switch perros
                perro.release(this.limite);                
            }
            //Si siguen comiendo, que se vayan
        }
        this.plato.release();
        mutex.release();
    }

    public void salirPerro(Object plato) throws InterruptedException {
        //Método que devuelve el plato que llega por parámetro a la pila de platos libres
        mutex.acquire();
        animalesComiendo--;
        System.out.println(Color.YELLOW + Thread.currentThread().getName() + " sale");
        platos.push(plato);
        //Si xx cantidad de gatos ya han comido y hay perros esperando, no liberar un permiso para los otros gatos
        if (animalesComiendo == 0 && (gatosEsperando + perrosEsperando) == 0) {
            //pueden entrar gatos
            gato.release(this.limite);
            //Perro tendría todos sus permisos
        } else if (contPerros < limite) {
            if (perrosEsperando == 0) {
                if (gatosEsperando > 0 && animalesComiendo == 0) {
                    perro.acquire(limite - contPerros);
                    //Bloquear a gatos
                    gato.release(limite);
                }
            }
        } else {
            if (gatosEsperando > 0 && animalesComiendo == 0) {
                gato.release(this.limite);
            }
        }
        this.plato.release();
        mutex.release();

    }

}
