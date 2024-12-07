package tdas;

import enums.ModoDeJuego;
import enums.TipoExcepcionTablero;
import excepciones.ExcepcionTablero;
import tdas.listaYCola.Lista;
import tdas.listaYCola.ListaSimplementeEnlazada;
import tdas.menu.MenuJuego;
import utils.ChequearGanador;

public class Tablero {

    //ATRIBUTOS
    private Lista<Ficha> fichas;
    private ModoDeJuego modoDeJuego;
    private Lista<Lista<Integer>> casillerosBloqueados;
    private MenuJuego menuJuego;

    //CONSTRUCTORES
    /**
     * Precondiciones:
     * - modoDeJuego debe ser un objeto válido de la clase ModoDeJuego. No debe ser null.
     * - menuJuego debe ser un objeto válido. No debe ser null.
     * Postcondiciones:
     * - Se crea una instancia de Tablero con el modo de juego proporcionado.
     * - Se inicializa la lista fichas como una lista vacía de tipo ListaSimplementeEnlazada.
     * - Se asgina el menu de juego.
     * - Se crea uina lista para casillerosBloqueados.
     */
    public Tablero(ModoDeJuego modoDeJuego, MenuJuego menuJuego) throws Exception {
        if(modoDeJuego == null){
            throw new ExcepcionTablero(TipoExcepcionTablero.MODO_DE_JUEGO);
        }
        if(menuJuego == null){
            throw new ExcepcionTablero(TipoExcepcionTablero.MENU_JUEGO_NULL);
        }
        this.modoDeJuego = modoDeJuego;
        this.fichas = new ListaSimplementeEnlazada<>();
        this.menuJuego = menuJuego;
        this.casillerosBloqueados = new ListaSimplementeEnlazada<>();
    }

    //METODOS DE COMPORTAMIENTO
    /**
     * Precondiciones:
     * - La lista posiciones no debe ser null.
     * - La lista no puede tener un tamaño distinto de 3 (Posiciones X,Y,Z).
     * Postcondiciones:
     * - Chequea que la posicion ingresada este dentro del tablero atraves de chequearPosicionDentroDeTablero
     * - Si alguna posición no es válida (fuera de rango o ya ocupada), el método devuelve `false` y muestra un mensaje en consola.
     * - Si la posición es válidas y no están ocupadas, el método devuelve `true`.
     *
     * @return true si la posición es válida y no está ocupada. false si la posición es inválida o ya está ocupada.
     */
    public boolean chequearPosicionCorrecta(Lista<Integer> posiciones) throws Exception {
        if(posiciones == null){
            throw new ExcepcionTablero(TipoExcepcionTablero.POSICIONES_VACIO);
        }
        if(posiciones.getTamanio() != 3){
            throw new ExcepcionTablero(TipoExcepcionTablero.POSICIONES_TAMANIO_INCORRECTO);
        }

        int posicionX = posiciones.obtener(1);
        int posicionY = posiciones.obtener(2);
        int posicionZ = posiciones.obtener(3);
        if((posicionX < 0 || posicionX > modoDeJuego.getCantidadDeCasilleros()) ||
                (posicionY < 0 || posicionY > modoDeJuego.getCantidadDeCasilleros()) ||
                (posicionZ < 0 || posicionZ > modoDeJuego.getCantidadDeCasilleros())){

            menuJuego.posicionIncorrecta(modoDeJuego.getCantidadDeCasilleros());
            return false;
        }

        fichas.iniciarCursor();
        while(fichas.avanzarCursor()){
            Ficha fichaActual = fichas.obtenerCursor();
            if((fichaActual.getPosicionX() == posicionX) &&
                    (fichaActual.getPosicionY() == posicionY) &&
                    (fichaActual.getPosicionZ() == posicionZ)){
                menuJuego.mostrarMensaje("La posición se encuentra ocupada.");
                return false;
            }
        }

        return !casillerosBloqueadosContiene(posiciones);
    }

    /**
     * Precondiciones:
     * - jugador no debe ser null.
     * Postcondiciones:
     * - Obtiene las fichas del jugador y verifica si esta dentro de los parametros para ser testeado como posible ganador.
     *
     * @return el resultado de ChequearGanador.chequear.
     */
    public boolean chequearGanador(Jugador jugador) throws Exception {
        if(jugador == null){
            throw new ExcepcionTablero(TipoExcepcionTablero.JUGADOR_NULL);
        }
        Lista<Ficha> fichasJugador = jugador.getFichas();
        if(fichasJugador == null || fichasJugador.estaVacia() || fichasJugador.getTamanio() < modoDeJuego.getCantidadDeCasilleros()){
            return false;
        }
        return ChequearGanador.chequear(fichasJugador, modoDeJuego.getCantidadDeCasilleros());
    }

