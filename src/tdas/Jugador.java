package tdas;

import enums.ColorJugador;
import enums.TipoExcepcionJugador;
import excepciones.ExcepcionJugador;
import tdas.cartas.Carta;
import tdas.listaYCola.Lista;
import tdas.listaYCola.ListaSimplementeEnlazada;

public class Jugador {

    //ATRIBUTOS
    private String nombre;
    private ColorJugador colorJugador;
    private Lista<Carta> cartas;
    private Lista<Ficha> fichas;
    private Ficha ultimaFichaModificada;

    //CONSTRUCTORES

    /**
     * Precondiciones:
     * - El nombre no debe estar vacío.
     * - El color del jugador no debe ser null.
     *
     * Postcondiciones:
     * - El jugador se inicializa con el nombre y color proporcionados.
     * - Se inicializan listas vacías para las fichas y las cartas del jugador.
     */
    public Jugador(String nombre, ColorJugador colorJugador) throws ExcepcionJugador {
        if(nombre.isEmpty()){
            throw new ExcepcionJugador(TipoExcepcionJugador.NOMBRE_VACIO);
        }
        if(colorJugador == null){
            throw new ExcepcionJugador(TipoExcepcionJugador.COLOR_NULL);
        }

        this.nombre = nombre;
        this.colorJugador = colorJugador;
        this.fichas = new ListaSimplementeEnlazada<>();
        this.cartas = new ListaSimplementeEnlazada<>();
    }

    //METODOS DE COMPORTAMIENTO

    /**
     * Precondiciones:
     * - La lista de cartas nuevas no debe ser null ni estar vacía.
     *
     * Postcondiciones:
     * - Las cartas de la lista `cartasNuevas` se agregan a la lista de cartas del jugador.
     */
    public void agregarCartas(Lista<Carta> cartasNuevas) throws Exception {
        if(cartasNuevas == null || cartasNuevas.estaVacia()){
            throw new ExcepcionJugador(TipoExcepcionJugador.CARTAS_VACIO_NULL);
        }
        cartasNuevas.iniciarCursor();
        while(cartasNuevas.avanzarCursor()){
            this.cartas.agregar(cartasNuevas.obtenerCursor());
        }
    }

    /**
     * Precondiciones:
     * - La ficha no debe ser null.
     *
     * Postcondiciones:
     * - Se agrega la ficha a la lista de fichas.
     */
    public void agregarFicha(Ficha ficha) throws Exception {
        if(ficha == null){
            throw new ExcepcionJugador(TipoExcepcionJugador.FICHA_NULL);
        }
        this.ultimaFichaModificada = ficha;
        this.fichas.agregar(ficha);
    }

    /**
     * Precondiciones:
     * - Chequea que la ficha sea correcta atraves de chequearFichaCorreta
     *
     * Postcondiciones:
     * - Las posiciones de la ficha se actualizan con las posiciones proporcionadas.
     * - La ficha se establece como ultimaFichaModificada.
     */
    public void moverFicha(Ficha ficha, Lista<Integer> posiciones) throws Exception {
        chequearFichaCorreta(ficha);
        if(posiciones == null || posiciones.estaVacia()){
            throw new ExcepcionJugador(TipoExcepcionJugador.POSICIONES_VACIO_NULL);
        }
        ficha.setPosiciones(posiciones);
        this.ultimaFichaModificada = ficha;
    }

    /**
     * Precondiciones:
     * - Chequea que la ficha sea correcta atraves de chequearFichaCorreta
     *
     * Postcondiciones:
     * - Asigna la ficha a ultimaFichaModifcada
     * - Remueve la ficha de la lista de fichas.
     */
    public void removerFicha(Ficha ficha) throws Exception {
        chequearFichaCorreta(ficha);
        this.ultimaFichaModificada = ficha;
        this.fichas.remover(ficha);
    }

    /**
     * Precondiciones:
     * - La carta no debe ser null
     * Postcondiciones:
     * - Si la carta esta en la lista de cartas, la remueve.
     */
    public void removerCarta(Carta carta) throws Exception {
        if(carta == null){
            throw new ExcepcionJugador(TipoExcepcionJugador.CARTAS_VACIO_NULL);
        }
        if(cartas.contiene(carta)){
            cartas.remover(carta);
        }
    }

    /**
     * Precondiciones:
     * - La ultimaFichaModificada no debe ser null
     *
     * Postcondiciones:
     * - Regresa la ficha a su posición anterior.
     */
    public void moverFichaAposicionAnterior(Tablero tablero) throws Exception {
        if (this.ultimaFichaModificada == null) {
            throw new ExcepcionJugador(TipoExcepcionJugador.FICHA_NULL);
        }
        this.ultimaFichaModificada.volverAPosicionAnterior(tablero);
    }

    /**
     * Precondiciones:
     * - La ficha no debe ser null y debe estar contenida en la lista fichas.
     * - La lista posiciones no debe ser null ni estar vacía.
     *
     * Postcondiciones:
     * -
     */
    private void chequearFichaCorreta(Ficha ficha) throws ExcepcionJugador {
        if (ficha == null) {
            throw new ExcepcionJugador(TipoExcepcionJugador.FICHA_NULL);
        }
        if(!fichas.contiene(ficha)){
            throw new ExcepcionJugador(TipoExcepcionJugador.FICHA_INVALIDA);
        }
    }

    //GETTERS SIMPLES
    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return el nombre del jugador.
     */
    public String getNombre(){
        return nombre;
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return el color del jugador.
     */
    public ColorJugador getColorJugador() {
        return colorJugador;
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return la lista de cartas del jugador.
     */
    public Lista<Carta> getCartas() {
        return cartas;
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return la cantidad de cartas del jugador.
     */
    public int getCantidadDeCartas(){
        return cartas.getTamanio();
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return la lista de fichas del jugador.
     */
    public Lista<Ficha> getFichas(){
        return fichas;
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return la cantidad de fichas del jugador.
     */
    public int getCantidadDeFichas(){ return fichas.getTamanio();}

}
