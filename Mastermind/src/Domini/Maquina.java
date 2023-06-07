package Domini;
import java.util.*;

/**
 * Clase hija de Jugador, que representa una Máquina que se asocia a una Partida
 */

 public class Maquina extends Jugador implements IMaquina {
    protected List<Integer> possibleSolution = null;
    protected List<List<Integer>> allCombinations = new ArrayList<>();
    protected List<List<Integer>> remaining = new ArrayList<>();
    protected List<Integer> possibleEvaluations = Arrays.asList(0, 1, 2, 3, 4, 10, 11, 12, 13, 20, 21, 22, 30, 40);
    protected int dificultad = 0;
    protected int maxSteps = 10;
    protected int round = 0;
    protected int type;
    protected int nb_colors;
    protected int nb_huecos;

    /**
     * Constructora de la Máquina
     * @param nb_colors Número de colores
     * @param nb_huecos Número de huecos
     */
    public Maquina(int nb_colors, int nb_huecos) {
        super();
        this.nb_colors = nb_colors;
        this.nb_huecos = nb_huecos;                
        this.type = 10 * nb_colors + nb_huecos;
        
        if(nb_huecos == 4) {
        	for(int a = 0; a < nb_colors; a++) {
                for(int b = 0; b < nb_colors; b++) {
                    for(int c = 0; c < nb_colors; c++) {
                        for(int d = 0; d < nb_colors; d++) {
                            List<Integer> code = new ArrayList<>();
                            code.add(a);
                            code.add(b);
                            code.add(c);
                            code.add(d);
                            allCombinations.add(code);
                        }
                    }
                }
            }   
        }
        else if(nb_huecos == 6) {
            for(int a = 0; a < nb_colors; a++) {
                for(int b = 0; b < nb_colors; b++) {
                    for(int c = 0; c < nb_colors; c++) {
                        for(int d = 0; d < nb_colors; d++) {
                            for(int e = 0; e < nb_colors; e++) {
                                for(int f = 0; f < nb_colors; f++) {
                                    List<Integer> code = new ArrayList<>();
                                    code.add(a);
                                    code.add(b);
                                    code.add(c);
                                    code.add(d);
                                    code.add(e);
                                    code.add(f);
                                    allCombinations.add(code);
                                }
                            }
                        }
                    }
                }
            }
        }
        remaining = new ArrayList<>(allCombinations);
    }
    
    /**
     * Resetea las variables correspondientes a default
     */
    public void resetVariables() {
        round = 0;
        maxSteps = 10;
        possibleSolution = null;
        remaining = new ArrayList<>(allCombinations);
    }
    
    /**
     * Setter de la dificultad
     * @param d Entero entre 0 y 2
     */
    public void setDificultad(int d) {
        this.dificultad = d;    
    }
    
    
    /**
     * Setter de maxSteps
     * @param m MaxSteps
     */
    public void setMaxSteps(int m) {
        this.maxSteps = m;    
    }
    
    
    /**
     * Getter de la ronda
     * @return Entero que representa la ronda
     */
    public int getRound() {
        return round;
    }
    
    
    /**
     * Getter de maxSteps
     * @return Entero que representa maxSteps
     */
    public int getMaxSteps() {
        return maxSteps;
    }
    
    
    /**
     * Getter de remaining
     * @return Devuelve una lista de enteros que representa remaining
     */
    public List<Integer> getRemaining() {
        return remaining.get(0);
    }
    
    
    /**
     * Getter del tamaño de remaining
     * @return Devuelve el tamño de remaining
     */
    public int getRemaining_nb() {
        return remaining.size();
    }
    
    
    /**
     * Resuelve el código secreto con el algoritmo correspondiente
     * @param solution Solución que debe resolver la máquina
     * @return Devuelve una matriz de enteros que representa todos los intentos
     */
	public List<List<Integer>> solve(List<Integer> solution) {
	    resetVariables();
        if(dificultad == 0) {
        	StaticSolver ss = new StaticSolver(nb_colors, nb_huecos);
        	return ss.solveStatic(solution);
        }
        else if(dificultad == 1) {
        	FiveGuessSolver fgs = new FiveGuessSolver(nb_colors, nb_huecos);
        	return fgs.solveFiveGuess(solution);
        }
        else {
        	GeneticSolver gs = new GeneticSolver(nb_colors, nb_huecos);
        	return gs.solveGenetic(solution);
        }
	}
	
	
	/**
	 * Dada un intento y su resultado, quita todas las combinaciones de la lista de las posibles soluciones
	 * @param guess Intento
	 * @param result Resultado del intento
	 */
    public void remove(List<Integer> guess, int result) {
        List<List<Integer>> updatedCombinations = new ArrayList<>();
        for (int i = 0; i < remaining.size(); i++) {
            List<Integer> code = remaining.get(i);
            
            if(evaluate(guess, code) == result) {
                updatedCombinations.add(code);
            }
        }
        remaining = updatedCombinations;
    }
  
   /**
    * Evalua el intento del usuario con la solución del código
    * @param guess Intento del usuario
    * @param solution Solución 
    * @return Devuelve la evaluación del código
    */
  //Given a Guess and the solution it returns the number of exact matches (red) and partial matches (white)
  public int evaluate(List<Integer> guess, List<Integer> solution) {
    int red = 0, white = 0;
    int[] guessCount = new int[this.nb_colors], solCount = new int[this.nb_colors];
    for (int i = 0; i < this.nb_huecos; i++) {
        if(guess.get(i).equals(solution.get(i))) red++;
        else { 
            guessCount[guess.get(i)]++; 
            solCount[solution.get(i)]++;
        }
    }
    for (int j = 0; j < this.nb_colors; j++) white += Math.min(guessCount[j], solCount[j]);
    return 10 * red + white;
    }
}
