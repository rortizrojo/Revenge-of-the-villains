/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Armas;

import org.newdawn.slick.*;

/**
 *
 * @author Usuario
 */
public class Sprite extends Animation {
    
  Punto posicion;
  
 
   public Sprite (Punto posicion) throws SlickException{
      super();
      this.posicion = posicion;
  }
    public Sprite () throws SlickException{
      this(new Punto(0,0));
   
  }
   public Sprite (float x,float y) throws SlickException{
      this(new Punto(x,y));
   
  }
   
   public Punto getPosicion (){
       return this.posicion;
   }
   public void setPosicion (Punto posicion){
       this.posicion=posicion;
   }
    public void setPosicion (float x, float y){
       this.posicion.setX(x);
       this.posicion.setY(y);
   }
}
