package tdas.cartas;

import enums.TipoDeCarta;

public class Carta {

    //ATRIBUTOS
    private final TipoDeCarta tipoDeCarta;

    //CONSTRUCTORES
    public Carta(TipoDeCarta tipoDeCarta) {
        this.tipoDeCarta = tipoDeCarta;
    }

    //GETTERS SIMPLES
    public TipoDeCarta getTipoDeCarta() {
        return tipoDeCarta;
    }

    public String getDescripcionDeCarta(){
        return tipoDeCarta.getDescripcion();
    }
}
