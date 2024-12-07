package excepciones;

import enums.TipoExcepcionJugador;

public class ExcepcionJugador extends Exception{
    public ExcepcionJugador(TipoExcepcionJugador tipoExcepcionJugador){
        super(tipoExcepcionJugador.getMensaje());
    }
}
