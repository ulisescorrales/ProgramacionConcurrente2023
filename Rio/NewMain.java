/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package programacionconcurrente2023.Rio;

/**
 *
 * @author ulisescorrales
 */
public class NewMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Rio rio=new Rio();
        int cantOvejas=5;
        int cantLobos=5;
        Oveja[] ovejar=new Oveja[cantOvejas];
        Lobo[] lobos= new Lobo[cantLobos];
        
        for (int i = 0; i < cantOvejas; i++) {
            ovejar[i]=new Oveja(rio,"Oveja "+(i+1));
            ovejar[i].start();
            lobos[i]=new Lobo(rio,"Lobo "+(i+1));
            lobos[i].start();
        }
        for (int i = 0; i < cantOvejas; i++) {
            
        }
    }
    
}
