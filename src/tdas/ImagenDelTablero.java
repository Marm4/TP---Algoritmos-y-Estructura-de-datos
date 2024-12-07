package tdas;

import enums.ModoDeJuego;
import enums.TipoExcepcionImagenDelTablero;
import excepciones.ExcepcionImagenDelTablero;
import tdas.listaYCola.Lista;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class ImagenDelTablero {
    private static final int TAMANIO_CASILLERO = 50;
    private static final int SEPARACION_CARAS = 20;
    private int cantidadDeCasilleros;
    private Lista<Ficha> fichas;
    private int numeroDeTablero;

    /**
     * Precondiciones:
     * - cantidadDeCasilleros debe ser un numero igual a la cantidad de casilleros de alguno de los modos de juego.
     * - La lista de fichas no debe ser null.
     * Postcondiciones:
     * - Se asigna la cantidadDeCasilleros.
     * - Se asigna la lista de fichas.
     * - Se asigna el valor de 1 al numeroDeTablero.
     */
   public ImagenDelTablero(int cantidadDeCasilleros, Lista<Ficha> fichas) throws ExcepcionImagenDelTablero {
        if(!ModoDeJuego.cantidadDeCasillerosCorrecta(cantidadDeCasilleros)){
            throw new ExcepcionImagenDelTablero(TipoExcepcionImagenDelTablero.CANTIDAD_CASILLEROS_INVALIDO);
        }
        if(fichas == null){
            throw new ExcepcionImagenDelTablero(TipoExcepcionImagenDelTablero.FICHAS_NULL);
        }

        this.cantidadDeCasilleros = cantidadDeCasilleros;
        this.fichas = fichas;
        this.numeroDeTablero = 1;
    }

    /**
     * Precondiciones:
     * -
     * Postcondiciones:
     * - Crea una imagen .bmp con el estado del tablero actual.
     */
    public void crearImagenDelTablero() throws IOException {
        int anchoImagen = this.cantidadDeCasilleros * TAMANIO_CASILLERO;
        int altoImagen = (this.cantidadDeCasilleros * TAMANIO_CASILLERO + SEPARACION_CARAS) * this.cantidadDeCasilleros - SEPARACION_CARAS;

        // Crear una imagen en blanco
        BufferedImage imagen = new BufferedImage(anchoImagen, altoImagen, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics = imagen.createGraphics();

        // Fondo blanco
        graphics.setColor(Color.WHITE);
        graphics.fillRect(0, 0, anchoImagen, altoImagen);

        // Dibujar las caras Z
        for (int z = 1; z <= this.cantidadDeCasilleros; z++) {
            int offsetY = (z - 1) * (this.cantidadDeCasilleros * TAMANIO_CASILLERO + SEPARACION_CARAS);  // Ajusta el desplazamiento en Y por nivel Z

            // Etiqueta de nivel Z
            /*graphics.setColor(Color.BLACK);
            graphics.drawString("posicionZ = " + z, 5, offsetY + 15);*/

            // Dibujar el grid
            graphics.setColor(Color.LIGHT_GRAY);
            for (int x = 0; x < this.cantidadDeCasilleros; x++) {
                for (int y = 0; y < this.cantidadDeCasilleros; y++) {
                    int posicionX = x * TAMANIO_CASILLERO;
                    int posicionY = offsetY + y * TAMANIO_CASILLERO;
                    graphics.drawRect(posicionX, posicionY, TAMANIO_CASILLERO, TAMANIO_CASILLERO);
                }
            }

            // Dibujar fichas en el nivel Z actual
            this.fichas.iniciarCursor();
            while(this.fichas.avanzarCursor()){
                Ficha ficha = this.fichas.obtenerCursor();
                if (ficha.getPosicionZ() == z) {  // Verifica si la ficha está en el nivel Z actual
                    graphics.setColor(ficha.getColor());  // Usar el color de la ficha
                    int coordenadaX = (ficha.getPosicionX() - 1) * TAMANIO_CASILLERO;
                    int coordenadaY = offsetY + (ficha.getPosicionY() - 1) * TAMANIO_CASILLERO;
                    graphics.fillRect(coordenadaX, coordenadaY, TAMANIO_CASILLERO, TAMANIO_CASILLERO);  // Dibuja la ficha en el tablero
                }
            }
        }

        graphics.dispose();
        ImageIO.write(imagen, "bmp", new File("tablero" + this.numeroDeTablero + ".bmp"));
        this.numeroDeTablero++;
    }

}