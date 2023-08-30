/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP3.Punto6;

/**
 *
 * @author ulisescorrales
 */
public class Sumador extends Thread{
    Arreglo arreglo;
    int limInf;
    int limSup;    ;
    
    public Sumador(Arreglo arreglo, int limInf, int limSup, String name) {
        super(name);
        this.arreglo = arreglo;
        this.limInf = limInf;
        this.limSup = limSup;        
    }
    
    public void run(){                        
        int temp=0;
        for (int i = limInf; i <= limSup ; i++) {
            temp+=arreglo.getNum(i);            
        }
        arreglo.setResultado(temp);
    }
}
