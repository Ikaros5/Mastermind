package Test;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import Domini.*;

public class CtrlPartidaTest {
	
	@Before
	public void start() {
		System.out.println();
	}
	
	@After
	public void end() {
		System.out.println("Tests realizados con exito");
	}
	@Ignore
	@Test
	public void probarIntroducirPartidaYGetNumPartidas() {
		System.out.println("Test de introducir partidas y comprobar su tamaño");
		Partida p1 = new Partida();
		Partida p2 = new Partida();
		Partida p3 = new Partida();
		CtrlPartida.introducirPartida(p1);
		CtrlPartida.introducirPartida(p2);
		CtrlPartida.introducirPartida(p3);
		assertEquals(3,CtrlPartida.getNumPartidas());
	}
	
	@Test
	public void probarGetPartida() {
		System.out.println("Test para probar el funcionamiento de GetPartida");
		Partida p1 = new Partida();
		p1.definirPartida(0, null, "CodeMaker");
		CtrlPartida.introducirPartida(p1);
		Integer d = p1.getId();
		Partida p2 = CtrlPartida.getPartida(d);
		assertEquals(p1.getId(),p2.getId());
	}
}
