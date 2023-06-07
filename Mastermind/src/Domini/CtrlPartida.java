package Domini;
import java.io.FileNotFoundException;
import java.util.*;
import Persistencia.*;

/**
 * Controlador de Dominio para las partidas, se encarga de almacenar las partidas y sus datos y comunicarse con las otras capas.
 */
public class CtrlPartida {
	static private HashSet<Partida> partidas = new HashSet<Partida>();
	private static CtrlPartidaDatos cj = new CtrlPartidaDatos();
	
	
	/**
	 * Constructora por defecto, inicializa el conjunto de partidas
	 */
	public CtrlPartida() {
		try {
			inicializarPartidasFichero();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}
	
	/**
	 * Getter del número de partidas actuales
	 * @return Int, tamaño del conjunto partidas
	 */
	public static int getNumPartidas() {
		return partidas.size();
	}
	
	/**
	 * 
	 * @param p Partida que se quiere introducir al sistema
	 */
	public static void introducirPartida(Partida p) {
		partidas.add(p);
	}
	
	/**
	 * Devuelve la partida con cierto id
	 * @param id ID de la partida que se busca
	 * @return p Partida con ID id si existe, null si no existe
	 */
	public static Partida getPartida(int id) {
		for (Partida p : partidas) {
			if (p.getId() == id) {
				return p;
			}
		}
		
		return null;
	}
	
	/**
	 * Se cargan las partidas del conjunto a la capa de persistencia
	 * @throws FileNotFoundException Fichero no existe
	 */
	public static void cargarPartidasAlFichero() throws FileNotFoundException {
	    cj.crearIndicePartidas(partidas);
	}

	/**
	 * Se descarga de persistencia las partias hacia el conjunto
	 * @throws FileNotFoundException No existe el fichero
	 */
	public static void inicializarPartidasFichero() throws FileNotFoundException {
	    partidas = cj.inicializarPartidas();
	}
	
	/**
	 * Devuelve la solución de la máquina al código secreto de la partida
	 * @param id ID de la partida que se quiere resolver
	 * @param code Código secreto que se tiene que resolver
	 * @return Devuelve los intentos de la máquina para resolver el código
	 */
	public List<List<Integer>> solve(int id, List<Integer> code) {
		Partida partida = null;
		for (Partida p : partidas) {
			if (p.getId() == id) partida = p;
		}
		Maquina m = partida.getMaquina();
		List<List<Integer>> solution = m.solve(code);
		return solution;
	}
	
	/**
	 * Constructora de partida a partir de parámetros
	 * @param usuario Usuario que juega la partida
	 * @param dificultad Dificultad de la partida
	 * @param rol Rol actual en la partida
	 * @return id de la partida que se ha creado
	 */
	public int crearPartida(String usuario, int dificultad, String rol) {
		Usuari u = CtrlUsuari.getUsuari(usuario);
		Partida p = new Partida();
		ArrayList<Integer> code_secreto = new ArrayList<> ();
		p.definirPartida(dificultad, u, rol);
		if (rol.equals("CodeBreaker")) {
			code_secreto = p.generaCodigoRandom(p.getLongCodigo(),p.getNumColores());
			p.setCodigoSecreto(code_secreto);
		}

		introducirPartida(p);
		return p.getId();
	}
	
	/**
	 * Genera un nuevo código aleatorio
	 * @param idP ID de la partida para la que se genera
	 */
	public void newCodeRandom(int idP) {
		Partida part = null;
		for (Partida p : partidas) {
			if (p.getId() == idP) {
				part = p;
			}
		}
		ArrayList<Integer> newCode = part.generaCodigoRandom(part.getLongCodigo(),part.getNumColores());
		part.setCodigoSecreto(newCode);
	}
	
	/**
	 * Getter de la ronda actual de la partida
	 * @param idP ID de la partida para la que se quiere mirar la ronda
	 * @return Int, que es el número de la ronda actual
	 */
	public int getRonda(int idP) {
		Partida part = null;
		for (Partida p : partidas) {
			if (p.getId() == idP) part = p;
		}
		return part.getRondaActual();
	}
	
	/**
	 * Getter de la puntuación actual de la partida
	 * @param idP ID de la partida para la que se quiere mirar la puntuación
	 * @return Int, que es la puntuación actual de la partida
	 */
	public int getPuntuacion(int idP) {
		Partida part = null;
		for (Partida p : partidas) {
			if (p.getId() == idP) part = p;
		}
		return part.getPuntuacion();
	}
	
	/**
	 * Se obtiene una pista sobre una posición y su color de la ronda actual
	 * @param code_secreto Código secreto sobre el que se quiere obtener una pista
	 * @return String, que contiene la posición y su color
	 */
	public String getPista(List<Integer> code_secreto) {
		Random random = new Random();
		int posicion = random.nextInt(code_secreto.size());
		return "En la posicion " + (posicion+1) + " esta el color " + code_secreto.get(posicion);
	}

	/**
	 * Devuelve el feedback de un intento de la partida, es decir, el número de rojas, blancas, y vacías.
	 * @param intento Intento para el cuál se quiere obtener el feedback
	 * @param idP ID de la partida que se está jugando
	 * @return Array de enteros que contiene el número de rojas, blancas y vacías
	 */
	public List<Integer> feedBack(List<Integer> intento,int idP) {
		List<Integer> feedback = new ArrayList<> ();
		Partida part  = null;
		for (Partida p : partidas) {
			if (p.getId() == idP) part = p;
		}
		feedback.add(part.getRojas(intento));
		feedback.add(part.getBlancas(intento));
		feedback.add(part.getNegras(intento));
		return feedback;
	}

	/**
	 * Actualiza la puntuación de la partida
	 * @param idp ID de la partida
	 * @param intentos_jugador Número de intentos que ha tardado el jugador en resolver el código
	 * @param intentos_maquina Número de intentos que ha tardado la máquina en resolver el código
	 * @return Int, puntuación de la ronda
	 */
	public int actualizarPuntuacion(int idp,int intentos_jugador, int intentos_maquina) {
		Partida part = null;
		for (Partida p : partidas) {
			if (p.getId() == idp) part = p;
		}
		int f = part.calcularPuntuacionRonda(intentos_maquina, intentos_jugador);
		part.actualizarPuntuacion(f);
		return f;
	}
	
	/**
	 * Cambia el rol del jugador
	 * @param idP ID de la partida
	 */
	public void cambioRol(int idP) {
		Partida part = new Partida();
		for (Partida p: partidas) {
			if (p.getId() == idP) part = p;
		}
		part.cambioRol();
	}

	/**
	 * Getter del rol actual del jugador
	 * @param idP ID de la partida
	 * @return String, el rol del jugador: CodeMaker o CodeBreaker
	 */
	public String getRol(int idP) {
		Partida part = new Partida();
		for (Partida p: partidas) {
			if (p.getId() == idP) part = p;
		}
		return part.getRol();
	}
	
	/**
	 * Obtiene un código secreto que la máquina está usando en esa ronda cuando es CodeMaker
	 * @param idP ID de la partida
	 * @return ArrayList de enteros, código secreto de la ronda
	 */
	public ArrayList<Integer> getCodeMaquina(int idP) {
		Partida part = null;
		for (Partida p : partidas) {
			if (p.getId() == idP) {
				part = p;
			}
		}
		return part.getCodeRandom();
	}
	
	/**
	 * Guarda la partida cuando no está acabada
	 * @param idP ID de la partida
	 * @param puntuacion Putuación de la partida
	 */
	public void guardarPartida(int idP,int puntuacion) {
		Partida part = null;
		for (Partida p: partidas) {
			if (p.getId() == idP) part = p;
		}
		part.guardarPartida(puntuacion);
	}
	
	/**
	 * Obtener una pista del código secreto cuando se es CodeBreaker
	 * @param idP ID de la partida
	 * @return Int, que es una posición del código secreto, a partir de la posición se obtiene el color, que juntos son la pista
	 */
	public int getPista(int idP) {
		Partida part = null;
		for (Partida p: partidas) {
			if (p.getId() == idP) part = p;
		}
		return part.pista();
	}
	
	/**
	 * Setter para poner una partida como acabada
	 * @param idP ID de la partida
	 */
	public void setAcabada(int idP) {
		Partida part = null;
		for (Partida p : partidas) {
			if (p.getId() == idP) {
				part = p;
			}
		}
		part.setState(true);
	}

	/**
	 * Actualiza el récord y el historial del usuario que juega la partida
	 * @param idP ID de la partida
	 */
	public void actualizarRecordHistorial(int idP) {
		Partida part = null;
		for (Partida p : partidas) {
			if (p.getId() == idP) part = p;
		}
		Usuari u  = part.getUsuariPartida();
		u.actualizarRecord(part);
		u.actualizarHistorial(part);
		u.actualizarNotFinished();
		
	}

	/**
	 * Actualiza el ranking estandar
	 * @param idP ID de la partida
	 */
	public void actualizarRankinEstandar(int idP) {
		Partida part = null;
		for (Partida p : partidas) {
			if (p.getId() == idP) part = p;
		}
		CtrlRanquing.actualizarRankingEstandar(part.getUsuariPartida().getNom(),part.getPuntuacion());
		
	}
	/**
	 * Actualiza el ranking difícil
	 * @param idP Id de la Partida
	 */
	public void actualizarRankinDificil(int idP) {
		Partida part = null;
		for (Partida p : partidas) {
			if (p.getId() == idP) part = p;
		}
		CtrlRanquing.actualizarRankingDificil(part.getUsuariPartida().getNom(),part.getPuntuacion());
		
	}
	
	/**
	 * Actualiza el ranking extremo
	 * @param idP Id de la Partida
	 */
	public void actualizarRankinExtremo(int idP) {
		Partida part = null;
		for (Partida p : partidas) {
			if (p.getId() == idP) part = p;
		}
		CtrlRanquing.actualizarRankingExtremo(part.getUsuariPartida().getNom(),part.getPuntuacion());
		
	}
	
	/**
	 * Actualiza las partidas no acabadas del usuario
	 * @param idP ID de la partida
	 */
	public void actualizarNotFinished(int idP) {
		Partida part = null;
		for (Partida p : partidas) {
			if (p.getId() == idP) part = p;
		}
		Usuari u = part.getUsuariPartida();
		u.introducirPartida(part);
	}
	
	/**
	 * Actualiza el historial del usuario
	 * @param idp ID de la partida
	 */
	public void actualizarHistorial(int idp) {
		Partida part = null;
		for (Partida p : partidas) {
			if (p.getId() == idp) {
				part = p;
			}
		}
		Usuari u = part.getUsuariPartida();
		u.actualizarHistorial(part);
	}
	
	/**
	 * Getter de la dificultad de la partida
	 * @param idP ID de la partida
	 * @return Int, representa la dificultad de la partida. 0 - Estandar, 1 - Difícil, 2 - Extrema
	 */
	public int getDifPartida(int idP) {
		Partida part = null;
		for (Partida p : partidas) {
			if (p.getId() == idP) {
				part = p;
			}
		}
		return part.getDificultad();
	}
	
	/**
	 * Elimina las partidas de un usuario
	 * @param name Nombre del usuario
	 */
	public void eliminarPartidasUsuario(String name) {
	    Iterator<Partida> iterator = partidas.iterator();
	    while (iterator.hasNext()) {
	        Partida p = iterator.next();
	        if (p.getUsuariPartida().getNom().equals(name)) {
	            iterator.remove();
	        }
	    }
	}

}
