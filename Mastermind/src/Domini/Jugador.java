package Domini;

/**
 * Clase abstracta de la cual heredarán las clases Maquina y Usuario
 */
public abstract class Jugador {
	protected String username; 
	
	/**
	 * Creadora por defecto
	 */
	public Jugador() {}
	
	/**
	 * Constructora con parametro
	 * @param username Nombre del Jugador
	 */
    public Jugador(String username) {
        this.username = username;
    }
    
    /**
     * Getter del nombre del Jugador
     * @return String username del Jugador
     */
    public String getNom() {
        return this.username;
    }
    
    /**
     * Setter del nombre del Jugador
     * @param name nombre del Jugador
     */
    public void setNom(String name) {
        this.username = name;
    }
}
