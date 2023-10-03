/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP5.Punto1;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Lugar {
    //Ahora hay 2 mozos activos
    private Semaphore esperar=new Semaphore(2);
    private Semaphore hayCliente=new Semaphore(0);
    private Semaphore servido=new Semaphore(0);
    private Semaphore sacarPlato=new Semaphore(0);
    
    public void pedirPlato(){
        try {
            esperar.acquire();
            hayCliente.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Lugar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void buscarPlato(){
        try {
            hayCliente.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Lugar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void servir(){
        servido.release();
    }
    public void comer(){
        try {
            servido.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(Lugar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void terminarComer(){
        sacarPlato.release();
    }
    public void volverAEsperar(){
        try {
            sacarPlato.acquire();
            esperar.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Lugar.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
