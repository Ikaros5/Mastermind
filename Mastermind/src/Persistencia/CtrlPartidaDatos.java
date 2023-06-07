package Persistencia;
import Domini.*;
import java.util.*;
import java.io.*;

/**
 * Controlador de Persistencia para las partidas, se encarga de pasar de la base de datos al controlador de la capa de dominio y viceversa
 */
public class CtrlPartidaDatos {
    private final String index_path;

    /**
     * Constructora por defecto, inicializa el index_path con el nombre que queremos para nuestro archivo
     */
    public CtrlPartidaDatos() {
        this.index_path = "PartidasGuardadas.txt";
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
     * Escribe en la base de datos todos los datos necesarios de las partidas
     * @param partidas Conjunto de partidas del sistema
     * @throws FileNotFoundException No existe el fichero
     */
    public void crearIndicePartidas(HashSet<Partida> partidas) throws FileNotFoundException {
        PrintWriter indexWriter = new PrintWriter(index_path);
        indexWriter.print(""); //borrar contenido del índice antiguo.
        for (Partida p : partidas) {
        	indexWriter.write(Integer.toString(p.getId()) + "\n");
        	indexWriter.write(Integer.toString(p.getDificultad()) + "\n");
        	indexWriter.write(p.getRol() + "\n");
        	indexWriter.write(Integer.toString(p.getNumRonda()) + "\n");
        	indexWriter.write(Integer.toString(p.getPuntuacion()) + "\n");
        	if (p.acabada()) {
        		indexWriter.write("1" + "\n");
        	}
        	else {
        		indexWriter.write("0" + "\n");
        	}
        	
        	if (p.getGuardada()) {
        		indexWriter.write("1" + "\n");
        	}
        	else {
        		indexWriter.write("0" + "\n");
        	}
        	indexWriter.write(((Jugador) p.getUsuariPartida()).getNom() + "\n");
        }
        indexWriter.close();
    }

    /**
     * Carga las partidas desde la base de datos, y deja todo listo para que funcione
     * @return HashSet de partidas conjunto de partidas que se han cargado desde la base de datos y que se mandan a la capa de dominio
     * @throws FileNotFoundException No existe el fichero
     */
    public HashSet<Partida> inicializarPartidas() throws FileNotFoundException {
        HashSet<Partida> result = new HashSet<>();
        File index = new File(index_path);
        Scanner reader = new Scanner(index);
        while (reader.hasNext()) {
            int id = Integer.parseInt(reader.nextLine());
            int dificultad = Integer.parseInt(reader.nextLine());
            String rol = reader.nextLine();
            int numRonda = Integer.parseInt(reader.nextLine());
            int puntuacion = Integer.parseInt(reader.nextLine());
            boolean state = reader.nextLine().equals("1");
            boolean guardada = reader.nextLine().equals("1");
            String username = reader.nextLine();

            Usuari player = CtrlUsuari.getUsuari(username);
            
            Partida p = new Partida(id);
            p.cargarPartida(dificultad, player, rol, numRonda, puntuacion, state, guardada);
            player.introducirPartida(p);
            if (p.acabada()) player.actualizarRecord(p);
            
            
            result.add(p);
        }
        return result;
    }
}
