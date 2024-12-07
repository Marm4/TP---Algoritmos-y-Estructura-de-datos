package estructurasDeDatos;

import org.junit.jupiter.api.Test;
import tdas.listaYCola.Lista;
import tdas.listaYCola.ListaSimplementeEnlazada;

import static org.junit.jupiter.api.Assertions.*;

class ListaTest {

    @Test
    void testCrearListaAPartirDeOtraLista() throws Exception {
        Lista<Integer> listaDeNumeros = new ListaSimplementeEnlazada<>();
        for(int i=1; i<=10; i++){
            listaDeNumeros.agregar(i);
        }
        assertEquals(10, listaDeNumeros.getTamanio());

        Lista<Integer> copiaDeListaDeNumero = new ListaSimplementeEnlazada<>(listaDeNumeros);
        assertEquals(10, copiaDeListaDeNumero.getTamanio());

        for(int i=1; i<=10; i++){
            assertEquals(listaDeNumeros.obtener(i), copiaDeListaDeNumero.obtener(i));
        }

        copiaDeListaDeNumero.remover((Integer)4);
        copiaDeListaDeNumero.remover((Integer)8);
        Lista<Integer> copia3 = new ListaSimplementeEnlazada<>(copiaDeListaDeNumero);
        copiaDeListaDeNumero.iniciarCursor();

        copia3.iniciarCursor();
        while (copia3.avanzarCursor()){
            System.out.println(copia3.obtenerCursor());
        }
    }

    @Test
    void testeRemoverPorElementoDeLista() throws Exception {
        Lista<Integer> listaNumeros = new ListaSimplementeEnlazada<>();

        listaNumeros.agregar(1);
        listaNumeros.remover(1);
        listaNumeros.agregar(1);

        assertTrue(listaNumeros.contiene(1));
    }

}