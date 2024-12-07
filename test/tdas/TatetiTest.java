package tdas;

import enums.ModoDeJuego;
import excepciones.ExcepcionTateti;
import org.junit.jupiter.api.Test;
import tdas.listaYCola.Lista;
import tdas.listaYCola.ListaSimplementeEnlazada;

import static org.junit.jupiter.api.Assertions.*;

class TatetiTest {

    /**
     * - Tateti al inicializarse con ModoDeJuego.MODO_UNO espera que haya entre 2 y 3 jugadores.
     */
    @Test
    void testConstructorValidoModoUno() throws Exception {
        Lista<String> jugadores = new ListaSimplementeEnlazada<>();

        jugadores.agregar("jugadorUno");
        jugadores.agregar("jugadorDos");

        Tateti tateti = new Tateti(ModoDeJuego.MODO_UNO, jugadores);
        assertNotNull(tateti, "El objeto Tateti no debería ser null");

    }

    /**
     * - Tateti al inicializarse con ModoDeJuego.MODO_DOS espera que haya entre 3 y 5 jugadores.
     */
    @Test
    void testConstructorValidoModoDos() throws Exception {
        Lista<String> jugadores = new ListaSimplementeEnlazada<>();

        jugadores.agregar("jugadorUno");
        jugadores.agregar("jugadorDos");
        jugadores.agregar("jugadorTres");
        jugadores.agregar("jugadorCuatro");

        Tateti tateti = new Tateti(ModoDeJuego.MODO_DOS, jugadores);
        assertNotNull(tateti, "El objeto Tateti no debería ser null");

    }

    /**
     * - Tateti al inicializarse con ModoDeJuego.MODO_TRES espera que haya entre 5 y 10 jugadores.
     */
    @Test
    void testConstructorValidoModoTres() throws Exception {
        Lista<String> jugadores = new ListaSimplementeEnlazada<>();

        jugadores.agregar("jugadorUno");
        jugadores.agregar("jugadorDos");
        jugadores.agregar("jugadorTres");
        jugadores.agregar("jugadorCuatro");
        jugadores.agregar("jugadorCinco");
        jugadores.agregar("jugadorSeis");
        jugadores.agregar("jugadorSiete");

        Tateti tateti = new Tateti(ModoDeJuego.MODO_TRES, jugadores);
        assertNotNull(tateti, "El objeto Tateti no debería ser null");
    }

    /**
     * - Tateti al inicializarse con ModoDeJuego.MODO_UNO espera que haya entre 2 y 3 jugadores.
     */
    @Test
    void testConstructorModoUnoInvalido()throws Exception {
        Lista<String> jugadores = new ListaSimplementeEnlazada<>();

        jugadores.agregar("jugadorUno");

        assertThrows(ExcepcionTateti.class, () -> new Tateti(ModoDeJuego.MODO_UNO, jugadores),
                "Se esperaba que la excepción ExcepcionTateti fuera lanzada debido a que ModoDeJuego.MODO_UNO espera que haya entre 2 y 3 jugadores y solamente hay 1");

        jugadores.agregar("jugadorDos");
        jugadores.agregar("jugadorTres");
        jugadores.agregar("jugadorCuatro");
        assertThrows(ExcepcionTateti.class, () -> new Tateti(ModoDeJuego.MODO_UNO, jugadores),
                "Se esperaba que la excepción ExcepcionTateti fuera lanzada debido a que ModoDeJuego.MODO_UNO espera que haya entre 2 y 3 jugadores y hay 4");

    }

    /**
     * - Tateti al inicializarse con ModoDeJuego.MODO_DOS espera que haya entre 3 y 5 jugadores.
     */
    @Test
    void testConstructorModoDosInvalido()throws Exception {
        Lista<String> jugadores = new ListaSimplementeEnlazada<>();

        jugadores.agregar("jugadorUno");
        jugadores.agregar("jugadorDos");

        assertThrows(ExcepcionTateti.class, () -> new Tateti(ModoDeJuego.MODO_DOS, jugadores),
                "Se esperaba que la excepción ExcepcionTateti fuera lanzada debido a que ModoDeJuego.MODO_DOS espera que haya entre 3 y 5 jugadores y solamente hay 2");

        jugadores.agregar("jugadorTres");
        jugadores.agregar("jugadorCuatro");
        jugadores.agregar("jugadorCinco");
        jugadores.agregar("jugadorSeis");

        assertThrows(ExcepcionTateti.class, () -> new Tateti(ModoDeJuego.MODO_DOS, jugadores),
                "Se esperaba que la excepción ExcepcionTateti fuera lanzada debido a que ModoDeJuego.MODO_DOS espera que haya entre 3 y 5 jugadores y hay 6");

    }

    /**
     * - Tateti al inicializarse con ModoDeJuego.MODO_TRES espera que haya entre 5 y 10 jugadores.
     */
    @Test
    void testConstructorModoTresInvalido()throws Exception {
        Lista<String> jugadores = new ListaSimplementeEnlazada<>();

        jugadores.agregar("jugadorUno");
        jugadores.agregar("jugadorDos");
        jugadores.agregar("jugadorTres");

        assertThrows(ExcepcionTateti.class, () -> new Tateti(ModoDeJuego.MODO_TRES, jugadores),
                "Se esperaba que la excepción ExcepcionTateti fuera lanzada debido a que ModoDeJuego.MODO_TRES espera que haya entre 5 y 10 jugadores y solamente hay 3");

        jugadores.agregar("jugadorCuatro");
        jugadores.agregar("jugadorCinco");
        jugadores.agregar("jugadorSeis");
        jugadores.agregar("jugadorSiete");
        jugadores.agregar("jugadorOcho");
        jugadores.agregar("jugadorNueve");
        jugadores.agregar("jugadorDiez");
        jugadores.agregar("jugadorOnce");

        assertThrows(ExcepcionTateti.class, () -> new Tateti(ModoDeJuego.MODO_TRES, jugadores),
                "Se esperaba que la excepción ExcepcionTateti fuera lanzada debido a que ModoDeJuego.MODO_TRES espera que haya entre 5 y 10 jugadores y hay 11");

    }

    /**
     * - Tateti al inicializarse con modoDeJuego igual a null espera que haya una excepción del tipo ExcepcionTateti.class.
     */
    @Test
    void testContructorModoDeJuegoInvalidos() {
        assertThrows(ExcepcionTateti.class, () -> new Tateti(null, null),
                "Se esperaba que la excepción ExcepcionTateti fuera lanzada debido a que modo de juego es null");

    }

    /**
     * - Tateti al inicializarse con nombreDeJugadores igual a null espera que haya una excepción del tipo ExcepcionTateti.class.
     */
    @Test
    void testContructorNombreJugadoresInvalidos() {
        assertThrows(ExcepcionTateti.class, () -> new Tateti(ModoDeJuego.MODO_UNO, null),
                "Se esperaba que la excepción ExcepcionTateti fuera lanzada debido a que jugadores null o vacíos.");
    }

}