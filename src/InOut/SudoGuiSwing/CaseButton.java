/*
 * Projet de AAV - IUT Informatique Paris Descartes 2014/2015
 * Victor LE - Groupe Alternance
 */

package InOut.SudoGuiSwing;


import javax.swing.JButton;

/**
* <p>
* La classe <b><code>CaseButton</code></b> est utilisée par <code>MainWindow</code> 
* pour afficher une case du Sodoku
* </p>
* <p>
* Une instance de cette classe représente donc une case du Sudoku. Elle permet 
* ainsi d'afficher le numéro contenu dans la case. <b><code>CaseButton</code></b>
* fournit ainsi des méthodes pour modifier ce nombre ou pour réinitialiser la 
* case.
* </p>
*
* @author Victor LE
* @version 1.0
*
* @see MainWindow
* @see Menu
*
* @since 1.0
*/
@SuppressWarnings("serial")
public class CaseButton extends JButton
{
	
	/** Le numéro de ligne. */
	private int row;
	
	/** Le numéro de colonne */
	private int col;
	
	/**
	 * Instancie un CaseButton
	 *
	 * @param row la ligne
	 * @param col la colonne
	 */
	public CaseButton (int row, int col)
	{
		this.row = row;
		this.col = col;
	}

	/**
	 * Retourne la valeur contenue dans la case
	 *
	 * @return la valeur
	 */
	public int getValue()
	{
		if(super.getText().equals(""))
			return 0;
		
		return Integer.parseInt(super.getText());
	}
	
	/**
	 * Retourne le numéro de colonne
	 *
	 * @return le numéro de colonne
	 */
	public int getCol() 
	{
		return col;
	}

	/**
	 * Retourne le numéro de ligne
	 *
	 * @return le numéro de ligne
	 */
	public int getRow() 
	{
		return row;
	}
	

	/**
	 * Efface la valeur contenue dans la case
	 */
	public void clear()
	{
		super.setText("");
	}
	
	/**
	 * Affecte une valeur à la case
	 *
	 * @param val la nouvelle valeur
	 */
	public void setValue(int val)
	{
		if(val < 1 || val > 9)
		{
			super.setText("");
		}
		else
		{
			super.setText(String.valueOf(val));
		}
		
	}
	
	
}

