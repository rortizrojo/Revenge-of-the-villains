/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package States;

import org.newdawn.slick.AngelCodeFont;
import org.newdawn.slick.Color;
import org.newdawn.slick.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Roberto
 */
public class PausaState extends BasicGameState {

    private Image fondopausa;

    private Font font;
    /**
     * The menu options
     */
    private final String[] options = new String[]{ "Continuar Juego", "Instructions", "Exit"};
    /**
     * The index of the selected option
     */
    private int selected;

    @Override
    public int getID() {
        return 7;

    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        fondopausa = new Image("res/images/states/ImagenFondoPausa.png", true);
        font = new AngelCodeFont("testdata/demo2.fnt", "testdata/demo2_00.tga");
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondopausa.draw();
        g.setFont(font);
        g.setColor(Color.yellow);

        for (int i = 0; i < options.length; i++) {
            g.drawString(options[i], 400 - (font.getWidth(options[i]) / 2), 200 + (i * 50));
            if (selected == i) {
                g.drawRect(200, 190 + (i * 50), 400, 50);
            }
        }
    }

    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());

        }
        if (container.getInput().isKeyPressed(Input.KEY_H)) {
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        if (container.getInput().isKeyPressed(Input.KEY_ENTER)) {
            switch (selected) {

                case 0:
                    
                    sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
                    break;
                
             
                case 1:
                    sbg.enterState(5, new FadeOutTransition(), new FadeInTransition());
                    break;
                case 2:
                    sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
                    break;
                default:
                    break;

            }
        }
    }

    @Override
    public void keyReleased(int key, char c) {
        if (key == Input.KEY_DOWN) {
            selected++;
            if (selected >= options.length) {
                selected = 0;
            }
        }
        if (key == Input.KEY_UP) {
            selected--;
            if (selected < 0) {
                selected = options.length - 1;
            }

        }
    }
    
  
}
