package Domini;
import java.util.*;


/**
 * Representació d'una Partida, amb tots els atributs necesaris
 */

public class Partida {
	private int id;										//identificador de la partida
	private ArrayList<ArrayList<Integer>> intentos;			//tablero de juego de dimensiones: num_intentos x 2*long_codigo --> codigo + pins de correcto/malcoloado/mal
	private int long_codigo;							//longitud del codigo
	public static int max_intentos = 10;						//numero de intentos maximos que tiene el codebreaker 
	private int num_intentos;							//numero de intento actual, siempre num_intentos < que max_intentos
	private int puntuacion;								//puntuacion actual de la partida
	private String rol;										//rol que tiene 
	private int num_colores;							//numero de colores con los que se jugara la partida
	private ArrayList<Integer> codigo_secreto;				//codigo secreto de la partida que pone el jugador como CodeMaker
	public static int max_rondas = 2;							//numero maximo de rondas de una partida
	private int num_ronda;								//numero de ronda actual de la partida. cada ronda consta de su parte como CodeMaker y CodeBreaker
	private boolean repetidos;								//booleano que determina si en el codigo hay repetidos o no
	private int dificultad;								//entero que representa la dificultad de la partida: 0-Estandar / 1-Dificil / 2-Extrema
	private Usuari player;
	private Maquina maquina;
	private boolean p; 										//booleano para saber si puedo usar la pista
	private boolean state;                                  //booleano para saber si la partida esta en pausa (false) o acabada (true)
	private boolean guardada;

	
	/**
	 * Constructora por defecto
	 */
	public Partida() {}
	
	/**
	 * Constructora asignando id de la partida
	 * @param id Id de la Partida
	 */
	public Partida(int id) {
		this.id = id;
	}
	
	/**
	 * Setter del id
	 * @param i Id de la partida
	 */
	public void setId(int i) {
		this.id = i;
	}
	
	/**
	 * Carga una partida asignando los atributos correspondientes
	 * @param dificultad Dificultad de la partida
	 * @param player Usuario correspondiente de la Partida
	 * @param rol Rol que ocupa actualmente el usuario
	 * @param numRonda Ronda actual en la que se encuentra la partida
	 * @param puntuacion Puntuación actual de la partida
	 * @param state Indica si esta acabada o no
	 * @param guardada True si está guardada, falso en cas contrario
	 */
	public void cargarPartida(int dificultad, Usuari player, String rol, int numRonda, int puntuacion, boolean state, boolean guardada) {
	    this.dificultad = dificultad;
	    this.player = player;
	    this.rol = rol;
	    this.num_ronda = numRonda;
	    this.state = state;
	    this.guardada = guardada;
	    if (this.dificultad == 0) {
	    	this.long_codigo = 4;
	    	this.num_colores = 4;
	    }
		else if (this.dificultad == 1) {
			this.long_codigo = 4;
	    	this.num_colores = 6;
		}
		else if (this.dificultad == 2) {
			this.long_codigo = 6;
	    	this.num_colores = 8;
		}
	    this.maquina = new Maquina(this.num_colores, this.long_codigo);
		this.maquina.setDificultad(this.dificultad);
		this.puntuacion = puntuacion;
	}
	
	/**
	 * Define una partida
	 * @param dif Dificultad de la Partida
	 * @param us Usuario que juega la partida
	 * @param rol Rol inicial
	 */
	public void definirPartida (int dif, Usuari us, String rol){
		this.id= CtrlPartida.getNumPartidas() + 1;
		this.rol = rol;
		this.player = us;
		this.dificultad = dif;
		this.state = false;
		this.guardada = false;
		if (this.dificultad == 0) iniciarPartidaEstandar();		//igual la dificultad y el rol en vez de strings podrían ser Enums
		else if (this.dificultad == 1) iniciarPartidaDificil();
		else if (this.dificultad == 2) iniciarPartidaExtremo();
		this.maquina = new Maquina(this.num_colores, this.long_codigo);
		this.maquina.setDificultad(this.dificultad);
	}

