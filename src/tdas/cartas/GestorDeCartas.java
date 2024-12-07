package tdas.cartas;

import enums.TipoDeCarta;
import enums.TipoExcepcionCartas;
import tdas.listaYCola.Lista;
import tdas.listaYCola.ListaSimplementeEnlazada;
import excepciones.ExcepcionCartas;
import tdas.Ficha;
import tdas.Jugador;
import tdas.Tateti;
import tdas.menu.MenuCartas;

public class GestorDeCartas {

    //ATRIBUTOS
    private Tateti tateti;
    private MenuCartas menuCartas;
    private Lista<Jugador> jugadoresQuePierdenTurno;
    private Jugador jugadorAnterior;
    private Ficha ultimaFichaBloqueada;
    private Ficha ultimaFichaCambioColor;
    private Ficha ultimaFichaDesbloqueada;
    private Lista<Integer> ultimoCasilleroSeleccionado;
    private TipoDeCarta cartaAnterior;
    private Jugador jugadorActual;


    /**
     * Precondiciones:
     * - tateti no debe ser null
     *
     * Postcondiciones:
     * - Asigna el valor a tateti.
     * - Crea una lista para jugadoresQuePierdenTurno y otra para ultimoCasilleroSeleccionado
     */
    public GestorDeCartas(Tateti tateti) throws Exception {
        if(tateti == null){
            throw new Exception("El tateti no puede ser null.");
        }
        this.tateti = tateti;
        this.menuCartas = new MenuCartas();
        this.jugadoresQuePierdenTurno = new ListaSimplementeEnlazada<>();
        this.ultimoCasilleroSeleccionado = new ListaSimplementeEnlazada<>();
    }

    /**
     * Precondiciones:
     * - Chequea que jugadorActual sea valido atraves de chequerQueJugadorNoEsNull.
     * - carta no debe ser null
     *
     * Postcondiciones:
     * - Se realiza una accion segun el tipo de carta.
     * - PIERDE_TURNO: Obtiene un jugador atraves de Menucartas.obtenerJugador() y luego la agrega a la lista de jugadoresQuePierdenTurno;
     * - BLOQUEA_FICHA: Obtiene una ficha atraves de MenuCartas.obtenerFichas, settea la ficha en no disponible y asigna la ficha a ultimaFichabloqueada.
     * - ANULA_CASILLERO: Obtiene un casillero atraves de Menu.Cartas.obtenerCasilleroPorCarta
     */
    public void ejecutarAccionDeCarta(Jugador jugadorActual, Carta carta) throws Exception {
        if(jugadorActual == null){
            throw new ExcepcionCartas(TipoExcepcionCartas.JUGADOR_NULL);
        }
        this.jugadorActual = jugadorActual;
        if(carta == null){
            throw new ExcepcionCartas(TipoExcepcionCartas.CARTAS_NULL);
        }

        TipoDeCarta tipoDeCarta = carta.getTipoDeCarta();
        switch (tipoDeCarta){
            case PIERDE_TURNO -> pierdeTurno();
            case BLOQUEA_FICHA -> bloquearFicha();
            case ANULA_CASILLERO -> anularCasillero();
            //case VUELVE_ATRAS -> vovlerUnaJugadaAtras();
            case CAMBIAR_COLOR_FICHA -> cambiarColorFicha();
            case DESBLOQUEAR_FICHA -> desbloquearFicha();
        }
        this.cartaAnterior = tipoDeCarta;
        this.jugadorAnterior = jugadorActual;
    }



    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Remueve jugadorActual de la lista temporal de jugadores.
     * - Selecciona un jugador de la lista restante y lo agrega a jugadoresQuePierdenTurno.
     */
    private void pierdeTurno() throws Exception {
        Lista<Jugador> jugadorAux = new ListaSimplementeEnlazada<>(tateti.getJugadores());
        if(jugadorAux.contiene(this.jugadorActual)){
            jugadorAux.remover(this.jugadorActual);
        }
        Jugador jugadorSeleccionado = menuCartas.obtenerJugador(jugadorAux);
        this.jugadoresQuePierdenTurno.agregar(jugadorSeleccionado);
    }

    /**
     * Precondiciones:
     * - jugador no debe ser nulo.
     * Postcondiciones:
     * - Si el jugador está en la lista de jugadores que pierden turno, se elimina de dicha lista y se devuelve true.
     * - Si el jugador no está en la lista, no se realiza ninguna acción y se devuelve false.
     *
     * @return true si el jugador esta dentro de la lista, false en caso contrario.
     */
    public boolean jugadorPierdeTurno(Jugador jugador) throws Exception {
        if(jugador == null){
            throw new ExcepcionCartas(TipoExcepcionCartas.JUGADOR_NULL);
        }
        if(this.jugadoresQuePierdenTurno.contiene(jugador)){
            this.jugadoresQuePierdenTurno.remover(jugador);
            return true;
        }
        return false;
    }

    /**
     * Precondiciones:
     * - La lista de fichas del tablero debe contener al menos una ficha que no sea del jugador actual.
     * Postcondiciones:
     * - La ficha seleccionada es marcada como no disponible.
     * - Se guarda la ficha bloqueada en la variable ultimaFichaBloqueada.
     */
    private void bloquearFicha() throws Exception{
        Lista<Ficha> fichasAuxiliar = obtenerFichasDisponibles();

        if(!fichasAuxiliar.estaVacia()){
            fichasAuxiliar.iniciarCursor();
            while(fichasAuxiliar.avanzarCursor()){
                Ficha ficha = fichasAuxiliar.obtenerCursor();
                if(!ficha.isDisponible()){
                    fichasAuxiliar.remover(ficha);
                }
            }

            Ficha ficha = this.menuCartas.obtenerFicha(fichasAuxiliar);
            ficha.setDisponible(false);
            this.ultimaFichaBloqueada = ficha;
        }
        else{
            this.menuCartas.mostrarMensaje("No hay cantidad suficiente de fichas para seleccionar.");
        }
    }

