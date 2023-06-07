package Presentacion;
import java.awt.Color;
import java.io.FileNotFoundException;
import java.util.*;

import javax.swing.JOptionPane;

import Domini.*;

/**
 * Controlador de la capa de Presentación cuya función es comunicarse con la capa de Dominio para poder recibir los datos que necesite
 */
public class CtrlPresentacion {
	private static CtrlRanquing cr;
	private static CtrlUsuari cu;
	private static CtrlPartida cp;
	private static ventanaGestionPerfil vPrincipal;
	private static String rol;
	
	
	/**
	 * Constructora por defecto
	 * @throws Exception No existe algún fichero
	 */
    public CtrlPresentacion() throws Exception {
        cr = new CtrlRanquing();
        cu = new CtrlUsuari();
        cp = new CtrlPartida();
        vPrincipal = new ventanaGestionPerfil();
    }
    
    /**
     * Inicia la ventana de Gestión Perfil
     */
    public static void iniGestionPerfil() {
    	vPrincipal = new ventanaGestionPerfil();
    }
    
    /**
     * Inicia la ventana del Menú Principal
     */
    public static void iniMenuPrincipal() {
    	ventanaMenuPrincipal mp = new ventanaMenuPrincipal();
    }
    
    
    /**
     * Inicia la ventana que muestra las reglas
     */
    public static void iniReglas() {
    	ventanaReglas reg = new ventanaReglas();
    }
    
    /**
     * Inicia la ventana que muestra el historial de partidas del usuario
     */
    public static void iniVentanaHistorial() {
    	ventanaHistorial vh = new ventanaHistorial();
    }
    
    /**
     * Inicia la ventana de ranquings
     */
    public static void iniVentanaRank() {
    	ventanaRanking vrank = new ventanaRanking();
    }
    
    
    /**
     * Inicia la ventana de records
     */
    public static void iniVentanaRecord() {
    	ventanaRecords vr = new ventanaRecords();
    }
    
    /**
     * Inicia la ventana de configurar partida
     */
    public static void iniVentanaConfigPartida() {
    	ventanaConfigPartida vp = new ventanaConfigPartida();
    }
    
    /**
     * Inicia partida estándar
     * @param p Rol inicial
     * @param dif Dificultad de la partida
     */
    public static void iniPE(String p,int dif) {
    	rol = p;
    	String user = getLogged();
    	int id = cp.crearPartida(user,dif,rol);
    	ventanaPartidaEstándar vpe = new ventanaPartidaEstándar(id);
    }
    
    /**
     * Inicia la ventana de partida estándar cargando previamente la partida
     * @param id Id de la partida
     */
    public static void iniPECargada(int id) {
    	ventanaPartidaEstándar vpe = new ventanaPartidaEstándar(id);
    }
    
    /**
     * Inicia la ventana de partida estándar cargando previamente la partida
     * @param id Id de la partida
     */
    public static void iniPDCargada(int id) {
    	ventanaPartidaDificil vpd = new ventanaPartidaDificil(id);
    }
    
    /**
     * Inicia la ventana de partida estándar cargando previamente la partida
     * @param id Id de la partida
     */
    public static void iniPEXCargada(int id) {
    	ventanaPartidaExtrema vpex = new ventanaPartidaExtrema(id);
    }
    
    /**
     * Inicia partida dificil
     * @param p Rol inicial
     * @param dif Dificultad de la partida
     */
    public static void iniPD(String p,int dif) {
    	rol = p;
    	String user = getLogged();
    	int id = cp.crearPartida(user,dif,rol);
    	ventanaPartidaDificil vpd = new ventanaPartidaDificil(id);
    }
    
    /**
     * Inicia partida extrema
     * @param p Rol inicial
     * @param dif Dificultad de la partida
     */
    public static void iniPEX(String p,int dif) {
    	rol = p;
    	String user = getLogged();
    	int id = cp.crearPartida(user,dif,rol);
    	ventanaPartidaExtrema vpex = new ventanaPartidaExtrema(id);
    }
    
    /**
     * Inicia la ventana de cargar partida
     */
    public static void iniVentanaCargar() {
    	ventanaCargarPartida vcp = new ventanaCargarPartida();
    }
    
   /**
    * Getter del rol de una partada identificada por idP
    * @param idP Id de la partida
    * @return String que representa el rol de la partida
    */
   public static String getRol(int idP) {
	  return cp.getRol(idP);
   }
    
   /**
    * LogOut del usuario
    */
    public static void cierraSesion() {
    	CtrlUsuari.logOut();
    }
    
