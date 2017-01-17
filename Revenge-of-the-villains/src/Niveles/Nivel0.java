/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Niveles;

import Engine.Camara;
import Engine.Colisiones;
import Engine.GestorColisiones;
import Engine.Nivel;
import Objetos.Puerta;
import Personajes.Enemigo;
import Personajes.EnumTipoEnemigo;
import Personajes.Jugador;
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
        Nivel.container = container;
        super.game = game;
        map = new TiledMap("res/tileMaps/Nivel 1.tmx");
        
        System.out.println("Empieza el nivel 0");
        Colisiones.getInstancia().setMap(map);
        jugador = new Jugador(container,game);

        //Se crea el prototipo de enemigo
        enemigoOriginal = Enemigo.getInstancia();
        GestorColisiones.getInstancia().anularCuerpo(enemigoOriginal);
        /**
         * Se hace una copia de enemigoOriginal por cada enemigo que se va a tener
         * y se modifican sus características
         */
        enemigo1 = (Enemigo) enemigoOriginal.copia();
        enemigo1.setPosX(1573);
        enemigo1.setPosY(1030);
        enemigo1.setNombre("enemigo1");
        
        enemigo2 = (Enemigo) enemigoOriginal.copia();
        enemigo2.setPosX(4421);
        enemigo2.setPosY(916);
        enemigo2.setNombre("enemigo2");
        enemigo2.setCaracteristicas(EnumTipoEnemigo.NORMAL);
               
        puerta = new Puerta(5819,928,0); 
        zoom = 0.65f;
        camara = new Camara(container, map, jugador, zoom);
        nivelAct = jugador.getNivel();
      
    }

}
