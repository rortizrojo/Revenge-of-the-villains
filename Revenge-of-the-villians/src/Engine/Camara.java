/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import Personajes.Personaje;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Roberto
 */
public class Camara {
    
    private float x;
    private float y;
    private float camX;
    private float camY;
    //private Graphics g;
    private final TiledMap map;
    private final float distanciaBordeX;
    private final float distanciaBordeY;
    private float anchoMapa;
    private float altoMapa; 
    private final float anchoVentana;
    private final float altoVentana;
    private final Personaje personaje;
    private float zoom;
    
    
    public Camara(GameContainer container , TiledMap map, Personaje personaje, float zoom){
        
        this.personaje = personaje;
        this.map = map;
        this.zoom = zoom;
        distanciaBordeX = 300;
        distanciaBordeY = 250;     
        anchoMapa= map.getWidth()*map.getTileWidth()*zoom;
        altoMapa = map.getHeight()*map.getTileHeight()*zoom;
        anchoVentana = container.getWidth();
        altoVentana= container.getHeight();
        //anchoTile = map.getTileHeight()*zoom;
      
    }

    public float getZoom() {
        return zoom;
    }

    public float getCamX() {
        
        return camX;
    }

    public float getCamY() {
        return camY;
    }
    
    
    public void moverCamara(){
        
        x = personaje.getPosX()*zoom;
        y = personaje.getPosY()*zoom;
   
        if (x >(anchoMapa - anchoVentana + distanciaBordeX)  && y > altoMapa-altoVentana+distanciaBordeY){
            camX = -(anchoMapa - anchoVentana);
            camY =  altoVentana-altoMapa;
        }
        else if ( x >(anchoMapa - anchoVentana + distanciaBordeX) && y <= altoMapa-altoVentana+distanciaBordeY){
            
            camX = -(anchoMapa - anchoVentana);
            camY = -y + distanciaBordeY;
        }
        
        else if(x > distanciaBordeX && y >  altoMapa-altoVentana+distanciaBordeY){
            camX = -x + distanciaBordeX;
            camY =  altoVentana-altoMapa;
           
        }
          
        else if(x > distanciaBordeX && y > distanciaBordeY){
                 
            
            camX = -x + distanciaBordeX;
            camY = -y + distanciaBordeY;
        }
        else if(x > distanciaBordeX && y <= distanciaBordeY){
            
            camX =  -x + distanciaBordeX;
           
                
        }
        
         else if (x <= distanciaBordeX && y > altoMapa-altoVentana+distanciaBordeY){
                
            camX = 0;
            camY = altoVentana-altoMapa;
        }
        
        else if (x <= distanciaBordeX && y > distanciaBordeY){
                
            camX = 0;
            camY = -y + distanciaBordeY;
        }
        else if (x <= distanciaBordeX && y <= distanciaBordeY){
             
            
        }
    
    }

    public void setZoom(float zoom) {
        this.zoom = zoom;
        anchoMapa= map.getWidth()*map.getTileWidth()*zoom;
        altoMapa = map.getHeight()*map.getTileHeight()*zoom;
        //anchoTile = map.getTileHeight()*zoom;
        x = personaje.getPosX()*zoom;
        y = personaje.getPosY()*zoom;
    }
}
