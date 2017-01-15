package Personajes;

import Engine.Camara;
import Engine.GestorColisiones;
import Engine.IColisionable;
import Armas.TiraBolas;
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
    
    private float distancia;
    private float daño;  
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

    public Enemigo(GameContainer container, float posX, float posY, EnumTipoEnemigo tipoEnemigo ) throws SlickException {
        
        super(container);
        gestor = GestorColisiones.getInstancia();
        gestor.registrarCuerpo(this);
        
        tiraBolas = new TiraBolas(this.daño);
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
    
        botonDerecha = false;
        botonIzquierda = false;
        botonArriba = false;
        
        saltar =false;
        
        tiraBolas.update(delta);
        tiraBolas.delete(map);
        sincronizarArea();
        gestor.comprobarColisiones();
        
        if(vida>0){
        
            // Según el estado realiza unas acciones u otras
            switch (estado){
                case 0: //VIGILANDO -> caminar despacio de lado a lado                          
                    if ((conectadoIzquierda || conectadoDerecha) && contador>30){
                        cambiar_dir = !cambiar_dir;
                        contador = 0;
                    }
                    
                    if(cambiar_dir)
                        botonIzquierda = true;            
                    else 
                        botonDerecha = true;
                    
                    if(contador<35)
                        contador++;
                    //System.out.println("estado 0");
                    break;   

                case 1: //EN GUARDIA -> se para y empieza a mirar a los lados buscando al jugador
                    mirandoIzquierda = contador<100;            
                    contador++;
                    if (contador > 200)
                        contador  = 0; 
                    //System.out.println("estado 1");
                    break;

                case 2: //ACERCANDOSE -> ve al personaje y se empieza a acercar
                    if (jugador_a_izq == true){
                        mirandoIzquierda = true;
                        if (conectadoSuelo && !conectadoIzquierda)//->puede avanzar 
                            botonIzquierda = true; 
                    }
                    if (jugador_a_izq == false){
                        mirandoIzquierda = false;
                        if(conectadoSuelo && !conectadoDerecha) //-> puede avanzar
                            botonDerecha = true;
                    }
                    if (!jugador_a_alt && jugador_a_igualX){//Esta en misma X pero muy arriba->se para                 
                            botonIzquierda = false;
                            botonDerecha = false;
                        }  
                    //System.out.println("estado 2");
                    break;

                case 3: //ATACANDO -> ataca a jugador
                    if (jugador_a_izq == true){
                        if (distancia >100 && jugador_a_alt && conectadoSuelo &&
                                !conectadoIzquierda)
                            botonIzquierda = true; 
                        if (distancia >100 && !jugador_a_alt){
                            mirandoIzquierda = true;
                            saltar = true; 
                        }
                        if (cont_disparo == 30){
                            tiraBolas.disparar(this, -500);       
                        }
                    }
                    else{ 
                        if (distancia >100 && jugador_a_alt && conectadoSuelo  && 
                                !conectadoDerecha)
                            botonDerecha = true;
                        if (distancia >100 && !jugador_a_alt){
                            mirandoIzquierda = false;
                            saltar = true; 
                        }
                        if (cont_disparo == 30){
                            tiraBolas.disparar(this, +500);
                        }
                    }       
                    cont_disparo++;
                    if (cont_disparo > 30)
                        cont_disparo=0;

                    //System.out.println("estado 3");
                    break;

                case 4: //FURIOSO -> ataca a jugador con mas fuerza
                    
                    if (jugador_a_izq == true){
                        if (distancia >50 && jugador_a_alt && conectadoSuelo && 
                                !conectadoIzquierda)
                            botonIzquierda = true; 
                        if (distancia >50 && !jugador_a_alt){
                            mirandoIzquierda = true;
                            saltar = true; 
                        }
                        if (cont_disparo == 5){
                            tiraBolas.disparar(this, -500);       
                        }
                    }
                    else{ 
                        if (distancia >50 && jugador_a_alt && conectadoSuelo  &&
                                !conectadoDerecha)
                            botonDerecha = true;
                        if (distancia >50 && !jugador_a_alt){
                            mirandoIzquierda = false;
                            saltar = true; 
                        }
                        if (cont_disparo == 5){
                            tiraBolas.disparar(this, +500);
                        }
                    }  
                    cont_disparo++;
                    if (cont_disparo > 5)
                        cont_disparo=0;
                    //System.out.println("estado 4");
                    break;

                case 5: //HUYENDO           
                    
                    if (jugador_a_izq == true){
                        botonDerecha = true; 
                    }
                    else{ 
                        botonIzquierda = true;                  
                    }   
                    //System.out.println("estado 5");
                    break;
            }
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
        
        if (vida>65 && distancia<=700 && distancia > 680) 
            estado = 1; //ESTADO EN GUARDIA = 1
        else if (vida>65 && distancia<=680 && distancia > 400) 
            estado = 2; //ESTADO ACERCANDOSE = 2
        else if (vida>65 && distancia<=680) 
            estado = 3; //ESTADO ATACANDO = 3
        else if (vida<=65 && vida>5 && distancia<=680) 
            estado = 4; //ESTADO FURIOSO = 4
        else if (vida<=5) 
            estado = 5; //ESTADO HUYENDO = 5 
        else
            estado = 0; //ESTADO VIGILANDO = 0
    }     
    
    @Override
    public Shape getAreaColision() {
       return areaColision; 
    }

    @Override
    public void alColisionar(IColisionable colision) {
        if (colision.getClass().getName().equals("Armas.Bala"))
            if(vida>0){
                vida = vida - 1.2f;
                if(vida<=0){
                   gestor.anularCuerpo(this);
                   muerteMario.play();
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
                this.daño= 0.6f;
                this.escala = 1.8f;
                break;
            case NORMAL: 
                this.daño= 0.9f;
                this.escala = 2.3f;
                break;
            default: 
                this.daño= 1.2f;
                this.escala = 2.8f;
                break;
            
        }
        anchoDibujado = anchoSprite*escala;
        altoDibujado = altoSprite*escala;
        
        areaColision = new Rectangle(posX, posY,anchoDibujado,altoDibujado);
        
        
    }
    

    
    
    
    
     @Override
    public Object copia() throws SlickException{
        return new Enemigo(this.container,this.posX, this.posY, this.tipoEnemigo );
    }
}
