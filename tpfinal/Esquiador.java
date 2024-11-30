/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacionconcurrente2023.tpfinal;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulises.corrales
 */
public class Esquiador extends Thread{
    private MedioElevacion[] medios;
    private CabinaInstructores cabina;
    private Confiteria confiteria;

    public Esquiador(MedioElevacion[] medios) {
        this.medios = medios;
    }
    
    public void run(){
        Random ran=new Random();
        int num;
        while(true){
            num=ran.nextInt(3);
            switch(num){
                case 0:
                    esquiar();
                    //esquiar
                    break;
                case 1:
                    tomarClase();
                    //tomar clases de esquí
                    break;
                case 2:
                    //ir a la confitería
                    break;
            }
        }
    }
    private void esquiar(){
        Random ran=new Random();
        int num;
        num=ran.nextInt(medios.length);
        MedioElevacion medioElegido=this.medios[num];
        
        medioElegido.subir();
        //Cuando el controlador del medio lo lleva a la cima puede bajar
        medioElegido.bajar();
        try {
            //Simular que esquia
            Thread.sleep(5000);
        } catch (InterruptedException ex) {
            Logger.getLogger(Esquiador.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    private void tomarClase(){
        boolean pudoComenzar;
        pudoComenzar=this.cabina.entrarClase();
        if(pudoComenzar){
            
        }
    }
}
