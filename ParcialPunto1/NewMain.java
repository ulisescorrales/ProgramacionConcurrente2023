/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.ParcialPunto1;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Almacen almacen=new Almacen();
        SalaCajas sala=new SalaCajas();
        int cantEmb=5;
        EmbotelladoraAgua[] ea=new EmbotelladoraAgua[cantEmb];
        EmbotelladoraVino[] ev=new EmbotelladoraVino[cantEmb];
        
        for (int i = 0; i < cantEmb; i++) {
            ea[i]=new EmbotelladoraAgua(sala,"Emboatelladora "+(i+1));
            ea[i].start();
            ev[i]=new EmbotelladoraVino(sala,"Emboatelladora "+(i+1));
            ev[i].start();            
        }
        Transporte transporte=new Transporte(almacen,"Transporte");
        transporte.start();
        Empaquetador empaquetador=new Empaquetador(sala,almacen,"Empaquetador");
        empaquetador.start();
    }
    
}
