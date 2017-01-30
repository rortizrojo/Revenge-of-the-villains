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
public class EstadoAcercandose implements Estado {
    
    
    @Override
    public void ejecutar(Enemigo enemigo){
        
        //System.out.println(enemigo.getNombre() + ": Estoy acercándome");
        if (enemigo.isJugador_a_izq() == true){
            enemigo.setMirandoIzquierda(true);
            if (enemigo.isConectadoSuelo() && !enemigo.isConectadoIzquierda())//->puede avanzar 
                enemigo.setBotonIzquierda(true); 
        }
        if (enemigo.isJugador_a_izq() == false){
            enemigo.setMirandoIzquierda(false);
            if(enemigo.isConectadoSuelo()  && !enemigo.isConectadoDerecha()) //-> puede avanzar
                enemigo.setBotonDerecha(true);
        }
        if (!enemigo.isJugador_a_alt() && enemigo.isJugador_a_igualX()){//Esta en misma X pero muy arriba->se para                 
            enemigo.setBotonIzquierda(false);
            enemigo.setBotonDerecha(false);
        }  

    }
    
    
}
