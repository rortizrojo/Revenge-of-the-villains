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
package EstadosEnemigo;

import Personajes.Enemigo;

/**
 *
 * @author rorti
 */
public class EstadoFurioso implements Estado {
    
    @Override
    public void ejecutar(Enemigo enemigo){
        
        //System.out.println(enemigo.getNombre() + ": Estoy en el estado Furioso");
        if (enemigo.isJugador_a_izq() == true){
            if (enemigo.getDistancia()>50 && enemigo.isJugador_a_alt() && 
                    enemigo.isConectadoSuelo() && !enemigo.isConectadoIzquierda())
                enemigo.setBotonIzquierda(true); 
            if (enemigo.getDistancia()>50 && !enemigo.isJugador_a_alt()){
                enemigo.setMirandoIzquierda(true);
                enemigo.setSaltar(true); 
            }
            if (Math.random()*100 <= 20){
                enemigo.getTiraBolas().disparar(enemigo, -500);       
            }
        }
        else{ 
            if (enemigo.getDistancia() >50 && enemigo.isJugador_a_alt() && 
                    enemigo.isConectadoSuelo()  && !enemigo.isConectadoDerecha())
                enemigo.setBotonDerecha(true); 
            if (enemigo.getDistancia()>50 && !enemigo.isJugador_a_alt()){
                enemigo.setBotonIzquierda(false);
                enemigo.setSaltar(true);
            }
            if ((Math.random()*100 ) <= 20){
                enemigo.getTiraBolas().disparar(enemigo, +500);
            }
        }  

    }
    
}
