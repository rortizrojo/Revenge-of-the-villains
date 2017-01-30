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
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 *
 * @author Usuario
 */
public class Lava implements IColisionable {

    
    private final Rectangle areaColision; 
    private final SpriteSheet sheetLava;
    private final Animation animationLava;
    private final Animation animationLava1;
    private final Animation animationLava2;
    private final Animation animationLava3;
    private final float anchoSprite;
    private final float altoSprite;
    private final float anchoDibujado;
    private final float altoDibujado;
    private final int duracionAnimacion;
    private float posPuertaX;
    private float posPuertaY;
    private final float prueba;
    private final Sound lava;
        
    public Lava(float x, float y, float velocidad) throws SlickException{
        
    
        this.posPuertaX = x;
        this.posPuertaY = y;
       //x =1100 -+50 Y= 1300
       prueba= posPuertaX;
        anchoSprite = 50;
        altoSprite = 50;
        anchoDibujado = anchoSprite *1f;
        altoDibujado = altoSprite *1f;
      
        animationLava = new Animation();
        animationLava1 = new Animation();
        animationLava2 = new Animation();
        animationLava3 = new Animation();
        
        sheetLava = new SpriteSheet("res/animations/lava.png", (int) anchoSprite, (int) altoSprite);
        duracionAnimacion = 150;
         for (int i = 0; i < 4; i++) {
            animationLava.addFrame(sheetLava.getSprite(i, 0), duracionAnimacion);
            animationLava1.addFrame(sheetLava.getSprite((i+1)%4, 0), duracionAnimacion);
            animationLava2.addFrame(sheetLava.getSprite((i+2)%4, 0), duracionAnimacion);
     
            animationLava3.addFrame(sheetLava.getSprite((i+3)%4, 0), duracionAnimacion);
         }
        lava = new Sound("res/sounds/lava.wav");
             
        areaColision = new Rectangle(x,y,anchoDibujado*10,altoDibujado);
        GestorColisiones.getInstancia().registrarCuerpo(this);
        
        
        
    }
    
    public void setPosicion(float x, float y){
        posPuertaX = x;
        posPuertaY = y;    
    }
       
    public void draw(){
            animationLava.draw(posPuertaX, posPuertaY, anchoDibujado, altoDibujado);
            animationLava1.draw(posPuertaX+50, posPuertaY, anchoDibujado, altoDibujado);   
            animationLava2.draw(posPuertaX+100, posPuertaY, anchoDibujado, altoDibujado);
            animationLava3.draw(posPuertaX+150, posPuertaY, anchoDibujado, altoDibujado);
            animationLava.draw(posPuertaX+200, posPuertaY, anchoDibujado, altoDibujado);
            animationLava1.draw(posPuertaX+250, posPuertaY, anchoDibujado, altoDibujado);   
            animationLava2.draw(posPuertaX+300, posPuertaY, anchoDibujado, altoDibujado);
            animationLava3.draw(posPuertaX+350, posPuertaY, anchoDibujado, altoDibujado);
            animationLava.draw(posPuertaX+400, posPuertaY, anchoDibujado, altoDibujado);
            animationLava1.draw(posPuertaX+450, posPuertaY, anchoDibujado, altoDibujado); 
            
    }
    public void update(){
       
        if(!lava.playing())
        
            lava.play(1.5f,0.3f); 
       
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
