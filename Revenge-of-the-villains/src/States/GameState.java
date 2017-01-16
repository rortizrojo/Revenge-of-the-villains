package States;

import Engine.Colisiones;
import Engine.GestorColisiones;
import Engine.IColisionable;
import Engine.Nivel;
import Juego.EnumStates;
import Memento.Conserje;
import Memento.Originador;
import Memento.Partida;
import Niveles.Nivel0;
import Niveles.Nivel1;
import Niveles.Nivel2;
import Objetos.Moneda;
import java.util.ArrayList;
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
    private Originador originador;
    private Conserje conserje;
    private boolean partidaGuardada;
    private ArrayList<Moneda> monedas;
   

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
                ArrayList lista = new ArrayList<IColisionable>();
                GestorColisiones.getInstancia().getListaColisionables().forEach((objeto) -> {
                    lista.add(objeto);
                });
                //System.out.println("Monedas antes de guardar" + nivel.getMonedas().size());
                guardarPartida(nivel,lista );
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
            if(!partidaGuardada)
                nivel = new Nivel0(container,game);
            else{
                Partida partida = recuperarPartida() ;
                nivel = partida.getNivel();
                nivel.getJugador().setMuerto(false);
                nivel.getJugador().setVida(100);
                nivel.getJugador().setPosX(200);
                nivel.getJugador().setPosY(200);
                //nivel.getJugador().getInventario().borrarMonedas();
                //System.out.println("Monedas: " + nivel.getMonedas().size());
                
                //System.out.println("elementos colisoinables "+recuperarPartida().getLista().size() );
                partida.getLista().forEach((objeto) -> {
                    GestorColisiones.getInstancia().getListaColisionables().add(objeto);
                });
    
            
            }
                
        
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
    
    public void guardarPartida(Nivel nivel,ArrayList<IColisionable> lista){
         
        try {
            //partidas
            Partida p1 = new Partida(nivel,lista);

            //originador
            originador = new Originador();

            //conserje
            conserje = new Conserje();

            //establecemos partidas y guardamos sus recuerdos para poder recuperarlos
            originador.setPartida(p1);
            conserje.setRecuerdo(originador.crearRecuerdo());
            partidaGuardada = true;
            System.out.println("Partida guardada: " + originador.getPartida().toString());
            
            
            //recuperamos la partida 1

        } catch (Exception e) {
            System.out.println("Error: "+e.toString());
        }
        
        
    }
    
    public Partida recuperarPartida(){
        originador.setRecuerdo(conserje.getRecuerdo(0));
        System.out.println("Partida recuperada: " + originador.getPartida().toString());
        return originador.getPartida();
    }
   
}
