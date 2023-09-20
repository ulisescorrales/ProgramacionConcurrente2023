/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto4;

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
        
        int cantClientes=8;
        int i;
        Cliente[] clientes=new Cliente[cantClientes];
        
        Impresora[] colImpresoras=new Impresora[2];
        for (i = 0; i < colImpresoras.length; i++) {
            colImpresoras[i] = new Impresora();
        }
        GestorImpresora gi=new GestorImpresora(colImpresoras);
        for (i = 0; i < cantClientes; i++) {
            clientes[i]=new Cliente(gi,"Cliente "+(i+1));
            clientes[i].start();
        }
    }
}
