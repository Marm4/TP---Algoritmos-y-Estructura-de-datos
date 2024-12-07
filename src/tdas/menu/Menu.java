package tdas.menu;

import enums.ModoDeJuego;
import enums.TipoExcepcionMenu;
import tdas.listaYCola.Lista;
import tdas.listaYCola.ListaSimplementeEnlazada;
import excepciones.ExcepcionMenu;
import tdas.Ficha;
import tdas.Jugador;
import tdas.cartas.Carta;

import java.util.Scanner;

public class Menu {
    private Scanner scanner;

    public Menu(){
        this.scanner = new Scanner(System.in);
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Muestra un mensaje de bienvenida con la descripción de los modos de juego disponibles.
     */
    public void mostrarMensajedeBienvenida(){
        System.out.println("\nBienvenido al juego TaTeTi Tridimensional");
        System.out.println("Hay tres modos de juego disponibles. A continuación se mencionan de la siguiente forma: 'Tamaño del tablero', 'Cantidad mínima de jugadores', 'Cantidad máxima de jugadores' \n");
        System.out.println("1. 3x3x3, 2, 3");
        System.out.println("2. 5x5x5, 3, 5");
        System.out.println("3. 10x10x10, 5, 10\n");
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Retorna el modo de juego seleccionado por el usuario, que puede ser uno de los tres modos definidos en ModoDeJuego.
     * - Si el número ingresado es inválido o no está dentro del rango esperado (1 a 3), se solicita al usuario que intente nuevamente.
     *
     * @return ModoDeJuego El modo de juego seleccionado por el usuario (MODO_UNO, MODO_DOS, o MODO_TRES).
     */
    public ModoDeJuego obtenerModoDeJuego() throws Exception {
        System.out.println("Introduzca el número de modo de juego seleccionado");

        return switch (obtenerUnNumero(1,3)) {
            case 1 -> ModoDeJuego.MODO_UNO;
            case 2 -> ModoDeJuego.MODO_DOS;
            case 3 -> ModoDeJuego.MODO_TRES;
            default -> throw new Exception("Ocurrió un error inesperado.");
        };
    }

    public Ficha obtenerFicha(Lista<Ficha> fichas) throws Exception {
        if(fichas == null || fichas.estaVacia()){
            throw new ExcepcionMenu(TipoExcepcionMenu.FICHAS_NULL);
        }
        System.out.println("\n Seleccione una ficha.");
        fichas.iniciarCursor();
        int i=0;
        while(fichas.avanzarCursor()){
            i++;
            Ficha ficha = fichas.obtenerCursor();
            System.out.println(i + ". PosicionX: " + ficha.getPosicionX() + ", PosicionY: " + ficha.getPosicionY() + ", PosicionZ: "+ ficha.getPosicionZ());
        }
        return fichas.obtener(obtenerUnNumero(1,i));
    }

    /**
     * Precondiciones:
     * - Se requiere que el usuario ingrese un número entre 1, valorMaximo y que condicionExtra no contenga el valorSeleccionado.
     * - El valor ingresado debe ser un número.
     *
     * Postcondiciones:.
     * - Si el valorMaximo es igual a 0, cualquier entrada de numero es correcta.
     * - Si el número ingresado es inválido o no está dentro del rango esperado, se solicita al usuario que intente nuevamente.
     *
     * @return valorSeleccionado, el valor seleccionado por el usuario.
     */
    protected int obtenerUnNumero(int valorMinimo, int valorMaximo){
        int valorSeleccionado = 0;
        boolean entradaValida = false;
        while (!entradaValida) {
            try {
                valorSeleccionado = scanner.nextInt();
                if(valorMinimo == 0 && valorMaximo == 0){
                    entradaValida = true;
                }
                else if (valorSeleccionado < valorMinimo || valorSeleccionado > valorMaximo) {
                    System.out.println("Número inválido. Intente nuevamente.");
                }
                else{
                    entradaValida = true;
                }
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println("Entrada no válida. Intente nuevamente.");
                scanner.next();
            }
        }
        return valorSeleccionado;
    }

    /**
     * Precondiciones:
     * - La lista cartas no debe ser nula ni estar vacía.
     * - scanner debe estar inicializado para recibir entrada del usuario.
     *
     * Postcondiciones:
     * - Muestra al usuario una lista de cartas con sus descripciones.
     * - Devuelve la carta seleccionada por el usuario.
     *
     * @return La carta seleccionada por el usuario, o null si no selecciona ninguna.
     */
    public Carta obtenerCarta(Lista<Carta> cartas, String mensaje) throws Exception {
        if(cartas == null){
            throw new ExcepcionMenu(TipoExcepcionMenu.CARTAS_NULL);
        }
        if(cartas.estaVacia()){
            System.out.println("\nNo hay cartas.");
            return null;
        }
        mostrarCartas(cartas, mensaje);
        System.out.println("0. No jugar carta");
        int numeroIngresado = obtenerUnNumero(0,cartas.getTamanio());
        if(numeroIngresado == 0){
            return null;
        }
        return cartas.obtener(numeroIngresado);
    }

    /**
     * Precondiciones:
     * - El jugador debe ingresar tres números separados por espacios para indicar las posiciones en el tablero (X, Y, Z).
     *
     * Postcondiciones:
     * - Se retorna una lista con tres números enteros que representan las posiciones X, Y, Z donde el jugador desea colocar su ficha.
     * - Si el jugador ingresa una cantidad incorrecta de números o valores no válidos, se le pedirá que ingrese nuevamente los valores.
     *
     * @return Una lista con los tres números enteros representando las posiciones X, Y, Z.
     */
    public Lista<Integer> obtenerPoscionDeFicha(String mensaje) throws Exception {
        if(!mensaje.isEmpty()){
            System.out.println(mensaje);
        }

        System.out.println("Se ingresa de a 1 numero. Primero la posición X, luego la posición Y y por ultimo la posición Z.");
        Integer posicionX = this.obtenerUnNumero(0,0);
        Integer posicionY = this.obtenerUnNumero(0,0);
        Integer posicionZ = this.obtenerUnNumero(0,0);

        Lista<Integer> numerosIngresados = new ListaSimplementeEnlazada<>();
        numerosIngresados.agregar(posicionX);
        numerosIngresados.agregar(posicionY);
        numerosIngresados.agregar(posicionZ);

        return numerosIngresados;
    }

    /**
     * Precondiciones:
     *  - El modoDeJuego no debe ser null.
     * - Se requiere que el usuario ingrese los nombres de los jugadores en una sola línea, separados por espacios.
     * - El número de jugadores ingresado debe estar dentro del rango permitido por el modo de juego (mínimo y máximo de jugadores).
     *
     * Postcondiciones:
     * - Retorna una lista con los nombres de los jugadores ingresados, si la cantidad es válida.
     * - Si la cantidad de jugadores es inválida (fuera del rango mínimo o máximo), el proceso se repite hasta que el usuario ingrese una cantidad válida.
     *
     * @return Una lista de nombres de los jugadores ingresados por el usuario.
     */
    public Lista<String> obtenerNombresDeJugadores(ModoDeJuego modoDeJuego) throws ExcepcionMenu {
        if (modoDeJuego == null) {
            throw new ExcepcionMenu(TipoExcepcionMenu.MODO_JUEGO_NULL);
        }

        Lista<String> nombres = new ListaSimplementeEnlazada<>();
        boolean entradaValida = false;
        int minCantidadJugadores = modoDeJuego.getCantidadDeJugadoresMinima();
        int maxCantidadJugadores = modoDeJuego.getCantidadDeJugadoresMaxima();

        System.out.println("Ingrese los nombres de los jugadores con un espacio entre medio. Ejemplo: 'Juan Pedro Santiago'");
        while (!entradaValida) {
            try {
                // Verificar si hay un salto de línea previo
                if (this.scanner.hasNextLine()) {
                    String input = this.scanner.nextLine(); // Lee la entrada del usuario

                    // Split con manejo adecuado de espacios múltiples
                    String[] nombresArray = input.split("\\s+"); // Esto manejará múltiples espacios correctamente
                    if (nombresArray.length >= minCantidadJugadores && nombresArray.length <= maxCantidadJugadores) {
                        // Asegurarse de que no haya nombres vacíos
                        for (String nombre : nombresArray) {
                            if (!nombre.trim().isEmpty()) {
                                nombres.agregar(nombre.trim());  // Trim para quitar espacios innecesarios
                            }
                        }
                        entradaValida = true;
                    } else {
                        System.out.println("Cantidad de jugadores no válida. Recuerde que la cantidad mínima es " + minCantidadJugadores + " y la cantidad máxima es " + maxCantidadJugadores);
                    }
                } else {
                    System.out.println("No se detectó entrada. Inténtelo nuevamente.");
                }
            } catch (Exception e) {
                System.out.println("Error al ingresar los nombres. Inténtelo nuevamente.\n" + e.getMessage());
            }
        }
        return nombres;
    }

    /**
     * Precondiciones:
     * - La lista `cartas` no debe ser null o estar vacia.
     *
     * Postcondiciones:
     * - Muestra en la consola un mensaje y la lista de cartas.
     */
    public void mostrarCartas(Lista<Carta> cartas, String mensaje) throws ExcepcionMenu {
        if(cartas == null || cartas.estaVacia()){
            throw new ExcepcionMenu(TipoExcepcionMenu.CARTAS_NULL);
        }

        if(!mensaje.isEmpty()){
            System.out.println(mensaje);
        }
        cartas.iniciarCursor();
        int i = 0;
        while(cartas.avanzarCursor()){
            i++;
            Carta carta = cartas.obtenerCursor();
            System.out.println(i + ". " + carta.getTipoDeCarta() + " (" + carta.getDescripcionDeCarta() + ")");
        }
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Muestra en consola el nombre del jugador que ha ganado.
     * - Cierra el scanner
     */
    public void ganador(String nombreJugador){
        System.out.println("\nGanador: " + nombreJugador);
        this.scanner.close();
    }

    public void mostrarPosicionesOcupadas(Lista<Jugador> jugadores, Lista<Lista<Integer>> casillerosBloqueados) throws Exception {
        if(jugadores == null || casillerosBloqueados == null){
            throw new ExcepcionMenu(TipoExcepcionMenu.JUGADORES_NULL_VACIO);
        }

        Lista<Jugador> jugadoresAux = new ListaSimplementeEnlazada<>(jugadores);

        if(!jugadoresAux.estaVacia()){
            this.mostrarMensaje("\nPosiciones ocupadas");
            jugadoresAux.iniciarCursor();
            while(jugadoresAux.avanzarCursor()){
                Jugador jugador = jugadoresAux.obtenerCursor();
                if(!jugador.getFichas().estaVacia()){
                    this.mostrarMensaje("\nFichas de " + jugador.getNombre());
                    this.mostrarPosicionesFichas(jugador.getFichas());
                }
            }
        }

        if(!casillerosBloqueados.estaVacia()){
            this.mostrarMensaje("\nPosiciones bloqueadas");
            casillerosBloqueados.iniciarCursor();
            while(casillerosBloqueados.avanzarCursor()){
                Lista<Integer> casillero = casillerosBloqueados.obtenerCursor();

                this.mostrarMensaje("(" + casillero.obtener(1) + ", " + casillero.obtener(2) + ", " + casillero.obtener(3)+ ")");
            }
        }
    }

    private void mostrarPosicionesFichas(Lista<Ficha> fichas) throws Exception {
        if(fichas == null){
            throw new Exception("fichas no debe ser null.");
        }


        while(fichas.avanzarCursor()){
            Ficha ficha = fichas.obtenerCursor();
            if(ficha.isDisponible()){
                this.mostrarMensaje("(" + ficha.getPosicionX() + ", " + ficha.getPosicionY() + ", " + ficha.getPosicionZ()+ ")");
            }
            else{
                this.mostrarMensaje("(" + ficha.getPosicionX() + ", " + ficha.getPosicionY() + ", " + ficha.getPosicionZ()+ ")" + " (Ficha bloqueada)");
            }

        }
    }

    public void mostrarMensaje(String mensaje){
        System.out.println(mensaje);
    }
}
