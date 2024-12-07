package tdas.listaYCola;

public class Nodo<T> {

    //ATRIBUTOS
    private T dato;
    private Nodo<T> siguiente;

    //CONSTRUCTORES
    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Se asigna el correspondiente dato.
     * - Se asigna null al nodo siguiente.
     */
    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    //GETTERS SIMPLES
    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * -
     *
     * @return el dato del nodo.
     */
    public T getDato() {
        return this.dato;
    }

    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * -
     * @return el nodo siguiente.
     */
    public Nodo<T> getSiguiente() {
        return this.siguiente;
    }

    //SETTERS SIMPLES
    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * - Modifica el valor del dato.
     */
    public void setDato(T dato) {
        this.dato = dato;
    }

    /**
     * Precondiciones:
     *  -
     * Postcondiciones:
     * - Modifica el nodo siguiente.
     */
    public void setSiguiente(Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }

}