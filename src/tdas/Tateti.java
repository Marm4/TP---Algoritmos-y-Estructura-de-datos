package tdas;

import enums.ColorJugador;
import enums.TipoExcepcionTateti;
import excepciones.ExcepcionTateti;
import enums.ModoDeJuego;
import tdas.cartas.Carta;
import tdas.cartas.GestorDeCartas;
import tdas.cartas.MazoDeCartas;
import tdas.listaYCola.Lista;
import tdas.listaYCola.ListaSimplementeEnlazada;
import tdas.menu.MenuJuego;

import java.util.Random;

public class Tateti {

    //ATRIBUTOS
    private Tablero tablero;
    private ListaSimplementeEnlazada<Jugador> jugadores;
    private MazoDeCartas mazoDeCartas;
    private GestorDeCartas gestorDeCartas;
    private MenuJuego menuJuego;
    private ImagenDelTablero imagenDelTablero;

    //CONSTRUCTORES
    /**
     * Precondiciones:
     * - modoDeJuego no debe ser null.
     * - nombreJugadores no debe ser null.
     * - nombreJugadores.getTamanio() debe estar entre modoDeJuego.getCantidadDeJugadoresMinima y modoDeJuego.getCantidadDeJugadoresMaxima.
     *
     * Postcondiciones:
     * - Crea un Tablero correspondiente al modoDeJuego.
     * - Crea una lista de Jugadores creada a partir de los nombres de nombreJugadores y los colores asignados a cada jugador.
     * - Crea un MazoDeCartas.
     * - Crea un gestorDeCartas.
     * - Crea un menuJuego.
     * - Crea una imagen del tablero.
     * - El número de jugadores será igual a la cantidad de elementos en nombreJugadores y estarán correctamente asignados a los colores de jugador definidos en `ColorJugador`.
     */
    public Tateti(ModoDeJuego modoDeJuego, Lista<String> nombreJugadores) throws Exception {
        if(modoDeJuego == null || nombreJugadores == null){
            throw new ExcepcionTateti(TipoExcepcionTateti.MODO_DE_JUEGO);
        }
        else if(nombreJugadores.getTamanio() < modoDeJuego.getCantidadDeJugadoresMinima() || nombreJugadores.getTamanio() > modoDeJuego.getCantidadDeJugadoresMaxima()){
            throw new ExcepcionTateti(TipoExcepcionTateti.CANTIDAD_DE_JUGADORES);
        }

        this.jugadores = new ListaSimplementeEnlazada<>();
        this.mazoDeCartas = new MazoDeCartas();
        this.gestorDeCartas = new GestorDeCartas(this);
        this.menuJuego = new MenuJuego();
        this.tablero = new Tablero(modoDeJuego, this.menuJuego);
        this.imagenDelTablero = new ImagenDelTablero(modoDeJuego.getCantidadDeCasilleros(), this.tablero.getFichas());
        ColorJugador[] colores = ColorJugador.values();
        nombreJugadores.iniciarCursor();

        int i = 0;
        while(nombreJugadores.avanzarCursor()){
            Jugador jugador = new Jugador(nombreJugadores.obtenerCursor(), colores[i]);
            this.jugadores.agregar(jugador);
            i++;
        }
    }

    //METODOS DE COMPORTAMIENTO
    /**
     * Precondiciones:
     * - jugadores no debe ser null y debe contener al menos un jugador.
     * Postcondiciones:
     * - El método ejecuta un bucle de turnos donde cada jugador juega su turno de manera alternada.
     * - Al final de cada turno, exporta una imagen con el estado actual del tablero.
     * - El método termina cuando un jugador gana, lo que se verifica mediante el método tablero.chequearGanador(jugador).
     * - El método siempre terminará con un ganador.
     */
    public void jugar() throws Exception {
        if(this.jugadores == null || this.jugadores.estaVacia()){
            throw new ExcepcionTateti(TipoExcepcionTateti.JUGADOR_NULL);
        }

        boolean ganador = false;
        Jugador jugador = null;

        while(!ganador){
            this.jugadores.iniciarCursor();
            while(this.jugadores.avanzarCursor() && !ganador){
                jugador = this.jugadores.obtenerCursor();
                jugarTurno(jugador);

                this.imagenDelTablero.crearImagenDelTablero();
                ganador = this.tablero.chequearGanador(jugador);
            }
        }

        this.menuJuego.ganador(jugador.getNombre());
    }

    /**
     * Precondiciones:
     * - Chequea que el jugador sea correcto atraves de chequearJugadorCorrecto.
     * Postcondiciones:
     * - Si el jugador tiene suficiente espacio en su mano (es decir, la suma de sus cartas actuales y las obtenidas no excede la cantidad máxima de cartas permitidas), las cartas son agregadas al jugador.
     * - Si no hay suficiente espacio, las cartas obtenidas se devuelven al mazo de cartas.
     * - Si el jugador tiene menor cantidad de fich as que la cantidad de fichas maximas, se agrega una ficha nueva.
     * - Si el jugador no tiene menor cantidad de fichas que la cantidad de fichas maximas, se mueve una ficha.
     */
    private void jugarTurno(Jugador jugador) throws Exception {
        chequearJugadorCorrecto(jugador);

        if(this.gestorDeCartas.jugadorPierdeTurno(jugador)){
            return;
        }

        Lista<Carta> cartas = TirarDadoYObtenerCartas();
        if(jugador.getCantidadDeCartas() + cartas.getTamanio() > ModoDeJuego.CANTIDAD_MAXIMA_DE_CARTAS_EN_MANO){
            this.mazoDeCartas.devolverCartaAlMazo(cartas);
        }
        else{
            jugador.agregarCartas(cartas);
        }

        this.menuJuego.mostrarPosicionesOcupadas(this.jugadores, this.tablero.getCasillerosBloqueados());

        if(jugador.getCantidadDeFichas() < this.tablero.getCantidadDeFichasMaximas()){
            agregarFichaNueva(jugador);
        }
        else{
            moverFicha(jugador);
        }

        jugarCartas(jugador);
    }

