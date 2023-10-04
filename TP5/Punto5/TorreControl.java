/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto5;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class TorreControl {

    private Semaphore usarPista = new Semaphore(1);
    private Semaphore puedeDespegar = new Semaphore(1);
    private Semaphore puedeAterrizar=new Semaphore(1);
    private Semaphore cantAterrizajes=new Semaphore(0);
    private int contAterrizajes = 0;
    private int esperandoAterrizar = 0;
    private int esperandoDespegar = 0;

    public void permitirAterrizar() {
        try {
            esperandoAterrizar++;
            System.out.println(Thread.currentThread().getName()+" pide aterrizar");
            //Se bloquea si hubieron 10 aterrizajes y un avión quiere despegar
            //puedeAterrizar.acquire();
            puedeDespegar.acquire();
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
            puedeDespegar.acquire();
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
        //Si no hay aviones que quieran aterrizar, puede despegar un avión
        if(esperandoAterrizar==0){
            puedeDespegar.release();            
        }else if(contAterrizajes>=10){
            //Si se contaron 10 aterrizajes, se libera un permiso para despegar y se reinicia el contador
            puedeDespegar.release();
            contAterrizajes=0;
        }
    }  

    public void terminarDespegar() {
        System.out.println(Thread.currentThread().getName()+" termina de despegar");
        esperandoDespegar--;        
        usarPista.release();
        //Si no hay aviones que quieran aterrizar, puede despegar un avión
        if(esperandoAterrizar==0){
            puedeDespegar.release();
        }
    }
}
