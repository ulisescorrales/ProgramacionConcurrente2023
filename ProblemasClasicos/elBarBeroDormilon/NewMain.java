/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.ProblemasClasicos.elBarBeroDormilon;

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
        int cantClientes=5;
        Silla silla=new Silla();
        Barbero barbero=new Barbero(silla,"Barbero");
        Cliente[] clientes=new Cliente[cantClientes];
        
        for (int i = 0; i < cantClientes; i++) {
            clientes[i]=new Cliente(silla,"Cliente "+(i+1));
            clientes[i].start();
        }
        barbero.start();
    }
    
}
