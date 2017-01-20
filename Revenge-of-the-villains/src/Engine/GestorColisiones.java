/*
 * Copyright (C) 2017 rorti
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package Engine;

import java.util.ArrayList;

/**
 * Clase que controla las colisiones entre objetos
 *
 * @author Roberto Ortiz
 */
public class GestorColisiones {
    
    private static ArrayList<IColisionable> listaColisionables;
    
    /**
     * Instancia de la clase. 
     * Es privada: solo accesible por la propia clase.
     * Es estática: propia de la clase (compartida por los objetos). 
     */
    private static GestorColisiones instancia;

    /**
     * Constructor privado. 
     * No se permite crear instancias de la clase Singleton usando new.
     */
    private GestorColisiones() {
        listaColisionables = new ArrayList<>();

    }

    /**
     * Método que añade un objeto colisionable a la lista
     * @param cuerpo 
     */
    public void registrarCuerpo(IColisionable cuerpo) {
        if (!listaColisionables.contains(cuerpo)) {
            listaColisionables.add(cuerpo);
            //System.out.println("Registrado cuerpo: " + cuerpo.getClass().getSimpleName());
        }
    }

    /**
     * Método que elimina un objeto colisionable
     * @param cuerpo Objeto colisionable
     */
    public void anularCuerpo(IColisionable cuerpo) {
        if (listaColisionables.contains(cuerpo)) {
            listaColisionables.remove(cuerpo);
            
            System.out.print("Queda: " );
            listaColisionables.forEach((elemento) -> {
                  
                  System.out.print(" "+ elemento.getClass().getSimpleName() );
            });
            
            System.out.println("");
    
            
        }
    }

    /**
     * Método que comprueba los objetos con los que colisiona
     */
    public void comprobarColisiones() {
        for (int i = 0; i < listaColisionables.size(); i++) {
            for (int j = 0; j < listaColisionables.size(); j++) {
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
        if (listaColisionables.get(i).getAreaColision().intersects(listaColisionables.get(j).getAreaColision())) {
            listaColisionables.get(i).alColisionar(listaColisionables.get(j));
        }
    }
    
    public int getNumberOfElements(){
        return listaColisionables.size();
    }
    
    public void eliminarElementos(){
        listaColisionables.clear();
    }

    public ArrayList<IColisionable> getListaColisionables() {
        return listaColisionables;
    }
    
    
    
    /**
     * Devuelve la instancia de la clase.
     * Acceso controlado a la única instancia. 
     * Otras clases que quieran una referencia a la única instancia de la clase
     * Singleton conseguirán esa instancia llamando al método estático 
     * getInstancia de la clase. 
     * @return Instancia de la clase.
     */
    public static GestorColisiones getInstancia() {
        if (instancia == null) {// Si la instancia es null, se crea.
            instancia = new GestorColisiones();
        }
        return instancia;
    }
}
