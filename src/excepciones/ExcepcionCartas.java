package excepciones;

import enums.TipoExcepcionCartas;

public class ExcepcionCartas extends Exception{

    public ExcepcionCartas(TipoExcepcionCartas tipoExcepcionCartas){
        super(tipoExcepcionCartas.getMensaje());
    }
}
