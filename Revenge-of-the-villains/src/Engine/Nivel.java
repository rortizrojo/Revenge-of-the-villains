/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import Juego.EnumStates;
import Objetos.BolaFuego;
import Objetos.Cofre;
import Objetos.Lava;
import Objetos.Puerta;
import Objetos.Moneda;
import Personajes.Enemigo;
import Personajes.Jugador;
import java.awt.Font;
import java.util.ArrayList;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Roberto
 */
public class Nivel {
   
    protected TiledMap map;
    protected Colisiones colisiones;
    protected Jugador jugador;
    protected static Enemigo enemigoOriginal ;
    protected Enemigo enemigo1;
    protected Enemigo enemigo2;
    protected Puerta puerta;
    protected Camara camara;
    protected Cofre cofre;
    protected ArrayList<Moneda> monedas;
    protected Inventario inventario;
    protected Lava lava;
    protected BolaFuego fuego1;
    protected BolaFuego fuego2;
    protected BolaFuego fuego3;
    protected static GameContainer container;
    protected StateBasedGame game;
    protected final UnicodeFont fuente;
    protected final UnicodeFont fuente2;
    protected int delta;
    protected float zoom;
    protected boolean pausado;
    protected int nivelAct;
    
    public Nivel()throws SlickException{
        java.awt.Font fuenteAWT = new java.awt.Font("Comic Sans MS",Font.BOLD, 24);
        java.awt.Font fuenteAW = new java.awt.Font("Comic Sans MS",Font.BOLD, 15);
        fuente = new UnicodeFont (fuenteAWT);
        fuente2= new UnicodeFont (fuenteAW);
        fuente2.addAsciiGlyphs();
        fuente.addAsciiGlyphs();
        ColorEffect colorFuente = new ColorEffect(java.awt.Color.WHITE);
        fuente2.getEffects().add(colorFuente);
        fuente.getEffects().add(colorFuente);
        fuente.loadGlyphs();
        fuente2.loadGlyphs();
        pausado = false;
       
    }

    public void update(int delta) throws SlickException{
        this.delta = delta;
        jugador.update(delta);
        enemigo1.update(delta);
        enemigo2.update(delta);
        jugador.setEnemigos_muertos(enemigo1.isMuerto()&&enemigo2.isMuerto());

        
        puerta.update();
        if (nivelAct==2){
            
            if(!enemigo2.isMuerto())
                cofre.update(delta, enemigo2.getPosX(), enemigo2.getPosY()+65);
            lava.update();
            fuego1.update(delta);
            fuego2.update(delta);
            fuego3.update(delta);
        }
        actualizarTeclado(game);
        
    }
    public void render(Graphics g)throws SlickException{
        
        g.translate(camara.getCamX(),camara.getCamY());
        //g.setWorldClip( -camara.getCamX(),-camara.getCamY(),container.getWidth(),container.getHeight());
        
        g.scale(zoom,zoom);
        
        switch (nivelAct) {
            case 0:
                map.render(0,0,map.getLayerIndex("Fondo"));
                map.render(0,0,map.getLayerIndex("Montañas fondo"));
                // map.render(0,0,map.getLayerIndex("Cielo"));
                map.render(0,0,map.getLayerIndex("Arboles"));
                map.render(0,0,map.getLayerIndex("Arboles 2"));
                //puerta.draw();
                map.render(0,0,map.getLayerIndex("Terreno 1"));
                //map.render(0,0,map.getLayerIndex("Colisiones"));
                break;
            case 1:
                map.render(0, 0, map.getLayerIndex("Montañas fondo"));
                map.render(0, 0, map.getLayerIndex("Arboles"));
                map.render(0, 0, map.getLayerIndex("Arboles 2"));
                map.render(0, 0, map.getLayerIndex("Terreno 1"));
                if(monedas!=null)
                    monedas.forEach((moneda) -> {  moneda.draw(); });
                
                break;
            default:
                map.render(0,0,map.getLayerIndex("Fondo"));
                map.render(0,0,map.getLayerIndex("FondoCastillo"));
                break;
        }
        
        puerta.draw();
        
        jugador.render(delta, g, camara);
        
        if(!enemigo1.isMuerto())
            enemigo1.render(delta, g, camara);  
        if(!enemigo2.isMuerto())
            enemigo2.render(delta, g, camara);
        if(jugador.getPasoDeNivel()|| jugador.getFinJuego()){
            fuente.drawString(jugador.getPosX()-100, jugador.getPosY()-100,"MISSION COMPLETED");
            fuente2.drawString(jugador.getPosX()-70, jugador.getPosY()-70,"(presiona F para continuar)");  
            
        } 
        if (nivelAct==2){
             render2();  
        }   
           
        if(jugador.getEnemigosMuertos()&&nivelAct==2)
                cofre.draw(); 
        
        
        g.scale(1/zoom, 1/zoom);
        g.setColor(Color.yellow);
                
        g.fillRect(container.getWidth()-camara.getCamX()- 700, container.getWidth()-camara.getCamY()-750, jugador.getVida()*2, 20 );
        g.drawString("Vida: " , container.getWidth()-camara.getCamX()- 750, container.getWidth()-camara.getCamY()-750);
       
        g.setColor(Color.red);
        g.fillRect(container.getWidth()-camara.getCamX() - 250, container.getWidth()-camara.getCamY()-750, enemigo2.getVida()*2, 20 );
            
        g.drawString("Vida Enemigo 1: " , container.getWidth()-camara.getCamX()- 400, container.getWidth()-camara.getCamY()-750);
        
        g.setColor(Color.orange);
        g.fillRect(container.getWidth()-camara.getCamX() - 250, container.getWidth()-camara.getCamY()-700, enemigo1.getVida()*2, 20 );
            
        g.drawString("Vida Enemigo 2: " , container.getWidth()-camara.getCamX() - 400 , container.getWidth()-camara.getCamY()-700);
        g.drawString("Monedas: " + jugador.getInventario().getMonedas(), container.getWidth()-camara.getCamX()- 750, container.getWidth()-camara.getCamY()-700);
    
        g.drawString("Puntuación: " + jugador.getPuntuacion(), container.getWidth()-camara.getCamX()- 750, container.getWidth()-camara.getCamY()-650);
    }

