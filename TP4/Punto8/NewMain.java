/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP4.Punto8;

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
        int cantProdElect=5,cantProdMec=5;
        ControladorProduccion contr=new ControladorProduccion();
        Control control=new Control(contr,"Control");
        
        
        for (int i = 0; i < cantProdElect; i++) {
            String arg = args[i];
            
        }
    }
    
}
