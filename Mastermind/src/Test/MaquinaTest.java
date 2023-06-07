package Test;
import static org.junit.Assert.*;

import java.util.*;

import org.junit.*;
import org.junit.Test;
import Domini.*;

public class MaquinaTest {
	@Test
	public void TestCreadora() {
        System.out.println("Test creadora");
        
        //Creadora de la Maquina Estandar (4 colores, 4 huecos)
        Maquina m1 = new Maquina(4, 4);
        assertEquals(256, m1.getRemaining_nb());
        
        //Creadora de la Maquina Dificil (6 colores, 4 huecos)
        Maquina m2 = new Maquina(6, 4);
        assertEquals(1296, m2.getRemaining_nb());
        
        //Creadora de la Maquina Maxima (8 colores, 6 huecos)
        Maquina m3 = new Maquina(8, 6);
        assertEquals(262144, m3.getRemaining_nb());
	}
	
	
    //Highly resource expensive test that may take a couple minutes. It calculates if the solution has 
	//fewer than 5 guesses for every possible combination, and that the average is 5801/1296 as expected 
	//with the Five Guess Algorithm.
	
	@Test
	public void TestSolveFiveGuess() {
		System.out.println("Test solve FiveGuess");
		Maquina m = new Maquina(6, 4);
	    List<List<Integer>> allCombinationsTested = new ArrayList<>();
	    int nb_colors = 6;
	    for(int a = 0; a < nb_colors; a++) {
	    	for(int b = 0; b < nb_colors; b++) {
                for(int c = 0; c < nb_colors; c++) {
                    for(int d = 0; d < nb_colors; d++) {
                        List<Integer> code = new ArrayList<>();
                        code.add(a);
                        code.add(b);
                        code.add(c);
                        code.add(d);
                        allCombinationsTested.add(code);
                    }
                }
            }
        }
	   
        List<List<Integer>> guesses;
        m.setDificultad(1);
        int count = 0;
        int maxCount = 0;
        for(int i = 0; i < 1296; i++) {
            guesses = m.solve(allCombinationsTested.get(i));
            int a = guesses.size();
            maxCount = Math.max(maxCount, a);
            count = count + a;
            if(i % 65 == 0) System.out.println("Test SolveFiveGuess at " + (i/65)*5 + "%");
        }
        System.out.println("Test SolveFiveGuess at 100%");
		assertEquals(5,maxCount);
		assertEquals(5801,count);
	}
		
	//For every possible solutions tests if the solution is the correct one.
	@Test
	public void TestSolveStatic64() {
		System.out.println("Test solveStatic64");
		Maquina m = new Maquina(6, 4);
	    List<List<Integer>> allCombinationsTested = new ArrayList<>();
	    int nb_colors = 6;
	    for(int a = 0; a < nb_colors; a++) {
	    	for(int b = 0; b < nb_colors; b++) {
                for(int c = 0; c < nb_colors; c++) {
                    for(int d = 0; d < nb_colors; d++) {
                        List<Integer> code = new ArrayList<>();
                        code.add(a);
                        code.add(b);
                        code.add(c);
                        code.add(d);
                        allCombinationsTested.add(code);
                    }
                }
            }
        }
        List<List<Integer>> guesses;
        m.setDificultad(0);
        int average = 0;
        for(int i = 0; i < 1296; i++) {
            guesses = m.solve(allCombinationsTested.get(i));
            average += guesses.size();
    		assertEquals(guesses.get(guesses.size()-1), allCombinationsTested.get(i));
        }
	}
	
	@Test
	public void TestSolveStatic44() {
		System.out.println("Test solveStatic44");
		Maquina m = new Maquina(4, 4);
	    
		List<List<Integer>> allCombinationsTested = new ArrayList<>();
	    int nb_colors = 4;
	    for(int a = 0; a < nb_colors; a++) {
	    	for(int b = 0; b < nb_colors; b++) {
                for(int c = 0; c < nb_colors; c++) {
                    for(int d = 0; d < nb_colors; d++) {
                        List<Integer> code = new ArrayList<>();
                        code.add(a);
                        code.add(b);
                        code.add(c);
                        code.add(d);
                        allCombinationsTested.add(code);
                    }
                }
            }
        }
        
	    List<List<Integer>> guesses;
        m.setDificultad(0);
        int average = 0;
        for(int i = 0; i < 256; i++) {
            guesses = m.solve(allCombinationsTested.get(i));
            average += guesses.size();
    		assertEquals(guesses.get(guesses.size()-1), allCombinationsTested.get(i));
        }
	}
	
