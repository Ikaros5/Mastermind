package Test;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.*;

import Domini.*;


public class UsuariTest {
	
	private Usuari usuari;
	
	@Before
    public void setUp() {
        usuari = new Usuari("username", "password");
    }
	
	@Test
    public void testGetPassword() {
        assertEquals("password", usuari.getPassword());
    }
	
	 @Test
	 public void testSetPassword() {
	    usuari.setPassword("new_password");
	    assertEquals("new_password", usuari.getPassword());
	 }
	
	 @Test
	 public void testActualizarHistorial() {
	    Partida partida = new Partida();
	    usuari.actualizarHistorial(partida);
	    assertTrue(usuari.getHistorial().contains(partida));
	 }
	
	 @Test
	 public void testActualizarRecord() {
	     Partida p1 = new Partida();
	     Partida p2 = new Partida();
	     Partida p3 = new Partida();
	     p1.definirPartida(0,usuari,"CodeMaker");
	     p2.definirPartida(0,usuari,"CodeMaker");
	     p3.definirPartida(0,usuari,"CodeMaker");
	     p1.setPuntuacion(50);
	     p2.setPuntuacion(0);
	     p3.setPuntuacion(100);
	     usuari.actualizarRecord(p1);
	     usuari.actualizarRecord(p2);
	     usuari.actualizarRecord(p3);
	     assertEquals(100, usuari.getRecordEstandar());
	  }
	 
	 
	 @Test
	 public void testGetHistorial() {
	     Partida partida1 = new Partida();
	     Partida partida2 = new Partida();
	     usuari.actualizarHistorial(partida1);
	     usuari.actualizarHistorial(partida2);
	     assertTrue(usuari.getHistorial().contains(partida1));
	     assertTrue(usuari.getHistorial().contains(partida2));
	 }
	 
	 @Test
	 public void testGetRecordEstandar() {
		 Partida p = new Partida();
		 p.definirPartida(0,usuari,"CodeMaker");
		 p.setPuntuacion(50);
		 usuari.actualizarRecord(p);
		 assertEquals(50, usuari.getRecordEstandar());
	 }
	 
	 @Test
	 public void testGetRecordDificil() {
		 Partida p = new Partida();
		 p.definirPartida(1,usuari,"CodeMaker");
		 p.setPuntuacion(50);
		 usuari.actualizarRecord(p);
		 assertEquals(50, usuari.getRecordDificil());    
	 }
	 
	 @Test
	 public void testGetRecordExtremo() {
		 Partida p = new Partida();
		 p.definirPartida(2,usuari,"CodeMaker");
		 p.setPuntuacion(50);
		 usuari.actualizarRecord(p);
		 assertEquals(50, usuari.getRecordExtremo());   
	 }
	 
	 
	 @Test
	 public void testGetPartidasNotFinished() {
	     Partida partida = new Partida();
	     usuari.introducirPartida(partida);
	     assertTrue(usuari.getPartidasNotFinished().contains(partida));
	 }
	 
	 @Test
	 public void testGetNotFinishedSize() {
	     Partida partida1 = new Partida();
	     Partida partida2 = new Partida();
	     usuari.introducirPartida(partida1);
	     usuari.introducirPartida(partida2);
	     assertEquals(Integer.valueOf(2), usuari.getNotFinishedSize());
	 }
	 
	
	 @Test
	 public void testListarPartidasNotFinished() {
		 Partida p1 = new Partida();
	     Partida p2 = new Partida();
	     Partida p3 = new Partida();
	     p1.setState(false);
	     p2.setState(false);
	     p3.setState(false);
	     usuari.introducirPartida(p1);
	     usuari.introducirPartida(p2);
	     usuari.introducirPartida(p3);
	     int expected = 3;
	     int size = usuari.getNotFinishedSize();
	     assertEquals(expected, size);
	    }
	 
	 @Test
	 public void testIntroducirPartidaAcabada() {
		 Partida p1 = new Partida();
	     p1.setState(true);
	     usuari.introducirPartida(p1);
	     assertTrue(usuari.getHistorial().contains(p1));
	     assertFalse(usuari.getPartidasNotFinished().contains(p1));
	 }
	 
	@Test
	public void testIntroducirPartidaNoAcabada() {
		 Partida p1 = new Partida();
	     p1.setState(false);
	     usuari.introducirPartida(p1);
	     assertFalse(usuari.getHistorial().contains(p1));
	     assertTrue(usuari.getPartidasNotFinished().contains(p1));
	 }
	
	 @Test
	 public void testActualizarNotFinished() {
		 Partida p1 = new Partida();
	     Partida p2 = new Partida();
	     Partida p3 = new Partida();
	     p1.setState(true);
	     p2.setState(false);
	     p3.setState(true);
		 usuari.introducirPartida(p1);
		 usuari.introducirPartida(p2);
		 usuari.introducirPartida(p3);
	     usuari.actualizarNotFinished();
	     assertFalse(usuari.getPartidasNotFinished().contains(p1));
	     assertTrue(usuari.getHistorial().contains(p1));
	     assertTrue(usuari.getPartidasNotFinished().contains(p2));
	     assertFalse(usuari.getHistorial().contains(p2));
	     assertFalse(usuari.getPartidasNotFinished().contains(p3));
	     assertTrue(usuari.getHistorial().contains(p3));
	 }
	 
}
