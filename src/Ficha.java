import java.util.ArrayList;
import java.util.List;

public class Ficha {
    private boolean disponible;
    private int color;
    private int posicionX;
    private int posicionY;
    private int posicionZ;
    private Jugador jugador;

    public Ficha(int posicionX, int posicionY, int posicionZ, Jugador jugador) {
        this.disponible = true;
        this.color = jugador.getJugadorNumero();
        this.posicionX = posicionX;
        this.posicionY = posicionY;
        this.posicionZ = posicionZ;
        this.jugador = jugador;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public List<Integer> getPosiciones() {
        List<Integer> posiciones = new ArrayList<>();
        posiciones.add(posicionX);
        posiciones.add(posicionY);
        posiciones.add(posicionZ);
        return posiciones;
    }

    public int getPosicionX() {
        return posicionX;
    }

    public int getPosicionY() {
        return posicionY;
    }

    public int getPosicionZ() {
        return posicionZ;
    }

    public Jugador getJugador() {
        return jugador;
    }
}
