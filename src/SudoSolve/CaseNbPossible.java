/*
 * Projet de AAV - IUT Informatique Paris Descartes 2014/2015
 * Victor LE - Groupe Alternance
 */

package SudoSolve;

/**
* <p>
* La classe <b><code>CaseNbPossible</code></b> repr�sente le nombre de valeurs
* possibles dans une case de Sudoku.  
* </p>
* <p>
* Cette classe est n�cessaire pour l'algorithme de r�solution de sudoku dans la 
* classe SudoSolveBT
* 
* </p>
* 
*
* @author Victor LE
* @version 1.0
*
* @see SudoSolveBT
*
* @since 1.0
*/
public class CaseNbPossible implements Comparable<CaseNbPossible> {
	
	/** le num�ro de la Ligne o� est situ�e la case */
	int row;
	
	/** le num�ro de Colonne o� est situ�e la case*/
	int col;
	
	/** Le nombre de valeurs possibles */
	int nbPossible;


	/**
	 * Retourne le num�ro de ligne
	 *
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Retourne le num�ro de colonne
	 *
	 * @return col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Retourne le nombre de possibilit�s
	 *
	 * @return nbPossible 
	 */
	public int getNbPossible() {
		return nbPossible;
	}

	/**
	 * Instancie un CaseNbPossible avec les donn�es pass� en param�tres
	 *
	 * @param row num�ro de ligne
	 * @param col num�ro de colonne
	 * @param nbPossible nombre de possibilit�
	 */
	public CaseNbPossible(int row, int col, int nbPossible) {
		this.row = row;
		this.col = col;
		this.nbPossible = nbPossible;
	}

	/**
	 * Permet de comparer deux CaseNbPossible
	 *
	 * @param o le CaseNbPossible � comparer 
	 * 
	 * @return -1 si le nombre de possibilt� est inf�rieur
	 * @return 0 si le nombre de possibiilt� est �gal
	 * @return 1 si le nombre de possibilit� est sup�rieur
	 */
	@Override
	public int compareTo(CaseNbPossible o) {
		if(this.getNbPossible() == o.getNbPossible())
			return 0;
		else if(this.getNbPossible() > o.getNbPossible())
			return 1;
		else return -1;
	}
}
