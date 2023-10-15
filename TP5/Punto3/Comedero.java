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

    private int cantPlatos;
    private Stack platosLibres;

    private int limite;
    private int contadorPerros = 0;
    private int contadorGatos = 0;

    private int gatosEsperando = 0;
    private int perrosEsperando = 0;

    //Sem genéricos
    private Semaphore puedeComerPerro;
    private Semaphore puedeComerGato;
    //Sem bianario
    private Semaphore mutex = new Semaphore(1);

    public Comedero(int cantPlatos, int limite) {
        platosLibres = new Stack();
        for (int i = 0; i < cantPlatos; i++) {
            platosLibres.add(new Object());
        }
        //Inicializa con perros queriendo comer        
        this.limite = limite;
        this.cantPlatos = cantPlatos;
        puedeComerPerro = new Semaphore(cantPlatos);
        puedeComerGato = new Semaphore(cantPlatos);
    }

    //Un animal sería consumidor cuando toma el plato y luego productor cuando devuelve el plato
    public Object comerPerro() throws InterruptedException {
        Object platoASacar;
        //Entra un perro al comedero
        mutex.acquire();
        perrosEsperando++;
        System.out.println(Thread.currentThread().getName()+" espera");
        if (perrosEsperando == 1 && esTurnoParaGatos()) {
            //Autbloquearse para cederle lugar a los gatos
            System.out.println(Color.RED+Thread.currentThread().getName()+" bloquea a todos los perros");
            puedeComerPerro.acquire(puedeComerPerro.availablePermits());            
        }
        mutex.release();
        //Si es el turno de los perros, pide el permiso
        puedeComerPerro.acquire();
        mutex.acquire();
        System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra");
        perrosEsperando--;
        //Bloquear a los gatos si nadie está usando un plato, y reiniciarles el contador
        if (platosLibres.size() == this.cantPlatos) {
            if (!esTurnoParaPerros()) {
                puedeComerGato.acquire(this.cantPlatos);                                
            }//Reiniciar contador del otro animal
            contadorGatos=0;
        }
        //Tomar un plato libre
        platoASacar = platosLibres.peek();
        platosLibres.pop();

        //Verificar contador y límite
        contadorPerros++;
        mutex.release();

        return platoASacar;
    }

    private boolean esTurnoParaGatos() {
        return gatosEsperando > 0 && contadorPerros >= limite;
    }

    public boolean esTurnoParaPerros() {
        return perrosEsperando > 0 && contadorGatos >= limite;
    }

    public void dejarComerPerro(Object platoARetornar) {
        //cuando reiniciar el contador?
        try {
            mutex.acquire();
            //Devolver el plato a la pila de platos libres
            platosLibres.push(platoARetornar);
            System.out.println(Color.YELLOW+Thread.currentThread().getName()+" deja el plato");
            if(!esTurnoParaGatos()){
                puedeComerPerro.release();
            }
            if (this.platosLibres.size() == this.cantPlatos) {
                //Si ya fueron devueltos todos los platos, los gatos pueden comer
                puedeComerGato.release(this.cantPlatos);                
                System.out.println(Color.GREEN+"Pueden comer los gatos");
            }            
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   

    public Object comerGato() throws InterruptedException{
        Object platoASacar;
        //Entra un gato al comedero
        mutex.acquire();
        gatosEsperando++;
        System.out.println(Thread.currentThread().getName()+" espera");
        if (gatosEsperando == 1 && esTurnoParaPerros()) {
            //Autbloquearse para cederle lugar a los perros
            puedeComerGato.acquire(puedeComerGato.availablePermits());
            System.out.println(Color.RED+Thread.currentThread().getName()+" bloquea a todos los gatos");
        }
        mutex.release();
        //Si es el turno de los gatos, pide el permiso
        puedeComerGato.acquire();
        mutex.acquire();
        System.out.println(Color.CYAN+Thread.currentThread().getName()+" entra");
        
        contadorGatos++;
        gatosEsperando--;
        
        //Bloquear a los gatos si nadie está usando un plato, y reiniciarles el contador
        if (platosLibres.size() == this.cantPlatos) {
            System.out.println(esTurnoParaGatos());
            if (!esTurnoParaGatos()) {
                //Bloquear a los perros                
                puedeComerPerro.acquire(this.cantPlatos);     
            }//Reiniciar contador del otro animal
            contadorPerros=0;
        }
        //Tomar un plato libre
        platoASacar = platosLibres.peek();
        platosLibres.pop();
                
        mutex.release();

        return platoASacar;
    }
    public void dejarComerGato(Object platoARetornar) {
        //cuando reiniciar el contador?
        try {
            mutex.acquire();
            //Devolver el plato a la pila de platos libres
            platosLibres.push(platoARetornar);
            System.out.println(Color.YELLOW+Thread.currentThread().getName()+" deja el plato");
            if(!esTurnoParaGatos()){
                puedeComerGato.release();
            }
            if (this.platosLibres.size() == this.cantPlatos) {
                //Si ya fueron devueltos todos los platos, los gatos pueden comer
                puedeComerPerro.release(this.cantPlatos);    
                System.out.println(Color.GREEN+"Pueden comer los perros");
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Comedero.class.getName()).log(Level.SEVERE, null, ex);
        }
    }   
}
