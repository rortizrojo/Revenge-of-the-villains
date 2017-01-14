/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import org.newdawn.slick.tiled.TiledMap;

/**
 *
 * @author Roberto
 */
public class Colisiones {

    private boolean[][] bloqueadoDerecha;
    private boolean[][] bloqueadoArriba;
    private boolean[][] bloqueadoIzquierda;
    private boolean[][] bloqueadoAbajo;
    private boolean[][] monedas;
    private String[][] dataPoints;
    private TiledMap map;

    public Colisiones(TiledMap map) {
        this.map = map;
        bloqueadoDerecha = new boolean[map.getWidth()][map.getHeight()];
        bloqueadoArriba = new boolean[map.getWidth()][map.getHeight()];
        bloqueadoIzquierda = new boolean[map.getWidth()][map.getHeight()];
        bloqueadoAbajo = new boolean[map.getWidth()][map.getHeight()];
        monedas = new boolean[map.getWidth()][map.getHeight()];
        dataPoints = new String[map.getWidth()][map.getHeight()];
        detectarColisiones();
        detectarMonedas();
        detectarDataPoints();
    }

    /**
     * Detecta colisiones del mapa
     */
    public void detectarColisiones() {
       
        int tileID;
        for (int x = 0; x < map.getWidth(); x++) {
            for (int y = 0; y < map.getHeight(); y++) {
                //System.out.println("width"+ x );
                tileID = map.getTileId(x, y, map.getLayerIndex("Colisiones"));
                
                bloqueadoDerecha[x][y] = map.getTileProperty(tileID, "dir", "0").contains("r") ;
                bloqueadoIzquierda[x][y] =  map.getTileProperty(tileID, "dir", "0").contains("l");
                bloqueadoArriba[x][y] = map.getTileProperty(tileID, "dir", "0").contains("u");
                bloqueadoAbajo[x][y] = map.getTileProperty(tileID, "dir", "0").contains("d");  
                
                 //   System.out.println("x : " + x + " y : "+ y  + " "+ bloqueadoAbajo[x][y]+ " " + map.getTileProperty(tileID, "dir", "0"));
               
            }
          

        }
     
    }
    /**
     * Encuentra tiles en el Tiled Map de "type" = "moneda" y guarda "true" en 
     * las posiciónes x, y del array donde las encontró
     */
    public void detectarMonedas(){
        
        if (map.getLayerIndex("Monedas")>-1){
            int tileID;
            for (int x = 0; x < map.getWidth(); x++) {
                for (int y = 0; y < map.getHeight(); y++) {
                    //System.out.println("width"+ x );
                    tileID = map.getTileId(x, y, map.getLayerIndex("Monedas"));

                    monedas[x][y] = map.getTileProperty(tileID, "type", "0").equals("moneda") ;

                }

            }
        }
        
    }
    /**
     * Recorre el mapa entero en busca de datapoints y guarda en el array el valor 
     * del atributo "num" del tile en el Tiled Map en la posición correspondiente
     */
    public void detectarDataPoints() {
       
        if (map.getLayerIndex("Datapoints")>-1){
            int tileID;
            for (int x = 0; x < map.getWidth(); x++) {
                for (int y = 0; y < map.getHeight(); y++) {
                    //System.out.println("width"+ x );
                    tileID = map.getTileId(x, y, map.getLayerIndex("Datapoints"));

                    dataPoints[x][y] = map.getTileProperty(tileID, "num", "-1");

                }

            }
        }
     
    }

    public String[][] getDataPoints() {
        return dataPoints;
    }
    
    public boolean[][] getMonedas() {
        return monedas;
    }
    
    public boolean[][] getBloqueadoDerecha() {
        return bloqueadoDerecha;
    }

    public void setBloqueadoDerecha(boolean[][] bloqueadoDerecha) {
        this.bloqueadoDerecha = bloqueadoDerecha;
    }

    public boolean[][] getBloqueadoArriba() {
        return bloqueadoArriba;
    }

    public void setBloqueadoArriba(boolean[][] bloqueadoArriba) {
        this.bloqueadoArriba = bloqueadoArriba;
    }

    public boolean[][] getBloqueadoIzquierda() {
        return bloqueadoIzquierda;
    }

    public void setBloqueadoIzquierda(boolean[][] bloqueadoIzquierda) {
        this.bloqueadoIzquierda = bloqueadoIzquierda;
    }

    public boolean[][] getBloqueadoAbajo() {
        return bloqueadoAbajo;
    }

    public void setBloqueadoAbajo(boolean[][] bloqueadoAbajo) {
        this.bloqueadoAbajo = bloqueadoAbajo;
    }

    public TiledMap getMap() {
        return map;
    }

    public void setMap(TiledMap map) {
        this.map = map;
    }

}
