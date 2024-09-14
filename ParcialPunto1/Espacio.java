/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.ParcialPunto1;

import java.util.concurrent.Semaphore;

/**
 *
 * @author ulisescorrales
 */
public class Espacio {
    //Objeto compartido entre Oxígeno e hidrógeno

    private int entra = 0;
    private Semaphore terminar = new Semaphore(0);
    private Semaphore oxigeno = new Semaphore(0);
    private Semaphore mutex = new Semaphore(1);
    private Semaphore hidrogeno = new Semaphore(0);
    private int esperaH = 0;
    //cada 10 se vacía
    private int cantAgua = 0;
    private Semaphore entrar = new Semaphore(3);

    //Método para oxígeno
    public boolean OListo() throws InterruptedException {

        boolean resp = false;
        mutex.acquire();
        oxigeno.release(2);
        mutex.release();

        hidrogeno.acquire();
        entrar.acquire();
        mutex.acquire();
        entra++;
        if (entra == 1) {
            //Solo el primer átomo en entrar (oxígeno o hidrógeno) tiene la responsabilidad de 
            //sumar agua en el contador del recipiente, el resto no suma
            resp = true;
        } else if (entra == 3) {
            entra = 0;
            //Permitir que otros tres átomo empiecen a formar agua
            entrar.release(3);
        }
        System.out.println("Entra oxígeno a formar agua");
        mutex.release();
        return resp;
    }

    //Método para hidrógeno
    public boolean HListo() throws InterruptedException {
        boolean resp = false;
        mutex.acquire();
        esperaH++;
        //Cada dos hidrógenos se liberan tres permisos, dos para hidrógeno y uno para oxígeno
        if (esperaH == 2) {
            hidrogeno.release(3);
            esperaH = 0;
        }
        mutex.release();

        oxigeno.acquire();
        hidrogeno.acquire();
        entrar.acquire();
        mutex.acquire();
        entra++;
        if (entra == 1) {
            resp = true;
        } else if (entra == 3) {
            //Solo el primer átomo en entrar (oxígeno o hidrógeno) tiene la responsabilidad de 
            //sumar agua en el contador del recipiente, el resto no suma
            entra = 0;
            //Permitir que otros tres átomo empiecen a formar agua
            entrar.release(3);
        }
        System.out.println("Entra oxígeno a formar agua");
        mutex.release();
        return resp;
    }

    //Método para oxígeno e hidrógeno
    public void formarAgua(boolean resp) throws InterruptedException {
        mutex.acquire();
        if (resp) {
            cantAgua++;
            System.out.println("Se formó agua");
            if (cantAgua == 10) {
                //Vaciar el recipiente
                System.out.println("Se vacía el recipiente");
                cantAgua = 0;
            }
        }
        mutex.release();
    }
}
