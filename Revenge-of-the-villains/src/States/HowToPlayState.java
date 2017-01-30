package States;

import Juego.EnumStates;
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
public class HowToPlayState extends BasicGameState {

    private Image howtoplay;

    @Override
    public int getID() {
        return EnumStates.INSTRUCCIONES.ordinal();

    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        howtoplay = new Image("res/images/states/ImagenHowToPlay.png", true);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        howtoplay.draw();
    }

    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(EnumStates.MENU.ordinal(), new FadeOutTransition(), new FadeInTransition());
        }
        if (container.getInput().isKeyPressed(Input.KEY_H)) {
            sbg.enterState(EnumStates.GAME.ordinal(), new FadeOutTransition(), new FadeInTransition());
        }
        if (container.getInput().isKeyPressed(Input.KEY_P)) {
            sbg.enterState(EnumStates.PAUSA.ordinal(), new FadeOutTransition(), new FadeInTransition());
        }
    }
}
