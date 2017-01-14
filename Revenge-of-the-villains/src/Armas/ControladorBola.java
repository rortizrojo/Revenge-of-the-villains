/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Armas;

import Engine.Icontrolador;
import Engine.GestorColision;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Usuario
 */
public class ControladorBola implements Icontrolador {

    private final ArrayList<Bola> bolas;
    private GestorColision gestor;
    private Bola bola;

    public ControladorBola() {
        
        bolas = new ArrayList<>();
        
    }

    @Override
    public void addBola(float x, float y, float velocidad,GestorColision gestor,float daño){
        try {
            bola = new Bola(x,y,velocidad,daño);
        } catch (SlickException ex) {
            Logger.getLogger(ControladorBola.class.getName()).log(Level.SEVERE, null, ex);
        }
        bolas.add(bola);
        this.gestor = gestor;
        gestor.registrarCuerpo(bola);
    }

    @Override
    public void draw() {   
        for (int i = 0; i < bolas.size(); i++) {       
            bolas.get(i).draw();     
        }
    }

    @Override
    public void delete(float x) {
        for (int i = 0; i < bolas.size(); i++) {
            if ((bolas.get(i).getAreaColision().getX() > x) || (bolas.get(i).getAreaColision().getX() < 0)) {
                Bola bola1 = bolas.get(i);
                bolas.remove(i);
                gestor.anularCuerpo(bola1);
            }
        }
    }

    @Override
    public void update(int delta) {
        bolas.forEach((bola1) -> {
            bola1.update(delta);
        });
    }

    @Override
    public void addBala(float x, float y, float velocidad, GestorColision gestor) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
