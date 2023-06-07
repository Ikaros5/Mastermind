package Domini;

import java.util.*;
/**
 * Clase hija de máquina que se crea únicamente para implementar el solve con el algoritmo Genetic
 */

public class GeneticSolver extends Maquina {

    //Size of the population within a generation.
    private final int POPULATION_SIZE = 150;

    //Number of generations
    private final int MAXGEN = 100;

    //Max amount of possible codes.
    private final int POSSIBLE_CODES_MAX = 1;
    
    private List<List<Integer>> population = new ArrayList<>();
    private List<List<Integer>> possibleCodes = new ArrayList<>();
    private List<Integer> fitness = new ArrayList<>();
    private List<Integer> reds = new ArrayList<>();
    private List<Integer> whites = new ArrayList<>();
    private List<List<Integer>> guesses = new ArrayList<>();
	private Random random = new Random();
    private int parentPos = 0;

    /**
     * Constructora por defecto 
     * @param colors Número de colores
     * @param huecos Número de huecos
     */
    public GeneticSolver(int colors, int huecos) {
    	super(colors, huecos);
    	List<Integer> code = Arrays.asList(0,0,0,0);
        possibleCodes.add(code);
    	
        for(int i = 0; i < this.maxSteps; i++) {
        	whites.add(0);
        	reds.add(0);
    	}
        for(int i = 0; i < POPULATION_SIZE; i++) {
        	fitness.add(0);
        	population.add(code);
        }
    }

    /**
     * Implementación del algoritmo genetic
     * @param solution Solución del turno que debe resolver la máquina
     * @return Devuelve una matriz de enteros que representa todos los intentos de la máquina hasta llegar a la solución
     */
    public List<List<Integer>> solveGenetic(List<Integer> solution) {
        for(int i = 0; i < this.maxSteps; i++) {
            possibleSolution = generateGuess();
            guesses.add(possibleSolution);
            if(possibleSolution.equals(solution)) {
            	return guesses;
            }
            
            int eval = evaluate(possibleSolution, solution);
            reds.set(i, eval / 10);
            whites.set(i, eval % 10);
            this.round++;
        }
        return guesses;
    }
    
    /**
     * Crea nuevas generaciones hasta que se han encontrado suficientes codigos elegibles
     * @return Devuelve una lista de enteros que representa nuevas generaciones
     */
    public List<Integer> generateGuess() {
        List<Integer> guesss = new ArrayList<>();
        boolean finished;
        possibleCodes.clear();
               
        //First guess is random
        if (this.round == 0) {
        	return generateRandomCode();
        }
        
        while(possibleCodes.isEmpty()) {
            int nb_generation = 0;
            finished = false;
            
            //Generate random population
            initPopulation();

            //Calculate the fitness of each code with the following formula: 2*Exact_matches + 1*Partial_matches
            calcFitness();

            //Sort population by fitness score
            sortByFitness(fitness, population);

            while(!finished && nb_generation <= MAXGEN && possibleCodes.size() <= POSSIBLE_CODES_MAX) {
                parentPos = 0;
                //Update the population
                evolvePopulation();

                //Calculate the fitness score of each code
                calcFitness();

                //Sort by fitness score
                sortByFitness(fitness, population);
                
                finished = updatePossibleCodes();

                nb_generation++;
            }
        }
        
        //Choose a guess from the possibleCodes.
        List<Integer> guess = possibleCodes.get(random.nextInt(possibleCodes.size()));
        return guess;
    }
    
    
    /**
     * True si todos los guesses tienen exactamente la misma evaluacion que la solucion, false en caso contrario
     */
    private boolean updatePossibleCodes() {
    	boolean finished;
    	for(int i = 0; i < population.size(); i++) {
    		finished = false;
    		for(int j = 0; j < guesses.size() && !finished; j++) {
    			int eval = evaluate(population.get(i), guesses.get(j));
                if (eval / 10 != reds.get(j) || eval % 10 != whites.get(j)) finished = true;
    		}
    		
			if (!finished && possibleCodes.size() < POSSIBLE_CODES_MAX) {				
			    if (!possibleCodes.contains(population.get(i)) && !guesses.contains(population.get(i))) {
			        possibleCodes.add(population.get(i));
			        if (possibleCodes.size() < POSSIBLE_CODES_MAX) return false;
			    }
			} 
			else return false;
	    }
    	return true;    		
	}
    
