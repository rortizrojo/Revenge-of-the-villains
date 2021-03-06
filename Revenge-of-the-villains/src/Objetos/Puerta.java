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
public class Puerta implements IColisionable {

    
    private final Rectangle areaColision; 
    private final SpriteSheet sheetPuerta;
    private Animation animationPuerta;
    private final float anchoSprite;
    private final float altoSprite;
    private final float anchoDibujado;
    private final float altoDibujado;
    private float posPuertaX;
    private float posPuertaY;
    
        
    public Puerta(float x, float y, float velocidad) throws SlickException{
        
    
        this.posPuertaX = x;
        this.posPuertaY = y;

        anchoSprite = 122;
        altoSprite = 123;
        anchoDibujado = anchoSprite *1f;
        altoDibujado = altoSprite *1f;
        
        animationPuerta = new Animation();
        sheetPuerta = new SpriteSheet("res/animations/animation.png", (int) anchoSprite, (int) altoSprite);
        animationPuerta = new Animation();
        // numeroFrames = 2;
       for (int i = 1; i >=0; i--) {
            animationPuerta.addFrame(sheetPuerta.getSprite(i, 0), 150);
        }
        areaColision = new Rectangle(x,y,anchoDibujado,altoDibujado);
        GestorColisiones.getInstancia().registrarCuerpo(this);
    }
    
    public void setPosicion(float x, float y){
        posPuertaX = x;
        posPuertaY = y;
        
    }
       
    public void draw(){


        animationPuerta.draw(posPuertaX, posPuertaY, anchoDibujado, altoDibujado);

        
    }
    
    
    public void update(){
        
        
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
        areaColision.setX(posPuertaX);
        areaColision.setY(posPuertaY);
    }
    
}
