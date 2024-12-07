package tdas.tableroTest;

import enums.ColorJugador;
import enums.ModoDeJuego;
import tdas.listaYCola.Lista;
import org.junit.jupiter.api.Test;
import tdas.Jugador;
import tdas.Tablero;
import tdas.menu.MenuJuego;

import static org.junit.jupiter.api.Assertions.*;
import static tdas.tableroTest.TableroGeneralesTest.crearFicha;
import static tdas.tableroTest.TableroGeneralesTest.crearListaYAgregarPosiciones;

public class TableroModoDosTest {

    /**
     * - Una vez que la posicion esta ocupada, debe ser una posicion incorrecta
     */
    @Test
    void testChequearPosicionIncorreta() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_DOS, new MenuJuego());

        Lista<Integer> posiciones = crearListaYAgregarPosiciones(4, 4,4);
        tablero.agregarFicha(crearFicha(posiciones, new Jugador("jugador", ColorJugador.AZUL)));

        assertFalse(tablero.chequearPosicionCorrecta(posiciones));
    }

    /**
     * - Si la posicion esta libre es una posicion libre
     */
    @Test
    void testChequearPosicionCorreta() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_DOS, new MenuJuego());

        Lista<Integer> posiciones = crearListaYAgregarPosiciones(5, 5,5);

        assertTrue(tablero.chequearPosicionCorrecta(posiciones));
    }

    /**
     * - Chekeamos por fila
     */
    @Test
    void testChequearGanadorCorrectoUno() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_DOS, new MenuJuego());
        Lista<Integer> posiciones1 = crearListaYAgregarPosiciones(1, 1,1);
        Lista<Integer> posiciones2 = crearListaYAgregarPosiciones(2, 1,1);
        Lista<Integer> posiciones3 = crearListaYAgregarPosiciones(3, 1,1);
        Lista<Integer> posiciones4 = crearListaYAgregarPosiciones(4, 1,1);
        Lista<Integer> posiciones5 = crearListaYAgregarPosiciones(5, 1,1);
        Jugador jugador = new Jugador("jugador", ColorJugador.AZUL);

        tablero.agregarFicha(crearFicha(posiciones1, jugador));
        tablero.agregarFicha(crearFicha(posiciones2, jugador));
        tablero.agregarFicha(crearFicha(posiciones3, jugador));
        tablero.agregarFicha(crearFicha(posiciones4, jugador));
        tablero.agregarFicha(crearFicha(posiciones5, jugador));

        assertTrue(tablero.chequearGanador(jugador));
    }

    /**
     * - Chekeamos por columna
     */
    @Test
    void testChequearGanadorCorrectoDos() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_DOS, new MenuJuego());
        Lista<Integer> posiciones1 = crearListaYAgregarPosiciones(1, 1,1);
        Lista<Integer> posiciones2 = crearListaYAgregarPosiciones(1, 2,1);
        Lista<Integer> posiciones3 = crearListaYAgregarPosiciones(1, 3,1);
        Lista<Integer> posiciones4 = crearListaYAgregarPosiciones(1, 4,1);
        Lista<Integer> posiciones5 = crearListaYAgregarPosiciones(1, 5,1);
        Jugador jugador = new Jugador("jugador", ColorJugador.AZUL);

        tablero.agregarFicha(crearFicha(posiciones1, jugador));
        tablero.agregarFicha(crearFicha(posiciones2, jugador));
        tablero.agregarFicha(crearFicha(posiciones3, jugador));
        tablero.agregarFicha(crearFicha(posiciones4, jugador));
        tablero.agregarFicha(crearFicha(posiciones5, jugador));

        assertTrue(tablero.chequearGanador(jugador));
    }


    /**
     * - Chekeamos por diagonalUno
     */
    @Test
    void testChequearGanadorCorrectoTres() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_DOS, new MenuJuego());
        Lista<Integer> posiciones1 = crearListaYAgregarPosiciones(1, 1,1);
        Lista<Integer> posiciones2 = crearListaYAgregarPosiciones(2, 2,1);
        Lista<Integer> posiciones3 = crearListaYAgregarPosiciones(3, 3,1);
        Lista<Integer> posiciones4 = crearListaYAgregarPosiciones(4, 4,1);
        Lista<Integer> posiciones5 = crearListaYAgregarPosiciones(5, 5,1);
        Jugador jugador = new Jugador("jugador", ColorJugador.AZUL);

        tablero.agregarFicha(crearFicha(posiciones1, jugador));
        tablero.agregarFicha(crearFicha(posiciones2, jugador));
        tablero.agregarFicha(crearFicha(posiciones3, jugador));
        tablero.agregarFicha(crearFicha(posiciones4, jugador));
        tablero.agregarFicha(crearFicha(posiciones5, jugador));

        assertTrue(tablero.chequearGanador(jugador));
    }

    /**
     * - Chekeamos por diagonalDos
     */
    @Test
    void testChequearGanadorCorrectoCuatro() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_DOS, new MenuJuego());
        Lista<Integer> posiciones1 = crearListaYAgregarPosiciones(5, 1,1);
        Lista<Integer> posiciones2 = crearListaYAgregarPosiciones(4, 2,1);
        Lista<Integer> posiciones3 = crearListaYAgregarPosiciones(3, 3,1);
        Lista<Integer> posiciones4 = crearListaYAgregarPosiciones(2, 4,1);
        Lista<Integer> posiciones5 = crearListaYAgregarPosiciones(1, 5,1);
        Jugador jugador = new Jugador("jugador", ColorJugador.AZUL);

        tablero.agregarFicha(crearFicha(posiciones1, jugador));
        tablero.agregarFicha(crearFicha(posiciones2, jugador));
        tablero.agregarFicha(crearFicha(posiciones3, jugador));
        tablero.agregarFicha(crearFicha(posiciones4, jugador));
        tablero.agregarFicha(crearFicha(posiciones5, jugador));

        assertTrue(tablero.chequearGanador(jugador));
    }
}
