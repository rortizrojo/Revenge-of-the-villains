/**
 * Personajes
 */
package Personajes;

import Engine.Camara;
import Engine.Colisiones;
import Engine.GestorColision;
import Engine.IColisionable;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Shape;

/**
 * Clase Sonic que representa al personaje Sonic que ayuda al 
 * protagonista a terminar la avventura
 * @author Roberto
 */
public class Sonic extends Personaje implements IColisionable {
 
    private float runningTime;

    //Sprite y animaciones:

    private final SpriteSheet sheetParadoIzquierda;
    private final SpriteSheet sheetParadoDerecha;
    private final Animation paradoDerecha;
    private final Animation paradoIzquierda;
   
    private boolean mirandoIzquierda;
    private final Rectangle areaColision;
    private final GestorColision gestor;


    public Sonic(GameContainer container, Colisiones colisiones, GestorColision gestor ) throws SlickException {
        
        super(container,colisiones);
        this.gestor = gestor;
        gestor.registrarCuerpo(this);

         
        //Posicion inicial
        posX = 1300;
        posY = 100;
      
        //Tamaño del sprite .png ver en propiedades de la imagen
        anchoSprite = 28;
        altoSprite = 39;
        //Escalado del sprite, ajustar con el numero
        anchoDibujado = anchoSprite*2f;
        altoDibujado = altoSprite*2f;

        areaColision = new Rectangle(posX, posY,anchoDibujado,altoDibujado);
  
        //Sheets y animaciones necesarias para el movimiento:
        sheetParadoIzquierda = new SpriteSheet("res/animations//Mario/paradoIzquierdaSprite.png", (int) anchoSprite, (int) altoSprite);
        sheetParadoDerecha = new SpriteSheet("res/animations//Mario/paradoDerechaSprite.png", (int) anchoSprite, (int) altoSprite);     
        
        //Crear la animaciones
        paradoIzquierda = new Animation();
        paradoDerecha = new Animation();
        
        //Cargas los frames de los sprites en las animaciones
        //ej.--> for (int i = 0; i < n_framesPorSprite-1; i++)


        for (int i = 0; i < 1; i++) {
            paradoIzquierda.addFrame(sheetParadoIzquierda.getSprite(i, 0), 150);
        }

        for (int i = 0; i < 4; i++) {
            paradoDerecha.addFrame(sheetParadoDerecha.getSprite(i, 0), 150);
        }
    
    }
      
     /**
     * Secuencia de acciones que realiza el personaje
     */
    @Override
    public void acciones() {
                
        runningTime = runningTime + delta;
        botonDerecha = false;
        botonIzquierda = false;
        botonArriba = false;

        sincronizarArea();
        gestor.comprobarColisiones();
        
        runningTime = (int)runningTime + delta*0.01f;
     
               
    }
         
    @Override
    public void render(int delta,Graphics g, Camara camara) throws SlickException {

        this.delta = delta;
        if(botonDerecha){
        
            mirandoIzquierda = false;
        } else if (botonIzquierda) {
        
            mirandoIzquierda = true;
          
        } else {
            if (mirandoIzquierda) {
                paradoIzquierda.draw(posX, posY, anchoDibujado, altoDibujado);
            } else {
                paradoDerecha.draw(posX, posY, anchoDibujado, altoDibujado);
            }
        }
        
           
    }
    
  
    @Override
    public Shape getAreaColision() {
       return areaColision;
    
    }

    @Override
    public void alColisionar(IColisionable colision) {
        if (colision.getClass().getName().equals("Armas.Bala"))
            vida--;
        
         
    }

    @Override
    public void sincronizarArea() {
        areaColision.setX(posX);
        areaColision.setY(posY);
    }

}

   