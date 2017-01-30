/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Armas;

import Engine.IColisionable;
import Personajes.Enemigo;
import Personajes.EnumTipoEnemigo;
import Personajes.Jugador;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Roberto
 */
public class Bala implements IColisionable {

    private final SpriteMovil bala;
    private final Rectangle areaColision; 
    private final SpriteSheet sheetBala;
    private final float anchoSprite;
    private final float altoSprite;
    private final float anchoDibujado;
    private final float altoDibujado;
    private final int numeroFrames;
    private float posBalaX;
    private float posBalaY;
    private final float posDisparoX;
    private final float posDisparoY;
 
   
        
    public Bala(float x, float y, float velocidad) throws SlickException{
        
        
        this.posDisparoX = x;
        this.posDisparoY = y;
        bala = new SpriteMovil(new Punto(posDisparoX, posDisparoY), 
                new Vector(new Punto(0, 0), 
                    new Punto(velocidad, 0)));
        
        anchoSprite = 11;
        altoSprite = 9;
        anchoDibujado = anchoSprite *1f;
        altoDibujado = altoSprite *1f;
        
        
        sheetBala = new SpriteSheet("res/animations/bala.png", (int) anchoSprite, (int) altoSprite);
        numeroFrames = 1;
        for(int i = 0; i<numeroFrames;i++ )
            bala.addFrame(sheetBala.getSprite(i, 0), 100);
        
        areaColision = new Rectangle(x,y,anchoDibujado,altoDibujado);
        
    }
    
    public void draw(){
      
        bala.draw(posBalaX, posBalaY, anchoDibujado, altoDibujado);
        
    }
    
    public void update(int delta){
        
        
        bala.update(delta);
        posBalaX = bala.getPosicion().getX();
        posBalaY = bala.getPosicion().getY();
        sincronizarArea();
    }
            
    @Override
    public Shape getAreaColision() {
        return areaColision;
    }

    @Override
    public void alColisionar(IColisionable colision) {
        if(colision.getClass().getName().equals("Personajes.Enemigo")){
           bala.setPosicion(0, -100);
        }
           
    }

    @Override
    public void sincronizarArea(){
        areaColision.setX(posBalaX);
        areaColision.setY(posBalaY);
    }
    
    

    
}
