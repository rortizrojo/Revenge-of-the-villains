/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personajes;

import Engine.Camara;
import Engine.Colisiones;
import Engine.IColisionable;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Roberto
 */
public abstract class Personaje implements IColisionable {

    protected String nombre; 
    
    protected float vida;
    protected float posX;
    protected float posY;

    protected float velocidadX = 0;
    protected float velocidadY = 0;
    protected float maxVelocidadD = 6;
    protected float maxVelocidadI = -6;
    protected float velocidadSalto = 10;

    protected float grav = 0.8f;
    protected int delta, cont_vida;
    protected boolean saltando = false;
    private float tiempoSalto = 10;
    private boolean puedeSaltar = false;

    protected static GameContainer container;
    protected Colisiones colisiones;

    // declara botones
    protected boolean botonIzquierda;
    protected boolean botonDerecha;
    protected boolean botonArriba;

    protected boolean[][] bloqueadoDerecha;
    protected boolean[][] bloqueadoIzquierda;
    protected boolean[][] bloqueadoArriba;
    protected boolean[][] bloqueadoAbajo;

    protected boolean conectadoSuelo = false;
    protected boolean conectadoIzquierda = false;
    protected boolean conectadoDerecha = false;
    protected boolean conectadoTecho = false;

    //Es el ancho y alto en pixeles del sprite
    protected float anchoSprite;
    protected float altoSprite;
    //Es el tamaño (ancho y alto en pixeles) en el que se va a dibujar en pantalla 
    protected float anchoDibujado;
    protected float altoDibujado;

    protected TiledMap map;

    public Personaje(GameContainer container) {

        this.container = container;
        colisiones = Colisiones.getInstancia();
        bloqueadoDerecha = colisiones.getBloqueadoDerecha();
        bloqueadoIzquierda = colisiones.getBloqueadoIzquierda();
        bloqueadoArriba = colisiones.getBloqueadoArriba();
        bloqueadoAbajo = colisiones.getBloqueadoAbajo();
        map = colisiones.getMap();

    }
    
    /**
     * Método plnatilla
     * @param delta Delta
     * @throws SlickException 
     */
    public final void update(int delta) throws SlickException {
        
        compruebaColisiones();
        //Este método lo implementan los hijos
        acciones();
        movimientoPersonaje();
        recuperaVida();
        //Este método lo implementan los hijos
        actualizarEstado();
        
    }

    public float getPosX() {
        return posX;
    }

    public void setPosX(float posX) {
        this.posX = posX;
    }

    public float getPosY() {
        return posY;
    }

    public void setPosY(float posY) {
        this.posY = posY;
    }

    public float getAnchoSprite() {
        return anchoSprite;
    }

    public float getAltoSprite() {
        return altoSprite;
    }

    public boolean isConectado() {
        return conectadoSuelo;
    }

    public float getVida() {
        return vida;
    }

    public void setVida(int vida) {
        this.vida = vida;
    }

    public boolean isConectadoSuelo() {
        return conectadoSuelo;
    }

    public boolean isConectadoIzquierda() {
        return conectadoIzquierda;
    }

    public boolean isConectadoDerecha() {
        return conectadoDerecha;
    }

    public boolean isConectadoTecho() {
        return conectadoTecho;
    }

    public void setBotonIzquierda(boolean botonIzquierda) {
        this.botonIzquierda = botonIzquierda;
    }

    public void setBotonDerecha(boolean botonDerecha) {
        this.botonDerecha = botonDerecha;
    }

    

    public void setColisiones() {
        this.colisiones = Colisiones.getInstancia();
        bloqueadoDerecha = colisiones.getBloqueadoDerecha();
        bloqueadoIzquierda = colisiones.getBloqueadoIzquierda();
        bloqueadoArriba = colisiones.getBloqueadoArriba();
        bloqueadoAbajo = colisiones.getBloqueadoAbajo();
        map = colisiones.getMap();
    }

    
    
    /*** METODOS DEL PATRÓN TEMPLATE METHOD ***/
    
    
    public abstract void acciones() throws SlickException;
    
