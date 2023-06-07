package Domini;
import java.io.FileNotFoundException;
import java.util.*;
import Persistencia.*;

/**
 * Controlador de Dominio para los usuarios, se encarga de almacenar todos los usarios, y a su vez comunicarse con las demás capas del Proyecto
 */

public class CtrlUsuari {
	static private HashMap<String,Usuari> usuaris = new HashMap<String,Usuari>(); //conjunto de usuarios que han iniciado sesion alguna vez en el sistema
	private static String userLogged; // nombre del usuario logueado
	private static CtrlUsuarioDatos cj = new CtrlUsuarioDatos();
	
	/**
	 * Constructora por defecto, inicializa el conjunto de usuarios
	 */
	public CtrlUsuari() {
		try {
			inicializarUsuariosFichero();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	/**
	 * Devuelve el usuario actualmente logeado
	 * @return String, null si no hay nadie logeado, en caso contrario devuelve el nombre del usuario
	 */
	 public static String getUserLogged() {
		if (userLogged == null) {
	            return null;
	    }
	    else return userLogged;
	}
	
	/**
	 * Getter de un usuario por su nombre
	 * @param name Nombre del usuario
	 * @return Devuelve el usuario asociado
	 */
	public static Usuari getUsuari(String name) {
		Usuari u = usuaris.get(name);
		return u;
	}

	/**
	 * Registra a un usuario
	 * @param name Nombre del usuario a registrar
	 * @param pwd Contraseña para ese usuario
	 * @return True si se regsitra con exito, false en caso contrario
	 */
	public static boolean RegistrarUsuario(String name, String pwd) {
		if (!usuaris.containsKey(name)) {
			Usuari u = new Usuari(name, pwd);
			u.setPassword(pwd);
			usuaris.put(name, u);
			return true;
		}
		else {
			return false;
		}
	}
	
	
	/**
	 * Logeo de un usuario
	 * @param name Nombre del usuario
	 * @param pwd Contraseña del usuario
	 * @return Devuelve -1 si no existe el usuario, 1 en caso de todo correcto, 0 en caso de que solo esté mal la contraseña
	 */
	public static int LogIn(String name, String pwd) {
		if (!usuaris.containsKey(name)) {
			return -1;
		}
		else {
			Usuari u = usuaris.get(name);
			if (u.getPassword().equals(pwd) ) {
				userLogged = name;
				return 1;
			}
			else {
				return 0;
			}
			
		}
	}
				
	
	/**
	 * Cierre de sesión
	 */
	public static void logOut() {
		userLogged = null;
	}
	
	/**
	 * Elimina un usuario totalmente del sistema
	 * @param name Nombre del usuario
	 * @param pwd  Contraseña del usuario
	 * @return Devuelve -1 si no existe el usuario, 0 si la contraseña es incorrecta, 1 en caso de éxito
	 */
	public static int eliminarUsuari(String name, String pwd) {
	    if (usuaris.containsKey(name)) {
	        if (usuaris.get(name).getPassword().equals(pwd)) {
	            usuaris.remove(name);
	            return 1;
	        } else {
	            return 0;
	        }
	    }
	    else {
	        return -1;
	    }
	}
	
	/**
	 * Se cargan los usuarios del conjunto a la capa de persistencia
	 * @throws FileNotFoundException Fichero no encontrado
	 */
	public void cargarUsuariosAlFichero() throws FileNotFoundException {
		cj.crearIndiceUsuarios(usuaris);
	}
	
	/**
	 * Se descarga de persistencia los usuarios hacia el conjunto
	 * @throws FileNotFoundException Fichero no encontrado
	 */
	public static void inicializarUsuariosFichero() throws FileNotFoundException {
		usuaris = cj.inicializarUsuarios();
	}
	
	/**
	 * Devuelve el historial de un usuario para la capa de presentación
	 * @param username Nombre del usuario
	 * @return Devuelve un array de string, donde cada 4 campos de ese array es una partida
	 */
    public ArrayList<String> getHistorial(String username) {
    	ArrayList<String> hist = new ArrayList<>();
    	Usuari u = CtrlUsuari.getUsuari(username);
    	Set<Partida> p = u.getHistorial();
    	for (Partida partida: p) {
    		int id = partida.getId();
    		String id1 = Integer.toString(id);
    		int d = partida.getDificultad();
    		String dif;
    		if (d == 0) dif = "Estandar";
    		else if (d == 1) dif = "Dificil";
    		else dif = "Extremo";
    		int punt = partida.getPuntuacion();
    		String puntuacion = Integer.toString(punt);
    		boolean g = partida.acabada();
    		String estat;
    		if (g) estat = "Acabada";
    		else estat = "Sin acabar";
    		hist.add(id1);
    		hist.add(dif);
    		hist.add(puntuacion);
    		hist.add(estat);
    	}
    	Set<Partida> pa = u.getPartidasNotFinished();
    	for (Partida partida: pa) {
    		int id = partida.getId();
    		String id1 = Integer.toString(id);
    		int d = partida.getDificultad();
    		String dif;
    		if (d == 0) dif = "Estandar";
    		else if (d == 1) dif = "Dificil";
    		else dif = "Extremo";
    		int punt = partida.getPuntuacion();
    		String puntuacion = Integer.toString(punt);
    		boolean g = partida.acabada();
    		String estat;
    		if (g) estat = "Acabada";
    		else estat = "Sin acabar";
    		hist.add(id1);
    		hist.add(dif);
    		hist.add(puntuacion);
    		hist.add(estat);
    	}
    	return hist;
    }
    
    /**
     * Devuelve el record estándar para ese usuario
     * @param username Nombre del usuario
     * @return int puntuación del usuario en la dificultad estándar
     */
    public static int getRecordEst(String username) {
    	Usuari u = usuaris.get(username);
    	if (u == null) {
    		return 0;
    	}
    	else return u.getRecordEstandar();  	
    }
    
    /**
     * Devuelve el record dificil para ese usuario
     * @param username Nombre del usuario
     * @return int puntuación del usuario en la dificultad dificil
     */
    public static int getRecordDif(String username) {
    	Usuari u = usuaris.get(username);
    	if (u == null) {
    		return 0;
    	}
    	else return u.getRecordDificil();  	
    }
    
    
    /**
     * Devuelve el record extremo para ese usuario
     * @param username Nombre del usuario
     * @return int puntuación del usuario en la dificultad extremo
     */
    public  static int getRecordExt(String username) {
    	Usuari u = usuaris.get(username);
    	if (u == null) {
    		return 0;
    	}
    	else return u.getRecordExtremo();  	
    }
    
    /**
     * Devuelve las partidas no finalizadas para la capa de presentación
     * @param name Nombre del usuario
     * @return Array de strings donde cada 5 campos de ese array es una partida
     */
    public ArrayList<String> getNotFinished(String name) {
    	Set<Partida> partidas = getUsuari(name).getPartidasNotFinished();
    	ArrayList<String> nf = new ArrayList<> ();
    	for (Partida p : partidas) {
    		nf.add(Integer.toString(p.getId()));
    		int dif = p.getDificultad();
    		if (dif == 0) nf.add("Estándar");
    		else if (dif == 1) nf.add("Dificil");
    		else nf.add("Extremo");
    		nf.add(p.getRol());
    		nf.add(Integer.toString(p.getRondaActual()));
    		nf.add(Integer.toString(p.getPuntuacion()));
    	}
    	return nf;
    }

}
