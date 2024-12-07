package tdas.tableroTest;

import enums.ColorJugador;
import enums.ModoDeJuego;
import tdas.listaYCola.Lista;
import org.junit.jupiter.api.Test;
import tdas.Jugador;
import tdas.Tablero;
import tdas.menu.MenuJuego;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tdas.tableroTest.TableroGeneralesTest.crearFicha;
import static tdas.tableroTest.TableroGeneralesTest.crearListaYAgregarPosiciones;

public class TableroModoTresTest {

    /**
     * - Una vez que la posicion esta ocupada, debe ser una posicion incorrecta
     */
    @Test
    void testChequearPosicionIncorreta() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_TRES, new MenuJuego());

        Lista<Integer> posiciones = crearListaYAgregarPosiciones(8, 7,9);
        tablero.agregarFicha(crearFicha(posiciones, new Jugador("jugador", ColorJugador.AZUL)));

        assertFalse(tablero.chequearPosicionCorrecta(posiciones));
    }

    /**
     * - Si la posicion esta libre es una posicion libre
     */
    @Test
    void testChequearPosicionCorreta() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_TRES, new MenuJuego());

        Lista<Integer> posiciones = crearListaYAgregarPosiciones(8, 8,8);

        assertTrue(tablero.chequearPosicionCorrecta(posiciones));
    }

    /**
     * - Chekeamos por fila
     */
    @Test
    void testChequearGanadorCorrectoUno() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_TRES, new MenuJuego());
        Lista<Integer> posiciones9 = crearListaYAgregarPosiciones(1, 2,1);
        Lista<Integer> posiciones1 = crearListaYAgregarPosiciones(2, 2,1);
        Lista<Integer> posiciones2 = crearListaYAgregarPosiciones(3, 2,1);
        Lista<Integer> posiciones3 = crearListaYAgregarPosiciones(4, 2,1);
        Lista<Integer> posiciones4 = crearListaYAgregarPosiciones(5, 2,1);
        Lista<Integer> posiciones5 = crearListaYAgregarPosiciones(6, 2,1);
        Lista<Integer> posiciones6 = crearListaYAgregarPosiciones(7, 2,1);
        Lista<Integer> posiciones7 = crearListaYAgregarPosiciones(8, 2,1);
        Lista<Integer> posiciones8 = crearListaYAgregarPosiciones(9, 2,1);
        Lista<Integer> posiciones10 = crearListaYAgregarPosiciones(10, 2,1);
        Jugador jugador = new Jugador("jugador", ColorJugador.AZUL);

        tablero.agregarFicha(crearFicha(posiciones1, jugador));
        tablero.agregarFicha(crearFicha(posiciones2, jugador));
        tablero.agregarFicha(crearFicha(posiciones3, jugador));
        tablero.agregarFicha(crearFicha(posiciones4, jugador));
        tablero.agregarFicha(crearFicha(posiciones5, jugador));
        tablero.agregarFicha(crearFicha(posiciones6, jugador));
        tablero.agregarFicha(crearFicha(posiciones7, jugador));
        tablero.agregarFicha(crearFicha(posiciones8, jugador));
        tablero.agregarFicha(crearFicha(posiciones9, jugador));
        tablero.agregarFicha(crearFicha(posiciones10, jugador));

        assertTrue(tablero.chequearGanador(jugador));
    }

    /**
     * - Chekeamos por columna
     */
    @Test
    void testChequearGanadorCorrectoDos() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_TRES, new MenuJuego());

        Lista<Integer> posiciones1 = crearListaYAgregarPosiciones(1, 10,1);
        Lista<Integer> posiciones2 = crearListaYAgregarPosiciones(1, 1,1);
        Lista<Integer> posiciones3 = crearListaYAgregarPosiciones(1, 2,1);
        Lista<Integer> posiciones4 = crearListaYAgregarPosiciones(1, 3,1);
        Lista<Integer> posiciones5 = crearListaYAgregarPosiciones(1, 4,1);
        Lista<Integer> posiciones6 = crearListaYAgregarPosiciones(1, 5,1);
        Lista<Integer> posiciones7 = crearListaYAgregarPosiciones(1, 6,1);
        Lista<Integer> posiciones8 = crearListaYAgregarPosiciones(1, 7,1);
        Lista<Integer> posiciones9 = crearListaYAgregarPosiciones(1, 8,1);
        Lista<Integer> posiciones10 = crearListaYAgregarPosiciones(1, 9,1);
        Jugador jugador = new Jugador("jugador", ColorJugador.AZUL);

        tablero.agregarFicha(crearFicha(posiciones1, jugador));
        tablero.agregarFicha(crearFicha(posiciones2, jugador));
        tablero.agregarFicha(crearFicha(posiciones3, jugador));
        tablero.agregarFicha(crearFicha(posiciones4, jugador));
        tablero.agregarFicha(crearFicha(posiciones5, jugador));
        tablero.agregarFicha(crearFicha(posiciones6, jugador));
        tablero.agregarFicha(crearFicha(posiciones7, jugador));
        tablero.agregarFicha(crearFicha(posiciones8, jugador));
        tablero.agregarFicha(crearFicha(posiciones9, jugador));
        tablero.agregarFicha(crearFicha(posiciones10, jugador));

        assertTrue(tablero.chequearGanador(jugador));
    }

    /**
     * - Chekeamos por diagonalUno
     */
    @Test
    void testChequearGanadorCorrectoTres() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_TRES, new MenuJuego());

        Lista<Integer> posiciones1 = crearListaYAgregarPosiciones(10, 10,1);
        Lista<Integer> posiciones2 = crearListaYAgregarPosiciones(1, 1,1);
        Lista<Integer> posiciones3 = crearListaYAgregarPosiciones(2, 2,1);
        Lista<Integer> posiciones4 = crearListaYAgregarPosiciones(3, 3,1);
        Lista<Integer> posiciones5 = crearListaYAgregarPosiciones(4, 4,1);
        Lista<Integer> posiciones6 = crearListaYAgregarPosiciones(5, 5,1);
        Lista<Integer> posiciones7 = crearListaYAgregarPosiciones(6, 6,1);
        Lista<Integer> posiciones8 = crearListaYAgregarPosiciones(7, 7,1);
        Lista<Integer> posiciones9 = crearListaYAgregarPosiciones(8, 8,1);
        Lista<Integer> posiciones10 = crearListaYAgregarPosiciones(9, 9,1);
        Jugador jugador = new Jugador("jugador", ColorJugador.AZUL);

        tablero.agregarFicha(crearFicha(posiciones1, jugador));
        tablero.agregarFicha(crearFicha(posiciones2, jugador));
        tablero.agregarFicha(crearFicha(posiciones3, jugador));
        tablero.agregarFicha(crearFicha(posiciones4, jugador));
        tablero.agregarFicha(crearFicha(posiciones5, jugador));
        tablero.agregarFicha(crearFicha(posiciones6, jugador));
        tablero.agregarFicha(crearFicha(posiciones7, jugador));
        tablero.agregarFicha(crearFicha(posiciones8, jugador));
        tablero.agregarFicha(crearFicha(posiciones9, jugador));
        tablero.agregarFicha(crearFicha(posiciones10, jugador));

        assertTrue(tablero.chequearGanador(jugador));
    }

    /**
     * - Chekeamos por diagonalDos
     */
    @Test
    void testChequearGanadorCorrectoCuatro() throws Exception {
        Tablero tablero = new Tablero(ModoDeJuego.MODO_TRES, new MenuJuego());

        Lista<Integer> posiciones1 = crearListaYAgregarPosiciones(1, 10,1);
        Lista<Integer> posiciones2 = crearListaYAgregarPosiciones(2, 9,1);
        Lista<Integer> posiciones3 = crearListaYAgregarPosiciones(3, 8,1);
        Lista<Integer> posiciones4 = crearListaYAgregarPosiciones(4, 7,1);
        Lista<Integer> posiciones5 = crearListaYAgregarPosiciones(5, 6,1);
        Lista<Integer> posiciones6 = crearListaYAgregarPosiciones(6, 5,1);
        Lista<Integer> posiciones7 = crearListaYAgregarPosiciones(7, 4,1);
        Lista<Integer> posiciones8 = crearListaYAgregarPosiciones(8, 3,1);
        Lista<Integer> posiciones9 = crearListaYAgregarPosiciones(9, 2,1);
        Lista<Integer> posiciones10 = crearListaYAgregarPosiciones(10, 1,1);
        Jugador jugador = new Jugador("jugador", ColorJugador.AZUL);

        tablero.agregarFicha(crearFicha(posiciones1, jugador));
        tablero.agregarFicha(crearFicha(posiciones2, jugador));
        tablero.agregarFicha(crearFicha(posiciones3, jugador));
        tablero.agregarFicha(crearFicha(posiciones4, jugador));
        tablero.agregarFicha(crearFicha(posiciones5, jugador));
        tablero.agregarFicha(crearFicha(posiciones6, jugador));
        tablero.agregarFicha(crearFicha(posiciones7, jugador));
        tablero.agregarFicha(crearFicha(posiciones8, jugador));
        tablero.agregarFicha(crearFicha(posiciones9, jugador));
        tablero.agregarFicha(crearFicha(posiciones10, jugador));

        assertTrue(tablero.chequearGanador(jugador));
    }
}
