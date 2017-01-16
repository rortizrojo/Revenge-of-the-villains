package EstadosEnemigo;

import Personajes.Enemigo;

/**
 * Decorador. 
 */
public abstract class Decorador implements Estado {

    /**
     * Referencia al estado.
     */
    private Estado estado;

    /**
     * Constructor.
     * @param estado
     */
    public Decorador(Estado estado) {
        this.estado = estado;
    }

    /**
     * Establece el Estado concreto.
     * @param estado Estado concreto.
     */
    public void setEstado(Estado estado) {
        this.estado = estado;
    }

    /**
     * Devuelve el estado concreto.
     * @return Estado concreto.
     */
    public Estado getEstado() {
        return estado;
    }

    
    @Override
    public void ejecutar(Enemigo enemigo) {
        estado.ejecutar(enemigo);    

    }


    @Override
    public String toString() {
        return estado.toString();
    }


}
