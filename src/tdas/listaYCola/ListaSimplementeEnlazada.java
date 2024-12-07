package tdas.listaYCola;

public class ListaSimplementeEnlazada<T> extends Lista<T> {

    //CONSTRUCTORES

    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * - Llama al constructor padre.
     */
    public ListaSimplementeEnlazada(){
        super();
    }

    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * - Llama al constructor padre.
     */
    public ListaSimplementeEnlazada(int tamanio, T valorInicial) throws Exception {
        super(tamanio, valorInicial);
    }

    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * - Llama al constructor padre.
     */
    public ListaSimplementeEnlazada(Lista<T> copiarLista) throws Exception {
        super(copiarLista);
    }

    //METODOS DE COMPORTAMIENTO
    /**
     * Precondiciones:
     *  - La posición debe ser una posición valida aceptada por validarPosicion.
     * Postcondiciones:
     * - Se agrega el elemento a la posición indicada.
     */
    public void agregar(T elemento, int posicion) throws Exception {
        validarPosicion(posicion);
        Nodo<T> nuevo = new Nodo<T>(elemento);
        if (posicion == 1) {
            nuevo.setSiguiente( this.getPrimero());
            this.setPrimero( nuevo );
        } else {
            Nodo<T> anterior = this.getNodo(posicion -1);
            nuevo.setSiguiente(anterior.getSiguiente());
            anterior.setSiguiente( nuevo );
        }
        this.aumentarTamanio();
    }

    /**
     * Precondiciones:
     *  - La posición debe ser una posición valida aceptada por validarPosicion.
     * Postcondiciones:
     * - Remueve de la lista el elemento en la posición indicada.
     */
    public void remover(int posicion) throws Exception {
        validarPosicion(posicion);
        this.iniciarCursor();
        Nodo<T> removido = null;
        if (posicion == 1) {
            removido = this.getPrimero();
            this.setPrimero(removido.getSiguiente() );
        } else {
            Nodo<T> anterior = this.getNodo(posicion -1);
            removido = anterior.getSiguiente();
            anterior.setSiguiente( removido.getSiguiente());
        }
        this.disminuirTamanio();
    }

    /**
     * Precondiciones:
     *  - El dato no debe ser null
     * Postcondiciones:
     * - Si el dato existe dentro de la lista, lo remueve.
     */
    public void remover(T dato) throws Exception{
        if(dato == null){
            throw new Exception("El dato no puede ser null. Verifique la información ingresada.");
        }
        this.iniciarCursor();
        Nodo<T> nodoAnterior = null;
        while(this.avanzarCursor()){
            T datoActual = this.obtenerCursor();
            if(datoActual.equals(dato)){
                if(nodoAnterior == null){
                    this.setPrimero(this.obtenerNodoDeCursor().getSiguiente());
                }
                else{
                    nodoAnterior.setSiguiente(this.obtenerNodoDeCursor().getSiguiente());

                }
                this.disminuirTamanio();
                return;
            }
            nodoAnterior = this.obtenerNodoDeCursor();
        }
    }
}
