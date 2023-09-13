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
        
        Impresora[] colImpresoras=new Impresora[2];
        for (int i = 0; i < colImpresoras.length; i++) {
            colImpresoras[i] = new Impresora();
        }
        GestorImpresora gi=new GestorImpresora(colImpresoras);
        Cliente c1=new Cliente(gi,"Cliente 1");
        Cliente c2=new Cliente(gi,"Cliente 2");
        Cliente c3=new Cliente(gi,"Cliente 3");
        Cliente c4=new Cliente(gi,"Cliente 4");
        
        c1.start();
        c2.start();
        c3.start();
        c4.start();
    }
    
}
