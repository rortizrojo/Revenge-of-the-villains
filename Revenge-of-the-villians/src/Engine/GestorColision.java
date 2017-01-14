/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.util.ArrayList;

/**
 * Interfaz que debe implemetar todos las acciones de colision .
 *
 * @author Valentin
 */
public class GestorColision {

    private final ArrayList<IColisionable> lista;

    public GestorColision() {
        lista = new ArrayList<>();

    }

    public void registrarCuerpo(IColisionable cuerpo) {
        if (!lista.contains(cuerpo)) {
            lista.add(cuerpo);
            //System.out.println("Registrado cuerpo: " + cuerpo.getClass().getSimpleName());
        }
    }

    public void anularCuerpo(IColisionable cuerpo) {
        if (lista.contains(cuerpo)) {
            lista.remove(cuerpo);
        }
    }

    /**
     * Método que comprueba los objetos con los que colisiona
     */
    public void comprobarColisiones() {
        for (int i = 0; i < lista.size(); i++) {
            for (int j = 0; j < lista.size(); j++) {
                if (i != j) {
                    buscarColision(i, j);
                    
                }
            }
        }
        //System.out.println("Colisionables: "+ lista.size());
    }
    
    /**
     * Método que comprueba si el objeto de la posición i del ArrayList colisiona 
     * con el objeto de la posición j del mismo ArrayList
     * @param i
     * @param j 
     */
    private void buscarColision(int i, int j) {
        if (lista.get(i).getAreaColision().intersects(lista.get(j).getAreaColision())) {
            lista.get(i).alColisionar(lista.get(j));
        }
    }
    
    
}
