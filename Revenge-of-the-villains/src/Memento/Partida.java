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
package Memento;

import Engine.IColisionable;
import Engine.Nivel;
import java.util.ArrayList;

/**
 *
 * @author rorti
 */
public class Partida {
    
    
    private Nivel nivel; // Nivel en la que se encuentra la partida.
    private FechaHora fecha; // Fecha en que se salva la partida.
    private ArrayList<IColisionable> lista ; //Objeto que contiene los objetos colisionables del nivel
    

    /**
     * Constructor.
     * @param nivel Nivel en la que se encuentra la partida.
     * @param gestor Objeto que contiene los objetos colisionables del nivel
     */
    public Partida(Nivel nivel, ArrayList<IColisionable> lista) {
        this.lista = lista;
        this.nivel = nivel;
        this.fecha = new FechaHora();
    }


    /**
     * Devuelve la fase en la que se encuentra la partida.
     * @return Fase en la que se encuentra la partida.
     */
    public Nivel getNivel() {
        return this.nivel;
    }

    /**
     * Devuelve la fecha en que se salva la partida.
     * @return Fecha en que se salva la partida.
     */
    public String getFecha() {
        return this.fecha.toString();
    }

    public ArrayList<IColisionable> getLista() {
        return lista;
    }
    
    

    /**
     * Devuelve la descripción de la partida.
     * @return Descripción de la partida.
     */
    @Override
    public String toString() {
        return "- Partida salvada:\nPuntuación: " + nivel.getJugador().getPuntuacion() + " # " 
                    + fecha.toString();
    }
}