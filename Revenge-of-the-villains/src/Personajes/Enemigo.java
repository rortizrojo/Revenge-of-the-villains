package Personajes;

import Engine.Camara;
import Engine.GestorColisiones;
import Engine.IColisionable;
import Armas.TiraBolas;
import EstadosEnemigo.*;
import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Rectangle;

/**
 *
 * @author Roberto
 */
public class Enemigo extends Personaje implements IColisionable,Copiable {
    
    private Estado estadoActual; // Estado actual
    private static Enemigo instancia;
    private float distancia;
    private float dañoArma;
    private float dañoTacto;
    private float escala;
    //jugador_a_izq=true -> jugador a izq. de Mario
    private boolean jugador_a_izq;
    //jugador_a_alt=true -> jugador a misma altura Mario
    private boolean jugador_a_alt;
    private boolean jugador_a_igualX; 
    private boolean saltar;
    private boolean cambiar_dir;
    private boolean muerto;
   
    private int estado, cont_disparo, cont_salto, contador,cont_muerte;
    
    ////////Sprites y animaciones:
    private final SpriteSheet sheetCorriendoDerecha;
    private final SpriteSheet sheetCorriendoIzquierda;
    private final SpriteSheet sheetParadoIzquierda;
    private final SpriteSheet sheetParadoDerecha;
    private final SpriteSheet sheetMuerte_Izq;
    private final SpriteSheet sheetMuerte_Der;
   
    private final Animation corriendoDerecha;
    private final Animation corriendoIzquierda;
    private final Animation paradoDerecha;
    private final Animation paradoIzquierda;
    private final Animation muerte_Izq;
    private final Animation muerte_Der;
    
    ///////Sonidos
    private final Sound saltoMario;
    private final Sound muerteMario;
    
    
    private boolean mirandoIzquierda;

    private Rectangle areaColision;
    private final GestorColisiones gestor;
    private final TiraBolas tiraBolas;
    private EnumTipoEnemigo tipoEnemigo;

    
    public Enemigo(GameContainer container,String nombre, float posX, float posY, EnumTipoEnemigo tipoEnemigo ) throws SlickException {
        
        super(container);
        gestor = GestorColisiones.getInstancia();
        gestor.registrarCuerpo(this);
        
        this.nombre = nombre;
        
        vida = 100;
        cont_muerte = 100;
        muerto=false;
        
        //Sonidos
        saltoMario = new Sound("res/sounds/saltoMario.wav");
        muerteMario = new Sound("res/sounds/muerteMario.wav");
        
        //Posicion inicial
        this.posX = posX;
        this.posY = posY;
        
        //Tamaño del sprite .png ver en propiedades de la imagen
        anchoSprite = 28;
        altoSprite = 39;
        //Escalado del sprite, ajustar con el numero
        setCaracteristicas(tipoEnemigo);
        
        tiraBolas = new TiraBolas(this.dañoArma);
        
        //Sheets y animaciones necesarias para el movimiento:
        sheetCorriendoDerecha = new SpriteSheet("res/animations//Mario/corriendoDerechaSprite.png", (int) anchoSprite, (int) altoSprite);
        sheetCorriendoIzquierda = new SpriteSheet("res/animations//Mario/corriendoIzquierdaSprite.png", (int) anchoSprite, (int) altoSprite);
        sheetParadoIzquierda = new SpriteSheet("res/animations//Mario/paradoIzquierdaSprite.png", (int) anchoSprite, (int) altoSprite);
        sheetParadoDerecha = new SpriteSheet("res/animations//Mario/paradoDerechaSprite.png", (int) anchoSprite, (int) altoSprite); 
        sheetMuerte_Izq = new SpriteSheet("res/animations/Mario/muriendoIzquierda.png", (int) anchoSprite, (int) altoSprite);
        sheetMuerte_Der = new SpriteSheet("res/animations/Mario/muriendoDerecha.png", (int) anchoSprite, (int) altoSprite);
        //Crear la animaciones
        corriendoDerecha = new Animation();
        corriendoIzquierda = new Animation();
        paradoIzquierda = new Animation();
        paradoDerecha = new Animation();
        muerte_Izq = new Animation();
        muerte_Der = new Animation();
        //Cargas los frames de los sprites en las animaciones
        //ej.--> for (int i = 0; i < n_framesPorSprite-1; i++)
        for (int i = 0; i < sheetCorriendoDerecha.getHorizontalCount(); i++) { 
            corriendoDerecha.addFrame(sheetCorriendoDerecha.getSprite(i, 0), 50);
        }

        for (int i = 0; i < sheetParadoIzquierda.getHorizontalCount(); i++) {
            paradoIzquierda.addFrame(sheetParadoIzquierda.getSprite(i, 0), 150);
        }

        for (int i = 0; i < sheetParadoDerecha.getHorizontalCount(); i++) {
            paradoDerecha.addFrame(sheetParadoDerecha.getSprite(i, 0), 150);
        }

        for (int i = 0; i < sheetCorriendoIzquierda.getHorizontalCount(); i++) {
            corriendoIzquierda.addFrame(sheetCorriendoIzquierda.getSprite(i, 0), 50);
        }    
        
        for (int i = 0; i < sheetMuerte_Der.getHorizontalCount(); i++) {
            muerte_Der.addFrame(sheetMuerte_Der.getSprite(i, 0), 150);
        }
        
        for (int i = sheetMuerte_Izq.getHorizontalCount()-1; i >= 0; i--) {
            
            muerte_Izq.addFrame(sheetMuerte_Izq.getSprite(i, 0), 150);
        }
    }

      
     /**
     * Secuencia de acciones que realiza el personaje
     */
    @Override
    public void acciones() {
    
        tiraBolas.update(delta);
        tiraBolas.delete(map);
        if(vida>0){
            botonDerecha = false;
            botonIzquierda = false;
            botonArriba = false;

            saltar =false;

            sincronizarArea();
            gestor.comprobarColisiones();
            
            // Según el estado realiza unas acciones u otras
            if(estadoActual != null)
                estadoActual.ejecutar(this);

            //Saltar con un frecuencia 
            if (saltar){
                if (cont_salto==50)
                    botonArriba =true;
                cont_salto++; 
                if (cont_salto>50)
                    cont_salto=0;       
            }//Sonidos
        
            if(saltando)
                if(!saltoMario.playing())
                    saltoMario.play(1f, 0.2f);
           
        }
        
    }
         
