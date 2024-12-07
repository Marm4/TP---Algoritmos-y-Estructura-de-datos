package tdas.listaYCola;

public abstract class Lista<T> {

    //ATRIBUTOS
    private Nodo<T> primero;
    private int tamanio;
    private Nodo<T> cursor;

    //CONSTRUCTORES

    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * - Crea una lista vacia.
     */
    public Lista() {
        this.primero = null;
        this.tamanio = 0;
        this.cursor = null;
    }

    /**
     * Precondiciones:
     *  - Tamanio debe ser mayor a 0.
     *  - valorInicial no puede ser null.
     * Postcondiciones:
     * - Crea una lista de tamanio definido con un valor incial en cada nodo.
     */
    public Lista(int tamanio, T valorInicial) throws Exception {
        this.primero = null;
        this.cursor = null;
        this.tamanio = 0;
        if(tamanio < 1){
            throw new Exception("El tamaño inicial debe ser mayor a 0");
        }
        if(valorInicial == null){
            throw new Exception("El valor incial no puede ser null");
        }
        for(int i=0; i<tamanio; i++){
            this.agregar(valorInicial);
        }
    }

    /**
     * Precondiciones:
     *  - copiarLista no debe ser null.
     * Postcondiciones:
     * - Crea una nueva lista copia de la lista copiarLista.
     */
    public Lista(Lista<T> copiarLista) throws Exception {
        if(copiarLista == null){
            throw new Exception("La lista ingresada no debe ser null.");
        }
        this.primero = null;
        this.tamanio = 0;
        this.cursor = null;

        for(int i=1; i<=copiarLista.getTamanio(); i++){
            this.agregar(copiarLista.obtener(i));
        }
    }

    //METODOS DE COMPORTAMIENTO
    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * -
     * @return true si el tamanio de la lista es 0 (Esta vacía), false en caso contrario
     */
    public boolean estaVacia() {
        return (this.tamanio == 0);
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Agrega un nuevo elemento a la lista en la posicion tamanio+1.
     */
    public void agregar(T elemento) throws Exception {
        this.agregar(elemento, this.getTamanio() + 1);
    }

    /**
     * Precondiciones:
     *  - La posición debe ser una posición valida aceptada por validarPosicion.
     * Postcondiciones:
     * - Se agrega el elemento a la posición indicada.
     */
    public abstract void agregar(T elemento, int posicion) throws Exception;

    /**
     * Precondiciones:
     *  - La posición debe ser una posición valida aceptada por validarPosicion.
     * Postcondiciones:
     * - Remueve de la lista el elemento en la posición indicada.
     */
    public abstract void remover(int posicion) throws Exception;

    /**
     * Precondiciones:
     *  - El dato no debe ser null
     * Postcondiciones:
     * - Si el dato existe dentro de la lista, lo remueve.
     */
    public abstract void remover(T dato) throws Exception;

    /**
     * Precondiciones:
     *  - La posición debe ser una posición valida aceptada por validarPosicion.
     * Postcondiciones:
     * -
     *
     * @return el dato en la posición ingresada.
     */
    public T obtener(int posicion) throws Exception {
        validarPosicion(posicion);
        return this.getNodo(posicion).getDato();
    }

    /**
     * Precondiciones:
     *  - La posición debe ser una posición valida aceptada por validarPosicion.
     * Postcondiciones:
     * - Cambia el elemento ingresado a la posición ingresada.
     */
    public void cambiar(T elemento, int posicion) throws Exception {
        validarPosicion(posicion);
        this.getNodo(posicion).setDato(elemento);
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Deja el cursor en posición para hacer un nuevo recorrido.
     */
    public void iniciarCursor() {
        this.cursor = null;
    }

    /**
     * Precondiciones:
     *  - Se ha iniciado un recorrido (invocando el método
     *    iniciarCursor()) y desde entonces no se han agregado o
     *    removido elementos de la Lista.
     * Postcondiciones:
     * - Mueve el cursor y lo posiciona en el siguiente elemento
     *   del recorrido.
     *   El valor de retorno indica si el cursor quedó posicionado
     *   sobre un elemento o no (en caso de que la Lista esté vacía o
     *   no existan más elementos por recorrer).
     *
     * @return true si pudo avanzar a la posición siguiente, false si termino el recorrido.
     */
    public boolean avanzarCursor() {
        if (this.cursor == null) {
            this.cursor = this.primero;
        } else {
            this.cursor = this.cursor.getSiguiente();
        }

        /* pudo avanzar si el cursor ahora apunta a un nodo */
        return (this.cursor != null);
    }

    /**
     * Precondiciones:
     * - El cursor está posicionado sobre un elemento de la Lista,
     *   (fue invocado el método avanzarCursor() y devolvió true).
     * Postcondiciones:
     * - Devuelve el elemento en la posición del cursor.
     */
    public T obtenerCursor() {
        T elemento = null;
        if (this.cursor != null) {
            elemento = this.cursor.getDato();
        }
        return elemento;
    }

    /**
     * Precondiciones:
     * - El cursor está posicionado sobre un elemento de la Lista,
     *   (fue invocado el método avanzarCursor() y devolvió true).
     * Postcondiciones:
     * - Devuelve el Nodo en la posición del cursor.
     */
    public Nodo<T> obtenerNodoDeCursor(){
        Nodo<T> nodo = null;
        if(this.cursor != null){
            nodo = this.cursor;
        }
        return nodo;
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return true si la lista contiene el dato ingresado. False en caso contrario.
     */
    public boolean contiene(T dato){
        this.iniciarCursor();
        while(this.avanzarCursor()){
            T datoActual = this.obtenerCursor();
            if(datoActual.equals(dato)){
                return true;
            }
        }
        return false;
    }

    protected void aumentarTamanio() {
        this.tamanio++;
    }

    protected void disminuirTamanio() {
        this.tamanio--;
    }

    //GETTERS SIMPLES
    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * -
     *
     * @return la cantidad de elementos que tiene la lista.
     */
    public int getTamanio() {
        return this.tamanio;
    }

    /**
     * Precondiciones:
     * - La posición debe ser una posición valida aceptada por validarPosicion.
     * Postcondiciones:
     * - Devuelve el nodo en la posición indicada.
     */
    public Nodo<T> getNodo(int posicion) throws Exception {
        validarPosicion(posicion);
        Nodo<T> actual = this.primero;
        for(int i = 1; i < posicion; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }

    protected void validarPosicion(int posicion) throws Exception {
        if ((posicion < 1) ||
                (posicion > this.tamanio + 1)) {
            throw new Exception("La posicion debe estar entre 1 y tamaño + 1");
        }
    }

    //GETTERS SIMPLES
    protected Nodo<T> getPrimero() {
        return this.primero;
    }

    //SETTERS SIMPLES
    protected void setPrimero(Nodo<T> primero) {
        this.primero = primero;
    }

    protected void setCursor(Nodo<T> cursor){
        this.cursor = cursor;
    }

}












