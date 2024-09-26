import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Consola {

    private Scanner scanner;
    private static Consola instance;

    private Consola(){
        scanner = new Scanner(System.in);
    }

    public static Consola getInstance() {
        if (instance == null) {
            instance = new Consola();
        }
        return instance;
    }

    public void bienvenido(){
        System.out.println("\nBienvenido al juego TaTeTi Tridimensional");
        System.out.println("Hay tres modos de juego disponibles, a continuacion seran mencionados de la forma: 'Tamaño tablero' ,'minima cantidad de jugadores', 'maxima cantidad de jugadores' \n");
        System.out.println("1. 3x3x3, 2, 3");
        System.out.println("2. 5x5x5, 3, 5");
        System.out.println("3. 10x10x10, 5, 10\n");
    }

    public Integer getModoJuego() {
        Integer modoJuego = null;
        boolean entradaValida = false;

        System.out.println("Introduzca el número de modo de juego seleccionado");

        while (!entradaValida) {
            try {
                modoJuego = scanner.nextInt();
                if(modoJuego < 1 || modoJuego > 3) {
                    System.out.println("Intentelo nuevamente");
                }
                else{
                    entradaValida = true;
                }
                scanner.nextLine();

            } catch (Exception e) {
                System.out.println("Intentelo nuevamente");
                scanner.next();
            }
        }
        return modoJuego;
    }

    public List<String> getNombres(int modoJuego) {
        List<String> nombres = new ArrayList<>();
        boolean entradaValida = false;
        int minCantidadJugadores = 2;
        int maxCantidadJugadores = 3;

        if(modoJuego == 2){
            minCantidadJugadores = 3;
            maxCantidadJugadores = 5;
        }
        else if(modoJuego == 3){
            minCantidadJugadores = 5;
            maxCantidadJugadores = 10;
        }

        System.out.println("Ingrese los nombres de los jugadores con un espacio entre medio. Ejemplo: 'Juan Pedro Santiago'");

        while (!entradaValida) {
            try {
                String input = scanner.nextLine();
                String[] nombresArray = input.split(" ");

                if (nombresArray.length >= minCantidadJugadores && nombresArray.length <= maxCantidadJugadores) {
                    for (String nombre : nombresArray) {
                        nombres.add(nombre);
                    }
                    entradaValida = true;
                } else {
                    System.out.println("Cantidad de jugadores no valida. Recuerde que la cantidad minima es " + minCantidadJugadores + " y la cantidad maximna es " + maxCantidadJugadores);
                }

            } catch (Exception e) {
                System.out.println("Error al ingresar los nombres. Intentelo nuevamente\n");
            }
        }

        return nombres;
    }



    public List<Integer> jugarTurno(String nombre) {
        List<Integer> numeros = new ArrayList<>();
        boolean entradaValida = false;

        while (!entradaValida) {
            System.out.println("\nTurno de " + nombre + "\nIngrese la posicion donde desea colocar la ficha: ");
            String input = scanner.nextLine();

            try {
                String[] numerosString = input.split(" ");
                if (numerosString.length != 3) {
                    throw new IllegalArgumentException("Debes ingresar exactamente tres números.");
                }

                for (String numero : numerosString) {
                    numeros.add(Integer.parseInt(numero));
                }

                entradaValida = true;

            } catch (NumberFormatException e) {
                System.out.println("Error: Uno de los valores ingresados no es un número válido. Inténtalo de nuevo.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        return numeros;
    }

    public void posicionIncorrecta(int tamaño){
        System.out.println("Posición incorrecta. Intentelo nuevamente. Recuerde que es un tablero de tamaño " + tamaño + " x " + tamaño + " x " + tamaño + "\n");
    }

    public void posicionOcupada(){
        System.out.println("La posición seleccionada se encuentra ocupada. Intentelo nuevamente.\n");
    }

    public void ganador(Jugador jugador){
        System.out.println("\n Ganador: " + jugador.getNombre());
        scanner.close();
    }

}
