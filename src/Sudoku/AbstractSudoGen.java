/*
 * Projet de AAV - IUT Informatique Paris Descartes 2014/2015
 * Victor LE - Groupe Alternance
 */
package Sudoku;

/**
* <p>
* La classe abtraite <b><code>AbstractSudoGen</code></b> permet � la classe qui 
* l'h�rite d'�tre compatible avec la classe <code>Sudoku</code> et ainsi de 
* g�n�rer des grilles de Sudoku valides 
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
	 * Instancie un <b>AbstractSudoGen</b> qui va permettre de g�n�rer 
	 * al�atoirement des grilles de Sudoku contenu dans le sudoku en param�tre
	 * 
	 * @param sudoku le sudoku
	 *
	 */
	public AbstractSudoGen(Sudoku sudoku) {
		this.sudoku = sudoku;
	}

	
	/**
	 * G�n�re une grille de Sudoku avec un nombre de solutions d�j� r�solues
	 * sp�cifi�s en param�tres
	 * 
	 * @param n le nombre de solutions de cases d�j� r�solues
	 */
	public abstract void generate(int n);
}
