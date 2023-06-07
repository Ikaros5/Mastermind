package Domini;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Clase hija de Maquina, que implementa el solver con el algoritmo Five Guess
 */

public class FiveGuessSolver extends Maquina{
	
	/**
	 * Constructora por defecto
	 * @param nb_colors Número de colores
	 * @param nb_huecos Número de huecos
	 */
	public FiveGuessSolver(int nb_colors, int nb_huecos) {
		super(nb_colors, nb_huecos);
	}
	
	/**
	 * Algoritmo del Five Guess
	 * @param solution Solution que debe resolver la maquina
	 * @return Devuelve una matriz que representa cada uno de los intentos
	 */
	public List<List<Integer>> solveFiveGuess(List<Integer> solution) {
        List<List<Integer>> guesses = new ArrayList<>();
        List<Integer> possibleSolution = new ArrayList<>();
        round++;
        for(int i = 0; i < maxSteps; i++) {
            if (round == 1) {
                possibleSolution = Arrays.asList(0, 0, 1, 1);
                guesses.add(possibleSolution);
                int evaluation = evaluate(possibleSolution, solution);
                remove(possibleSolution, evaluation);
                if(possibleSolution.equals(solution))return guesses;
            }
            
            List<List<Integer>> options = new LinkedList<>();
            int minimax= 1297;
            for (List<Integer> s : allCombinations) {
                int maxScore = 0;
                for (int r : possibleEvaluations) {
                    int currScore = 0;
                    for (List<Integer> ans : remaining) {
                        if (evaluate(ans, s) == r) {
                            currScore++ ;
                        }
                    }
                    maxScore= Math.max(maxScore, currScore);
                }
                if (maxScore < minimax) {
                    options.clear();
                    minimax= maxScore;
                }
                if (maxScore == minimax) {
                    options.add(s);
                }
    
            }
            options.sort((a, b) -> {
            for (int j = 0; j < a.size(); j++) {
                if (a.get(j) < b.get(j)) {
                    return -1;
                } else if (a.get(j) > b.get(j)) {
                    return 1;
                }
            }
            return 0;
            });
        
            boolean found = false;
            int j = 0;
            while (!found && j < options.size()) {
                if (remaining.contains(options.get(j)) && !guesses.contains(options.get(j))) {
                    found = true;
                    possibleSolution = options.get(j);                    
                    guesses.add(possibleSolution);
                    int evaluation = evaluate(possibleSolution, solution);
                    remove(possibleSolution, evaluation);
                }
                j++ ;
            }
            if (!found) {
                possibleSolution = options.get(0);
                guesses.add(possibleSolution);
                int evaluation = evaluate(possibleSolution, solution);
                remove(possibleSolution, evaluation);
            }
            round++;
            if(possibleSolution.equals(solution)) {
                return guesses;
            }
        }  
        return guesses;
    }

}
