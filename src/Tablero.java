import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Tablero {
    private List<Ficha> fichas;
    private int modoJuego;

    private static final int MODO_UNO = 3;
    private static final int MODO_DOS = 5;
    private static final int MODO_TRES = 10;

    private static final int CANTIDAD_FICHAS_MODO_UNO = 4;
    private static final int CANTIDAD_FICHAS_MODO_DOS = 7;
    private static final int CANTIDAD_FICHAS_MODO_TRES = 14;

    public Tablero(int modoJuego) throws Exception {
        if(modoJuego <= 0 || modoJuego > 3){
            throw new Exception("Error al crear el tablero. Ingrese un numero mayor a 0 y menor o igual a 3");
        }

        this.modoJuego = modoJuego;
        this.fichas = new ArrayList<>();
    }
    public boolean chequearPosicionCorrecta(List<Integer> posiciones){
        boolean posicionCorrecta = true;
        for(Integer posicion : posiciones){
            if(posicion < 0 || posicion > modoJuego){
                posicionCorrecta = false;
            }
        }
        return posicionCorrecta;
    }

    public boolean chequearPosicionOcupada(List<Integer> posiciones){
        boolean posicionOcupada = false;
        for(Ficha ficha : fichas){
            int posicionX = posiciones.get(0);
            int posicionY = posiciones.get(1);
            int posicionZ = posiciones.get(2);

            if(ficha.getPosicionX() == posicionX && ficha.getPosicionY() == posicionY && ficha.getPosicionZ() == posicionZ){
                posicionOcupada = true;
            }
        }
        return posicionOcupada;
    }

    public int getModoJuego() {
        return modoJuego;
    }

    public void addFichas(Ficha ficha){
        fichas.add(ficha);
    }

    public boolean chequearGanador(Jugador jugador){
        List<Ficha> fichasJugador = jugador.getFichas();
        System.out.println("Chequeando ganadores");
        if(fichasJugador == null || fichasJugador.isEmpty() || fichasJugador.size() < modoJuego){
            return false;
        }

        return chequearFichasConsecutivas(fichasJugador);
    }

    private boolean chequearFichasConsecutivas(List<Ficha> fichasJugador){
        List<Integer> fichasConsecutivasColumna = new ArrayList<>(Collections.nCopies(modoJuego, 0));

        for(int fila = 0; fila < modoJuego; fila++){
            int fichasConsecutivasFila = 0;
            int fichasConsecutivasPrimeraDiagonal = 0;
            int fichasConsecutivasUltimaDiagonal = 0;

            int filaPrimeraDiagonal = 0;
            int filaUltimaDiagonal = modoJuego-1;

            for(int columna = 0; columna < modoJuego; columna++){
                if(existeFichaEnPosicion(fila, columna, 0, fichasJugador)){
                    fichasConsecutivasFila++;
                }
                else{
                    fichasConsecutivasFila = 0;
                }

                if(existeFichaEnPosicion(fila, columna, 0, fichasJugador)){
                    int valorActual = fichasConsecutivasColumna.get(columna);
                    if(valorActual == modoJuego-1){
                        return true;
                    }
                    else{
                        fichasConsecutivasColumna.set(columna, valorActual+1);
                    }
                }
                else{
                    fichasConsecutivasColumna.set(columna, 0);
                }

                if(fila == 0){
                    if(existeFichaEnPosicion(filaPrimeraDiagonal, columna, 0, fichasJugador)){
                        fichasConsecutivasPrimeraDiagonal++;
                    }
                    filaPrimeraDiagonal++;
                }
                else if(fila == modoJuego-1){
                    if(existeFichaEnPosicion(filaUltimaDiagonal, columna, 0, fichasJugador)){
                        fichasConsecutivasUltimaDiagonal++;
                    }
                    filaUltimaDiagonal--;
                }
            }
            if(fichasConsecutivasFila == modoJuego || fichasConsecutivasPrimeraDiagonal == modoJuego || fichasConsecutivasUltimaDiagonal == modoJuego){
                return true;
            }
        }

        return false;
    }

    private boolean existeFichaEnPosicion(int posicionX, int posicionY, int posicionZ, List<Ficha> fichasJugador){
        for(Ficha ficha : fichasJugador){
            if(ficha.getPosicionX() == posicionX && ficha.getPosicionY() == posicionY && ficha.getPosicionZ() == posicionZ){
                return true;
            }
        }
        return false;
    }


}