	@Test
	public void TestGeneticSolver() {
        System.out.println("Test Genetic Solver");
        
        double guesses_total = 0;
        int repetitions = 100;
        int guesses_max = 0;
        
        for (int i = 0; i < repetitions; i++) {
            GeneticSolver gs = new GeneticSolver(8,6);
            List<Integer> solution = gs.generateRandomCode();
            if(i % 5 == 0) System.out.println("Test SolveGenetic at " + i + "%");
            List<List<Integer>> guesses = gs.solveGenetic(solution);
            guesses_total += guesses.size();
            if(guesses.size() > guesses_max) guesses_max = guesses.size();
        }
        
        System.out.println("Test SolveGenetic at 100%");
        System.out.println("Average Guesses with "+repetitions+" repetitions: "+guesses_total/repetitions);
        System.out.println("Max Guesses: "+guesses_max+"\n");
	}

  	@Test
  	public void TestResetVariables() {
  		System.out.println("Test resetVariables");
  		Maquina m = new Maquina(6,4);
  		m.resetVariables();
  		assertEquals(0,m.getRound());
  		assertEquals(10,m.getMaxSteps());
  	}
     
	@Test
	public void TestEvaluate() {
		System.out.println("Test evaluate");
		Maquina m = new Maquina(6,4);
    	
		List<Integer> code1 = Arrays.asList(1,2,2,3);
		List<Integer> code2 = Arrays.asList(0,2,1,3);
		List<Integer> code3 = Arrays.asList(3,1,1,2);
        
        assertEquals(21, m.evaluate(code1, code2));
        assertEquals(03, m.evaluate(code1, code3));
        assertEquals(12, m.evaluate(code2, code3));
	}
	
	@Test
	public void TestRemove() {
		System.out.println("Test remove");
		List<Integer> code = Arrays.asList(1,2,2,1);

        
        //4 reds, only 1 possible remaining code (the solution)
		Maquina m1 = new Maquina(6,4);
		m1.remove(code, 40);
		
		//0 reds and whites -> no 1 or 2 in the code -> only 3 colors remaining, so 4^4 possible combinations
		Maquina m2 = new Maquina(6,4);
		m2.remove(code, 0);
		
		//4 whites and code has only 2 different numbers -> only 1 possible remaining (the inverse)
		Maquina m3 = new Maquina(6,4);
		m3.remove(code, 4);
		
		/* 3 reds:
		X221 -> X != 1, otherwise we get the same code again
		1X21 -> X != 2
		12X1 -> X != 2
		122X -> X != 1
		Total: 4 codes with 5 different values for X -> 4*5 = 20 remaining */
		Maquina m4 = new Maquina(6,4);
		m4.remove(code, 30);
		
		//3 reds 1 white -> impossible combination (how can there be a white if all 3 other pegs are placed
		//on the right spot?) -> all removed (0 remaining)
		Maquina m5 = new Maquina(6,4);
		m5.remove(code, 31);
			
		assertEquals(1, m1.getRemaining_nb());
		assertEquals(256, m2.getRemaining_nb());
		assertEquals(1, m3.getRemaining_nb());
		assertEquals(20, m4.getRemaining_nb());
		assertEquals(0, m5.getRemaining_nb());
}
      
	@Test
	public void TestGetRound() {
		System.out.println("Test getRound");
		Maquina m = new Maquina(6,4);
		int r = m.getRound();
		assertEquals(0,r);
	}
	
	@Test
	public void TestGetRemaining_nb() {
		System.out.println("Test getRemaining_nb");
		Maquina m1 = new Maquina(6,4);
		assertEquals(1296,m1.getRemaining_nb());
		Maquina m2 = new Maquina(4,4);
		assertEquals(256,m2.getRemaining_nb());
	}
	
	@Test
	public void TestGetRemaining() {
		System.out.println("Test getRemaining");
		Maquina m = new Maquina(6,4);
		List<Integer> code = Arrays.asList(0,0,0,0);
		assertEquals(code,m.getRemaining());
	}	
}
