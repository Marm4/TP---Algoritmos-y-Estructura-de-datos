import enums.ModoDeJuego;
import tdas.listaYCola.Lista;
import tdas.Tateti;
import tdas.menu.Menu;

public class Main {
    public static void main(String[] args) throws Exception {
        Menu menu = new Menu();
        menu.mostrarMensajedeBienvenida();

        ModoDeJuego modoDeJuego = menu.obtenerModoDeJuego();
        Lista<String> nombresJugadores = menu.obtenerNombresDeJugadores(modoDeJuego);

        Tateti tateti = new Tateti(modoDeJuego, nombresJugadores);
        tateti.jugar();

    }
}