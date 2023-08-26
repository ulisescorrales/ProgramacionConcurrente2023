/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package programacionconcurrente2023.TP3.Punto2;

/**
 *
 * @author ulisescorrales
 */
public class Energia {

    private byte valor = 10;
    Object val = new Object();

    public byte drenar() {
        synchronized (val) {
            this.valor -= 3;
            System.out.println("Demonio drena");
            System.out.println("Nuevo valor: " + this.valor);
            return this.valor;
        }
    }

    public byte revitalizar() {
        synchronized (val) {
            this.valor += 3;
            System.out.println("Sanador cura");
            System.out.println("Nuevo valor: " + this.valor);
            return this.valor;
        }
    }
}
