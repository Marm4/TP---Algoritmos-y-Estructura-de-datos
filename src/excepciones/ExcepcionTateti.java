package excepciones;

import enums.TipoExcepcionTateti;

public class ExcepcionTateti extends Exception{

    public ExcepcionTateti(TipoExcepcionTateti tipoDeExcepcion) {
        super(tipoDeExcepcion.getMensaje());
    }
}
