package enums;

import java.util.Random;

public enum TipoDeCarta {
    PIERDE_TURNO("Hace que el jugador seleccionado pierda su proximo turno, impidiendo que pueda realizar una acción en esa ronda"),
    BLOQUEA_FICHA("Bloquea la ficha de un jugador seleccionado, lo que significa que no podrá mover esa ficha hasta que se desbloquee"),
    ANULA_CASILLERO("Anula un casillero seleccionado, impidiendo que se puedan colocar fichas en ese espacio durante el juego. No se puede anular un casillero donde hay una ficha"),
    //VUELVE_ATRAS("Revierte la jugada realizada, haciendo que el jugador anterior y jugador actual pierdan su movimiento y el estado del juego vuelva a la situación previa. Tambien revierte las acciones de algunas cartas anteriores"),
    CAMBIAR_COLOR_FICHA("Cambia el color de la ficha de un jugador seleccionado para que coincida con el color del jugador actual"),
    DESBLOQUEAR_FICHA("Desbloquea alguna ficha del jugador.");


    private static final Random random = new Random();
    private String descripcion;

    TipoDeCarta(String descripcion){
        this.descripcion = descripcion;
    }

    public static TipoDeCarta obtenerCartaAleatoria() {
        TipoDeCarta[] valores = TipoDeCarta.values();
        return valores[random.nextInt(valores.length)];
    }

    public String getDescripcion(){
        return this.descripcion;
    }
}
