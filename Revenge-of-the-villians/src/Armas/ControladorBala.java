/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Armas;

import Engine.Icontrolador;
import Engine.GestorColision;
import java.util.ArrayList;
import org.newdawn.slick.SlickException;

/**
 *
 * @author Usuario
 */
public class ControladorBala implements Icontrolador{

    private final ArrayList<Bala> balas;
    private Bala bala;
    private GestorColision gestor;
    
    public ControladorBala() {
        
        balas = new ArrayList<>();
        
    }

    @Override
    public void addBala(float x, float y, float velocidad,GestorColision gestor) throws SlickException {
        bala = new Bala(x,y,velocidad);
        balas.add(bala);
        this.gestor = gestor;
        gestor.registrarCuerpo(bala);
    }

    @Override
    public void draw() {
        
        balas.forEach((bala1) -> {
            bala1.draw();
        });
    }

    @Override
    public void delete(float x) {
        for (int i = 0; i < balas.size(); i++) {
            if ((balas.get(i).getAreaColision().getX() > x) || (balas.get(i).getAreaColision().getX() < 0)) {
                Bala bala1 = balas.get(i);
                balas.remove(i);
                gestor.anularCuerpo(bala1);
            }
        }
    }

    @Override
    public void update(int delta) {
        balas.forEach((bala1) -> {
            bala1.update(delta);
        });
    }

    @Override
    public void addBola(float x, float y, float velocidad, GestorColision gestor, float daño) throws SlickException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
   
}
