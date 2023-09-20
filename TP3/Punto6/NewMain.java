/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP3.Punto6;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        long t1=System.currentTimeMillis();
        int longitud=50000;
        int [] arreglo=new int[longitud];
        int i;
        byte cantSumadores=3;
        Random num=new Random();
        Sumador[] sumadores=new Sumador[cantSumadores];
                
        for (i = 0; i < longitud; i++) {
            arreglo[i]=num.nextInt(1,11);
        }
        Arreglo arr=new Arreglo(arreglo);
        
        int div=longitud/cantSumadores;
        int resto=longitud%cantSumadores;
        
        for (i = 0; i < cantSumadores-1; i++) {            
            sumadores[i]=new Sumador(arr,div*i,div*(i+1)-1,"Sumador "+(i+1));
            sumadores[i].start();
        }                        
        sumadores[cantSumadores-1]=new Sumador(arr,div*i,div*(i+1)-1+resto,"Sumador "+(i+1));
        sumadores[cantSumadores-1].start();
        
        int suma=0;
        for (i = 0; i < longitud; i++) {
            suma=suma+arreglo[i];
        }
        long t2=System.currentTimeMillis();
        System.out.println("Tiempo: "+(t2-t1));
        //Join
        for (i = 0; i < cantSumadores; i++) {
            try {
                sumadores[i].join();
            } catch (InterruptedException ex) {
                Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        //Mostrar resultados finales
        System.out.println("Suma concurrente: "+arr.getResultado());        
    }
    
}
