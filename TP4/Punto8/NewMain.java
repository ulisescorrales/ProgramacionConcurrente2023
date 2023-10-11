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
        int cantElect=7,cantMec=7,capacidad=2;
        ControladorProduccion contr=new ControladorProduccion(capacidad);
        Control control=new Control(contr,"Control");
        ProductoElectrico[] prodElectricos=new ProductoElectrico[cantElect];
        ProductoMecanico[] prodMecanicos=new ProductoMecanico[cantMec];
        
        
        for (int i = 0; i < cantElect; i++) {
            prodElectricos[i]=new ProductoElectrico(contr,"Electrico "+(1+i));
            prodElectricos[i].start();
        }
        for (int i = 0; i < cantMec; i++) {
            prodMecanicos[i]=new ProductoMecanico(contr,"Mecánico"+(1+i));
            prodMecanicos[i].start();
        }
        control.start();
    }
    
}
