import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Consola.getInstance().bienvenido();
        Integer modoJuego = Consola.getInstance().getModoJuego();
        List<String> nombres = Consola.getInstance().getNombres(modoJuego);

        try {
            Tateti tateti = new Tateti(modoJuego, nombres);
            tateti.jugar();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}