/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Armas.Bola;
import Engine.Camara;
import Engine.Colisiones;
import Engine.GestorColision;
import Engine.IColisionable;
import Engine.Inventario;
import Armas.Pistola;
import Objetos.Moneda;
import Objetos.Puerta;
import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;

import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.Sound;
import org.newdawn.slick.SpriteSheet;
import org.newdawn.slick.geom.Shape;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.state.StateBasedGame;

/**
 *
 * @author Roberto
 */
public class Jugador extends Personaje implements IColisionable {

    private final StateBasedGame game;
    private SpriteSheet sheetCorriendoDerecha;
    private SpriteSheet sheetCorriendoIzquierda;
    private SpriteSheet sheetParadoIzquierda;
    private SpriteSheet sheetParadoDerecha;
    private SpriteSheet sheetMuerte;
    private Animation corriendoDerecha;
    private Animation corriendoIzquierda;
    private Animation paradoDerecha;
    private Animation paradoIzquierda;
    private Animation muerte;
    private Boolean pasoDeNivel, enemigos_muertos, finJuego;
    private Inventario inventario;
    private boolean puedeEntrar;
    private boolean botonDisparo;
    private boolean mirandoIzquierda;
    private boolean mirandoDerecha;
    private String[][] dataPoints;

    private Pistola pistola;
    private Rectangle areaColision;
    private GestorColision gestor;
    
    
    private int nivel;
    private int tiempoReinicio;
    private boolean muerto;
    private Mario mario;
    private Enemigo enemigo;
    private Bola bola; 
    private Moneda moneda;
    private Puerta puerta;
    private Sound salto;
    private Sound correrJug1;
    private Sound correrJug2;
    private Sound dispBala;
    private Sound muerteJugador;
    private boolean muerteEscuchada;
    private boolean der;
    private boolean monedasRecogidas;
    

    public Jugador(GameContainer container, StateBasedGame game, Colisiones colisiones, GestorColision gestor) throws SlickException {
        
        super(container, colisiones);
        
        this.game = game;
        this.gestor = gestor;
        gestor.registrarCuerpo(this);
        finJuego = false;
        enemigos_muertos = false;
        pasoDeNivel=false;
        muerto = false;
        monedasRecogidas = false;
        tiempoReinicio = 120;
        vida = 100;
        posX = 50;
        posY = 350;
        anchoSprite = 32;
        altoSprite = 42;
        anchoDibujado = anchoSprite * 2f;
        altoDibujado = altoSprite * 2f;
        nivel=0;
        der = false;
        
        areaColision = new Rectangle(posX, posY,anchoDibujado,altoDibujado);
        sheetCorriendoDerecha = new SpriteSheet("res/animations//Metal/corriendoDerechaSprite.png", (int) anchoSprite, (int) altoSprite);
        sheetCorriendoIzquierda = new SpriteSheet("res/animations//Metal/corriendoIzquierdaSprite.png", (int) anchoSprite, (int) altoSprite);
        sheetParadoIzquierda = new SpriteSheet("res/animations//Metal/paradoIzquierdaSprite.png", (int) anchoSprite, (int) altoSprite);
        sheetParadoDerecha = new SpriteSheet("res/animations//Metal/paradoDerechaSprite.png", (int) anchoSprite, (int) altoSprite);
        sheetMuerte = new SpriteSheet("res/animations/Metal/Tarmo/muerte estrella/mortajao.png", (int) 38, (int) 47);
        
        pistola = new Pistola(1.0f);
        
        
        inventario = new Inventario();
        dataPoints = colisiones.getDataPoints();
        
        corriendoDerecha = new Animation();
        corriendoIzquierda = new Animation();
        paradoIzquierda = new Animation();
        paradoDerecha = new Animation();
        muerte = new Animation();
        salto  = new Sound("res/sounds/salto.wav");
        correrJug1  = new Sound("res/sounds/pasoJug1.wav");
        correrJug2 = new Sound("res/sounds/pasoJug2.wav");
        dispBala = new Sound("res/sounds/disparoBala.wav");
        muerteJugador = new Sound("res/sounds/gameOver.wav");
        muerteEscuchada = false;
        
        for (int i = 0; i < 11; i++) {
            corriendoDerecha.addFrame(sheetCorriendoDerecha.getSprite(i, 0), 50);
        }

        for (int i = 0; i < 4; i++) {
            paradoIzquierda.addFrame(sheetParadoIzquierda.getSprite(i, 0), 150);
        }

        for (int i = 0; i < 4; i++) {
            paradoDerecha.addFrame(sheetParadoDerecha.getSprite(i, 0), 150);
        }

        for (int i = 10; i >=0; i--) {
            corriendoIzquierda.addFrame(sheetCorriendoIzquierda.getSprite(i, 0), 50);
        }
        
        for (int i = 0; i < 25; i++) {
            muerte.addFrame(sheetMuerte.getSprite(i, 0), 150);
        }

    }
    
