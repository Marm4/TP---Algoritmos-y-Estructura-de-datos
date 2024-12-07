package tdas.menu;

import enums.ColorJugador;
import tdas.listaYCola.Lista;
import tdas.listaYCola.ListaSimplementeEnlazada;
import org.junit.jupiter.api.Test;
import tdas.Ficha;
import tdas.Jugador;

import static org.junit.jupiter.api.Assertions.*;

class MenuTest {

    @Test
    void mostrarPosicionesOcupadas() throws Exception {
        Menu menu = new Menu();
        Jugador jugador1 = new Jugador("Jugador1", ColorJugador.AZUL);
        Jugador jugador2 = new Jugador("Jugador2", ColorJugador.VERDE);
        Jugador jugador3 = new Jugador("Jugador3", ColorJugador.AMARILLO);
        Lista<Jugador> jugadores = new ListaSimplementeEnlazada<>();

        jugadores.agregar(jugador1);
        jugadores.agregar(jugador2);
        jugadores.agregar(jugador3);

        Lista<Integer> posiciones = new ListaSimplementeEnlazada<>();
        posiciones.agregar(1);
        posiciones.agregar(1);
        posiciones.agregar(1);

        Ficha ficha = new Ficha(posiciones, jugador1);
        jugador1.agregarFicha(ficha);
        Ficha ficha2 = new Ficha(posiciones, jugador2);
        jugador2.agregarFicha(ficha2);
        Ficha ficha3 = new Ficha(posiciones, jugador3);
        jugador3.agregarFicha(ficha3);
        Lista<Lista<Integer>> posicionesOcupadas = new ListaSimplementeEnlazada<>();

        menu.mostrarPosicionesOcupadas(jugadores, posicionesOcupadas);


        jugador3.removerFicha(ficha3);
        ficha3.setJugador(jugador2);
        jugador2.agregarFicha(ficha);

        menu.mostrarPosicionesOcupadas(jugadores, posicionesOcupadas);
        menu.mostrarPosicionesOcupadas(jugadores, posicionesOcupadas);
        assertTrue(jugador3.getFichas().estaVacia());
    }

}