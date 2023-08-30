/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP3.Punto6;

/**
 *
 * @author ulisescorrales
 */
public class Arreglo {
    private int[] arreglo;
    private int resultado=0;

    public Arreglo(int[] arreglo) {
        this.arreglo = arreglo;
    }
    
    //Es necesario que sea sincronizado?
    public int getNum(int i){
        return arreglo[i];
    }
    public synchronized void setResultado(int resParcial){        
        resultado+=resParcial;        
    }
    public int getResultado(){
        return this.resultado;
    }
}
