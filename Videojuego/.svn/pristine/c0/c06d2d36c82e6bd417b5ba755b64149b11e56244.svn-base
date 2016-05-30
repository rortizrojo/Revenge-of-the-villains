/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Niveles;

import Engine.Camara;
import Engine.Colisiones;
import Engine.GestorColision;
import Engine.Nivel;
import Objetos.Moneda;
import Objetos.Puerta;
import Personajes.Enemigo;
import Personajes.Mario;
import java.util.ArrayList;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
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
            gestor = nivel.getGestor();
            map = new TiledMap("res/tileMaps/Nivel 2.tmx");

            colisiones = new Colisiones(map);
            jugador.setColisiones(colisiones);
            //jugador.setMonedas();
            mario = new Mario(container, colisiones, gestor, 350,1000, 1.0f);
            enemigo = new Enemigo(container, colisiones, gestor, 5550,1000, 0.6f);
            puerta = new Puerta(46,430,0,gestor);
            zoom = 0.65f;
            camara = new Camara(container, map,  jugador, zoom );

            monedas = new ArrayList<Moneda>();
            colocarMonedas(colisiones);
            nivelAct = jugador.getNivel();
                
        
    }

    public void colocarMonedas(Colisiones colisiones)throws SlickException{
        int tileID;
            for (int x = 0; x < map.getWidth(); x++) {
                for (int y = 0; y < map.getHeight(); y++) {
                    //System.out.println("width"+ x );
                    tileID = map.getTileId(x, y, map.getLayerIndex("Monedas"));

                    if(colisiones.getMonedas()[x][y]) {
                       Moneda moneda = new Moneda(x*50,y*50, gestor);
                        System.out.println("Añadida moneda");
                       monedas.add(moneda) ;
                       gestor.registrarCuerpo(moneda);
                    }
                }

            }
            System.out.println("Añadidas: "+ monedas.size());
    }
     
}
