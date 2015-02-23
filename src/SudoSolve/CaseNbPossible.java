/*
 * Projet de AAV - IUT Informatique Paris Descartes 2014/2015
 * Victor LE - Groupe Alternance
 */

package SudoSolve;

/**
* <p>
* La classe <b><code>CaseNbPossible</code></b> représente le nombre de valeurs
* possibles dans une case de Sudoku.  
* </p>
* <p>
* Cette classe est nécessaire pour l'algorithme de résolution de sudoku dans la 
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
	
	/** le numéro de la Ligne où est située la case */
	int row;
	
	/** le numéro de Colonne où est située la case*/
	int col;
	
	/** Le nombre de valeurs possibles */
	int nbPossible;


	/**
	 * Retourne le numéro de ligne
	 *
	 * @return row
	 */
	public int getRow() {
		return row;
	}

	/**
	 * Retourne le numéro de colonne
	 *
	 * @return col
	 */
	public int getCol() {
		return col;
	}

	/**
	 * Retourne le nombre de possibilités
	 *
	 * @return nbPossible 
	 */
	public int getNbPossible() {
		return nbPossible;
	}

	/**
	 * Instancie un CaseNbPossible avec les données passé en paramètres
	 *
	 * @param row numéro de ligne
	 * @param col numéro de colonne
	 * @param nbPossible nombre de possibilité
	 */
	public CaseNbPossible(int row, int col, int nbPossible) {
		this.row = row;
		this.col = col;
		this.nbPossible = nbPossible;
	}

	/**
	 * Permet de comparer deux CaseNbPossible
	 *
	 * @param o le CaseNbPossible à comparer 
	 * 
	 * @return -1 si le nombre de possibilté est inférieur
	 * @return 0 si le nombre de possibiilté est égal
	 * @return 1 si le nombre de possibilité est supérieur
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