    public abstract void actualizarEstado() throws SlickException;

    public abstract void render(int delta, Graphics g) throws SlickException;

    
    private final void compruebaColisiones() {
        //Comprueba colisiones solo si el jugador esta dentro de los limites del mapa
        //para que no provoque una excepcion de indexOutOfBounds en el array
        if((posY+altoDibujado+velocidadY)<(map.getHeight()*map.getTileHeight()) && posY>=0){
            conectadoDerecha = bloqueadoIzquierda   [(int)(posX+anchoDibujado+velocidadX)/map.getTileWidth()][(int)(posY+ altoDibujado+velocidadY-1)/map.getTileHeight()]
                    || bloqueadoIzquierda[(int)(posX+anchoDibujado+velocidadX)/map.getTileWidth()][(int)(posY+ velocidadY)/map.getTileHeight()];

            conectadoIzquierda = bloqueadoDerecha   [(int)(posX + velocidadX) / map.getTileWidth()][(int) (posY+ altoDibujado+velocidadY-1) / map.getTileHeight()]||
                     bloqueadoDerecha[(int) (posX+velocidadX) / map.getTileWidth()][(int) (posY+ velocidadY) / map.getTileHeight()];

            conectadoSuelo = bloqueadoArriba[((int)(posX+anchoDibujado)/map.getTileWidth())-1][(int)(posY+altoDibujado+velocidadY)/map.getTileHeight()]
                || bloqueadoArriba[((int)(posX)/map.getTileWidth())+1][(int)(posY+altoDibujado+velocidadY)/map.getTileHeight()];      
            conectadoTecho = (bloqueadoAbajo[((int)(posX+anchoDibujado)/map.getTileWidth())-1][(int)(posY)/map.getTileHeight()] 
                || bloqueadoAbajo[(int)(posX)/map.getTileWidth()][(int)(posY)/map.getTileHeight()]);
        }
    }

    private final void movimientoPersonaje() {
        if(vida>0){
            
            posX += velocidadX;

            // pulsando botones.......
            if (botonDerecha) {
                velocidadX += 0.5;
                if (velocidadX >= maxVelocidadD) {
                    velocidadX = maxVelocidadD;
                }
            }

            if (botonIzquierda) {
                velocidadX -= 0.5;
                if (velocidadX <= maxVelocidadI) {
                    velocidadX = maxVelocidadI;
                }
            }

            if ((botonIzquierda && botonDerecha) || (!botonIzquierda && !botonDerecha)) {
                velocidadX = 0;
            }

            if (botonArriba) {
                if (puedeSaltar) {
                    saltando = true;
                }

            }

            //esta parte del codigo es la gravedad y colision del personaje
            if (conectadoDerecha) {

                while (!conectadoDerecha) {
                    posX += Math.signum(velocidadX);

                }
                if (botonDerecha) {
                    velocidadX = 0;
                }

            }
            if (conectadoIzquierda) {
                while (!conectadoIzquierda) {
                    posX += Math.signum(velocidadX);

                }
                if (botonIzquierda) {
                    velocidadX = 0;
                }
            }

            if (conectadoSuelo) {
                puedeSaltar = true;
                while (!conectadoSuelo) {
                    posY += Math.signum(velocidadY);
                }
                velocidadY = 0;
            } else {
                velocidadY += grav;
            }

            if (saltando) {
                puedeSaltar = false;
                if (conectadoTecho) {
                    velocidadY = +6;
                    saltando = false;
                    tiempoSalto = 0;

                } else {
                    velocidadY = -velocidadSalto;

                }
                posY += velocidadY;

                tiempoSalto -= 1;
                if (tiempoSalto <= 0) {
                    tiempoSalto = 10;
                    saltando = false;

                }
            }

            
        }
    }

    private final void recuperaVida() {
        //RECUPERAR VIDA PERDIDA PASADO UN TIEMPO        
        if (vida<75 && vida > 2){
        cont_vida++;
                if (cont_vida > 25){
                    vida++;
                    cont_vida=0;
                }
        }
        posY += velocidadY;
    }
}
