package Personajes;

import org.newdawn.slick.SlickException;

/**
 * Prototipo. Interfaz con el método de copia. 
 */
public interface Copiable {

    /**
     * Motodo de copia.
     * @return Objeto copiado.
     */
    public Object copia() throws SlickException;
}