	/**
	 * Asignación de atributos para crear una partida estándar
	 */
	public void iniciarPartidaEstandar () {			//creadora de la clase para dificultad estandar
		this.long_codigo = 4;
		this.intentos = new ArrayList<ArrayList<Integer>>();
		this.num_intentos = 0;
		this.num_ronda = 0;
		this.repetidos = true;
		this.num_colores = 4;
		this.puntuacion = 0;
	};
	
	/**
	 * Asignación de atributos para crear una partida difícil
	 */
	public void iniciarPartidaDificil () {			//creadora de la clase para dificultad dificil
		this.long_codigo = 4;
		this.intentos = new ArrayList<ArrayList<Integer>>();
		this.num_intentos = 0;
		this.num_ronda = 0;
		this.repetidos = true;
		this.num_colores = 6;
		this.puntuacion = 0;
	}
	
	/**
	 * Asignación de atributos para crear una partida extrema
	 */
	public void iniciarPartidaExtremo () {			//creadora de la clase para dificultad extrema
		this.long_codigo = 6;
		this.intentos = new ArrayList<ArrayList<Integer>>();
		this.num_intentos = 0;
		this.num_ronda = 0;
		this.repetidos = true;
		this.num_colores = 8;
		this.puntuacion = 0;
	}
	
	/**
	 * Getter de la Máquina asignada a la Partida
	 * @return Devuelve la máquina asociada a la partida
	 */
	public Maquina getMaquina() {
		return this.maquina;
	}
	
	/**
	 * Guarda una partida
	 * @param puntuacion Puntuación de la partida en ese momento
	 */
	public void guardarPartida(int puntuacion) {
		this.puntuacion = puntuacion;
		this.guardada = true;
		this.state = false;
		this.num_ronda = 1;
	}	

	/**
	 * Genera un codigo random para adivinar
	 * @param longitud Indica el número de huecos
	 * @param num Indica el número de colores
	 * @return Devuelve un array list de enteros, que indica el código secreto
	 */
	public ArrayList<Integer> generaCodigoRandom(int longitud, int num){
		ArrayList<Integer> lista = new ArrayList<>();
        Random random = new Random();

        for (int i = 0; i < longitud; i++) {
            lista.add(random.nextInt(num));
        }
        //System.out.println(lista);
        return lista;
	}
	
	/**
	 * Calcula la puntuación de un ronda
	 * @param int_maquina Intentos de la máquina
	 * @param int_jugador Intentos del Usuario
	 * @return Devuelve 25 en caso de empate, 50 en caso de que gane el usuario, y en caso de que pierda devuelve 0
	 */
	public int calcularPuntuacionRonda(int int_maquina, int int_jugador) {
		if (int_maquina > int_jugador) return 50; //ganas
		if (int_maquina < int_jugador) return 0; //pierdes
		return 25; //empatas
	}
	
	/**
	 * Cambia el rol que ocupa el Usuario
	 */
	public void cambioRol() {
		if (this.rol.equals("CodeBreaker") )
		{
			this.rol = "CodeMaker";
		} else {
			this.rol = "CodeBreaker";
		}
	}
	
	/**
	 * Setter del codigo secreto
	 * @param arrayList Representación del codigo secreto como un ArrayList de enteros
	 */
	public void setCodigoSecreto (ArrayList<Integer> arrayList) {
		this.codigo_secreto = arrayList;		//funcion en alguna clase (usuario/maquina) que devuelve un codigo que se usa como secreto
	}
		
	/**
	 * Getter del id
	 * @return Devuelve el id de la Partida
	 */
	public int getId() {
		return this.id;
	}
	
	/**
	 * Getter de la longitud del código
	 * @return Devuelve la longitud del código
	 */
	public int getLongCodigo() {
		return this.long_codigo;
	}
	
