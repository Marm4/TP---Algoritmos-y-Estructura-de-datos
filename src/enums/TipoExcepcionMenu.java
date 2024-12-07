package enums;

public enum TipoExcepcionMenu {
    CARTAS_NULL("La lista de cartas no debe ser null. Ingrese una lista de cartas correcta."),
    JUGADORES_NULL_VACIO("La lista de jugares y el jugador actual no debe ser null. La lista de jugadores no puede estar vacia"),
    SCANNER_NULL("El scanner debe estar inicializado para recibir entrada del usuario."),
    FICHAS_NULL("La lista de fichas no debe ser null. Ingrese una lista de fichas correcta."),
    TABLERO_NULL("El tablero no debe ser null. Ingrese un tablero correcto."),
    MODO_JUEGO_NULL("El modo de juego no debe ser null. Ingrese un modo de juego correcto.");

    private String mensaje;

    TipoExcepcionMenu(String mensaje){
        this.mensaje = mensaje;
    }

    public String getMensaje(){
        return this.mensaje;
    }
}
