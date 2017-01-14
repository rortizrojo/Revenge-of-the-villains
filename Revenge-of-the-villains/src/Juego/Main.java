/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Juego;

import States.CreditosState;
import States.EndState;
import States.GameOverState;
import States.GameState;
import States.HowToPlayState;
import States.IntroState;

import States.MenuState;
import States.PausaState;
import States.PuntuacionState;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.SlickException;

import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Roberto
 */
public class Main extends StateBasedGame {

    public Main() {
        super("Revenge of the villains");
    }

    public static void main(String[] args) {

        try {
            AppGameContainer appgc = new AppGameContainer(new Main());
            appgc.setDisplayMode(800, 600, false);
            appgc.setTargetFrameRate(60);
            appgc.setShowFPS(false);
            appgc.setIcon("res/images/icon.png");
            appgc.start();
        } catch (SlickException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void initStatesList(GameContainer container) throws SlickException {
        this.addState(new IntroState());            //ID 0
        this.addState(new MenuState());             //ID 1
        this.addState(new GameState());             //ID 2
        this.addState(new CreditosState());         //ID 3
        this.addState(new PuntuacionState());       //ID 4
        this.addState(new HowToPlayState());        //ID 5
        this.addState(new PausaState());            //ID 6
        this.addState(new GameOverState());         //ID 7
        this.addState(new EndState());              //ID 8
    }

}