    /**
     * Para todo los codigos de la poblacion, calcula su salud(fitness)
     */
    private void calcFitness() {
    	int xtmp, ytmp;
        for(int i = 0; i < POPULATION_SIZE; i++) {
        	xtmp = 0;
        	ytmp = 0;
        	for (int j = 0; j < guesses.size(); j++) {
                int eval = evaluate(population.get(i), guesses.get(j));
                xtmp += Math.abs(eval/10 - reds.get(j)); //+width * j;
                ytmp += Math.abs(eval%10 - whites.get(j)); //+width * j;
            }
        	fitness.set(i, xtmp + ytmp);
        }
    }

    //Evolve the population using crossover, mutation, permutation and inversion.
    // 0.5 probability for both crossover1 and crossover2
    // 0.03 chance for mutation
    // 0.03 chance for permutation
    // 0.02 chance for inversion
    
    /**
     * Evoluciona la población usando crossover, mutation, permutacion y inversión.
     */
    private void evolvePopulation() {
        List<List<Integer>> updatedPopulation = new ArrayList<>(POPULATION_SIZE);
        updatedPopulation = population;
        
        for (int i = 0; i < POPULATION_SIZE; i += 2) {
            if (random.nextInt(2) == 0) crossover1(updatedPopulation, i, i + 1);
            else crossover2(updatedPopulation, i, i + 1);
        }
        
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if (random.nextInt(100) < 3) mutation(updatedPopulation, i);
            else if (random.nextInt(100) < 3) permutation(updatedPopulation, i);
            else if (random.nextInt(100) < 2) inversion(updatedPopulation, i);
        }

        changeDuplicated(updatedPopulation);
        population = updatedPopulation;
    }

    //Update duplicates
    /**
     * Actualiza los duplicados
     * @param updatedPopulation Población
     */
    private void changeDuplicated(List<List<Integer>> updatedPopulation) {
        for (int i = 0; i < POPULATION_SIZE; i++) {
            if(population.get(i).equals(updatedPopulation.get(i))) updatedPopulation.set(i, generateRandomCode());
        }
    }

    
    /**
     * Cambia los colores de dos posiciones aleatorias
     * @param updatedPopulation Población
     * @param pos Posición
     */
    private void permutation(List<List<Integer>> updatedPopulation, int pos) {
    	int pos1 = this.random.nextInt(this.nb_huecos);
        int pos2 = this.random.nextInt(this.nb_huecos);
        int tmp = updatedPopulation.get(pos).get(pos1);
        updatedPopulation.get(pos).set(pos1, updatedPopulation.get(pos).get(pos2));
        updatedPopulation.get(pos).set(pos2, tmp);
    }
 
    
    /**
     * Invierte la secuencia de colores entre dos posiciones aleatorias
     * @param updatedPopulation
     * @param pos
     */
    private void inversion(List<List<Integer>> updatedPopulation, int pos) {
        int pos1 = random.nextInt(this.nb_huecos);
        int pos2 = random.nextInt(this.nb_huecos);
        
        //Swap the value to make pos1 the bigger one
        if (pos1 < pos2) swap_values(pos1, pos2);
        
        for (int i = 0; i < (pos2 - pos1)/2; i++) {
            int tmp = updatedPopulation.get(pos).get(pos1 + i);
            updatedPopulation.get(pos).set(pos1 + i, updatedPopulation.get(pos).get(pos2 - i));
            updatedPopulation.get(pos).set(pos2 - i, tmp);
        }
    }

    
    /**
     * Cambio de color de una posición aleatoria con un color aleatorio
     * @param updatedPopulation Población
     * @param pos Posición
     */
    private void mutation(List<List<Integer>> updatedPopulation, int pos) {
        updatedPopulation.get(pos).set(random.nextInt(this.nb_huecos), random.nextInt(this.nb_colors));
    }

    /**
     * Se selecciona un punto al azar. Child 1 usa Parent1 hasta la posición y Parent2 después, y Child2 usa Parent2 hasta la posición y Parent1 después
     * @param updatedPopulation Población
     * @param child1
     * @param child2
     */
    private void crossover1(List<List<Integer>> updatedPopulation, int child1, int child2) {
    	int parent1 = getRandomParent();
        int parent2 = getRandomParent();
        int pos = random.nextInt(this.nb_huecos)+1;

        for (int i = 0; i < this.nb_huecos; i++) {
            if (i <= pos) {
                updatedPopulation.get(child1).set(i, population.get(parent1).get(i));
                updatedPopulation.get(child2).set(i, population.get(parent2).get(i));
            } else {
                updatedPopulation.get(child1).set(i, population.get(parent2).get(i));
                updatedPopulation.get(child2).set(i, population.get(parent1).get(i));
            }
        }
    }

    /**
     * Dos puntos son seleccionados aleatoriamente. Child 1 usa Parent1 para el exterior y Parent2 para el interior, y Child2 a la inversa
     * @param updatedPopulation Población
     * @param child1
     * @param child2
     */
    private void crossover2(List<List<Integer>> updatedPopulation, int child1, int child2) {
    	int parent1 = getRandomParent();
        int parent2 = getRandomParent();
        int pos1 = random.nextInt(this.nb_huecos)+1;
        int pos2 = random.nextInt(this.nb_huecos)+1;
        
        //Swap the value to make pos1 the smaller one
        if (pos1 > pos2) swap_values(pos1, pos2);
        
        for (int i = 0; i < this.nb_huecos; i++) {
            if (i <= pos1 || i > pos2) {
            	List<Integer> code = updatedPopulation.get(child1);
            	int value = population.get(parent1).get(i);
                code.set(i, value);
                updatedPopulation.get(child2).set(i, population.get(parent2).get(i));
            } 
            else {
                updatedPopulation.get(child1).set(i, population.get(parent2).get(i));
                updatedPopulation.get(child2).set(i, population.get(parent1).get(i));
            }
        }
    }

    /**
     * Getter para una buena posición de un padre de la población.
     * @return Devuelve una posición que se encuentra entre la primera quinta parte de la población
     */
    private int getRandomParent() {
        parentPos = random.nextInt(POPULATION_SIZE);
        if (parentPos < POPULATION_SIZE / 5) return parentPos;
        else return 0;
    }

    /**
     * Inicia la población con guesses aleatorios
     */
    private void initPopulation() {
        possibleCodes.clear();
        for(int i = 0; i < POPULATION_SIZE; i++) population.set(i, generateRandomCode());
    }

    /**
     * Genera codigos random de longitud nb_huecos usando como colores [0,nb_colors]
     * @return Devuelve el código como un array de enteros
     */
    public List<Integer> generateRandomCode() {
        List<Integer> code = new ArrayList<>();
        for(int i = 0; i < this.nb_huecos; i++) {
        	int number = random.nextInt(this.nb_colors);
            code.add(number);
        }
        return code;
    }
    
    /**
     * Cambia dos valores
     * @param a
     * @param b
     */
    private void swap_values(int a, int b) {
        int tmp = a;
        a = b;
        b = tmp;
    }
    
