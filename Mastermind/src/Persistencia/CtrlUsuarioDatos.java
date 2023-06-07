package Persistencia;
import Domini.*;
import java.util.*;
import java.io.*;

/**
 * Controlador de Persistencia para los usuarios, se encarga de pasar de la base de datos a los controladores de la capa de dominio y viceversa
 */
public class CtrlUsuarioDatos {
	private final String index_path;
	
	/**
	 * Constructora por defecto,  inicializa los index_path con los nombres que queremos para nuestro archivo
	 */
	public CtrlUsuarioDatos() {
		this.index_path = "./UsuariosLogeados.txt";
	}
	
	/**
     * Comprueba si ya existe un archivo con el nombre index_path
     * @return boolean, true si existe, false si no
     */
    public boolean existeIndice() {
        File index = new File(index_path);
        return index.exists();
    }
    
    /**
     * Escribe en la base de datos todos los datos necesarios de los usuarios
     * @param usuarios Conjuto de los usuarios del sistema
     * @throws FileNotFoundException No existe el fichero
     */
    public void crearIndiceUsuarios(HashMap<String,Usuari> usuarios) throws FileNotFoundException {
    	PrintWriter indexWriter = new PrintWriter(index_path);
        indexWriter.print(""); //borrar contenido del índice antiguo.
        for (String nombre : usuarios.keySet()) {
            indexWriter.write(nombre); //Nombre usuario
            indexWriter.write("\n");
            indexWriter.write(usuarios.get(nombre).getPassword() + "\n"); //Contraseña
        }
        indexWriter.close();
    	
    }
    
    /**
     * Carga los usuarios desde la base de datos, y deja todo listo para que funcione
     * @return Conjunto de los usuarios que se han cargado desde la base de datos
     * @throws FileNotFoundException No existe el fichero
     */
    public HashMap<String,Usuari> inicializarUsuarios() throws FileNotFoundException {
        HashMap<String,Usuari> result = new HashMap<>();
        File index = new File(index_path);
        Scanner reader = new Scanner(index);
        while (reader.hasNext()) {
        	String nombre = reader.nextLine();
        	String password = reader.nextLine();
        	Usuari u = new Usuari(nombre,password);
        	result.put(nombre,u);
        }
        return result;
    }

}

