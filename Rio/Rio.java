/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.Rio;

import java.util.concurrent.Semaphore;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ulisescorrales
 */
public class Rio {
    Semaphore mutex=new Semaphore(1);
    Semaphore lobo=new Semaphore(1);
    Semaphore oveja=new Semaphore(1);
    int esperandoOveja=0;
    int esperandoLobo=0;
    int ovejasAdentro=0;
    int lobosAdentro=0;
    
    public void entrarOveja(){
        try {
            mutex.acquire();
            esperandoOveja++;
            if(esperandoOveja+esperandoLobo==1){//Como no hay límite, no es necesario incluir la capacidad
                System.out.println("    bloquear lobos");
                lobo.acquire();
            }            
            mutex.release();
            
            oveja.acquire();
            //
            mutex.acquire();
            System.out.println("Entra oveja");            
            if(esperandoLobo==0){
                System.out.println("    libera oveja");
                oveja.release();
            }
            ovejasAdentro++;
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void entrarLobo(){
        try {
            mutex.acquire();
            esperandoLobo++;
            if(esperandoOveja+esperandoLobo==1){//Como no hay límite, no es necesario incluir la capacidad
                System.out.println("    bloquear ovejas");
                oveja.acquire();
            }
            System.out.println("Quiere entrar lobo");
            mutex.release();
            
            lobo.acquire();
            mutex.acquire();
            lobo.release();//irrestricto;            
            lobosAdentro++;
            System.out.println("Entra lobo");
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void salirLobo(){
        try {
            mutex.acquire();
            System.out.println("Sale lobo");
            lobosAdentro--;
            esperandoLobo--;
            if(esperandoLobo==0){
                oveja.release();
            }
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void salirOveja(){
        try {
            mutex.acquire();
            System.out.println("Sale Oveja");
            ovejasAdentro--;
            esperandoOveja--;
            if(esperandoOveja==0){
                lobo.release();
            }            
            mutex.release();
        } catch (InterruptedException ex) {
            Logger.getLogger(Rio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
