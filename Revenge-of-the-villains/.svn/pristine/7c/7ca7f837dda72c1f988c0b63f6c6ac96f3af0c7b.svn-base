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
import Objetos.Puerta;
import Personajes.Enemigo;
import Personajes.Jugador;
import Personajes.Mario;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Roberto
 */
public class Nivel0 extends Nivel {
    
    

    //private Puerta puerta;
    public Nivel0( GameContainer container, StateBasedGame game/*GestorColision gestor*/)throws SlickException{
        super(); 
        super.container = container;
        super.game = game;
        gestor = new GestorColision();
        map = new TiledMap("res/tileMaps/Nivel 1.tmx");
      //  puerta = new Puerta (5819,928,0,gestor);
        
        
        colisiones = new Colisiones(map);
        jugador = new Jugador(container,game, colisiones, gestor);
        mario = new Mario(container, colisiones, gestor, 4421, 916, 1.2f);
        enemigo = new Enemigo(container, colisiones, gestor, 1573, 1030, 0.6f);
        puerta = new Puerta(5819,928,0,gestor); 
        zoom = 0.65f;
        camara = new Camara(container, map, jugador, zoom);
        nivelAct = jugador.getNivel();
      
    }

}
