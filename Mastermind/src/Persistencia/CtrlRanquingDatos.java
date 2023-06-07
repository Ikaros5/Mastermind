package Persistencia;
import Domini.*;
import java.io.*;
import java.util.*;

/**
 * Controlador de Persistencia para los rankings, se encarga de pasar de la base de datos a los controladores de la capa de dominio y viceversa
 */
public class CtrlRanquingDatos {
	private final String index_path0;
	private final String index_path1;
	private final String index_path2;
	
	/**
	 * Constructora por defecto, inicializa los index_path con los nombres que queremos para nuestros archivos
	 */
	public CtrlRanquingDatos() {
		this.index_path0 = "RanquingEstandar.txt";
		this.index_path1 = "RanquingDificil.txt";
		this.index_path2 = "RanquingExtremo.txt";
		
	}
	
	/**
	 * Escribe en la base de datos todos los datos necesarios del ranking estándar
	 * @param rank Ranking de dificultad estándar
	 * @throws FileNotFoundException No existe el fichero
	 */
	public void crearIndiceRanquingEstandar(HashMap<String,Integer> rank) throws FileNotFoundException {
		PrintWriter indexWriter = new PrintWriter(index_path0);
		indexWriter.print(""); // borra el contenido del indice
		for (String user : rank.keySet()) {
			indexWriter.write(user);
			indexWriter.write("\n");
			indexWriter.print(rank.get(user));
			indexWriter.print("\n");
		}
		indexWriter.close();
	}
	
	/**
	 * Escribe en la base de datos todos los datos necesarios del ranking difícil
	 * @param rank Ranking de dificultad difícil
	 * @throws FileNotFoundException Fichero no encontrado
	 */
	public void crearIndiceRanquingDificil(HashMap<String,Integer> rank) throws FileNotFoundException {
		PrintWriter indexWriter = new PrintWriter(index_path1);
		indexWriter.print(""); // borra el contenido del indice
		for (String user : rank.keySet()) {
			indexWriter.write(user);
			indexWriter.write("\n");
			indexWriter.print(rank.get(user));
			indexWriter.print("\n");
		}
		indexWriter.close();
	}
	
	/**
	 * Escribe en la base de datos todos los datos necesarios del ranking extremo
	 * @param rank Ranking de dificultad extrema
	 * @throws FileNotFoundException No existe el fichero
	 */
	public void crearIndiceRanquingExtremo(HashMap<String,Integer> rank) throws FileNotFoundException {
		PrintWriter indexWriter = new PrintWriter(index_path2);
		indexWriter.print(""); // borra el contenido del indice
		for (String user : rank.keySet()) {
			indexWriter.write(user);
			indexWriter.write("\n");
			indexWriter.print(rank.get(user));
			indexWriter.print("\n");
		}
		indexWriter.close();
	}
	
	/**
	 * Carga el ranking estándar desde la base de datos, y deja todo listo para que funcione
	 * @return Ranking estándar con los datos cargados desde la base de datos
	 * @throws FileNotFoundException No existe el fichero
	 */
    public HashMap<String,Integer> inicializarRankEstandar() throws FileNotFoundException {
        HashMap<String,Integer> result = new HashMap<>();
        File index = new File(index_path0);
        Scanner reader = new Scanner(index);
        while (reader.hasNext()) {
        	String nombre = reader.nextLine();
        	String punt = reader.nextLine();
        	Integer puntuacion = Integer.parseInt(punt);
        	result.put(nombre,puntuacion);
        }
        return result;
    }
    
    /**
	 * Carga el ranking difícil desde la base de datos, y deja todo listo para que funcione
	 * @return Ranking difícil con los datos cargados desde la base de datos
	 * @throws FileNotFoundException No existe el fichero
	 */
    public HashMap<String,Integer> inicializarRankDificil() throws FileNotFoundException {
        HashMap<String,Integer> result = new HashMap<>();
        File index = new File(index_path1);
        Scanner reader = new Scanner(index);
        while (reader.hasNext()) {
        	String nombre = reader.nextLine();
        	String punt = reader.nextLine();
        	Integer puntuacion = Integer.parseInt(punt);
        	result.put(nombre,puntuacion);
        }
        return result;
    }
    
    /**
	 * Carga el ranking extremo desde la base de datos, y deja todo listo para que funcione
	 * @return Ranking extremo con los datos cargados desde la base de datos
	 * @throws FileNotFoundException No existe el fichero
	 */
    public HashMap<String,Integer> inicializarRankExtremo() throws FileNotFoundException {
        HashMap<String,Integer> result = new HashMap<>();
        File index = new File(index_path2);
        Scanner reader = new Scanner(index);
        while (reader.hasNext()) {
        	String nombre = reader.nextLine();
        	String punt = reader.nextLine();
        	Integer puntuacion = Integer.parseInt(punt);
        	result.put(nombre,puntuacion);
        }
        return result;
    }
}
