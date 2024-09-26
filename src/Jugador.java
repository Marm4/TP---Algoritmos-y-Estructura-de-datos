import java.util.ArrayList;
import java.util.List;

public class Jugador {
    private String nombre;
    private int jugadorNumero;
    private List<Cartas> cartas;
    private List<Ficha> fichas;




    public Jugador(String nombre, int jugadorNumero) throws Exception {
        if(jugadorNumero <= 0){
            throw new Exception("El numero de jugador debe ser mayor que 0");
        }
        else if(nombre.isEmpty()){
            throw new Exception("El nombre no puede estar vacio");
        }

        this.nombre = nombre;
        this.jugadorNumero = jugadorNumero;
        this.fichas = new ArrayList<>();
    }

    public String getNombre(){
        return nombre;
    }

    public int getJugadorNumero() {
        return jugadorNumero;
    }

    public void setJugadorNumero(int jugadorNumero) {
        this.jugadorNumero = jugadorNumero;
    }


    public List<Cartas> getCartas() {
        return cartas;
    }

    public void setCartas(List<Cartas> cartas) {
        this.cartas = cartas;
    }

    public List<Ficha> getFichas(){
        return fichas;
    }

    public void addFicha(Ficha ficha){
        this.fichas.add(ficha);
    }
}
