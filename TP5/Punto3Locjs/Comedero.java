/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto3Locjs;

import java.util.Stack;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import programacionconcurrente2023.Color;

/**
 *
 *
 */
public class Comedero {

    //Estructura de pila para representar los platos libres
    private Stack platos;
    private int cantPlatos;    
    private int gatosEsperando = 0;
    private int perrosEsperando = 0;    
    //Contador de veces seguidas que un perro y gato entran a comer, cuando la otra especie come se reinicia en cero
    private int contGatos = 0;
    private int contPerros = 0;
    //Limite de xx veces seguidaS que puede comer un animal hasta que de deba cambiar el permiso hacia la otra especie en caso de que pida comer
    private int limite;
    private int perrosAdentro;
    Lock lock = new ReentrantLock();
    Condition gatos = lock.newCondition();
    Condition perros = lock.newCondition();
    private int gatosAdentro = 0;

    public Comedero(int cantPlatos, int limite) {
        this.cantPlatos = cantPlatos;        
        platos = new Stack();
        for (int i = 0; i < cantPlatos; i++) {
            platos.push(new Object());
        }
        this.limite = limite;
    }

    public Object comerGato() throws InterruptedException {
        Object retornar;
        lock.lock();
        gatosEsperando++;        
        while (perrosAdentro > 0 || (contGatos >= limite && perrosEsperando > 0) || platos.isEmpty()) {            
            System.out.println("gato intenta entrar");
            gatos.await();
        }
        gatosEsperando--;
        System.out.println(Color.CYAN+Thread.currentThread().getName() + " entra");
        contGatos++;
        cantPlatos--;
        gatosAdentro++;
        System.out.println("ContGatos: "+this.contGatos);
        contPerros=0;
        retornar = platos.peek();
        platos.pop();
        lock.unlock();
        return retornar;
    }

    public Object comerPerro() throws InterruptedException {
        Object retornar;
        lock.lock();
        perrosEsperando++;
        while (gatosAdentro > 0 || (contPerros >= limite && gatosEsperando > 0) || platos.isEmpty()) {            
            System.out.println("perro intenta entrar");
            perros.await();
        }
        perrosEsperando--;
        System.out.println(Color.GREEN+Thread.currentThread().getName() + " entra");
        contPerros++;
        System.out.println("ContGatos: "+this.contGatos);
        cantPlatos--;
        perrosAdentro++;
        contGatos=0;
        retornar = platos.peek();
        platos.pop();
        lock.unlock();
        return retornar;
    }

    public void salirGato(Object plato) throws InterruptedException {
        lock.lock();
        gatosAdentro--;
        platos.push(plato);
        System.out.println(Color.YELLOW+Thread.currentThread().getName()+" sale");
        //Si el límite no se sobrepaso y al mismo timpo no hay perros
        if (!(contGatos >= limite && perrosEsperando > 0) && gatosAdentro>0) {
            gatos.signal();
        } else if (gatosAdentro == 0) {
            perros.signalAll();
            //Reiniciar            
        }
        lock.unlock();
    }

    public void salirPerro(Object plato) throws InterruptedException {
        lock.lock();
        perrosAdentro--;
        platos.push(plato);
        //Si el límite no se sobrepaso y al mismo timpo no hay perros
        System.out.println(Color.YELLOW+Thread.currentThread().getName()+" sale");
        if (!(contPerros >= limite && gatosEsperando > 0) && perrosAdentro>0) {
            perros.signal();
        } else if (perrosAdentro == 0) {
            gatos.signalAll();                        
        }
        lock.unlock();
    }
    

}