    /**
     * Precondiciones:
     * - La lista de fichas del tablero debe contener al menos una ficha que no sea del jugador actual.
     * Postcondiciones:
     * - La ficha seleccionada es marcada como disponible.
     * - Se guarda la ficha bloqueada en la variable ultimaFichaDesbloqueada.
     */
    private void desbloquearFicha() throws Exception {
        Lista<Ficha> fichasAuxiliar = new ListaSimplementeEnlazada<>(this.jugadorActual.getFichas());

        if(!fichasAuxiliar.estaVacia()){
            fichasAuxiliar.iniciarCursor();
            while(fichasAuxiliar.avanzarCursor()){
                Ficha ficha = fichasAuxiliar.obtenerCursor();
                if(ficha.isDisponible()){
                    fichasAuxiliar.remover(ficha);
                }
            }

            Ficha ficha = this.menuCartas.obtenerFicha(fichasAuxiliar);
            ficha.setDisponible(true);
            this.ultimaFichaDesbloqueada = ficha;
        }
        else{
            this.menuCartas.mostrarMensaje("No hay cantidad suficiente de fichas para seleccionar.");
        }
    }


    /**
     * Precondiciones:
     * - La lista de fichas del tablero debe contener al menos una ficha que no sea del jugador actual.
     * Postcondiciones:
     * - La ficha seleccionada es asignada al jugador actual.
     * - Se guarda la ficha cuyo color ha sido cambiado en la variable ultimaFichaCambioColor.
     */
    private void cambiarColorFicha() throws Exception {
        Lista<Ficha> fichasAuxiliar = obtenerFichasDisponibles();

        if(!fichasAuxiliar.estaVacia()){
            Ficha ficha = this.menuCartas.obtenerFicha(fichasAuxiliar);
            Jugador anteriorJugador = ficha.getJugador();
            anteriorJugador.removerFicha(ficha);
            ficha.setJugador(this.jugadorActual);
            this.jugadorActual.agregarFicha(ficha);
            this.ultimaFichaCambioColor = ficha;
        }
        else{
            this.menuCartas.mostrarMensaje("No hay cantidad suficiente de fichas para seleccionar.");
        }
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - El usuario selecciona una posición válida para anular.
     * - La posición seleccionada es bloqueada en el tablero.
     * - Se guarda la última posición seleccionada en la variable ultimoCasilleroSeleccionado.
     */
    private void anularCasillero() throws Exception {
        menuCartas.mostrarPosicionesOcupadas(tateti.getJugadores(), tateti.getTablero().getCasillerosBloqueados());

        boolean entradaValida = false;
        Lista<Integer> posicionCasillero = new ListaSimplementeEnlazada<>();

        String mensaje = "\nIngrese la posicion del casillero para anular. Escriba los numeros separados por un espacio de la forma: " +
                "posicionX posicionY posicionZ.";

        while(!entradaValida){
            posicionCasillero = menuCartas.obtenerPoscionDeFicha(mensaje);
            entradaValida = tateti.getTablero().chequearPosicionCorrecta(posicionCasillero);
        }

        tateti.getTablero().agregarPosicionBloqueada(posicionCasillero);
        this.ultimoCasilleroSeleccionado = posicionCasillero;
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Las fichas del jugador actual son removidas de la lista auxiliar de fichas disponibles.
     *
     * @return una lista de fichas disponibles en el tablero que no pertenecen al jugador actual.
     */
    private Lista<Ficha> obtenerFichasDisponibles() throws Exception {
        Lista<Ficha> fichasAuxiliar = new ListaSimplementeEnlazada<>(tateti.getTablero().getFichas());
        Lista<Ficha> fichasJugador = this.jugadorActual.getFichas();

        fichasJugador.iniciarCursor();
        while(fichasJugador.avanzarCursor()){
            Ficha fichaJugador = fichasJugador.obtenerCursor();
            if(fichasAuxiliar.contiene(fichaJugador)){
                fichasAuxiliar.remover(fichaJugador);
            }
        }
        return fichasAuxiliar;
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Se revierten los cambios realizados en la jugada anterior del jugador actual y del jugador anterior.
     * - Según el valor de cartaAnterior, se ejecutan acciones específicas como:
     *   - Desbloquear la ficha bloqueada.
     *   - Restaurar el casillero anulado.
     *   - Cambiar el color de la ficha a su estado anterior.
     */
    private void vovlerUnaJugadaAtras() throws Exception {
        if(this.jugadorAnterior != null){
            this.jugadorAnterior.moverFichaAposicionAnterior(tateti.getTablero());
        }
        this.jugadorActual.moverFichaAposicionAnterior(tateti.getTablero());
        if(this.cartaAnterior != null){
            switch (this.cartaAnterior){
                case BLOQUEA_FICHA -> this.ultimaFichaBloqueada.setDisponible(true);

                case ANULA_CASILLERO -> this.tateti.getTablero().removerPosicionBloqueada(ultimoCasilleroSeleccionado);

                case CAMBIAR_COLOR_FICHA -> this.ultimaFichaCambioColor.volverAJugadorAnterior();
            }
        }
    }
}
