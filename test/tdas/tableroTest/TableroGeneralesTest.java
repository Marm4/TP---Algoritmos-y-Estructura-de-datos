package tdas.tableroTest;

import enums.ColorJugador;
import enums.ModoDeJuego;
import tdas.listaYCola.Lista;
import tdas.listaYCola.ListaSimplementeEnlazada;
import excepciones.ExcepcionTablero;
import org.junit.jupiter.api.Test;
import tdas.Ficha;
import tdas.Jugador;
import tdas.Tablero;
import tdas.menu.MenuJuego;

import static org.junit.jupiter.api.Assertions.*;

public class TableroGeneralesTest {

    /**
     * - Los tableros se tienen que crear exitosamente sin que salten excepciones
     */
    @Test
    void testConstructorValido() throws Exception {
        Tablero tableroUno = new Tablero(ModoDeJuego.MODO_UNO, new MenuJuego());
        Tablero tableroDos = new Tablero(ModoDeJuego.MODO_DOS, new MenuJuego());
        Tablero tableroTres = new Tablero(ModoDeJuego.MODO_TRES, new MenuJuego());
    }

    /**
     * - Se esperaba que la excepción ExcepcionTablero fuera lanzada debido a que el modoDeJuego es null
     */
    @Test
    void testConstructorInvalido(){
        assertThrows(ExcepcionTablero.class, () -> new Tablero(null, new MenuJuego()),
                "Se esperaba que la excepción ExcepcionTablero fuera lanzada debido a que el modoDeJuego es null");

    }

    /**
     * - El jugador no deberia ser ganador
     */
    @Test
    void testChequearGanadorIncorrecto() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_UNO, new MenuJuego());
        Jugador jugador = new Jugador("jugador", ColorJugador.AZUL);
        assertFalse(tablero.chequearGanador(jugador));
    }

    /**
     * - Se esperaba que la excepción ExcepcionTablero fuera lanzada debido a que el jugador es null
     */
    @Test
    void testChequearGanadorNull() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_UNO, new MenuJuego());

        assertThrows(ExcepcionTablero.class, () -> tablero.chequearGanador(null),
                "Se esperaba que la excepción ExcepcionTablero fuera lanzada debido a que el jugador es null");
    }

    /**
     * - Se esperaba que la ficha creada se agregue al tablero sin que salten excepciones
     */
    @Test
    void testAgregarFichaValido() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_UNO, new MenuJuego());

        Lista<Integer> posiciones = crearListaYAgregarPosiciones(1,1,3);

        Jugador jugador = new Jugador("jugador", ColorJugador.AZUL);
        Ficha ficha = new Ficha(posiciones, jugador);

        tablero.agregarFicha(ficha);
    }

    /**
     * - Se esperaba que la excepción ExcepcionTablero fuera lanzada debido a la ficha es null
     */
    @Test
    void testAgregarFichaInvalido() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_UNO, new MenuJuego());

        assertThrows(ExcepcionTablero.class, () -> tablero.agregarFicha(null),
                "Se esperaba que la excepción ExcepcionTablero fuera lanzada debido a la ficha es null");

    }

    /**
     * - Si la posicion esta fuera del rango del tablero, debe ser una posicion incorrecta
     */
    @Test
    void testChequearPosicionIncorreta() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_UNO, new MenuJuego());
        Lista<Integer> posiciones = crearListaYAgregarPosiciones(4, 4,4);

        assertThrows(ExcepcionTablero.class, () -> tablero.agregarFicha(crearFicha(posiciones, new Jugador("jugador", ColorJugador.AZUL))),
                "Se esperaba que la excepción ExcepcionTablero fuera lanzada debido a que la posicion esta fuera del rango");
    }

    /**
     * - Se esperaba que la excepción ExcepcionTablero fuera lanzada debido a la lista es null
     */
    @Test
    void testChequearPosicionNull() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_UNO, new MenuJuego());

        assertThrows(ExcepcionTablero.class, () -> tablero.chequearPosicionCorrecta(null),
                "Se esperaba que la excepción ExcepcionTablero fuera lanzada debido a la lista es null");
    }

    /**
     * - Se check que la ficha se agregue en la posicion, luego se remueve y check que la posición esta libre.
     */
    @Test
    void testRemoverFicha() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_UNO, new MenuJuego());

        Lista<Integer> posiciones = crearListaYAgregarPosiciones(1,2,3);
        Jugador jugador = new Jugador("jugador", ColorJugador.AZUL);

        Ficha ficha = crearFicha(posiciones, jugador);
        tablero.agregarFicha(ficha);

        assertFalse(tablero.chequearPosicionCorrecta(ficha.getPosiciones()));

        tablero.removerFicha(ficha);
        assertTrue(tablero.chequearPosicionCorrecta(ficha.getPosiciones()));
    }

    /**
     * - Se agrega una posición bloqueada y se check que no es una posición correcta. Luego se quita la posición bloqueada
     * - y se check que la posición es correcta.
     */
    @Test
    void agregarPosicionBloqueadaYQuitarPosicionBloqueada() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_UNO, new MenuJuego());

        Lista<Integer> posiciones = crearListaYAgregarPosiciones(0,1,2);

        tablero.agregarPosicionBloqueada(posiciones);
        assertFalse(tablero.chequearPosicionCorrecta(posiciones));
        tablero.removerPosicionBloqueada(posiciones);
        assertTrue(tablero.chequearPosicionCorrecta(posiciones));
    }

    /**
     * - Crea y devuelve una lista.
     */
    static Lista<Integer> crearListaYAgregarPosiciones(int posicionX, int posicionY, int posicionZ) throws Exception {
        Lista<Integer> posiciones = new ListaSimplementeEnlazada<>();
        posiciones.agregar(posicionX);
        posiciones.agregar(posicionY);
        posiciones.agregar(posicionZ);

        return posiciones;
    }

/**
 * - Crea y devuelve una ficha.
 */
    static Ficha crearFicha(Lista<Integer> posiciones, Jugador jugador) throws Exception {
        Ficha ficha = new Ficha(posiciones, jugador);

        jugador.agregarFicha(ficha);
        return ficha;
    }
}
