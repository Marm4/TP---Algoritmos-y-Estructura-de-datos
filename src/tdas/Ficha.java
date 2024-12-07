package tdas;

import tdas.listaYCola.Lista;
import tdas.listaYCola.ListaSimplementeEnlazada;

import java.awt.*;

public class Ficha {

    //ATRIBUTOS
    private boolean disponible;
    private Integer posicionX;
    private Integer posicionY;
    private Integer posicionZ;
    private Jugador jugador;
    private Jugador jugadorAnterior;
    private Integer posicionAnteriorX;
    private Integer posicionAnteriorY;
    private Integer posicionAnteriorZ;

    //CONSTRUCTORES

    /**
     * Precondiciones:
     * - La lista de posiciones no debe ser null ni vacía.
     * - El jugador no debe ser null.
     * Postcondiciones:
     * - La ficha se inicializa con las posiciones X, Y, Z correspondientes a los valores en la lista de posiciones.
     * - Se asigna el jugador correspondiente a la ficha.
     * - El estado de la ficha se establece como disponible.
     */
    public Ficha(Lista<Integer> posiciones, Jugador jugador) throws Exception {
        if(posiciones == null || posiciones.estaVacia()){
            throw new Exception("La lista de posiciones no puede ser nula o estar vacia");
        }
        if(jugador == null){
            throw new Exception("El jugador no puede ser nulo");
        }
        this.disponible = true;
        this.jugador = jugador;
        this.setPosiciones(posiciones);
    }

    //METODOS DE COMPORTAMIENTO
    /**
     * Precondiciones:
     * - jugadorAnterior no debe ser null
     * -
     * Postcondiciones:
     * - Se crea un jugadorAux que es igula a jugador
     * - Se asigna jugadorAnterior a jugador.
     * - Se remueve la ficha de jugadorAnterior.
     * - Se agrega la ficha al jugador.
     */
    public void volverAJugadorAnterior() throws Exception {
        if(this.jugadorAnterior == null){
            throw new Exception("La ficha debe haber cambiado de jugador anteriormente.");
        }
        Jugador jugadorAux = this.jugador;
        this.jugador = this.jugadorAnterior;
        this.jugadorAnterior = jugadorAux;
        this.jugadorAnterior.removerFicha(this);
        this.jugador.agregarFicha(this);
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Si la ficha tiene posicionesAnteriores en X,Y,Z, crea posicionesAux para cada posicion X,Y,Z.
     * - A cada posicion se le asigna su posicion anterior.
     * - A cada posicionAnterior se le asigna la posicionAux/
     * - Si la ficha no tiene posicionesAnteriores en X,Y,Z, remueve la ficha.
     */
    public void volverAPosicionAnterior(Tablero tablero) throws Exception {
        if(this.posicionAnteriorX != null && this.posicionAnteriorY !=null && this.posicionAnteriorZ !=null){
            int posicionXAux = this.posicionX;
            int posicionYAux = this.posicionY;
            int posicionZAux = this.posicionZ;

            this.posicionX = this.posicionAnteriorX;
            this.posicionY = this.posicionAnteriorY;
            this.posicionZ = this.posicionAnteriorZ;

            this.posicionAnteriorX = posicionXAux;
            this.posicionAnteriorY = posicionYAux;
            this.posicionAnteriorZ = posicionZAux;
        }
        else{
            this.jugador.removerFicha(this);
            tablero.removerFicha(this);
        }

    }

    //SETTERS SIMPLES
    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Cambia la disponibilidad de la ficha.
     */
    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    /**
     * Precondiciones:
     * - La lista de posiciones no debe ser null ni vacía.
     * - El jugador no debe ser null.
     * Postcondiciones:
     * - Si las posiciones X,Y,Z son distinto de null, de le asigna estos valos a posicionAnterior respectivo.
     * - Se asigna las posiciones de la lista a cada posicion respectiva.
     */
    public void setPosiciones(Lista<Integer> posiciones) throws Exception {
        if(posiciones == null || posiciones.estaVacia()){
            throw new Exception("La lista de posiciones no puede ser nula o estar vacia");
        }
        if(this.jugador == null){
            throw new Exception("El jugador no puede ser nulo");
        }

        if(this.posicionX != null && this.posicionY !=null && this.posicionZ !=null){
            this.posicionAnteriorX = this.posicionX;
            this.posicionAnteriorY = this.posicionY;
            this.posicionAnteriorZ = this.posicionZ;
        }

        this.posicionX = posiciones.getNodo(1).getDato();
        this.posicionY = posiciones.getNodo(2).getDato();
        this.posicionZ = posiciones.getNodo(3).getDato();
    }

    //GETTERS SIMPLES
    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public int getPosicionZ() {
        return posicionZ;
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Crea una lista con las posiciones.
     *
     * @return devuelve la lista de posiciones.
     */
    public Lista<Integer> getPosiciones() throws Exception {
        Lista<Integer> posiciones = new ListaSimplementeEnlazada<>();
        posiciones.agregar(posicionX);
        posiciones.agregar(posicionY);
        posiciones.agregar(posicionZ);
        return posiciones;
    }

    /**
     * Precondiciones:
     * - jugador no debe ser null.
     * Postcondiciones:
     * - Establece el valor de jugador como el jugador actual.
     * - Si ya había un jugador asignado, guarda su referencia en jugadorAnterior antes de actualizar jugador.
     */
    public void setJugador(Jugador jugador) throws Exception {
        if(jugador == null){
            throw new Exception("El jugador no puede ser nulo");
        }
        if(this.jugador != null){
            this.jugadorAnterior = this.jugador;
        }
        this.jugador = jugador;
    }

    //GETTERS SIMPLES
    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return el jugador dueño de la ficha.
     */
    public Jugador getJugador() {
        return jugador;
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return el color del jugador dueño de la ficha (que también es el color de la ficha).
     */
    public Color getColor(){
        return this.jugador.getColorJugador().obtenerColorGrafico();
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return si la ficha esta disponible para realizar un movimiento.
     */
    public boolean isDisponible() {
        return disponible;
    }
}
