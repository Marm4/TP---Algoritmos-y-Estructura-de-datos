package enums;

public enum TipoExcepcionImagenDelTablero {
    CANTIDAD_CASILLEROS_INVALIDO("La cantidad de casilleros debe ser igual al de algun modo de juego."),
    FICHAS_NULL("La lista de fichas no puede ser null."),
    RUTA_ARCHIVO_VACIO("La ruta del archivo no puede estar vacia.");

    private String mensaje;
    TipoExcepcionImagenDelTablero(String mensaje){
        this.mensaje = mensaje;
    }

    public String getMensaje(){
        return this.mensaje;
    }
}
