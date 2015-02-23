/*
 * Projet de AAV - IUT Informatique Paris Descartes 2014/2015
 * Victor LE - Groupe Alternance
 */
package Sudoku;

import java.io.FileNotFoundException;
import java.util.List;

/**
* <p>
* La classe abtraite <b><code>AbstractSudoSolve</code></b> permet à la classe qui 
* l'hérite d'être compatible avec la classe <code>Sudoku</code> et ainsi de 
* résoudre des grilles de Sudoku 
* </p>
*
* @author Victor LE
* @version 1.0
*
* @see Sudoku
*
* @since 1.0
*/
public abstract class AbstractSudoSolve {
	
	protected Sudoku sudoku;
	
	/**
	 * Instancie un <b>AbstractSudoSolve</b> qui va permettre de résoudre 
	 * la grille du Sudoku passé en paramètre
	 * 
	 * @param sudoku le sudoku
	 *
	 */
	public AbstractSudoSolve(Sudoku sudoku) {
		this.sudoku = sudoku;
	}
	
	
	/**
	 * Résout la grille du Sudoku
	 *
	 */
	public abstract void solve();
	
	
	/**
	 * Renvoie la liste de solutions possibles de la grille du Sudoku
	 * Il faut spécifier un nombre de solutions maximal à retourner, pour éviter
	 * une liste trop longue.
	 * 
	 * @param nbSolMax			  Nombre de solutions maximal à renvoyer	
	 * @return List<Sudoku>  la liste des solutions possibles
	 *
	 */
	public abstract List<Sudoku> solveAll(int nbSolMax);

	
	/**
	 * Renvoie la liste de solutions possibles de la grille du Sudoku sous un
	 * fichier texte
	 * Il faut spécifier un nombre de solutions maximal à retourner, pour éviter
	 * une liste trop longue.
	 * 
	 * @param nbSolMax			  Nombre de solutions maximal à renvoyer	
	 * @param chemin			  Le chemin du fichier texte	
	 * @throws FileNotFoundException 
	 *
	 * 
	 */
	public abstract void solveAll(String chemin, int nbSolMax) throws FileNotFoundException;
	
	
}
