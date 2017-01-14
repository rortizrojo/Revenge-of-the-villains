/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Niveles;

import Engine.Camara;
import Engine.Colisiones;
import Engine.Nivel;
import Objetos.Puerta;
import Personajes.Enemigo;
import Personajes.Jugador;
import Personajes.Mario;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.tiled.TiledMap;

/**
 * Clase que inicializa datos del nivel 1
 * @author Roberto
 */
public class Nivel0 extends Nivel {
    
    

    //private Puerta puerta;
    public Nivel0( GameContainer container, StateBasedGame game)throws SlickException{
        super(); 
        super.container = container;
        super.game = game;
        map = new TiledMap("res/tileMaps/Nivel 1.tmx");
      //  puerta = new Puerta (5819,928,0,gestor);
        
        
        colisiones = new Colisiones(map);
        jugador = new Jugador(container,game, colisiones);
        mario = new Mario(container, colisiones,  4421, 916, 1.2f);
        enemigo = new Enemigo(container, colisiones,  1573, 1030, 0.6f);
        puerta = new Puerta(5819,928,0); 
        zoom = 0.65f;
        camara = new Camara(container, map, jugador, zoom);
        nivelAct = jugador.getNivel();
      
    }

}