	/**
	 * Getter de la puntuación
	 * @return Devuelve la puntuación de la Partida
	 */
	public int getPuntuacion () {
		return this.puntuacion;
	}
	
	
	/**
	 * Getter del usuario de la Partida
	 * @return Devuelve el Usuario asignado a la Partida
	 */
	public Usuari getUsuariPartida () {
		return this.player;
	}
	
	
	/**
	 * Getter de la ronda actual de la Partida
	 * @return Devuelve la ronda en la que se encuentra la Partida
	 */
	public int getRondaActual () {
		return this.num_ronda;
	}
	
	/**
	 * Getter del atributo numColores
	 * @return Devuelve el número de colores que tiene disponible 
	 */
	public int getNumColores() {
		return this.num_colores;
	}

	/**
	 * Getter del número de intentos
	 * @return Devuelve el número de intentos
	 */
	public int getNumIntentos () {
		return this.num_intentos;
	}
	
	/**
	 * Getter de la dificultad de la Partida
	 * @return Devuelve 0, 1 o 2(Estándar, dificil, extremo)
	 */
	public int getDificultad () {
		return this.dificultad;
	}
	

	/**
	 * Getter del rol
	 * @return Devuelve un String que indica el tipo de rol
	 */
	public String getRol() {	
		return this.rol;
	}
	
	/**
	 * Getter del código secreto
	 * @return Devuelve un ArrayList de enteros que representa los colores del código secreto
	 */
	public ArrayList<Integer> getCodeRandom() {
		return this.codigo_secreto;
	}

	/**
	 * Setter de num_intentos
	 * @param max_intentos2 Intentos a asignar al atributo
	 */
	public void setNumIntentos(int max_intentos2) {
		this.num_intentos = max_intentos;
		
	}

	/**
	 * Setter del numero de ronda
	 * @param max_rondas2 Ronda a asignar al atributo 
	 */
	public void setNumRonda(int max_rondas2) {
		this.num_ronda = max_rondas2;
		
	}

	/**
	 * Setter de la puntuación
	 * @param i Parámetro a asignar al id
	 */
	public void setPuntuacion(int i) {
		this.puntuacion = i;
	}
	
	/**
	 * Actualiza la puntuación
	 * @param i Cantidad sumar a la puntuación
	 */
	public void actualizarPuntuacion(int i) {
		this.puntuacion += i;
	}
	
	/**
	 * Da una pista al usuario cuando es codeBreaker
	 * @return Devuelve la posicion donde se encuentra un color
	 */
	public int pista() {
		Random random = new Random();
		int posicion = random.nextInt(this.long_codigo);
		return posicion;
	}

	/**
	 * Getter del atributo state
	 * @return True si está acabada, false caso contrario
	 */
	public boolean acabada () {
		return state;
	}
	
	/**
	 * Setter del atributo state
	 * @param x Valor para settear el state
	 */
	public void setState(boolean x) {
		this.state = x;
		this.num_ronda = max_rondas;
	}

	/**
	 * Getter del atributo ronda
	 * @return Devuelve la ronda
	 */
	public int getNumRonda() {
		return this.num_ronda;
	}

	
	/**
	 * Getter del atributo guardada
	 * @return True si está guardado, false caso contrario
	 */
	public boolean getGuardada() {
		return guardada;
	}
	
	/**
	 * Calcula la cantidad de rojas del código introducido por el usuario
	 * @param code Intento del usuario
	 * @return Devuelve un entero representando las rojas
	 */
	public int getRojas(List<Integer> code) {
		return this.maquina.evaluate(code, codigo_secreto) / 10;
	}
	
	
	/**
	 * Calcula la cantidad de blancas del código introducido por el usuario
	 * @param code Intento del usuario
	 * @return Devuelve un entero representando las blancas
	 */
	public int getBlancas(List<Integer> code) {
		return this.maquina.evaluate(code, codigo_secreto) % 10;
	}
	
	/**
	 * Calcula la cantidad de negras del código introducido por el usuario
	 * @param code Intento del usuario
	 * @return Devuelve un entero representando las negras
	 */
	public int getNegras(List<Integer> code) {
		return long_codigo - (getBlancas(code) + getRojas(code));
	}
	
}



