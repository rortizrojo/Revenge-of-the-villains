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
 * Interfaz que define los métodos que dependen del estado del objeto.
 * @author Salvador Oton
 */
public interface Estado {
    /**
     * Modifica el estado del objeto
     * @param enemigo Enemigo
     */
    public void ejecutar(Enemigo enemigo);
}
