/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import Juego.EnumStates;
import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Roberto
 */
public class GameOverState extends BasicGameState {

    private Image gameoverfondo;
    private Sound opcion;
    private Sound enter;
    private Font font;
    /**
     * The menu options
     */
    private final String[] options = new String[]{"Restart","Exit"};
    /**
     * The index of the selected option
     */
    private int selected;

    @Override
    public int getID() {
        return EnumStates.GAMEOVER.ordinal();
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        gameoverfondo = new Image("res/images/states/ImagenGameOver.png", true);
        font = new AngelCodeFont("res/fonts/demo2.fnt", "res/fonts/demo2_00.tga");
        opcion = new Sound("res/sounds/opcion.wav");
        enter = new Sound("res/sounds/enter.wav");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        gameoverfondo.draw();
        g.setFont(font);
        g.setColor(Color.white);

        for (int opcion = 0; opcion < options.length; opcion++) {
            g.drawString(options[opcion], 400 - (font.getWidth(options[opcion]) / 2), 200 + (opcion * 50));
            if (selected == opcion) {
                g.drawRect(200, 190 + (opcion * 50), 400, 50);
            }
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(EnumStates.MENU.ordinal(), new FadeOutTransition(), new FadeInTransition());
        
        }
        if (container.getInput().isKeyPressed(Input.KEY_ENTER)) {
            enter.play();
            switch (selected) {

                case 0:
                    sbg.enterState(EnumStates.GAME.ordinal(), new FadeOutTransition(), new FadeInTransition());
                    break;
               
                case 1:
                    container.exit();
                default:
                    break;

            }
        }
        //System.out.println("seleccionado" + selected);
    }

    @Override
    public void keyReleased(int key, char c) {
        
        if (key == Input.KEY_DOWN) {
            opcion.play();
            selected++;
            if (selected >= options.length) {
                selected = 0;
            }
        }
        if (key == Input.KEY_UP) {
            opcion.play();
            selected--;
            if (selected < 0) {
                selected = options.length - 1;
            }

        }
       
    }
}