    /**
     * Precondiciones:
     * - Chequea que el jugador sea correcto atraves de chequearJugadorCorrecto.
     * Postcondiciones:
     * - Obtiene una lista de posiciones de obtenerPosicionFicha.
     * - Cuando la posición es válida, se crea una nueva ficha en esa posición y se agrega al tablero y al jugador.
     */
    private void agregarFichaNueva(Jugador jugador) throws Exception {
        chequearJugadorCorrecto(jugador);

        String mensaje = "\nTurno de " + jugador.getNombre() + ". Color: " + jugador.getColorJugador() + "\nIngrese la posición donde desea colocar la ficha.";
        Lista<Integer> posiciones = obtenerPoscionFicha(mensaje);
        Ficha ficha = new Ficha(posiciones, jugador);
        this.tablero.agregarFicha(ficha);
        jugador.agregarFicha(ficha);
    }

    /**
     * Precondiciones:
     * - El jugador no debe ser null
     * Postcondiciones:
     * - Se obtiene una ficha para mover atraves de menuJuego.obtenerFichaParaMover
     * - Se obtiene una lista de Integer con la nueva posicion para la ficha.
     * - Se mueve la ficha con la posicion ingresada.
     */
    private void moverFicha(Jugador jugador) throws Exception {
        chequearJugadorCorrecto(jugador);
        String mensaje = "\nTurno de " + jugador.getNombre() + ". Color: " + jugador.getColorJugador();
        Ficha ficha;

        do{
            ficha = this.menuJuego.obtenerFichaParaMover(jugador, mensaje);
            mensaje = "La ficha se encuentra bloqueada. Seleccione otra ficha";
        }
        while(!ficha.isDisponible());

        mensaje = "\nIngrese la posición donde desea mover la ficha.";
        Lista<Integer> posiciones = obtenerPoscionFicha(mensaje);
        jugador.moverFicha(ficha, posiciones);
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Obtiene una lista de posiciones de obtenerPosicionFicha. El jugador selecciona una posición en el tablero, que es validada con tablero.chequearPosicionCorrecta(posiciones).
     *
     * @return la lista de posiciones.
     */
    private Lista<Integer> obtenerPoscionFicha(String mensaje) throws Exception {
        Lista<Integer> posiciones;
        do{
            posiciones = this.menuJuego.obtenerPoscionDeFicha(mensaje);
        }
        while (!this.tablero.chequearPosicionCorrecta(posiciones));
        return posiciones;
    }

    /**
     * Precondiciones:
     * - jugadores no debe ser null y debe contener al menos un jugador.
     * - jugador no debe ser null.
     * -
     * Postcondiciones:
     * -
     */
    private void chequearJugadorCorrecto(Jugador jugador) throws ExcepcionTateti {
        if(this.jugadores == null || this.jugadores.estaVacia() || jugador == null){
            throw new ExcepcionTateti(TipoExcepcionTateti.JUGADOR_NULL);
        }
    }

    /**
     * Precondiciones:
     * - El jugador no debe ser null.
     * Postcondiciones:
     * - Se obtiene una carta atraves de menuJuego.obtenerCarta
     * - Se ejecuta la acción de esa carta para el jugador.
     */
    private void jugarCartas(Jugador jugador) throws Exception {
        chequearJugadorCorrecto(jugador);

        Carta carta = this.menuJuego.obtenerCarta(jugador.getCartas(), "Seleccione una carta para jugar.");
        if(carta == null){
            return;
        }

        this.gestorDeCartas.ejecutarAccionDeCarta(jugador, carta);
        jugador.removerCarta(carta);
        this.mazoDeCartas.devolverCartaAlMazo(carta);
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Se lanza un dado (valor entre 0 y 5) y se obtienen cartas del mazo igual a la cantidad resultante del dado.
     * - Se muetran las cartas atraves de menuJuego.mostrarCartas.
     * @return devuelve las cartas obtenidas son devueltas en una lista de tipo `Lista<Carta>`.
     */
    private Lista<Carta> TirarDadoYObtenerCartas() throws Exception {
        Random random = new Random();
        int numeroDelDado = random.nextInt(6)+1;

        Lista<Carta> cartasObtenidas = new ListaSimplementeEnlazada<>();
        for(int i=1; i<=numeroDelDado; i++){
            cartasObtenidas.agregar(this.mazoDeCartas.getCarta());
        }

        String mensaje = "\nSe tiro el dado y cayo en el numero: " + numeroDelDado +
                "\nSe levanto del mazo la cantidad de "+ numeroDelDado +" cartas. Las cartas que tocaron fueron: ";

        this.menuJuego.mostrarCartas(cartasObtenidas, mensaje);
        return cartasObtenidas;
    }

    //GETTERS SIMPLES
    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return el tablero de juego.
     */
    public Tablero getTablero(){
        return this.tablero;
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return la lista de jugadores.
     */
    public Lista<Jugador> getJugadores(){
        return this.jugadores;
    }

}
