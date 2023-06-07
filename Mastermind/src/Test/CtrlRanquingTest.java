package Test;
import org.junit.*;
import org.junit.Test;
import static org.junit.Assert.*;
import Domini.*;


public class CtrlRanquingTest {
	
	@Before
	public void start() {
		System.out.println();
	}
	
	@After
	public void end() {
		System.out.println("Tests realizados con exito");
	}
	
	
	/*
	@Test
	//Prueba a añadir en un ranquing como la logica es igual en todos los ranquings,entonces si funciona en un tipo de ranquing tambien funciona en los otros
	public void añadirEnRanquings() {
		CtrlRanquing cr = new CtrlRanquing();
		System.out.println("Testeo funcion de añadir en los ranquings");
		Usuari u = new Usuari("Adrian", "11072002");
		Usuari u2 = new Usuari("Sergio", "74627453843beh");
		Usuari u3 = new Usuari("Javier", "vdyvsyvdu");
		Usuari u4 = new Usuari("dbu", "506694");
		cr.actualizarRankingEstandar(u, 150);
		CtrlRanquing.actualizarRankingEstandar(u2, 75);
		CtrlRanquing.actualizarRankingEstandar(u3, 50);
		CtrlRanquing.actualizarRankingEstandar(u4, 600);
		//Intenta insertar uno que ya existe y con peor puntuación
		CtrlRanquing.actualizarRankingEstandar(u, 5);
		assertEquals((Integer)4,CtrlRanquing.getSizeEstandar());
		//Intenta añadir un usuario existente pero con mejor puntuación
		CtrlRanquing.actualizarRankingEstandar(u, 155);
		assertEquals((Integer)4,CtrlRanquing.getSizeEstandar());
	}
	*/
	
	/*
	@Test
	//Prueba de borrado de puntuaciones de usuarios
	public void BorrarUsuarios() {
		System.out.println("Testeo de la funcion de borrar puntuaciones de los ranquings");
		Usuari u = new Usuari("Adrian", "11072002");
		Usuari u2 = new Usuari("Sergio", "74627453843beh");
		Usuari u3 = new Usuari("Javier", "vdyvsyvdu");
		Usuari u4 = new Usuari("dbu", "506694");
		CtrlRanquing.afegirAlRankingEstandar(u, 150);
		CtrlRanquing.afegirAlRankingEstandar(u2, 75);
		CtrlRanquing.afegirAlRankingEstandar(u3, 50);
		CtrlRanquing.afegirAlRankingEstandar(u4, 600);
		CtrlRanquing.deletePartidasEstandarUsuari("pepito");
		assertEquals((Integer)4,CtrlRanquing.getSizeEstandar());
		// --------------------------------------------------
		//Borrando uno que si que existe
		CtrlRanquing.deletePartidasEstandarUsuari(u.getNom());
		assertEquals((Integer)3,CtrlRanquing.getSizeEstandar());
		
	}
	*/
	
}

	