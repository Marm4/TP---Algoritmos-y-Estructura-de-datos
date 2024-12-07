package enums;

import java.awt.*;

public enum ColorJugador {

    /**
     * La cantidad de colores es 10, que es la cantidad maxima de jugadores.
     */
    AZUL, AMARILLO, ROJO, VERDE, CYAN, NARANJA, MORADO, ROSA, BLANCO, NEGRO;

    public Color obtenerColorGrafico() {
        switch (this) {
            case AZUL -> { return Color.BLUE; }
            case AMARILLO -> { return Color.YELLOW; }
            case ROJO -> { return Color.RED; }
            case VERDE -> { return Color.GREEN; }
            case CYAN -> { return Color.CYAN; }
            case NARANJA -> { return new Color(255, 165, 0); }
            case MORADO -> { return new Color(128, 0, 128); }
            case ROSA -> { return Color.PINK; }
            case BLANCO -> { return Color.WHITE; }
            case NEGRO -> { return Color.BLACK; }
            default -> { return Color.GRAY; }
        }
    }
}
