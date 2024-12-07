package excepciones;

import enums.TipoExcepcionJugador;
import enums.TipoExcepcionMenu;

public class ExcepcionMenu extends Exception{
    public ExcepcionMenu(TipoExcepcionMenu tipoExcepcionMenu){
        super(tipoExcepcionMenu.getMensaje());
    }
}