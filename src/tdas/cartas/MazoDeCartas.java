package tdas.cartas;

import enums.ModoDeJuego;
import enums.TipoDeCarta;
import enums.TipoExcepcionCartas;
import tdas.listaYCola.Cola;
import tdas.listaYCola.Lista;
import excepciones.ExcepcionCartas;

public class MazoDeCartas {

    //ATRIBUTOS
    private Cola<Carta> cartas;

    //CONSTRUCTORES
    /**
     * Precondiciones:
     * -
     *
     * Postcondiciones:
     * - Se crea un nuevo objeto MazoDeCartas, que contiene una cola de cartas aleatorias.
     */
    public MazoDeCartas() throws Exception {
        this.cartas = new Cola<>();

        for(int i=0; i < ModoDeJuego.CANTIDAD_CARTAS_MAZO; i++){
            Carta carta = new Carta(TipoDeCarta.obtenerCartaAleatoria());
            this.cartas.acolar(carta);
        }
    }

    //METODOS DE COMPORTAMIENTO
    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Si hay cartas disponibles en la cola, se obtiene y se elimina la carta de la cola.
     * - Si la cola está vacía, se genera una nueva carta aleatoria.
     *
     * @return La carta obtenida del mazo. Si el mazo estaba vacío, se devuelve una nueva carta aleatoria.
     */
    public Carta getCarta(){
        Carta carta = cartas.desacolar();
        if(carta == null){
            carta = new Carta(TipoDeCarta.obtenerCartaAleatoria());
        }
        return carta;
    }

    /**
     * Precondiciones:
     * - La carta no debe ser null.
     *
     * Postcondiciones:
     * - La carta se agrega al mazo utilizando el método acolar de la cola.
     */
    public void devolverCartaAlMazo(Carta carta) throws Exception {
        if(carta == null){
            throw new ExcepcionCartas(TipoExcepcionCartas.CARTAS_VACIO);
        }
        cartas.acolar(carta);
    }

    /**
     * Precondiciones:
     * - La lista de cartas no debe ser null ni estar vacía.
     *
     * Postcondiciones:
     * - Las cartas proporcionadas se devuelven al mazo, invocando el método devolverCartaAlMazo para cada carta.
     */
    public void devolverCartaAlMazo(Lista<Carta> cartas) throws Exception {
        if(cartas == null || cartas.estaVacia()){
            throw new ExcepcionCartas(TipoExcepcionCartas.CARTAS_VACIO);
        }
        cartas.iniciarCursor();
        while(cartas.avanzarCursor()){
            this.devolverCartaAlMazo(cartas.obtenerCursor());
        }
    }
}
