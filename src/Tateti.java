import java.util.ArrayList;
import java.util.List;

public class Tateti {
    private Tablero tablero;
    private List<Jugador> jugadores;
    private int modoJuego;
    private Jugador ultimoJugador;

    private static final int CANTIDAD_MODOS_DE_JUEGO = 3;

    private static final int MIN_CANTIDAD_JUGADORES = 2;
    private static final int MAX_CANTIDAD_JUGADORES = 5;

    private static final int MODO_UNO = 3;
    private static final int MODO_DOS = 5;
    private static final int MODO_TRES = 10;

    private static final int CANTIDAD_CARTAS_MODO_UNO = 4;
    private static final int CANTIDAD_CARTAS_MODO_DOS = 7;
    private static final int CANTIDAD_CARTAS_MODO_TRES = 14;

    public Tateti(int modoJuego, List<String> nombreJugadores) throws Exception {
        if(modoJuego <= 0 || modoJuego > 3){
            throw new Exception("Modo de juego invalido. Ingrese un numero mayor a 0 y menor o igual a " + CANTIDAD_MODOS_DE_JUEGO);
        }
        else if(nombreJugadores.size() < MIN_CANTIDAD_JUGADORES || nombreJugadores.size() > MAX_CANTIDAD_JUGADORES){
            throw new Exception("Cantidad de jugadores invalidos. La cantidad minima de jugadores es: " + MIN_CANTIDAD_JUGADORES + ", la cantidad maxima es: " + MAX_CANTIDAD_JUGADORES);
        }

        switch (modoJuego) {
            case 1:
                tablero = new Tablero(MODO_UNO);
                agregarJugadores(nombreJugadores, CANTIDAD_CARTAS_MODO_UNO);
                break;

            case 2:
                tablero = new Tablero(MODO_DOS);
                agregarJugadores(nombreJugadores, CANTIDAD_CARTAS_MODO_DOS);
                break;

            case 3:
                tablero = new Tablero(MODO_TRES);
                agregarJugadores(nombreJugadores, CANTIDAD_CARTAS_MODO_TRES);
                break;
        }

        this.modoJuego = modoJuego;
    }

    private void agregarJugadores(List<String> nombreJugadores, int cantidadFichas) throws Exception {
        jugadores = new ArrayList<>();
        for(int i = 0; i < nombreJugadores.size(); i++){
            Jugador jugador = new Jugador(nombreJugadores.get(i), i+1);
            jugadores.add(jugador);
        }
    }

    public void jugar(){
        boolean ganador = false;
        while(!ganador){
            jugarTurno();
            ganador = tab  lero.chequearGanador(ultimoJugador);
        }

        Consola.getInstance().ganador(ultimoJugador);
    }

    private void jugarTurno(){
        if(ultimoJugador == null || ultimoJugador == jugadores.getLast()){
            ultimoJugador = jugadores.getFirst();
        }
        else{
            ultimoJugador = jugadores.get(ultimoJugador.getJugadorNumero());
        }


        boolean posicionCorrecta = false;
        while(!posicionCorrecta){
            List<Integer> posiciones = Consola.getInstance().jugarTurno(ultimoJugador.getNombre());
            posicionCorrecta = chequearPosicion(posiciones);

            if(posicionCorrecta){
                Ficha ficha = new Ficha(posiciones.get(0), posiciones.get(1), posiciones.get(2), ultimoJugador);
                tablero.addFichas(ficha);
                ultimoJugador.addFicha(ficha);
            }
        }
    }

    private boolean chequearPosicion(List<Integer> posiciones){
        boolean posicionCorrecta = tablero.chequearPosicionCorrecta(posiciones);
        if(!posicionCorrecta){
            Consola.getInstance().posicionIncorrecta(tablero.getModoJuego());
        }
        else{
            if(tablero.chequearPosicionOcupada(posiciones)){
                Consola.getInstance().posicionOcupada();
                posicionCorrecta = false;
            }
        }
        return posicionCorrecta;
    }

}
