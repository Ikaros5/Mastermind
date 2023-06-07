package Test;
import static org.junit.Assert.*;
import java.util.*;

import org.junit.*;

import Domini.*;

public class PartidaTest {
    private Partida partida;
    private Usuari user;

    @Before
    public void setUp() {
        user = new Usuari("test", "pass");
        partida = new Partida();
        partida.definirPartida(0, user, "CodeMaker");
    }

    @Test
    public void testIniciarPartidaEstandar() {
    	partida = new Partida();
    	partida.definirPartida(0, user, "CodeMaker");
        assertEquals(4, partida.getLongCodigo());
        assertEquals(4, partida.getNumColores());
    }

    @Test
    public void testIniciarPartidaDificil() {
        partida = new Partida();
        partida.definirPartida(1, user, "CodeMaker");
        assertEquals(4, partida.getLongCodigo());
        assertEquals(6, partida.getNumColores());
    }

    @Test
    public void testIniciarPartidaExtremo() {
        partida = new Partida();
        partida.definirPartida(2, user, "CodeMaker");
        assertEquals(6, partida.getLongCodigo());
        assertEquals(8, partida.getNumColores());
    }
    
    @Test
    public void testGeneraCodigoRandom() {
        ArrayList<Integer> codigo = partida.generaCodigoRandom(4, 4);
        assertEquals(4, codigo.size());
        for (Integer color : codigo) {
            assertTrue(color >= 0 && color < 4);
        }
    }
    
    @Test
    public void testCambioRol() {
    	partida = new Partida();
    	partida.definirPartida(0, user, "CodeMaker");
        partida.cambioRol();
        assertEquals("CodeBreaker", partida.getRol());
    }

    
    @Test
    public void testCalcularPuntuacionRonda() {
        assertEquals(50, partida.calcularPuntuacionRonda(10, 5));
        assertEquals(0, partida.calcularPuntuacionRonda(5, 10));
        assertEquals(25, partida.calcularPuntuacionRonda(10, 10));
    }
}
