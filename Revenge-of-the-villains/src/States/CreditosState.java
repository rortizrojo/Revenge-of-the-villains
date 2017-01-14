package States;

import Juego.EnumStates;
import java.awt.Font;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.UnicodeFont;
import org.newdawn.slick.font.effects.ColorEffect;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;
import org.newdawn.slick.state.transition.FadeInTransition;
import org.newdawn.slick.state.transition.FadeOutTransition;

/**
 *
 * @author Roberto
 */
public class CreditosState extends BasicGameState {

    private Image fondocreditos;
    private UnicodeFont fuente;
    private UnicodeFont fuente2;
    
    
    @Override
    public int getID() {
        return EnumStates.CREDITOS.ordinal();

    }

    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
      
        java.awt.Font fuenteAWT = new java.awt.Font("Comic Sans MS",Font.BOLD, 24);
        java.awt.Font fuenteAW = new java.awt.Font("Comic Sans MS",Font.BOLD, 15);
        fuente = new UnicodeFont (fuenteAWT);
        fuente2= new UnicodeFont (fuenteAW);
        fuente2.addAsciiGlyphs();
        fuente.addAsciiGlyphs();
        ColorEffect colorFuente = new ColorEffect(java.awt.Color.cyan);
        fuente2.getEffects().add(colorFuente);
        fuente.getEffects().add(colorFuente);
        fuente.loadGlyphs();
        fuente2.loadGlyphs();
        fondocreditos = new Image("res/images/states/ImagenFondoCreditos.png", true);
    }

    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {
        fondocreditos.draw();
        
        fuente.drawString(150, 300,"Roberto Ortiz");
        fuente.drawString(150, 330,"Felipe Arango");
        g.drawString("\nPulse ESC para volver al menú principal ", 440, 550);
    }

    @Override
    public void update(GameContainer container, StateBasedGame sbg, int delta) throws SlickException {
        if (container.getInput().isKeyPressed(Input.KEY_ESCAPE)) {
            sbg.enterState(EnumStates.MENU.ordinal(), new FadeOutTransition(), new FadeInTransition());

        }
    }

}
