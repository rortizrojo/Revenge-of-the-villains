package Niveles;

import Engine.Camara;
import Engine.Colisiones;
import Engine.Nivel;
import Objetos.BolaFuego;
import Objetos.Cofre;
import Objetos.Lava;
import Personajes.Enemigo;
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
        gestor = nivel.getGestor();
        map = new TiledMap("res/tileMaps/NivelFinalMario.tmx");
        colisiones = new Colisiones(map);
        jugador.setColisiones(colisiones);
        mario = new Mario(container, colisiones, gestor, 500, 200, 1.0f);
        enemigo = new Enemigo(container, colisiones, gestor, 1300, 1650, 0.7f);
        cofre = new Cofre (mario.getPosX(),mario.getPosY()+50,0,gestor);
        lava = new Lava (1100,1300,0,gestor);
        fuego1 = new BolaFuego (1150,1300,0,gestor);
        fuego2 = new BolaFuego (1300,950,0,gestor);
        fuego3 = new BolaFuego (1450,1300,0,gestor);
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
