/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto8;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;
import programacionconcurrente2023.Color;

/**
 *
 * @author ulisescorrales
 */
public class ControladorProduccion {

    private char lineaActiva = 'm';//e es electrico,m es mecanico
    private int capacidad;   
    private int contProductos = 0;
    private boolean cambiar=false;
    private Semaphore mutex = new Semaphore(1);    
    private Semaphore mecanico = new Semaphore(1);
    private Semaphore electrico = new Semaphore(0);
    private Semaphore puedeAlternar=new Semaphore(1);
    
    public ControladorProduccion(int capacidad) {
        this.capacidad = capacidad;
    }

    public void alternarLineas() {
        try {
            mutex.acquire();
            //Avisar que se quiere alternar las líneas
            cambiar=true;    
            mutex.release();
            System.out.println(Color.CYAN+"Se pide alternar línea");
            puedeAlternar.acquire();
            //Intercambiar linea activa            
            if (lineaActiva == 'e') {
                lineaActiva = 'm';     
                System.out.println(Color.CYAN+"Alternando Línea a MECÁNICO");
                mecanico.release();
            } else {
                lineaActiva = 'e';
                System.out.println(Color.CYAN+"Alternando Línea a ELÉCTRICO");
                electrico.release();
            }     
            puedeAlternar.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void llegaElectrico() {
        try {
            electrico.acquire();
            if(contProductos==0){                
                puedeAlternar.acquire();
                System.out.println("Se prohibe alternar");
            }
            System.out.println(Color.GREEN+"Llega "+Thread.currentThread().getName());
            mutex.acquire();
            contProductos++;
            //Si no hay capacidad o se quiere alternar líneas, no se elimina un permiso para el siguiente prod. eléctrico            
            if ((contProductos < capacidad) && !cambiar) {
                electrico.release();
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void saleElectrico(){
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }        
        contProductos--;             
        if(contProductos==0){
            System.out.println("Puede alternar");
            //Si ya no quedan productos en línea, puede alternar
            puedeAlternar.release();
        }else if(contProductos==capacidad-1 && !cambiar){
            electrico.release();
        }
        mutex.release();
    }

    public void llegaMecanico() {
        try {
            mecanico.acquire();
            if(contProductos==0){                
                puedeAlternar.acquire();
                System.out.println("Se prohibe alternar");
            }
            mutex.acquire();
            contProductos++;
            //Si no hay capacidad, no se elimina un permiso para el siguiente prod. eléctrico            
            if ((contProductos < capacidad) && !cambiar) {
                mecanico.release();
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void saleMecanico() {
        try {
            mutex.acquire();
        } catch (InterruptedException ex) {
            Logger.getLogger(ControladorProduccion.class.getName()).log(Level.SEVERE, null, ex);
        }        
        contProductos--;             
        if(contProductos==0){
            System.out.println("Puede alternar");
            //Si ya no quedan productos en línea, puede alternar
            puedeAlternar.release();
        }else if(contProductos==capacidad-1 && !cambiar){
            mecanico.release();
        }
        mutex.release();
    }
}
