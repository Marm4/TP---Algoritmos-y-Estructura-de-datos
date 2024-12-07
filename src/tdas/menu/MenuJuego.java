package tdas.menu;

import enums.TipoExcepcionMenu;
import excepciones.ExcepcionMenu;
import tdas.Ficha;
import tdas.Jugador;

public class MenuJuego extends Menu{

    public MenuJuego(){
        super();
    }

    public Ficha obtenerFichaParaMover(Jugador jugador, String mensaje) throws Exception {
        if(jugador == null){
            throw new ExcepcionMenu(TipoExcepcionMenu.JUGADORES_NULL_VACIO);
        }

        if(!mensaje.isEmpty()){
            System.out.println(mensaje);
        }
        return this.obtenerFicha(jugador.getFichas());
    }

    /**
     * Precondiciones:
     * - El tamaño debe ser un número entero positivo que representa el tamaño del tablero en cada dimensión (X, Y, Z).
     * - Se hace una verificación que este dentro del tamaño minimo y maximo del tablero.
     *
     * Postcondiciones:
     * - Muestra un mensaje en consola indicando que la posición es incorrecta, con una referencia al tamaño del tablero.
     */
    public void posicionIncorrecta(int tamanio) throws Exception {
        if(tamanio <= 0 || tamanio > 10){
            throw new Exception("El tamaño pasado por parametro es incorrecto.");
        }
        System.out.println("Posición incorrecta. Inténtelo nuevamente. Recuerde que es un tablero de tamaño " + tamanio + " x " + tamanio + " x " + tamanio + "\n");
    }


}