    public void acciones() throws SlickException {
        pasoDeNivel=false;
        finJuego =false;
       
        
        //Cargar sonidos
        if(saltando)
            if(!salto.playing())
                salto.play(1.1f, 0.2f);
        
        
        if(botonIzquierda || botonDerecha){
            if(conectadoSuelo)
                if(!(correrJug1.playing()||correrJug2.playing())){
                
                    if(der){
                        correrJug1.play(1.2f,0.5f);
                        der=false;
                    }else{
                        correrJug2.play(1f,0.2f);
                        der=true;
                    }
                                
                }
        } 
        if(botonDisparo)
            dispBala.play(1f,0.6f);
            
        if(vida<=0 && !muerteEscuchada){
            muerteJugador.play();
            muerteEscuchada =true;
        }
            
        
        
        if(vida>0){
            //creando botones.....
            botonDerecha = container.getInput().isKeyDown(Input.KEY_D);
            botonIzquierda = container.getInput().isKeyDown(Input.KEY_A);
            botonArriba = container.getInput().isKeyPressed(Input.KEY_SPACE);

            botonDisparo = container.getInput().isKeyPressed(Input.KEY_J);
            //botonAccion = container.getInput().isKeyPressed(Input.KEY_F);

            if (botonDisparo) {
                if(mirandoIzquierda)
                    pistola.disparar(this, -900, gestor);
                if(mirandoDerecha)
                    pistola.disparar(this, 900, gestor);
            }
            
            sincronizarArea();
            gestor.comprobarColisiones();
            gestor.anularCuerpo(moneda);
        }
       
        
        pistola.update(delta);
        pistola.delete(map);
        if((posY+altoDibujado+velocidadY)>=(map.getHeight()*map.getTileHeight()))
            vida = vida - vida;
    }

   
 
    public void setEnemigos_muertos(Boolean enemigos_muertos) {
        this.enemigos_muertos = enemigos_muertos;
    }

    public void pasarNivel()throws SlickException{
        gestor.anularCuerpo(puerta);
        if (nivel==0||nivel==1){
            nivel++; 
            vida = 100;
            if (nivel==1||nivel ==2){
                 
                game.enterState(2);
                
            }
        }
    }
    @Override
    public void render(int delta,Graphics g, Camara camara) throws SlickException {
        
            this.delta = delta;

            /* Codigo para depurar colisiones con el mapa

            System.out.println("celda x: "+(int)(posX-1)/map.getTileWidth());
            System.out.println("celda y: "+(int)(posY+altoDibujado+velocidadY)/map.getTileHeight());

            System.out.println("conectado derecha "+conectadoDerecha);
            System.out.println("conectado izq "+conectadoIzquierda);
            System.out.println("conectado suelo "+conectadoSuelo);
            System.out.println("conectado techo "+ conectadoTecho);
            */
            //Si muere
            if(vida <= 0){
               if(conectadoSuelo){
                    velocidadY = 0;
                    velocidadX = 0;
                    grav = 0;
               }
                //Carga animacion de muerte
                muerte.draw(posX, posY-12, 38*2f, 47*2f);

                if(muerte.getFrame() == 24)
                    muerte.stop();

                if (muerte.isStopped()){

                    tiempoReinicio -=1;
                    if (tiempoReinicio <=0)
                        muerto = true;
                }

            }else if(botonDerecha){
                corriendoDerecha.draw(posX, posY, anchoDibujado, altoDibujado);
                mirandoDerecha = true;
                mirandoIzquierda = false;
            } else if (botonIzquierda) {
                corriendoIzquierda.draw(posX, posY, anchoDibujado, altoDibujado);
                mirandoIzquierda = true;
                mirandoDerecha = false;
            } else {
                if (mirandoIzquierda) {
                    paradoIzquierda.draw(posX, posY, anchoDibujado, altoDibujado);
                } else {
                    paradoDerecha.draw(posX, posY, anchoDibujado, altoDibujado);
                }
            }
            pistola.render();
            
           
           
    }

    public int getNivel() {
        return nivel;
    }
    public Boolean getPasoDeNivel() {
        return pasoDeNivel;
    }
    public boolean isMuerto() {
        return muerto;
    }
    public boolean isPuedeEntrar() {
        return puedeEntrar;
    }
   
    public boolean getPasoNivel(){
        return pasoDeNivel;
    }
     public boolean getFinJuego(){
        return finJuego;
    }
    public boolean getEnemigosMuertos(){
        return enemigos_muertos;
    }

    public Inventario getInventario() {
        return inventario;
    }

    public void setMuerto(boolean muerto) {
        this.muerto = muerto;
    }
    

    
    
    @Override
    public Shape getAreaColision() {
        return areaColision;
    }

    @Override
    public void alColisionar(IColisionable colision) {
        
        if(colision.getClass().getName().equals("Armas.Bola") ){
            bola = (Bola)colision;
            if (bola != null)
                if(vida>0){
                    vida = vida - bola.getDaño();
                }
        }
        if(colision.getClass().getName().equals("Objetos.Puerta")&&enemigos_muertos){
            this.puerta = (Puerta)colision;
            if(nivel == 0||nivel==2)
                pasoDeNivel = true;
            else if(nivel ==1 && monedasRecogidas)
                pasoDeNivel = true;
            
         
        }
          if(colision.getClass().getName().equals("Objetos.Lava")){
            vida= vida-vida;
       
        }
          if(colision.getClass().getName().equals("Objetos.BolaFuego")){
            vida= vida-vida;
       
        }
         if(colision.getClass().getName().equals("Objetos.Cofre")&&enemigos_muertos){
           finJuego = true; 
           
        }
        if(colision.getClass().getName().equals("Personajes.Mario")){ 
            mario = (Mario)colision;
            if(!mario.isMuerto())
                if(vida>0)
                   vida = vida - 0.05f;         
        }    
        if(colision.getClass().getName().equals("Personajes.Enemigo")){ 
            enemigo = (Enemigo)colision;
            if(!enemigo.isMuerto())
                if(vida>0)
                   vida = vida - 0.03f;
        } 
        if(colision.getClass().getName().equals("Objetos.Moneda")){ 
            moneda = (Moneda)colision;
            
            if(!moneda.isCogida()){
                inventario.setMonedas();
                if(inventario.getMonedas()== 10)
                    monedasRecogidas = true;
            }
        } 
    }

    @Override
    public void sincronizarArea() {
        areaColision.setX(posX);
        areaColision.setY(posY);
    }

}