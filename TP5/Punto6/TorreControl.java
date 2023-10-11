/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto6;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class TorreControl {

    private Semaphore usarPista = new Semaphore(1);
    private Semaphore noHayAterrizar = new Semaphore(0);
    private Semaphore noHayDespegar=new Semaphore(1);
    private int contAterrizajes = 0;
    private int esperandoAterrizar = 0;
    private int esperandoDespegar = 0;

    public void permitirAterrizar() {
        try {
            esperandoAterrizar++;
            System.out.println(Thread.currentThread().getName()+" pide aterrizar");
            //Se bloquea si hubieron 10 aterrizajes y un avión quiere despegar
            noHayDespegar.acquire();
            usarPista.acquire();
            System.out.println(Thread.currentThread().getName()+" está aterrizando");
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
     public void permitirDespegar() {
        try {
            //Antes de pedir la pista, espera a que los aviones aterricen
            esperandoDespegar++;
            System.out.println(Thread.currentThread().getName()+" pide despegar");
            if (esperandoAterrizar > 0 && contAterrizajes < 10) {
                noHayAterrizar.acquire();
            }
            usarPista.acquire();
            System.out.println(Thread.currentThread().getName()+" está despegando");
        } catch (InterruptedException ex) {
            Logger.getLogger(TorreControl.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
    public void terminarAterrizar() {
        System.out.println(Thread.currentThread().getName()+" termina de aterrizar");
        usarPista.release();
        esperandoAterrizar--;
        contAterrizajes++;
        if(contAterrizajes<10 || esperandoDespegar==0){
            noHayDespegar.release();
        }
        //Si ya terminaron de aterrizar y había un avión esperando a despegar
        if (esperandoAterrizar == 0 && esperandoDespegar > 0) {
            noHayAterrizar.release();
        }
    }  

    public void terminarDespegar() {
        System.out.println(Thread.currentThread().getName()+" termina de despegar");
        esperandoDespegar--;
        usarPista.release();
    }
}
