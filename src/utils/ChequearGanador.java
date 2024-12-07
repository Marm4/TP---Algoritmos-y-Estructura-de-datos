package utils;

import enums.TipoExcepcionTablero;
import tdas.listaYCola.Lista;
import excepciones.ExcepcionTablero;
import tdas.Ficha;

public class ChequearGanador {

    /**
     * Precondiciones:
     * - fichasJugador no debe ser null.
     * - cantidadCasilleros debe ser un valor positivo.
     *
     * Postcondiciones:
     * - Retorna true si se cumple alguna de las condiciones de victoria; de lo contrario, retorna false.
     *
     * @return boolean
     */
    public static boolean chequear(Lista<Ficha> fichasJugador, int cantidadCasilleros) throws Exception {
        if(fichasJugador == null){
            throw new ExcepcionTablero(TipoExcepcionTablero.FICHAS_NULL);
        }

        for (int i=1; i <=cantidadCasilleros; i++) {
            if (verificarFila(fichasJugador, i, cantidadCasilleros) || verificarColumna(fichasJugador, i, cantidadCasilleros) || verificarProfundidad(fichasJugador, i, cantidadCasilleros)) {
                return true;
            }
        }

        if (verificarDiagonalesEnPlanos(fichasJugador, cantidadCasilleros)) {
            return true;
        }

        return verificarDiagonales3D(fichasJugador, cantidadCasilleros);
    }

    /**
     * Precondiciones:
     * - fichasJugador no debe ser null.
     * - profundidad y cantidadCasilleros deben ser valores no negativos y válidos en el contexto del tablero.
     *
     * Postcondiciones:
     * - Retorna true si se encuentra una fila completa en la profundidad indicada; de lo contrario, retorna false.
     *
     * @return boolean
     */
    private static boolean verificarFila(Lista<Ficha> fichasJugador, int profundidad, int cantidadCasilleros) throws Exception {
        for (int fila=1; fila <=cantidadCasilleros; fila++) {
            int contador = 0;
            for (int columna=1; columna<=cantidadCasilleros; columna++) {
                if (existeFichaEnPosicion(profundidad, fila, columna, fichasJugador, cantidadCasilleros)) {
                    contador++;
                }
            }
            if (contador == cantidadCasilleros) return true;
        }
        return false;
    }

    /**
     * Precondiciones:
     * - fichasJugador no debe ser null.
     * - profundidad y cantidadCasilleros deben ser valores no negativos y válidos en el contexto del tablero.
     *
     * Postcondiciones:
     * - Retorna true si se encuentra una columna completa en la profundidad indicada; de lo contrario, retorna false.
     *
     * @return boolean
     */
    private static boolean verificarColumna(Lista<Ficha> fichasJugador, int profundidad, int cantidadCasilleros) throws Exception {
        for (int columna=1; columna<=cantidadCasilleros; columna++) {
            int contador = 0;
            for (int fila=1; fila <=cantidadCasilleros; fila++) {
                if (existeFichaEnPosicion(profundidad, fila, columna, fichasJugador, cantidadCasilleros)) {
                    contador++;
                }
            }
            if (contador == cantidadCasilleros){
                return true;
            }

        }
        return false;
    }

    /**
     * Precondiciones:
     * - fichasJugador no debe ser null.
     * - columna y cantidadCasilleros deben ser valores no negativos y válidos en el contexto del tablero.
     *
     * Postcondiciones:
     * - Retorna true si se encuentra una profundidad completa en la columna indicada; de lo contrario, retorna false.
     *
     * @return boolean
     */
    private static boolean verificarProfundidad(Lista<Ficha> fichasJugador, int columna, int cantidadCasilleros) throws Exception {

        for (int fila=1; fila<=cantidadCasilleros; fila++) {
            int contador = 0;
            for (int profundidad=1; profundidad<=cantidadCasilleros; profundidad++) {
                if (existeFichaEnPosicion(profundidad, fila, columna, fichasJugador, cantidadCasilleros)) {
                    contador++;
                }
            }
            if (contador == cantidadCasilleros){
                return true;
            }
        }
        return false;
    }