    @Override
    public void render(int delta,Graphics g, Camara camara) throws SlickException {

        this.delta = delta;
        //Si muere
        if(vida <= 0){
            if(conectadoSuelo){
                velocidadY = 0;
                velocidadX = 0;
                grav = 0;
            }
            if (mirandoIzquierda){
                //Carga animacion de muerte
                muerte_Izq.draw(posX, posY, anchoDibujado, altoDibujado);

                if(muerte_Izq.getFrame() == muerte_Izq.getFrameCount()-1) //muerte.getFrame()==nºframe-1
                    muerte_Izq.stop();
                if (muerte_Izq.isStopped()){
                    cont_muerte -=1;
                    if (cont_muerte <=0)
                        muerto = true;
                }
            }
            else{
                //Carga animacion de muerte
                muerte_Der.draw(posX, posY, anchoDibujado, altoDibujado);

                if(muerte_Der.getFrame() == muerte_Der.getFrameCount()-1) //muerte.getFrame()==nºframe-1
                    muerte_Der.stop();
                if (muerte_Der.isStopped()){
                    cont_muerte -=1;
                    if (cont_muerte <=0)
                        muerto = true;
                }
            }
        } else if(botonDerecha){
            corriendoDerecha.draw(posX, posY, anchoDibujado, altoDibujado);
            mirandoIzquierda = false;
        } else if (botonIzquierda) {
            corriendoIzquierda.draw(posX, posY, anchoDibujado, altoDibujado);
            mirandoIzquierda = true;
        } else {
            if (mirandoIzquierda) {
                paradoIzquierda.draw(posX, posY, anchoDibujado, altoDibujado);
            } else {
                paradoDerecha.draw(posX, posY, anchoDibujado, altoDibujado);
            }
        }
 
    tiraBolas.render();
           
    }

    public void actualizarEstado(float jugadorX, float jugadorY) {  
     
        distancia = (float)Math.sqrt((Math.pow(posX-jugadorX,2))+(Math.pow(posY-jugadorY,2)));    
        //jugador_a_izq=true -> jugador a la izq. de Mario
        jugador_a_izq = jugadorX < posX; 
        //jugador_a_alt=true -> jugador a la misma altura Mario
        jugador_a_alt = (Math.abs(jugadorY-posY) < 50);  
        jugador_a_igualX = (Math.abs(jugadorX-posX) < 50);
        
        if (vida>65 && distancia<=700 && distancia > 680) { 
            //ESTADO EN GUARDIA = 1
            estadoActual = new EstadoGuardia();
        }
        else if (vida>65 && distancia<=680 && distancia > 400) { 
            //ESTADO ACERCANDOSE = 2
            estadoActual = new EstadoAcercandose();
        }
        else if (vida>65 && distancia<=680) {
            //ESTADO ATACANDO = 3  
            estadoActual = new EstadoAtacando();
        }
        else if (vida<=65 && vida>5 && distancia<=680) {
            //ESTADO FURIOSO = 4
            estadoActual = new EstadoFurioso();
        } 
        else if (vida<=5) {
            //ESTADO HUYENDO = 5 
            estadoActual = new EstadoHuyendo();
        } 
        else{
            //ESTADO VIGILANDO = 0
            estadoActual = new EstadoVigilando();
        }
            
    }     
    
