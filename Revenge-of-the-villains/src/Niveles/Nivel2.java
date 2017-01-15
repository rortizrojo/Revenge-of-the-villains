package Niveles;

import Engine.Camara;
import Engine.Colisiones;
import Engine.Nivel;
import Objetos.BolaFuego;
import Objetos.Cofre;
import Objetos.Lava;
import Personajes.Enemigo;
import Personajes.EnumTipoEnemigo;
import Personajes.Mario;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Roberto
 */
public class Nivel2 extends Nivel {

    //private Puerta puerta; 
    public Nivel2( GameContainer container, StateBasedGame game, Nivel nivel)throws SlickException{
        super();
        super.container = container;
        super.game = game;
        jugador = nivel.getJugador();
        puerta = nivel.getPuerta();
        map = new TiledMap("res/tileMaps/NivelFinalMario.tmx");
        Colisiones.getInstancia().setMap(map);
        jugador.setColisiones();
        //enemigoFuerte = new Enemigo(container, 500, 200, 1.0f,EnumTipoEnemigo.NORMAL);
        //enemigoDebil = new Enemigo(container,1300, 1650, 0.7f, EnumTipoEnemigo.DEBIL);
        
        
        enemigo1 = new Enemigo(container,1300, 1650, EnumTipoEnemigo.NORMAL);
        enemigo2 = (Enemigo) enemigo1.copia();
        enemigo2.setPosX(500);
        enemigo2.setPosY(200);
        enemigo2.setCaracteristicas(EnumTipoEnemigo.FUERTE);
        
        cofre = new Cofre (enemigo2.getPosX(),enemigo2.getPosY()+50,0);
        lava = new Lava (1100,1300,0);
        fuego1 = new BolaFuego (1150,1300,0);
        fuego2 = new BolaFuego (1300,950,0);
        fuego3 = new BolaFuego (1450,1300,0);
        zoom = 0.65f;
        camara = new Camara(container, map, jugador , zoom);
        nivelAct = jugador.getNivel();
    }

    @Override
    public void setMap(TiledMap map) {
        this.map = map;
    }

    @Override
    public TiledMap getMap() {
        return map;
    }
 
   
}
