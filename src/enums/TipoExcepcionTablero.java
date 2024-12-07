package enums;

public enum TipoExcepcionTablero {
    MODO_DE_JUEGO("El modo de juego no puede ser null. Por favor, asegúrese de definir el modo de juego antes de continuar."),
    MENU_JUEGO_NULL("El menu de juego no puede ser null. Por favor, asegúrese de definir el menu de juego antes de continuar."),
    POSICIONES_VACIO("La lista de posiciones no puede estar vacía. Asegúrese de proporcionar una posición válida."),
    POSICIONES_TAMANIO_INCORRECTO("La lista de posiciones debe ser de igual a 3. Asegúrese de proporcionar una posición válida."),
    JUGADOR_NULL("El jugador no puede ser null. Por favor, ingrese un jugador antes de continuar."),
    FICHAS_NULL("La lista de fichas o la ficha no puede ser null. Verifique que todas las fichas sean válidas."),
    POSICION_FUERA_DE_RANGO("La posición está fuera del rango esperado. Asegúrese de que las posiciones se encuentren dentro del rango permitido."),
    POSICION_INVALIDA("La posición es invalida. Ingrese una posición correcta."),
    CASILLERO_NULL_VACIO("La lista de posiciones del casillero no puede ser null o estar vacio. Ingrese una lista válida");

    private String mensaje;

    TipoExcepcionTablero(String mensaje){
        this.mensaje = mensaje;
    }

    public String getMensaje(){
        return this.mensaje;
    }
}
