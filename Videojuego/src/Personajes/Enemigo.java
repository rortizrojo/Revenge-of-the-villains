package Personajes;

import Engine.Camara;
import Engine.Colisiones;
import Engine.GestorColision;
import Engine.IColisionable;
import Armas.TiraBolas;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
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
public class Enemigo extends Personaje implements IColisionable {
                                          //jugador_a_izq=true -> jugador a izq. de Mario
    private float distancia, daño;    //jugador_a_alt=true -> jugador a misma altura Mario
    private boolean jugador_a_izq,jugador_a_alt, jugador_a_igualX, saltar, cambiar_dir, muerto; 
    private int estado, cont_disparo, cont_salto, contador,cont_muerte;
    private SpriteSheet sheet;
    private Animation animation;
    private int tiempoDisparo;
    
    ////////Sprite y animaciones:
    private SpriteSheet sheetCorriendoDerecha;
    private SpriteSheet sheetCorriendoIzquierda;
    private SpriteSheet sheetParadoIzquierda;
    private SpriteSheet sheetParadoDerecha;
    private SpriteSheet sheetMuerte_Izq;
    private SpriteSheet sheetMuerte_Der;
    private Animation corriendoDerecha;
    private Animation corriendoIzquierda;
    private Animation paradoDerecha;
    private Animation paradoIzquierda;
   
    private Animation muerte_Izq;
    private Animation muerte_Der;
    
    private Sound saltoMario;
    private Sound correrMario1;
    private Sound correrMario2;
    private Sound muerteMario;
    
    private boolean der;
    /////////

    private boolean puedeDisparar;
    private boolean mirandoIzquierda;

    private Rectangle areaColision;
    private GestorColision gestor;

      
    private TiraBolas tiraBolas;
    

    public Enemigo(GameContainer container, Colisiones colisiones, GestorColision gestor, float posX, float posY, float daño ) throws SlickException {
        
        super(container,colisiones);
        this.gestor = gestor;
        gestor.registrarCuerpo(this);

        tiraBolas = new TiraBolas(daño);
        tiempoDisparo = 30;
        puedeDisparar = false;
        vida = 100;
        cont_muerte = 100;
        muerto=false;
        
        //Sonidos
        saltoMario = new Sound("res/sounds/saltoMario.wav");
        correrMario1  = new Sound("res/sounds/pasoMario1.wav");
        correrMario2 = new Sound("res/sounds/pasoMario2.wav");
        muerteMario = new Sound("res/sounds/muerteMario.wav");
        
        //Posicion inicial
        this.posX = posX;
        this.posY = posY;
        
        //Daño
        this.daño= daño;
      
        //Tamaño del sprite .png ver en propiedades de la imagen
        anchoSprite = 28;
        altoSprite = 39;
        //Escalado del sprite, ajustar con el numero
        anchoDibujado = anchoSprite*2.0f;
        altoDibujado = altoSprite*2.0f;

        areaColision = new Rectangle(posX, posY,anchoDibujado,altoDibujado);
  
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
        for (int i = 0; i < 6; i++) { 
            corriendoDerecha.addFrame(sheetCorriendoDerecha.getSprite(i, 0), 50);
        }

        for (int i = 0; i < 2; i++) {
            paradoIzquierda.addFrame(sheetParadoIzquierda.getSprite(i, 0), 150);
        }

        for (int i = 0; i < 5; i++) {
            paradoDerecha.addFrame(sheetParadoDerecha.getSprite(i, 0), 150);
        }

        for (int i = 0; i < 7; i++) {
            corriendoIzquierda.addFrame(sheetCorriendoIzquierda.getSprite(i, 0), 50);
        }    
        
        for (int i = 0; i < 8; i++) {
            muerte_Der.addFrame(sheetMuerte_Der.getSprite(i, 0), 150);
        }
        
        for (int i = 7; i >= 0; i--) {
            
            muerte_Izq.addFrame(sheetMuerte_Izq.getSprite(i, 0), 150);
        }
    }
      
     /**
     * Secuencia de acciones que realiza el personaje
     */

    public void acciones() throws SlickException {
    
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
                    if(contador<100)                       
                        mirandoIzquierda = true;      
                    else
                        mirandoIzquierda = false;            
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
                    if (jugador_a_izq == true){//jugador_a_izq=true -> jugador a izq. de Mario
                        if (distancia >100 && jugador_a_alt && conectadoSuelo && !conectadoIzquierda)
                            botonIzquierda = true; 
                        if (distancia >100 && !jugador_a_alt){
                            mirandoIzquierda = true;
                            saltar = true; 
                        }
                        if (cont_disparo == 30){
                            tiraBolas.disparar(this, -500, gestor);       
                        }
                    }
                    else{ 
                        if (distancia >100 && jugador_a_alt && conectadoSuelo  && !conectadoDerecha)
                            botonDerecha = true;
                        if (distancia >100 && !jugador_a_alt){
                            mirandoIzquierda = false;
                            saltar = true; 
                        }
                        if (cont_disparo == 30){
                            tiraBolas.disparar(this, +500, gestor);
                        }
                    }       
                    cont_disparo++;
                    if (cont_disparo > 30)
                        cont_disparo=0;

                    //System.out.println("estado 3");
                    break;

                case 4: //FURIOSO -> ataca a jugador con mas fuerza
                    if (jugador_a_izq == true){//jugador_a_izq=true -> jugador a izq. de Mario
                        if (distancia >50 && jugador_a_alt && conectadoSuelo && !conectadoIzquierda)
                            botonIzquierda = true; 
                        if (distancia >50 && !jugador_a_alt){
                            mirandoIzquierda = true;
                            saltar = true; 
                        }
                        if (cont_disparo == 5){
                            tiraBolas.disparar(this, -500, gestor);       
                        }
                    }
                    else{ 
                        if (distancia >50 && jugador_a_alt && conectadoSuelo  && !conectadoDerecha)
                            botonDerecha = true;
                        if (distancia >50 && !jugador_a_alt){
                            mirandoIzquierda = false;
                            saltar = true; 
                        }
                        if (cont_disparo == 5){
                            tiraBolas.disparar(this, +500, gestor);
                        }
                    }  
                    cont_disparo++;
                    if (cont_disparo > 5)
                        cont_disparo=0;
                    //System.out.println("estado 4");
                    break;

                case 5: //HUYENDO              
                    if (jugador_a_izq == true){//jugador_a_izq=true -> jugador a izq. de Mario
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

                if(muerte_Izq.getFrame() == 7) //muerte.getFrame()==nºframe-1
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

                if(muerte_Der.getFrame() == 7) //muerte.getFrame()==nºframe-1
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
        jugador_a_izq = jugadorX < posX; //jugador_a_izq=true -> jugador a izq. de Mario
        jugador_a_alt = (Math.abs(jugadorY-posY) < 50);  //jugador_a_alt=true -> jugador a misma altura Mario
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

    public float getDaño() {
        return daño;
    }
  
    public Shape getAreaColision() {
       return areaColision; 
    }

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

}
