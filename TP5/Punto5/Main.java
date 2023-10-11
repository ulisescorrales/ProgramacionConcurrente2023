/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package programacionconcurrente2023.TP5.Punto5;

public class Main {

    
    public static void main(String[] args) {
        /*Algoritmo principal, se crean e inicializan las instancias de cada clase
        * Tren y MaquinaExpendedora: recurso compartido 
          pasajeros y control: hilos que intervienen
        */
        int capacidadTren=5;
        Tren tren=new Tren(capacidadTren);
        int cantPasajeros=10;
        Pasajero[] pasajeros=new Pasajero[cantPasajeros];
        MaquinaExpendedora me=new MaquinaExpendedora();
        
        for (int i = 0; i < cantPasajeros; i++) {
            pasajeros[i]=new Pasajero(tren,me,String.valueOf(i+1));
            pasajeros[i].start();
        }
        
        ControlTren control=new ControlTren(tren,"Control");
        control.start();
    }
    
}
