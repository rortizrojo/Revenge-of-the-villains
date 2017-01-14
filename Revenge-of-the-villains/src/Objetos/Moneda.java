
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
public class Moneda implements IColisionable {

    
    private final Rectangle areaColision; 
    private final SpriteSheet sheetMoneda;
    private final Animation animationMoneda;
    private final float anchoSprite;
    private final float altoSprite;
    private final float anchoDibujado;
    private final float altoDibujado;
    private final int numeroFrames;
    private float posMonedaX;
    private float posMonedaY;
    private boolean cogida;
    private final Sound monedaSound;
    
        
    public Moneda(float x, float y) throws SlickException{
        
        this.posMonedaX = x;
        this.posMonedaY = y;
        cogida = false;
        anchoSprite = 100;
        altoSprite = 100;
        anchoDibujado = anchoSprite *0.5f;
        altoDibujado = altoSprite *0.5f;
        
        monedaSound = new Sound("res/sounds/moneda.wav");
        animationMoneda = new Animation();
        sheetMoneda = new SpriteSheet("res/animations/moneda.png", (int) anchoSprite, (int) altoSprite);

        numeroFrames = 6;
        for (int i = 0; i < numeroFrames; i++) {
            animationMoneda.addFrame(sheetMoneda.getSprite(i, 0), 200);
        }
        areaColision = new Rectangle(x,y,anchoDibujado,altoDibujado);
        GestorColisiones.getInstancia().registrarCuerpo(this);
        
    }
    
    public void update(int delta){
        
        sincronizarArea();
    }
    
    
    public void setPosicion(float x, float y){
        posMonedaX = x;
        posMonedaY = y;    
    }
       
    public void draw(){
        if(!cogida)
            animationMoneda.draw(posMonedaX, posMonedaY, anchoDibujado, altoDibujado);
    }

    public boolean isCogida() {
        return cogida;
    }
    
            
    @Override
    public Shape getAreaColision() {
        return areaColision;
    }

    @Override
    public void alColisionar(IColisionable colision) {
        if(colision.getClass().getName().equals("Personajes.Jugador")){
            cogida = true;
            
            if(cogida)
                if(!monedaSound.playing())
                    monedaSound.play();
            
            
        }
        
            
    }

    @Override
    public void sincronizarArea() {
        areaColision.setX(posMonedaX);
        areaColision.setY(posMonedaY);
    }
}