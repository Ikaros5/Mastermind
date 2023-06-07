package Domini;
import java.util.*;

/**
 * Clase hija de Jugador, e implementa todo lo que tiene que ver con un Usuario que tiene un perfil y juega partidas
 */

public class Usuari extends Jugador {
	private String password;
	private int recordEstandar;
	private int recordDificil;
	private int recordExtremo;
    private Set<Partida> historial;
    private Set<Partida> notFinished;
    
    /**
     * Creadora por defecto
     */
    public Usuari() {}
	
    /**
     * Creadora
     * @param username Nombre del usuario
     * @param password Contraseña del usuario
     */
    public Usuari(String username, String password) {
        super(username);
        this.password = password;
        this.recordEstandar = -1; 
        this.recordDificil = -1; 
        this.recordExtremo = -1; 
        this.historial = new HashSet<Partida>();
        this.notFinished = new HashSet<Partida>();
    }
	
    /**
     * Getter de la contraseña
     * @return String contraseña del usuario
     */
    public String getPassword() {
        return this.password;
    }
    
    /**
     * Setter de la contraseña
     * @param pwd Contraseña a asignar
     */
    public void setPassword(String pwd) {
        this.password = pwd;
    }
    
    
    /**
     * Se añade al historial una Partida
     * @param partida de la Clase Partida
     */
    public void actualizarHistorial(Partida partida) {
        this.historial.add(partida);
    }
    
    
    /**
     * Se actualiza el record dependiendo la partida
     * @param partida de la clase Partida
     */
    public void actualizarRecord(Partida partida) {
        Integer modo = partida.getDificultad();

        // Dependiendo del modo de juego, actualizamos el récord correspondiente
        if (modo == 0) {
        	if (recordEstandar != -1) { 
        		if (partida.getPuntuacion() >= recordEstandar) {
        			recordEstandar = partida.getPuntuacion();
        		}
        	}
        	else {
        		recordEstandar = partida.getPuntuacion();
        	}
        	
        } else if (modo == 1) {
        	if (recordDificil != -1) { 
        		if (partida.getPuntuacion() >= recordDificil) {
        			recordDificil = partida.getPuntuacion();
        		}
        	}
        	else {
        		recordDificil = partida.getPuntuacion();
        	}
        	
        } else if (modo == 2) {
        	if (recordExtremo != -1) { 
        		if (partida.getPuntuacion() >= recordExtremo) {
        			recordExtremo = partida.getPuntuacion();
        		}
        	}
        	else {
        		recordExtremo = partida.getPuntuacion();
        	}
        }
    }

    /**
     * Getter del historial
     * @return Set de Partidas que es el historial de partidas del jugador
     */
    public Set<Partida> getHistorial() {
        return this.historial;
    }
    
    /**
     * Getter del record estandar del usuario
     * @return int Puntuación del usuario en la dificultad estándar
     */
    public int getRecordEstandar() {
        return this.recordEstandar;
    }
    
    /**
     * Getter del record dificil del usuario
     * @return int Puntuación del usuario en la dificultad dificil
     */   
    public int getRecordDificil() {
    	return this.recordDificil;
    }
    
    /**
     * Getter del record extremo del usuario
     * @return int Puntuación del usuario en la dificultad extremo
     */
    public int getRecordExtremo() {
    	return this.recordExtremo;
    }
    
    /**
     * getter de las partidas no finalizadas del usuario
     * @return Set de Partidas conjunto de partidas no acabadas
     */
    public Set<Partida> getPartidasNotFinished() {
    	return this.notFinished;
    }
    
   
    /**
     * Getter del tamaño del conjunto de partidas no finalizadas
     * @return int tamaño del conjunto
     */
    public Integer getNotFinishedSize() {
    	return this.notFinished.size();
    }
    
    /**
     * Introduce una partida, si está acabada en el historial, sinó a las partidas no finalizadas
     * @param p Partida
     */
    public void introducirPartida(Partida p) {
    	if (p.acabada()) {
    		this.historial.add(p);
    	}
    	else {
    		this.notFinished.add(p);
    	}
    }
    
    /**
     * Actualiza el conjunto de no finalizadas,revisando si alguna partida ya está finalizada para borrarla
     */
    public void actualizarNotFinished() {
    	if (this.notFinished.size() > 0) {
    		for (Partida p : this.notFinished) {
    			if (p.acabada()) {
    				notFinished.remove(p);
    			}
    		}
    	}
    }
}
