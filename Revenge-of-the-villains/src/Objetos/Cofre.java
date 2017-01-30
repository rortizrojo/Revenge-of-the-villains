/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Engine.GestorColisiones;
import Engine.IColisionable;
import org.newdawn.slick.Animation;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Usuario
 */
public class Cofre implements IColisionable {

    
    private final Rectangle areaColision; 
    private final SpriteSheet sheetCofre;
    private Animation animationCofre;
    private final float anchoSprite;
    private final float altoSprite;
    private final float anchoDibujado;
    private final float altoDibujado;
    private float posCofreX;
    private float posCofreY;
    
        
    public Cofre(float x, float y, float velocidad) throws SlickException{
        
    
        this.posCofreX = x;
        this.posCofreY = y;
       

        anchoSprite = 54;
        altoSprite = 42;
        anchoDibujado = anchoSprite *1f;
        altoDibujado = altoSprite *1f;
        
        animationCofre = new Animation();
        sheetCofre = new SpriteSheet("res/images/leftovers.png", (int) anchoSprite, (int) altoSprite);
        animationCofre = new Animation();
        //numeroFrames = 2;
        for (int i = 0; i >=0; i--) {
            animationCofre.addFrame(sheetCofre.getSprite(i, 0), 150);
        }
        areaColision = new Rectangle(x,y,anchoDibujado,altoDibujado);
        
        GestorColisiones.getInstancia().registrarCuerpo(this);
    }
    
    public void setPosicion(float x, float y){
        posCofreX = x;
        posCofreY = y;    
    }
       
    public void draw(){
        animationCofre.draw(posCofreX, posCofreY, anchoDibujado, altoDibujado);
    }
    
    
    public void update(int delta, float posX, float posY){    
        animationCofre.update(delta);
        setPosicion(posX,posY);
        sincronizarArea();
    }
            
    @Override
    public Shape getAreaColision() {
        return areaColision;
    }

    @Override
    public void alColisionar(IColisionable colision) {
      
    }

    @Override
    public void sincronizarArea() {
        areaColision.setX(posCofreX);
        areaColision.setY(posCofreY);
    }
    
}
