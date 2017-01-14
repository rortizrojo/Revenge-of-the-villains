/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Armas;

import org.newdawn.slick.*;

/**
 *
 * @author Usuario
 */
public class SpriteMovil extends Sprite{
    
    private Vector velocidad;

    public SpriteMovil(Punto posicion, Vector velocidad) throws SlickException {
        super(posicion);
        this.velocidad= velocidad;
    }

    public Vector getVelocidad() {
        return velocidad;
    }

    public void setVelocidad(Vector velocidad) {
        this.velocidad = velocidad;
    }
    public void update(int delta){
       float x = posicion.getX() + velocidad.getX()*((float)delta/1000);
       float y = posicion.getY() + velocidad.gety()*((float)delta/1000);
       this.setPosicion(x,y);
               
    }
}
