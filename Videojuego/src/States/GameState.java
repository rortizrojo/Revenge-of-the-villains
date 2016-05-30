package States;

import Engine.Nivel;
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
    private GameContainer container;
    private StateBasedGame game;
    private Nivel nivel;
   

    public int getID() {
        return 2;
    }

    
    public void init(GameContainer container, StateBasedGame game) throws SlickException {
     
        this.container = container;
        this.game = game;
        
        System.out.println("Me estoy reiniciando");
    
        //Si el jugador no se ha creado
        if(nivel != null){     
                
                if(nivel.getJugador().isMuerto()){
                    System.out.println("El juego no esta pausado o el juegador esta muerto"
                            + "por lo que pongo el nuvel a null y reinicio");
                    nivel = null;
                    init(container, game);
                }

                //Si el nivel del juagador es el 1
                System.out.println("El valor de pausado es: "+ nivel.isPausado());
                System.out.println("El nivel del jugador es: "+nivel.getJugador().getNivel());
                if(nivel.getJugador().getNivel()==1&&!nivel.isPausado()){  
                    
                    System.out.println("Arranca nivel 1");
                    
                    nivel = new Nivel1(container,game, nivel);

                //Si el nivel del juagador es el 2
                }else if(nivel.getJugador().getNivel()==2&&!nivel.isPausado()){              
                    System.out.println("Arranca nivel 2");
                    nivel = new Nivel2(container,game,nivel);

                }else if(!nivel.isPausado()){
                    System.out.println("El juego no esta pausado o el juegador esta muerto"
                            + "por lo que pongo el nuvel a null y reinicio");
                    nivel = null;
                    init(container, game);
                }else nivel.setPausado(false);

                
            //Si el jugador no existe es porque es el nivel 0 y hay que crear al jugador
                //y todo lo demas
            }
            else{
                System.out.println("Como el nivel es null inicializo el nivel 0 ");
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
        System.out.println("Se ejecuta este método sobreescrito");
       container.getInput().clearKeyPressedRecord();
       init(container, game); 
       
   
    }
   
}
