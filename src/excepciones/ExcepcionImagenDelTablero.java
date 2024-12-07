package excepciones;

import enums.TipoExcepcionImagenDelTablero;

public class ExcepcionImagenDelTablero extends Exception{

    public ExcepcionImagenDelTablero(TipoExcepcionImagenDelTablero tipoExcepcionImagenDelTablero){
        super(tipoExcepcionImagenDelTablero.getMensaje());
    }
}

