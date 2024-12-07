package tdas.listaYCola;

public class Cola<T> {
    //ATRIBUTOS
    private Nodo<T> frente;
    private Nodo<T> ultimo;
    private int tamanio;

    //CONSTRUCTORES
    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * - Inicializa la cola.
     */
    public Cola() {
        this.frente = null;
        this.ultimo = null;
        this.tamanio = 0;
    }

    //METODOS DE COMPORTAMIENTO

    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * -
     *
     * @return true si no contiene elementos (tamanio = 0). False en caso contrario.
     */
    public boolean estaVacia() {
        return (this.tamanio == 0);
    }

    /**
     * Precondiciones:
     *  - El elemento no debe ser null.
     * Postcondiciones:
     * - Agregame el elemnto a la cola
     */
    public void acolar(T elemento) throws Exception {
        if(elemento == null){
            throw new Exception("El elemento no puede ser null");
        }
        Nodo<T> nuevo = new Nodo<T>(elemento);
        if (!this.estaVacia()) {
            this.ultimo.setSiguiente(nuevo);
            this.ultimo = nuevo;
        } else {
            this.frente = nuevo;
            this.ultimo = nuevo;
        }
        this.tamanio++;
    }

    /**
     * Precondiciones:
     *  - La lista no debe ser null.
     * Postcondiciones:
     * - Agregame la lista de elementos a la cola.
     */
    void acolar(Lista<T> lista) throws Exception {
        if(lista == null){
            throw new Exception("La lista no puede ser null");
        }

        lista.iniciarCursor();
        while (!lista.avanzarCursor()) {
            this.acolar(lista.obtenerCursor());
        }
    }

    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * - Quita el elemento en el frente de la cola.
     *
     * @return el elemento en el frente de la cola.
     */
    public T desacolar() {
        T elemento = null;
        if (!this.estaVacia()) {
            elemento = this.frente.getDato();
            this.frente = this.frente.getSiguiente();
            this.tamanio--;
            if (estaVacia()) {
                this.ultimo = null;
            }
        }
        return elemento;
    }

    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * -
     *
     * @return la cantidad de elementos de la cola.
     */
    public int contarElementos() {
        return this.tamanio;
    }

    //GETTERS SIMPLES
    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * - Devuelve el elemento en el frente de la cola. Solo lectura.
     */
    public T obtener() {
        T elemento = null;
        if (!this.estaVacia()) {
            elemento = this.frente.getDato();
        }
        return elemento;
    }
}
