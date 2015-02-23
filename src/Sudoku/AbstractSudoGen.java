/*
 * Projet de AAV - IUT Informatique Paris Descartes 2014/2015
 * Victor LE - Groupe Alternance
 */
package Sudoku;

/**
* <p>
* La classe abtraite <b><code>AbstractSudoGen</code></b> permet à la classe qui 
* l'hérite d'être compatible avec la classe <code>Sudoku</code> et ainsi de 
* générer des grilles de Sudoku valides 
* </p>
*
* @author Victor LE
* @version 1.0
*
* @see Sudoku
*
* @since 1.0
*/
public abstract class AbstractSudoGen {
	
	protected Sudoku sudoku;
	
	/**
	 * Instancie un <b>AbstractSudoGen</b> qui va permettre de générer 
	 * aléatoirement des grilles de Sudoku contenu dans le sudoku en paramètre
	 * 
	 * @param sudoku le sudoku
	 *
	 */
	public AbstractSudoGen(Sudoku sudoku) {
		this.sudoku = sudoku;
	}

	
	/**
	 * Génère une grille de Sudoku avec un nombre de solutions déjà résolues
	 * spécifiés en paramètres
	 * 
	 * @param n le nombre de solutions de cases déjà résolues
	 */
	public abstract void generate(int n);
}
