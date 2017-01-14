/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Objetos;

import Armas.Punto;
import Armas.SpriteMovil;
import Armas.Vector;
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
public class BolaFuego implements IColisionable {

    private final SpriteMovil fuego;
    private final Rectangle areaColision; 
    private final SpriteSheet sheetPuerta;
    private final Animation animationFuego;
    private final float anchoSprite;
    private final float altoSprite;
    private final float anchoDibujado;
    private final float altoDibujado;
    private float posFuegoX;
    private float posFuegoY;
    private final float posIniX;
    private final float posIniY;
    
        
    public BolaFuego(float x, float y, float velocidad) throws SlickException{
        
        
    
        this.posIniX = x;
        this.posIniY = y;
        fuego = new SpriteMovil(new Punto(posIniX, posIniY), 
                                new Vector(new Punto(0, 0), 
                                new Punto(0, velocidad)));
        anchoSprite = 50;
        altoSprite = 49;
        anchoDibujado = anchoSprite *1f;
        altoDibujado = altoSprite *1f;
          
        animationFuego = new Animation();
        sheetPuerta = new SpriteSheet("res/animations/fuego.png", (int) anchoSprite, (int) altoSprite);
        
        //numeroFrames = 4;
        for (int i = 0; i <4; i++) {
            animationFuego.addFrame(sheetPuerta.getSprite(i, 0), 100);
        }
        areaColision = new Rectangle(x,y,anchoDibujado,altoDibujado);
        GestorColisiones.getInstancia().registrarCuerpo(this);
    }
    
    public void setPosicion(float x, float y){
        posFuegoX = x;
        posFuegoY = y;    
    }
       
    public void draw(){
        animationFuego.draw(posFuegoX, posFuegoY, anchoDibujado, altoDibujado);
       //  fuego.draw(posFuegoX, posFuegoY, anchoDibujado, altoDibujado);
    }
    
    
    public void update(int delta){ 
        fuego.update(delta);
        animationFuego.update(delta);
        
        
        if (fuego.getPosicion().getY()<=950){
           fuego.setVelocidad( new Vector(new Punto(0, 0), 
                    new Punto(0, +400)));
        }else if(fuego.getPosicion().getY()>=1300){
            fuego.setVelocidad( new Vector(new Punto(0, 0), 
                    new Punto(0, -400)));
        }
        posFuegoX = fuego.getPosicion().getX();
        posFuegoY = fuego.getPosicion().getY();
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
        areaColision.setX(posFuegoX);
        areaColision.setY(posFuegoY);
    }
    
}
