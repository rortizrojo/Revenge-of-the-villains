package States;

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
        return 5;

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
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());
        }
        if (container.getInput().isKeyPressed(Input.KEY_H)) {
            sbg.enterState(2, new FadeOutTransition(), new FadeInTransition());
        }
        if (container.getInput().isKeyPressed(Input.KEY_P)) {
            sbg.enterState(7, new FadeOutTransition(), new FadeInTransition());
        }
    }
}
