
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Armas;

import Engine.GestorColision;
import Personajes.Personaje;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Roberto
 */
public class TiraBolas extends Arma {
    
    private final ControladorBola bolas;
    private final Sound disparoBola;

    
    public TiraBolas(float daño) throws SlickException{        
        super(daño);
        bolas = new ControladorBola();
        disparoBola = new Sound("res/sounds/disparoBola.wav");
        
    }
    
    public void disparar(Personaje personaje, float velocidad, GestorColision gestor){
        bolas.addBola((float)personaje.getPosX()+20,(float)personaje.getPosY()+20, velocidad,gestor,daño);
        disparoBola.play(1f,0.2f);
    }
    
    public void update(int delta){
    
        bolas.update(delta);
        
    }
    public void delete(TiledMap map){
        bolas.delete(map.getWidth()*map.getTileWidth());
    }
        
    
    @Override
    public void render(){
        
        bolas.draw();
        
    }
    
}
