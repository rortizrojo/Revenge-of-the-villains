/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import org.newdawn.slick.SlickException;

/**
 *
 * @author Usuario
 */
public interface Icontrolador {
    
    
    public void addBala(float x,float y, float velocidad, GestorColision gestor) throws SlickException;
    public void addBola(float x,float y, float velocidad, GestorColision gestor, float daño) throws SlickException;
    public void draw();
    public void update(int delta);
    public void delete (float delta);
}
