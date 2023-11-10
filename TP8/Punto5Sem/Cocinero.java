/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP8.Punto5Sem;

/**
 *
 * @author ulisescorrales
 */
public class Cocinero extends Thread {

    Olla olla;

    public Cocinero(Olla olla, String name) {
        super(name);
        this.olla = olla;
    }

    public void run() {
        while (true) {
            this.olla.cocinar();
        }
    }
}
