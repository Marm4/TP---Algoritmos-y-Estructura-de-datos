package enums;

import java.util.Arrays;

public enum ModoDeJuego {
    MODO_UNO(3,  4,2,3),
    MODO_DOS(5,  7, 3, 5),
    MODO_TRES(10,  14, 5, 10);

    public static final int CANTIDAD_CARTAS_MAZO = 50;
    public static final int CANTIDAD_MAXIMA_DE_CARTAS_EN_MANO = 8;

    private final int cantidadDeCasilleros;
    private final int cantidadDeFichas;
    private final int cantidadDeJugadoresMinima;
    private final int cantidadDeJugadoresMaxima;

    ModoDeJuego(int cantidadDeCasilleros, int cantidadDeFichas, int cantidadDeJugadoresMinima, int cantidadDeJugadoresMaxima) {
        this.cantidadDeCasilleros = cantidadDeCasilleros;
        this.cantidadDeFichas = cantidadDeFichas;
        this.cantidadDeJugadoresMinima = cantidadDeJugadoresMinima;
        this.cantidadDeJugadoresMaxima = cantidadDeJugadoresMaxima;
    }

    public int getCantidadDeCasilleros() {
        return cantidadDeCasilleros;
    }

    public int getCantidadDeFichas() {
        return cantidadDeFichas;
    }

    public int getCantidadDeJugadoresMinima() {
        return cantidadDeJugadoresMinima;
    }

    public int getCantidadDeJugadoresMaxima() {
        return cantidadDeJugadoresMaxima;
    }

    public static boolean cantidadDeCasillerosCorrecta(int cantidadDeCasilleros) {
        return Arrays.stream(ModoDeJuego.values())
                .anyMatch(modo -> modo.getCantidadDeCasilleros() == cantidadDeCasilleros);
    }
}