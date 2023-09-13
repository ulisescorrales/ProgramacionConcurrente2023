/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.ProblemasClasicos.elBarBeroDormilon;

/**
 *
 * @author ulisescorrales
 */
public class Cliente extends Thread{
    private Silla silla;

    public Cliente(Silla silla, String name) {
        super(name);
        this.silla = silla;
    }
    
    public void run(){
        System.out.println(this.getName()+" espera");
        silla.sentarse();
        System.out.println(this.getName()+" se sienta");
        silla.salir();
        System.out.println(this.getName()+" salió");
    }
}
