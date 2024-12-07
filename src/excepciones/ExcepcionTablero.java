package excepciones;

import enums.TipoExcepcionTablero;

public class ExcepcionTablero extends Exception{

    public ExcepcionTablero(TipoExcepcionTablero tipoExcepcionTablero){
        super(tipoExcepcionTablero.getMensaje());
    }
}
