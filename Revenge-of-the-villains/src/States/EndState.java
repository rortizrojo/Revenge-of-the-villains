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
public class EndState extends BasicGameState {

    private Image IntroSTATE;

    @Override
    public int getID() {
        return 8;
    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        IntroSTATE = new Image("res/images/states/EndSTATE.png",true);

    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        IntroSTATE.draw();       
        //g.drawString("\nPulse ESPACIO ",650, 550);

    }

    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {

        if (container.getInput().isKeyPressed(Input.KEY_SPACE)) {
            sbg.enterState(3, new FadeOutTransition(), new FadeInTransition());

        }
    }
}
