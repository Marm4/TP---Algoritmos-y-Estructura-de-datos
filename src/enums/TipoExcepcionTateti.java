package enums;

public enum TipoExcepcionTateti {
    MODO_DE_JUEGO("El modo de juego y la lista de jugadores no pueden ser null. Por favor, asegúrese de proporcionar ambos valores antes de continuar."),
    CANTIDAD_DE_JUGADORES("La cantidad de jugadores no es válida. Verifique los valores ingresados."),
    JUGADOR_NULL("No se puede iniciar el juego sin jugadores. Por favor, asegúrese de agregar al menos un jugador antes de comenzar.");

    private final String mensaje;

    TipoExcepcionTateti(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getMensaje() {
        return mensaje;
    }
}
