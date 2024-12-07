package enums;

public enum TipoExcepcionJugador {
    NOMBRE_VACIO("El nombre del jugador no puede estar vacío. Por favor, ingrese un nombre válido para el jugador."),
    COLOR_NULL("El color del jugador debe ser válido. Asegúrese de proporcionar un color correcto."),
    CARTAS_VACIO_NULL("La lista de cartas no puede ser nula ni estar vacía. Verifique que se haya asignado correctamente un conjunto de cartas."),
    FICHA_NULL("La ficha no puede ser nula. Asegúrese de que la ficha esté correctamente asignada antes de proceder."),
    FICHA_INVALIDA("La ficha no pertenece al jugador. Asegúrese de que la ficha este la lista de fichas del jugador."),
    POSICIONES_VACIO_NULL("La lista de posiciones no puede estar vacía ni ser null. Asegúrese de proporcionar una posición válida.");

    private String mensaje;

    TipoExcepcionJugador(String mensaje){
        this.mensaje = mensaje;
    }

    public String getMensaje(){
        return mensaje;
    }
}
