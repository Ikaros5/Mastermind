package Domini;
import java.io.FileNotFoundException;
import java.util.*;
import Persistencia.*;

/**
 * Controlador del ranquing en dominio, donde guarda los conjuntos de las mejores puntuaciones para cada dificultad
 */
public class CtrlRanquing {
	
	private static HashMap<String,Integer> rankEstandar = new HashMap<>();
	private static HashMap<String,Integer> rankDificil = new HashMap<>();
	private static HashMap<String,Integer> rankExtremo = new HashMap<>();
	private static CtrlRanquingDatos cr = new CtrlRanquingDatos();
	
	
	/**
	 * Constructora por defecto
	 */
	public CtrlRanquing() {
		try {
			inicializarRankingsFichero();
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Getter del ranquing estandar
	 * @return Devuelve un conjunto de nombre + puntuación
	 */
	public HashMap<String,Integer> getRankEstandar() {
		return sortMapByValue(rankEstandar);
	}
	
	/**
	 * Getter del ranquing dificil
	 * @return Devuelve un conjunto de nombre + puntuación
	 */
	public HashMap<String,Integer> getRankDificil() {
		return sortMapByValue(rankDificil);
	}
	
	/**
	 * Getter del ranquing extremo
	 * @return Devuelve un conjunto de nombre + puntuación
	 */
	public HashMap<String,Integer> getRankExtremo() {
		return sortMapByValue(rankExtremo);
	}
		
	/**
	 * Getter del tamaño del ranquing estandar
	 * @return int tamaño del ranquing
	 */
	public static Integer getSizeEstandar() {
		return rankEstandar.size();
	}
	
	/**
	 * Getter del tamaño del ranquing dificil
	 * @return int tamaño del ranquing
	 */
	public static Integer getSizeDificil() {
		return rankDificil.size();
	}
	
	/**
	 * Getter del tamaño del ranquing extremo
	 * @return int tamaño del ranquing
	 */
	public static Integer getSizeExtremo() {
		return rankExtremo.size();
	}
		
	/**
	 * Ordena un conjunto de nombre+puntuacion por puntuacion descendente
	 * @param m Conjunto de String,integer
	 * @return Devuelve el conjunto ordenado
	 */
	public static HashMap<String,Integer> sortMapByValue(HashMap<String,Integer> m) {
		HashMap<String, Integer> sorted = new LinkedHashMap<>();
		m.entrySet()
		        .stream()
		        .sorted(Map.Entry.<String, Integer>comparingByValue(Comparator.reverseOrder()))
		        .forEachOrdered(x -> sorted.put(x.getKey(), x.getValue()));
		return sorted;
		
	}
	
	
	/**
	 * Borra del ranquing estandar las puntuaciones hechas por ese usuario
	 * @param name Nombre del usuario
	 */
	public static void deletePartidasEstandarUsuari(String name) {
		if (rankEstandar.containsKey(name)) {
			rankEstandar.remove(name);
		}
	}
	
	/**
	 * Borra del ranquing dificil las puntuaciones hechas por ese usuario
	 * @param name Nombre del usuario
	 */
	public static void deletePartidasDificilUsuari(String name) {
		if (rankDificil.containsKey(name)) {
			rankDificil.remove(name);
		}
	}
	
	/**
	 * Borra del ranquing extremo las puntuaciones hechas por ese usuario
	 * @param name Nombre del usuario
	 */
	public static void deletePartidasExtremoUsuari(String name) {
		if (rankExtremo.containsKey(name)) {
			rankExtremo.remove(name);
		}
	}
	
	/**
	 * Borra de los ranquing las puntuaciones hechas por ese usuario
	 * @param name Nombre del usuario
	 */
	public void deletePartidasRankings(String name) {
		deletePartidasEstandarUsuari(name);
		deletePartidasDificilUsuari(name);
		deletePartidasExtremoUsuari(name);
	}
	
	
	/**
	 * Carga los ranquings a la capa de persistencia
	 * @throws FileNotFoundException No existe el fichero
	 */
	public void cargarRankingsAlFichero() throws FileNotFoundException {
		cr.crearIndiceRanquingEstandar(rankEstandar);
		cr.crearIndiceRanquingDificil(rankDificil);
		cr.crearIndiceRanquingExtremo(rankExtremo);
	}
	
	
	/**
	 * Descarga de la capa de persistencia los ranquings para inicializarlos en dominio
	 * @throws FileNotFoundException Fichero no encontrado
	 */
	public static void inicializarRankingsFichero() throws FileNotFoundException {
		rankEstandar = cr.inicializarRankEstandar();
		rankDificil = cr.inicializarRankDificil();
		rankExtremo = cr.inicializarRankExtremo();
		
	}
	
	
	/**
	 * Comprueba si ha de añadir la puntuacion al ranquing estandar
	 * @param name Nombre del usuario
	 * @param puntuacio Puntuación de la partida
	 */
	public static void actualizarRankingEstandar(String name,Integer puntuacio) {
		Usuari u = CtrlUsuari.getUsuari(name);
		String key = u.getNom();
		if (rankEstandar.containsKey(key)) {
			Integer oldValue = rankEstandar.get(key);
			if (oldValue < puntuacio) {
				rankEstandar.put(key,puntuacio);
			}
		}
		else {
			rankEstandar.put(key, puntuacio);
		}
		
	}
	
	/**
	 * Comprueba si ha de añadir la puntuacion al ranquing dificil
	 * @param name Nombre del usuario
	 * @param puntuacio Puntuación de la partida
	 */
	public static void actualizarRankingDificil(String name,Integer puntuacio) {
		Usuari u = CtrlUsuari.getUsuari(name);
		String key = u.getNom();
		if (rankDificil.containsKey(key)) {
			Integer oldValue = rankDificil.get(key);
			if (oldValue < puntuacio) {
				rankDificil.put(key,puntuacio);
			}
		}
		else {
			rankDificil.put(key, puntuacio);
		}
		
	}
	
	/**
	 * Comprueba si ha de añadir la puntuacion al ranquing extremo
	 * @param name Nombre del usuario
	 * @param puntuacio Puntuación de la partida
	 */
	public static void actualizarRankingExtremo(String name,Integer puntuacio) {
		Usuari u = CtrlUsuari.getUsuari(name);
		String key = u.getNom();
		if (rankExtremo.containsKey(key)) {
			Integer oldValue = rankExtremo.get(key);
			if (oldValue < puntuacio) {
				rankExtremo.put(key,puntuacio);
			}
		}
		else {
			rankExtremo.put(key, puntuacio);
		}
		
	}
	
	


}
