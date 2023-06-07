package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Clase hija de Maquina, que implementa la función de Static Solver
 */
public class StaticSolver extends Maquina{
	
	/**
	 * Creadora por defecto
	 * @param colors Número de colores
	 * @param huecos Número de huecos
	 */
	public StaticSolver(int colors, int huecos) {
		super(colors, huecos);
	}
	
	/**
	 * Dado un codigo secreto, resuelve el codigo con este algoritmo
	 * @param solution Codigo secreto introducido por el usuario
	 * @return devuelve una matriz de enteros que son todos los intentos de la máquina hasta llegar a la solución
	 */
	public List<List<Integer>> solveStatic(List<Integer> solution) {
        List<List<Integer>> guesses = new ArrayList<>();
        
        if(type == 44) {
	        List<List<Integer>> staticGuesses44 = Arrays.asList(
	                Arrays.asList(1, 2, 2, 1),
	                Arrays.asList(3, 3, 1, 1),
	                Arrays.asList(0, 0, 2, 1),
	                Arrays.asList(2, 3, 3, 0)
	        );
	        for(int i = 0; i < maxSteps; i++) {
	            possibleSolution = round < staticGuesses44.size() ? staticGuesses44.get(round) : remaining.get(0);
        		if(finish_guess(guesses, solution)) return guesses; 
	        }
        }
        else if(type == 64) {
        	List<List<Integer>> staticGuesses64 = Arrays.asList(
        			Arrays.asList(1, 2, 2, 1),
        			Arrays.asList(2, 3, 5, 4),
        			Arrays.asList(3, 3, 1, 1),
        			Arrays.asList(4, 5, 2, 4),
        			Arrays.asList(5, 0, 5, 0),
        			Arrays.asList(0, 0, 4, 3)
        			);
        	for(int i = 0; i < maxSteps; i++) {
        		possibleSolution = round < staticGuesses64.size() ? staticGuesses64.get(round) : remaining.get(0);
        		if(finish_guess(guesses, solution)) return guesses; 
        	}  	
        }
        return guesses;
    }
	
	/**
	 * 
	 * @param guesses Matriz de enteros que representa los intentos
	 * @param solution Código a resolver
	 * @return True si acierta, false caso contrario
	 */
	public boolean finish_guess(List<List<Integer>> guesses, List<Integer> solution) {
		guesses.add(possibleSolution);
		int evaluation = evaluate(possibleSolution, solution);
		remove(possibleSolution, evaluation);
		round++;
		if(possibleSolution.equals(solution)) {
			return true;
		}
		return false;
	}
}
