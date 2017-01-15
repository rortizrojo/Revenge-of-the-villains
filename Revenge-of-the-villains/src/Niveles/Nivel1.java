package Niveles;

import Engine.Camara;
import Engine.Colisiones;
import Engine.Nivel;
import Objetos.Moneda;
import Objetos.Puerta;
import Personajes.Enemigo;
import Personajes.EnumTipoEnemigo;
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
            Nivel.container = container;
            super.game = game;
            jugador = nivel.getJugador();
            map = new TiledMap("res/tileMaps/Nivel 2.tmx");

            colisiones = Colisiones.getInstancia();
            colisiones.setMap(map);
            jugador.setColisiones();

            
            enemigo1 = (Enemigo) enemigoOriginal.copia();
            enemigo1.setPosX(5550);
            enemigo1.setPosY(1000);
            enemigo1.setNombre("enemigo1");
            enemigo1.setCaracteristicas(EnumTipoEnemigo.NORMAL);
            
            enemigo2 = (Enemigo) enemigoOriginal.copia();
            enemigo2.setPosX(350);
            enemigo2.setPosY(1000);
            enemigo2.setNombre("enemigo2");
            enemigo2.setCaracteristicas(EnumTipoEnemigo.NORMAL);
                        
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