    public Colisiones getColisiones() {
        return colisiones;
    }

    public Jugador getJugador() {
        return jugador;
    }

    public Enemigo getEnemigo2() {
        return enemigo2;
    }

    public Enemigo getEnemigo1() {
        return enemigo1;
    }

    public Puerta getPuerta() {
        return puerta;
    }

    public Camara getCamara() {
        return camara;
    }

    public GameContainer getContainer() {
        return container;
    }

    public static void setContainer(GameContainer container) {
        Nivel.container = container;
    }
    
    

    public StateBasedGame getGame() {
        return game;
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

    public boolean isPausado() {
        return pausado;
    }

    public void setPausado(boolean pausado) {
        this.pausado = pausado;
    }

  
    public void actualizarTeclado (StateBasedGame game) throws SlickException{
        if (container.getInput().isKeyPressed(Input.KEY_B) ) {  //SOLO PARA HACER PRUEBAS          
            jugador.pasarNivel(); 
            
        } 
        if (container.getInput().isKeyPressed(Input.KEY_N )){ //SOLO PARA HACER PRUEBAS  
            enemigo2.setVida(0);
            enemigo1.setVida(0);
        }
        if (container.getInput().isKeyPressed(Input.KEY_P)) {
            pausado = true;
            game.enterState(EnumStates.PAUSA.ordinal(), new FadeOutTransition(), new FadeInTransition());
            
        }
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            game.enterState(EnumStates.MENU.ordinal(), new FadeOutTransition(), new FadeInTransition());
        }  
        if (container.getInput().isKeyPressed(Input.KEY_F) && (jugador.getPasoDeNivel()|| jugador.getFinJuego())) {           
            if (jugador.getNivel()<2){   
                jugador.pasarNivel();            
            }
            else {
                jugador.setMuerto(true);
                game.enterState(EnumStates.ENDSTATE.ordinal(), new FadeOutTransition(), new FadeInTransition());
                
            }
        }   
        if (container.getInput().isKeyDown(Input.KEY_I)) {
            if(zoom<1f){
               zoom = zoom+0.005f; 
               camara.setZoom(zoom);
            }
        } 
        if (container.getInput().isKeyDown(Input.KEY_O)) {
            if(zoom>0.5f){
                System.out.println("zoom: "+ zoom);
                zoom = zoom-0.005f;
                camara.setZoom(zoom);
            }
            
            
        } 
        
    }
    public void render2(){
        map.render(0,0,map.getLayerIndex("Terreno"));  
        fuego1.draw();
        fuego2.draw();
        fuego3.draw();
        lava.draw();
       
    
    }

    public static Enemigo getEnemigoOriginal() {
        return enemigoOriginal;
    }

    public ArrayList<Moneda> getMonedas() {
        return monedas;
    }

    public Lava getLava() {
        return lava;
    }
             
        
}
