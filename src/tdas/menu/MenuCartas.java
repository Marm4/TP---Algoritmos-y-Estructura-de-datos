package tdas.menu;

import enums.TipoExcepcionMenu;
import tdas.listaYCola.Lista;
import excepciones.ExcepcionMenu;
import tdas.Jugador;

public class MenuCartas extends Menu{

    public MenuCartas(){
        super();
    }

    /**
     * Precondiciones:
     * - La lista jugadores no debe ser null ni estar vacía.
     * - jugadorActual no debe ser null.
     *
     * Postcondiciones:
     * - Muestra al usuario una lista de jugadores con sus nombres.
     * - Devuelve el jugador seleccionado por el usuario.
     *
     * @return El jugador seleccionado por el usuario.
     */
    public Jugador obtenerJugador(Lista<Jugador> jugadores) throws Exception {
        if (jugadores == null || jugadores.estaVacia()) {
            throw new ExcepcionMenu(TipoExcepcionMenu.JUGADORES_NULL_VACIO);
        }

        System.out.println("\nSeleccione un jugador");
        jugadores.iniciarCursor();
        int i = 0;
        while (jugadores.avanzarCursor()) {
            i++;
            Jugador jugador = jugadores.obtenerCursor();
            System.out.println(i + ". " + jugador.getNombre());
        }

        return jugadores.obtener(obtenerUnNumero(1,i));
    }

}
