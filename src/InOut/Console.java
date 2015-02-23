/*
 * Projet de AAV - IUT Informatique Paris Descartes 2014/2015
 * Victor LE - Groupe Alternance
 */
package InOut;

import java.io.FileNotFoundException;
import java.util.List;

import SudoGen.SudoGen;
import SudoSolve.SudoSolveBT;
import Sudoku.Sudoku;

public class Console {
	
	public static void main(String[] args) throws FileNotFoundException {
		
		
		Sudoku s = new Sudoku();
		s.setSolver(new SudoSolveBT(s));
		
		s.setGen(new SudoGen(s));
		
		
			s.generate(25);
			System.out.println(s);
			long debut = System.nanoTime() ; 
	
			
	
	
			
			
			// Resolution du Sudoku
//			s.solve();
			
			// Ecriture solutions dans fichier
//			try {
//				s.solutionsToFile("s.txt", 1000000);
//			} catch (FileNotFoundException e) {
//				// TODO Auto-generated catch block
//				e.printStackTrace();
//			}
			
			
			
			
			// Stockage dans une liste
			s.solutionsToFile("s.txt", 1000);
//			System.out.println(ss.size());
//			for(Sudoku sss : ss) {
//				System.out.println(sss);
//			}
//			
			long fin = System.nanoTime();
		
			System.out.println("Méthode exécutée en " +(fin - debut)/1000000f + " millisecondes");
//		}
	}

}
