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
public class PuntuacionState extends BasicGameState {

    private Image fondopuntuacion;

    @Override
    public int getID() {
        return 4;

    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
        
        fondopuntuacion = new Image("res/images/states/ImagenFondoHighScore.png", true);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondopuntuacion.draw();
    }

    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(1, new FadeOutTransition(), new FadeInTransition());

        }
        if (container.getInput().isKeyPressed(Input.KEY_P)) {
            sbg.enterState(7, new FadeOutTransition(), new FadeInTransition());
    }
    }
}
