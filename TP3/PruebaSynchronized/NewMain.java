/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.TP3.PruebaSynchronized;

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
        OC objeto=new OC();
        
        HiloNS ns=new HiloNS(objeto,"NS");
        HiloNS ns2=new HiloNS(objeto,"NS2");
        HiloS s1=new HiloS(objeto,"S1");
        HiloS s2=new HiloS(objeto,"S2");
        HiloS2 s3=new HiloS2(objeto,"S3");
                
        s2.start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            Logger.getLogger(NewMain.class.getName()).log(Level.SEVERE, null, ex);
        }
        ns.start();
        s1.start();        
        s3.start();
        ns2.start();
    }   
    
}