//========================================================   QUICKSORT   =================================================================================//

    /**
     * Ordena la población por salud
     * @param fitness Salud
     * @param pop Población
     */
    private void sortByFitness(List<Integer> fitness, List<List<Integer>> pop) {
        sort(fitness, pop, 0, fitness.size() - 1);
    }

    //Helper function for recursive sorting.
    /**
     * Función auxiliar para el ordenamiento recursivo
     * @param fitness Salud
     * @param pop Población
     * @param low
     * @param up
     */
    private void sort(List<Integer> fitness, List<List<Integer>> pop, int low, int up) {
        int p = (low + up) / 2;
        if (up > low) {
            int mid = divide(fitness, pop, low, up, p);
            sort(fitness, pop, low, mid - 1);
            sort(fitness, pop, mid + 1, up);
        }
    }

    /**
     * Función auxiliar para dividir el array
     * @param fitness Salud
     * @param pop Población
     * @param low
     * @param up
     * @param pivot
     * @return Devuelve el indíce por donde se dividió el array
     */
    private int divide(List<Integer> fitness, List<List<Integer>> pop, int low, int up, int pivot) {
        int pn = low;
        int pv = fitness.get(pivot);
        swap_list(fitness, pivot, up);
        swap_matrix(pop, pivot, up);
        for (int i = low; i < up; i++) {
            if (fitness.get(i) <= pv) {
                swap_list(fitness, pn, i);
                swap_matrix(pop, pn++, i);
            }
            swap_list(fitness, up, pn);
            swap_matrix(pop, up, pn);
        }
        return pn;
    }

    /**
     * Función auxliar para cambiar dos elementos de un array de enteros
     * @param fitness Salud
     * @param a
     * @param b
     */
    private void swap_list(List<Integer> fitness, int a, int b) {
        int tmp = fitness.get(a);
        fitness.set(a, fitness.get(b));
        fitness.set(b, tmp);
    }

    /**
     * Función auxiliar para cambiar dos valores de arrays de códigos(matriz de enteros)
     * @param pop Población
     * @param a
     * @param b
     */
    private void swap_matrix(List<List<Integer>> pop, int a, int b) {
        List<Integer> tmp = pop.get(a);
        pop.set(a, pop.get(b));
        pop.set(b, tmp);
    }
}
