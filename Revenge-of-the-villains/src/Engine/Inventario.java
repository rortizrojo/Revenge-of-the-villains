/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

/**
 *
 * @author Roberto
 */
public class Inventario {

    private int monedas;
    
    
    public Inventario(){
        
        monedas = 0;
        
    }



    public int getMonedas() {
        return monedas;
    }

    public void setMonedas() {
        this.monedas++;
    }
    
    
}