    /**
     * Precondiciones:
     * - fichasJugador no debe ser null.
     * - cantidadCasilleros debe ser un valor positivo y válido en el contexto del tablero.
     *
     * Postcondiciones:
     * - Retorna true si todas las posiciones en alguna de las diagonales 3D contienen fichas del jugador; de lo contrario, retorna false.
     *
     * @return boolean
     */
    private static boolean verificarDiagonales3D(Lista<Ficha> fichasJugador, int cantidadCasilleros) throws Exception {

        // Diagonal principal
        boolean diagonalPrincipal = true;
        for (int i=1; i<=cantidadCasilleros; i++) {
            if (!existeFichaEnPosicion(i, i, i, fichasJugador, cantidadCasilleros)) {
                diagonalPrincipal = false;
                break;
            }
        }
        if (diagonalPrincipal){
            return true;
        }

        // Diagonal inversa
        boolean diagonalInversa = true;
        for (int i=1; i<=cantidadCasilleros; i++) {
            if (!existeFichaEnPosicion(i, i, cantidadCasilleros + 1 - i, fichasJugador, cantidadCasilleros)) {
                diagonalInversa = false;
                break;
            }
        }
        return diagonalInversa;
    }

    /**
     * Precondiciones:
     * - fichasJugador no debe ser null.
     * - cantidadCasilleros debe ser un valor positivo y válido en el contexto del tablero.
     *
     * Postcondiciones:
     * - Retorna true si todas las posiciones en alguna de las diagonales dentro de cualquier plano 2D contienen fichas del jugador; de lo contrario, retorna false.
     *
     * @return boolean
     */
    private static boolean verificarDiagonalesEnPlanos(Lista<Ficha> fichasJugador, int cantidadCasilleros) throws Exception {

        for (int profundidad=1; profundidad<=cantidadCasilleros; profundidad++) {
            boolean diagonal1 = true;
            boolean diagonal2 = true;
            for (int i=1; i<=cantidadCasilleros; i++) {
                if (!existeFichaEnPosicion(profundidad, i, i, fichasJugador, cantidadCasilleros)) {
                    diagonal1 = false;
                }
                if (!existeFichaEnPosicion(profundidad, i, cantidadCasilleros + 1 - i, fichasJugador, cantidadCasilleros)) {
                    diagonal2 = false;
                }
            }
            if (diagonal1 || diagonal2) return true;
        }

        for (int fila=1; fila<=cantidadCasilleros; fila++) {
            boolean diagonal1 = true;
            boolean diagonal2 = true;
            for (int i=1; i<=cantidadCasilleros; i++) {
                if (!existeFichaEnPosicion(i, fila, i, fichasJugador, cantidadCasilleros)) {
                    diagonal1 = false;
                }
                if (!existeFichaEnPosicion(i, fila, cantidadCasilleros + 1 - i, fichasJugador, cantidadCasilleros)) {
                    diagonal2 = false;
                }
            }
            if (diagonal1 || diagonal2) return true;
        }


        for (int columna=1; columna<=cantidadCasilleros; columna++) {
            boolean diagonal1 = true;
            boolean diagonal2 = true;
            for (int i=1; i<=cantidadCasilleros; i++) {
                if (!existeFichaEnPosicion(i, i, columna, fichasJugador, cantidadCasilleros)) {
                    diagonal1 = false;
                }
                if (!existeFichaEnPosicion(cantidadCasilleros + 1 - i, i, columna, fichasJugador, cantidadCasilleros)) {
                    diagonal2 = false;
                }
            }
            if (diagonal1 || diagonal2) return true;
        }

        return false;
    }

    /**
     * Precondiciones:
     * - fichasJugador no debe ser `null` ni vacía.
     * - Los valores posicionX, posicionY y posicionZ deben estar dentro de los límites del tablero.
     *
     * Postcondiciones:
     * - Si se encuentra una ficha en la posición especificada, se retorna true.
     * - Si no se encuentra ninguna ficha en la posición especificada, se retorna false.
     *
     * @return true si existe una ficha en la posición especificada, false en caso contrario.
     */
    private static boolean existeFichaEnPosicion(int posicionX, int posicionY, int posicionZ, Lista<Ficha> fichasJugador, int cantidadCasilleros) throws ExcepcionTablero {
        if((posicionX < 1 || posicionX > cantidadCasilleros) ||
                (posicionY < 1 || posicionY > cantidadCasilleros) ||
                (posicionZ < 1 || posicionZ > cantidadCasilleros)){
            throw new ExcepcionTablero(TipoExcepcionTablero.POSICION_FUERA_DE_RANGO);
        }
        if(fichasJugador == null){
            throw new ExcepcionTablero(TipoExcepcionTablero.FICHAS_NULL);
        }

        fichasJugador.iniciarCursor();
        while(fichasJugador.avanzarCursor()){
            Ficha ficha = fichasJugador.obtenerCursor();
            if(ficha.getPosicionX() == posicionX && ficha.getPosicionY() == posicionY && ficha.getPosicionZ() == posicionZ){
                return true;
            }
        }
        return false;
    }
}