    @Override
    public Shape getAreaColision() {
       return areaColision; 
    }

    @Override
    public void alColisionar(IColisionable colision) {
        if (colision.getClass().getName().equals("Armas.Bala")){
            Jugador.getInstancia().setPuntuacion(Jugador.getInstancia().getPuntuacion() + 50);
            if(vida>0){
                vida = vida - 1.2f;
                if(vida<=0){
                   gestor.anularCuerpo(this);
                   muerteMario.play();
                   Jugador.getInstancia().setPuntuacion(Jugador.getInstancia().getPuntuacion()+200);
                }
            }
        }
        
                
    }
    public boolean isMuerto() {
        return muerto;
    }

    @Override
    public void sincronizarArea() {
        areaColision.setX(posX);
        areaColision.setY(posY);
    }

    public EnumTipoEnemigo getTipoEnemigo() {
        return tipoEnemigo;
    }

    public void setCaracteristicas(EnumTipoEnemigo tipoEnemigo) {
        this.tipoEnemigo = tipoEnemigo;
        switch(tipoEnemigo){
            case DEBIL: 
                this.dañoArma= 0.6f;
                this.dañoTacto= 0.2f;
                this.escala = 1.8f;
                break;
            case NORMAL: 
                this.dañoArma= 0.8f;
                this.dañoTacto= 0.4f;
                this.escala = 2.3f;
                break;
            default: 
                this.dañoArma= 1.0f;
                this.dañoTacto= 0.6f;
                this.escala = 2.8f;
                break;
            
        }
        anchoDibujado = anchoSprite*escala;
        altoDibujado = altoSprite*escala;
        
        areaColision = new Rectangle(posX, posY,anchoDibujado,altoDibujado);
        
        
    }
    
    @Override
    public Object copia() throws SlickException{
        return new Enemigo(this.container,this.nombre, this.posX, this.posY, this.tipoEnemigo );
    }
    
    
    /**
     * Devuelve la instancia de la clase.
     * Acceso controlado a la única instancia. 
     * Otras clases que quieran una referencia a la única instancia de la clase Singleton conseguirán esa instancia 
     * llamando al método estático getInstancia de la clase. 
     * @return Instancia de la clase.
     * @throws org.newdawn.slick.SlickException
     */
    public static Enemigo getInstancia() throws SlickException {
        if(instancia == null)
            return new Enemigo(container, "enemigoBase" , 0, 0, EnumTipoEnemigo.DEBIL);
            
        return instancia;
    }

    public float getDañoArma() {
        return dañoArma;
    }

    public float getDañoTacto() {
        return dañoTacto;
    }

    public float getDistancia() {
        return distancia;
    }

    public int getContador() {
        return contador;
    }

    public void setContador(int contador) {
        this.contador = contador;
    }

    public boolean isCambiar_dir() {
        return cambiar_dir;
    }

    public void setCambiar_dir(boolean cambiar_dir) {
        this.cambiar_dir = cambiar_dir;
    }

    public boolean isMirandoIzquierda() {
        return mirandoIzquierda;
    }

    public void setMirandoIzquierda(boolean mirandoIzquierda) {
        this.mirandoIzquierda = mirandoIzquierda;
    }
    

    public boolean isJugador_a_izq() {
        return jugador_a_izq;
    }

    public boolean isJugador_a_alt() {
        return jugador_a_alt;
    }

    public void setSaltar(boolean saltar) {
        this.saltar = saltar;
    }

    public TiraBolas getTiraBolas() {
        return tiraBolas;
    }

    public int getCont_disparo() {
        return cont_disparo;
    }

    public void setCont_disparo(int cont_disparo) {
        this.cont_disparo = cont_disparo;
    }

    public boolean isJugador_a_igualX() {
        return jugador_a_igualX;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    

    
    
    
    
}
