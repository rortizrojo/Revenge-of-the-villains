package Armas;

import Engine.GestorColision;
import Personajes.Personaje;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Roberto
 */
public class Pistola extends Arma {
    
    private final ControladorBala balas;
    
    public Pistola(float daño) throws SlickException{
        super(daño);
       
        balas = new ControladorBala();
        
    }
    
    public void disparar(Personaje personaje, float velocidad, GestorColision gestor){
        
        balas.addBala((float)personaje.getPosX()+40,(float)personaje.getPosY()+40, velocidad,gestor);
        
    }
    
    public void update(int delta){
    
        balas.update(delta);
       
        
        
    }
    
    public void delete(TiledMap map){
        balas.delete(map.getWidth()*map.getTileWidth());
    }
    
    @Override
    public void render(){
        
        balas.draw();
        
    }
    
}