    /**
     * Inciar sesión
     * @param username Nombre del usuario
     * @param password Contraseña del usuario
     */
    public static void iniciaSesion(String username, String password) {
    	int res = CtrlUsuari.LogIn(username, password);
    	if (res == -1) {
    		JOptionPane.showMessageDialog(null, " No existe este usuario, \n clique antes a 'Registrar'", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	else if (res == 0) {
    		JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    	else {
    		iniMenuPrincipal();
    		vPrincipal.cierra();
    	}
    }
    
    /**
     * Registra perfil
     * @param username Nombre del usuario
     * @param password Contraseña del usuario
     */
    public static void registraPerfil(String username, String password) {
    	boolean res = CtrlUsuari.RegistrarUsuario(username, password);
    	if (res) {
    		//iniMenuPrincipal();
    		//vPrincipal.cierra();
    		JOptionPane.showMessageDialog(null, "Se registró con éxito", "Exito", JOptionPane.INFORMATION_MESSAGE);
    	}
    	else {
    		JOptionPane.showMessageDialog(null, "Ya existe ese nombre de usuario en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
    	}
    }
    
    /**
     * Elimina perfil
     * @param username Nombre del usuario
     * @param password Contraseña del usuario
     */
    public static void eliminarPerfil(String username, String password) {
    	Object[] o = new Object[]{"Sí", "No"};
        int seleccion = JOptionPane.showOptionDialog(null, "Se eliminará el usuario.\n¿Desea continuar?\n", "", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null, o, "Sí");
        if (seleccion == 0) {
        	int res = CtrlUsuari.eliminarUsuari(username, password);
        	if (res == -1) {
        		JOptionPane.showMessageDialog(null, "No existe el usuario en el sistema", "Error", JOptionPane.ERROR_MESSAGE);
        	}
        	else if (res == 0) {
        		JOptionPane.showMessageDialog(null, "Contraseña incorrecta", "Error", JOptionPane.ERROR_MESSAGE);
        	}
        	else {
        		JOptionPane.showMessageDialog(null, "Eliminado con exito", "Exito", JOptionPane.INFORMATION_MESSAGE);
        		cp.eliminarPartidasUsuario(username);
        		cr.deletePartidasRankings(username);
        	}
        	
        }
    }
    
    /**
     * Ordena al dominio a que guarde todos sus datos en la capa de persistencia
     * @throws FileNotFoundException No existe algún fichero
     */
    public static void guardarEnPersistencia() throws FileNotFoundException {
    	cr.cargarRankingsAlFichero();
    	//cp.cargarPartidasAlFichero();
    	CtrlPartida.cargarPartidasAlFichero();
    	cu.cargarUsuariosAlFichero();
    }
    
    
    /**
     * Getter del historial del usuario logeado
     * @param username Nombre del usuario
     * @return Devuelve las partidas del usuario
     */
    public static ArrayList<String> getHist(String username) {
    	return cu.getHistorial(username);
    }
    
    /**
     * Getter del usuario actualmente logeado
     * @return Devuelve el nombre del usuario logeado
     */
    public static String getLogged() {
    	return CtrlUsuari.getUserLogged();
    }
    
    
    /**
     * Getter del ranquing estandar
     * @return Devuelve el ranquing estandar
     */
    public static HashMap<String,Integer> getRankEstandar() {
    	return cr.getRankEstandar();
    }
    
    /**
     * Getter del ranquing dificil
     * @return Devuelve el ranquing dificl
     */
    public static HashMap<String,Integer> getRankDificil() {
    	return cr.getRankDificil();
    }
    
    /**
     * Getter del ranquing extremo
     * @return Devuelve el ranquing extremo
     */
    public static HashMap<String,Integer> getRankExtremo() {
    	return cr.getRankExtremo();
    }
    
    /**
     * Getter del record estandar
     * @return Devuelve el record estandar
     */
    public static int getRecordEst() {
    	return CtrlUsuari.getRecordEst(getLogged());
    }
    
    /**
     * Getter del record dificil
     * @return Devuelve el record dificil
     */
    public static int getRecordDif() {
    	return CtrlUsuari.getRecordDif(getLogged());
    }
    
    /**
     * Getter del record extremo
     * @return Devuelve el record extremo
     */
    public static int getRecordExt() {
    	return CtrlUsuari.getRecordExt(getLogged());
    }
    
    /**
     * Crea una partida con la dificultad indicada
     * @param dificultad Dificultad(0,1,2)
     * @return Devuelve el id de la partida creada
     */
    public static int crearPartida(int dificultad) {
    	String user = getLogged();
    	return cp.crearPartida(user,dificultad,rol);
    }
    
    /**
     * Solve del codigo secreto generado por la maquina
     * @param id Id de la partida
     * @param code CodeSecreto puesto en la ventana de la partida
     * @return Devuelve los intentos de la maquina
     */
    public static List<List<Integer>> solve(int id, List<Integer> code) {
    	return cp.solve(id, code);
    }
    
    /**
     * Getter de la ronda de la Partida
     * @param idP Id de la Partida
     * @return Devuelve la ronda en la que se encuentra la partida
     */
    public static int getRondaPartida(int idP) {
    	return cp.getRonda(idP);
    }
    
    /**
     * Getter de la puntuación de la partida
     * @param idP Id de la partida
     * @return Devuelve la puntuación de la partida
     */
    public static int getPuntuacionPartida(int idP) {
    	return cp.getPuntuacion(idP);
    }
    
    /**
     * Devuelve el feedback del intento
     * @param intento Intento del jugador
     * @param idP Id de la partida
     * @return Devuelve cuantas rojas, blancas y negras hay en el intento
     */
    public static List<Integer> feedBackIntento(List<Integer> intento,int idP) {
    	return cp.feedBack(intento,idP);
    }
    
    /**
     * Setter de la puntuación 
     * @param idp Id de la partida
     * @param intentos_jugador Numero de intentos del jugador
     * @param intentos_maquina Número de intentos de la maquina
     * @return Devuelve la puntuación una vez actualizada
     */
    public static int actualizarPuntuacion(int idp,int intentos_jugador,int intentos_maquina) {
    	return cp.actualizarPuntuacion(idp,intentos_jugador,intentos_maquina);
    }
    
    /**
     * Cambia de rol en la partida
     * @param idP Id de la partida
     */
    public static void cambioRol(int idP) {
    	cp.cambioRol(idP);
    	
    }
    
    /**
     * Getter del codigo secreto de la máquina
     * @param idP Id de la partida
     * @return Devuelve el codigo secreto
     */
    public static ArrayList<Integer> getCode(int idP) {
    	return cp.getCodeMaquina(idP);
    }
    
    /**
     * Guarda una partida en dominio
     * @param idP Id de la Partida
     * @param puntuacion Puntuación de la partida
     */
    public static void guardarPartida(int idP, int puntuacion) {
    	cp.guardarPartida(idP,puntuacion);
    }
    
    /**
     * Genera un nuevo codigo random
     * @param idP Id de la partida
     */
    public static void newCode(int idP) {
    	cp.newCodeRandom(idP);
    }
    
    /**
     * getter de la pista
     * @param idP Id de la partida
     * @return Devuelve la pista
     */
    public static int getPista(int idP) {
    	return cp.getPista(idP);
    }
    
    /**
     * Setter para una partida acabada
     * @param idP Id de la partida
     */
    public static void setAcabada(int idP) {
    	cp.setAcabada(idP);
    }
    
    /**
     * Actualiza el record y el historial de una partida
     * @param idP Id de la partida
     */
    public static void actualizarRecordHistorial(int idP) {
    	cp.actualizarRecordHistorial(idP);
    }
    
    /**
     * Actualiza el ranquing estandar
     * @param idP Id de la partida
     */
    public static void actualizarRankingEstandar(int idP) {
    	cp.actualizarRankinEstandar(idP);
    }
    
    /**
     * Actualiza el ranquing dificil
     * @param idP Id de la partida
     */
    public static void actualizarRankingDificil(int idP) {
    	cp.actualizarRankinDificil(idP);
    }
    
    /**
     * Actualiza el ranquing extremo
     * @param idP Id de la partida
     */
    public static void actualizarRankingExtremo(int idP) {
    	cp.actualizarRankinExtremo(idP);
    }
    
    /**
     * Actualiza las partidas not finished del usuario
     * @param idP Id de la partida
     */
    public static void actualizarNotFinished(int idP) {
    	cp.actualizarNotFinished(idP);
    }
    
    /**
     * getter de las partidas sin finalizar
     * @return Devuelve las partidas no finalizadas como un array de strings
     */
    public static ArrayList<String> getNotFinished() {
    	return cu.getNotFinished(getLogged());
    }
    
    /**
     * Actualiza el historial
     * @param idp Id de la partida
     */
    public static void actualizarHistorial(int idp) {
    	cp.actualizarHistorial(idp);
    }
    
    /**
     * Getter de la dificultad de la partida
     * @param idP Id de la Partida
     * @return Devuelve la dificultad de la Partida
     */
    public static int getDifPartida(int idP) {
    	return cp.getDifPartida(idP);
    }
    
    /**
     * Convierte un Array de colores a un diccionario con valor (Color,Integer)
     * @param colores Array de colores
     * @return Devuelve el map de (Color,Integer)
     */
    public static HashMap<Color,Integer> Colors2Ints(Color[] colores) {
		HashMap<Color,Integer> map = new HashMap<> ();
		int k = 0;
		for (Color c : colores) {
			map.put(c, k);
			++k;
		}
		return map;
	}
	
    /**
     * Convierte un array de colores a un diccionario con valor (Integer,Color)
     * @param colores Array de colores
     * @return Devuelve el map de (Integer,Color)
     */
	public static HashMap<Integer,Color> Ints2Colors(Color[] colores) {
		HashMap<Integer,Color> map = new HashMap<> ();
		int k = 0;
		for (Color c : colores) {
			map.put(k,c);
			++k;
		}
		return map;
	}
	
	/**
	 * Borra las partidas del usuario logeado
	 */
	public static void borrarPartidasUsuario() {
		cp.eliminarPartidasUsuario(getLogged());
	}

}
