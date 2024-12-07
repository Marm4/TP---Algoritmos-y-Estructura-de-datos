package enums;

public enum TipoExcepcionCartas {
    CARTAS_VACIO("La lista de cartas o la carta no puede estar vacía. Asegúrese de que se haya proporcionado una carta válida."),
    CARTAS_NULL("La lista de cartas o la carta no puede ser null. Asegúrese de que se haya proporcionado una carta válida."),
    JUGADOR_NULL("El jugador no puede ser null. Por favor, ingrese un jugador antes de continuar.");


    private String mensaje;

    TipoExcepcionCartas(String mensaje){
        this.mensaje = mensaje;
    }

    public String getMensaje(){
        return this.mensaje;
    }
}