    /**
     * Precondiciones:
     * - ficha no debe ser null.
     * - Se debe verificar que las posiciones de la ficha sean validas atravez del metodo chequearPosicionCorrecta.
     * Postcondiciones:
     * - La ficha se agrega a la lista de fichas del tablero.
     */
    public void agregarFicha(Ficha ficha) throws Exception {
        if(ficha == null){
            throw new ExcepcionTablero(TipoExcepcionTablero.FICHAS_NULL);
        }
        if(!chequearPosicionCorrecta(ficha.getPosiciones())){
            throw new ExcepcionTablero(TipoExcepcionTablero.POSICION_INVALIDA);
        }

        Lista<Integer> posiciones = ficha.getPosiciones();
        posiciones.iniciarCursor();
        while(posiciones.avanzarCursor()){
            int posicionActual = posiciones.obtenerCursor();
            if(posicionActual <= 0 || posicionActual > modoDeJuego.getCantidadDeCasilleros()){
                throw new ExcepcionTablero(TipoExcepcionTablero.POSICION_FUERA_DE_RANGO);
            }
        }

        fichas.agregar(ficha);
    }

    /**
     * Precondiciones:
     * - La lista casilleroBloqueado no debe ser null ni estar vacía.
     * - Se debe verificar que las posiciones sean validas atravez del metodo chequearPosicionCorrecta.
     * Postcondiciones:
     * - El casilleroBloqueado se agrega a la lista de casillerosBloqueados.
     */
    public void agregarPosicionBloqueada(Lista<Integer> casilleroBloqueado) throws Exception {
        if(casilleroBloqueado == null || casilleroBloqueado.estaVacia()){
            throw new ExcepcionTablero(TipoExcepcionTablero.CASILLERO_NULL_VACIO);
        }
        if(!chequearPosicionCorrecta(casilleroBloqueado)){
            throw new ExcepcionTablero(TipoExcepcionTablero.POSICION_INVALIDA);
        }

        casillerosBloqueados.agregar(casilleroBloqueado);
    }

    /**
     * Precondiciones:
     * - La lista casilleroBloqueado no debe ser null ni estar vacía.
     * Postcondiciones:
     * - El casilleroBloqueado se quita de la lista de casillerosBloqueados.
     */
    public void removerPosicionBloqueada(Lista<Integer> casilleroBloqueado) throws Exception {
        if(casilleroBloqueado == null || casilleroBloqueado.estaVacia()){
            throw new ExcepcionTablero(TipoExcepcionTablero.CASILLERO_NULL_VACIO);
        }
        casillerosBloqueados.remover(casilleroBloqueado);
    }

    /**
     * Precondiciones:
     * - La ficha no debe ser null.
     * Postcondiciones:
     * - Se remueve la ficha del tablero.
     */
    public void removerFicha(Ficha ficha) throws Exception {
        if(ficha == null){
            throw new ExcepcionTablero(TipoExcepcionTablero.FICHAS_NULL);
        }
        this.fichas.remover(ficha);
    }

    /**
     * Precondiciones:
     * - La lista de posiciones no debe ser null o estar vacia.
     * Postcondiciones:
     * -
     *
     * @return true si la posicion para la ficha esta prohibida, false en caso contrario.
     */
    private boolean casillerosBloqueadosContiene(Lista<Integer> posiciones) throws Exception {
        if(posiciones == null || posiciones.estaVacia()){
            throw new ExcepcionTablero(TipoExcepcionTablero.POSICIONES_VACIO);
        }

        casillerosBloqueados.iniciarCursor();
        while(casillerosBloqueados.avanzarCursor()){
            Lista<Integer> casillero = casillerosBloqueados.obtenerCursor();
            if((casillero.obtener(1) == posiciones.obtener(1)) &&
                    (casillero.obtener(2) == posiciones.obtener(2)) &&
                    (casillero.obtener(3) == posiciones.obtener(3))){
                this.menuJuego.mostrarMensaje("La posición seleccionada se encuentra bloqueada.");
                return true;
            }
        }

        return false;
    }
    
    //GETTERS SIMPLES
    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return la lista de fichas.
     */
    public Lista<Ficha> getFichas(){
        return this.fichas;
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return la cantidad de fichas del modo de juego actual.
     */
    public int getCantidadDeFichasMaximas(){
        return this.modoDeJuego.getCantidadDeFichas();
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return la lista de casilleros bloqueados.
     */
    public Lista<Lista<Integer>> getCasillerosBloqueados(){
        return this.casillerosBloqueados;
    }



}
