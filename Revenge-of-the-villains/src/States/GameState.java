package States;

import Engine.GestorColisiones;
import Engine.Nivel;
import Juego.EnumStates;
import Niveles.Nivel0;
import Niveles.Nivel1;
import Niveles.Nivel2;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Roberto
 */
public class GameState extends BasicGameState {
    private Nivel nivel;
   

    @Override
    public int getID() {
        return EnumStates.GAME.ordinal();
    }

    /**
     * Metodo que se ejecuta al inicio del Gamestate
     * @param container Contenedor del juego
     * @param game Estado del juego
     * @throws SlickException Si se produce una excepción de Slick2d
     */
    @Override
    public void init(GameContainer container, StateBasedGame game) throws SlickException {

    
        //Si existe un nivel 
        if(nivel != null){     
            //Si el jugador está muerto, se pone el nivel a null para reiniciar todo
            if(nivel.getJugador().isMuerto()){
                GestorColisiones.getInstancia().eliminarElementos();
                nivel = null;
                init(container, game);
            }
            
            //System.out.println("El nivel está pausado? "+ nivel.isPausado());
            //System.out.println("El nivel del jugador es: "+nivel.getJugador().getNivel());
            
            //Si el nivel del juagador es el 1 y no está pausado
            if(nivel.getJugador().getNivel()==1&&!nivel.isPausado()){  
                    
                //Se arranca el nivel 1
                nivel = new Nivel1(container,game, nivel);

            //Si no, si el nivel del juagador es el 2 y no está pausado
            }else if(nivel.getJugador().getNivel()==2&&!nivel.isPausado()){              
                //Se arranca el nivel 1
                nivel = new Nivel2(container,game,nivel);
            //Si no, si el nivel no esta pausado     
            }else if(!nivel.isPausado()){
                //Se pone el nivel a null y se reinicia
                nivel = null;
                init(container, game);
            // Si no, se quita la pausa al juego
            }else nivel.setPausado(false);

                
            //Si el nivel no existe es porque es el nivel 0 y hay que crear al jugador
            //y todo lo demas
        }
        //Si no se ha creado el nivel, se inicializa con nivel 0
        else{
            nivel = new Nivel0(container, game);

        }

        
    }
    
    @Override
    public void render(GameContainer container, StateBasedGame game, Graphics g) throws SlickException {        
        if (nivel!=null)
           nivel.render(g);
            
    }
    @Override
    public void update(GameContainer container, StateBasedGame game, int delta) throws SlickException {  
        if (nivel!=null)
            nivel.update(delta);
       
    }   
 
    
 
    @Override
    public void enter(GameContainer container, StateBasedGame game)throws SlickException{
       // System.out.println("Se ejecuta este método sobreescrito");
       
       container.getInput().clearKeyPressedRecord();
       init(container, game); 
       
   
    }
   
}
