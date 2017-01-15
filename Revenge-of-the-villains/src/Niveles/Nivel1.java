package Niveles;

import Engine.Camara;
import Engine.Colisiones;
import Engine.Nivel;
import Objetos.Moneda;
import Objetos.Puerta;
import Personajes.Enemigo;
import Personajes.EnumTipoEnemigo;
import Personajes.Mario;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Roberto
 */
public class Nivel1 extends Nivel{

        
         //private Puerta puerta; 
    public Nivel1(GameContainer container, StateBasedGame game, Nivel nivel)throws SlickException{
       
        
            super();
            super.container = container;
            super.game = game;
            jugador = nivel.getJugador();
            map = new TiledMap("res/tileMaps/Nivel 2.tmx");

            colisiones = Colisiones.getInstancia();
            colisiones.setMap(map);
            jugador.setColisiones();
            //jugador.setMonedas();
            //enemigoDebil = new Enemigo(container,5550,1000, 0.6f,  EnumTipoEnemigo.DEBIL);
            //enemigoFuerte = new Enemigo(container, 350,1000, 1.0f, EnumTipoEnemigo.NORMAL);
            enemigo1 = new Enemigo(container,5550,1000, EnumTipoEnemigo.NORMAL);
            enemigo2 = (Enemigo) enemigo1.copia();
            enemigo2.setPosX(350);
            enemigo2.setPosY(1000);

            puerta = new Puerta(46,430,0);
            zoom = 0.65f;
            camara = new Camara(container, map,  jugador, zoom );

            monedas = new ArrayList<>();
            colocarMonedas();
            nivelAct = jugador.getNivel();
                
        
    }

    private void colocarMonedas()throws SlickException{
        //int tileID;
            for (int x = 0; x < map.getWidth(); x++) {
                for (int y = 0; y < map.getHeight(); y++) {
                    //System.out.println("width"+ x );
                    //tileID = map.getTileId(x, y, map.getLayerIndex("Monedas"));

                    if(colisiones.getMonedas()[x][y]) {
                       Moneda moneda = new Moneda(x*50,y*50);
                       //System.out.println("Añadida moneda");
                       monedas.add(moneda) ;
                       
                    }
                }

            }
            //System.out.println("Añadidas: "+ monedas.size());
    }
     
}
