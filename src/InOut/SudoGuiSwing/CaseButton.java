/*
 * Projet de AAV - IUT Informatique Paris Descartes 2014/2015
 * Victor LE - Groupe Alternance
 */

package InOut.SudoGuiSwing;


import javax.swing.JButton;

/**
* <p>
* La classe <b><code>CaseButton</code></b> est utilis�e par <code>MainWindow</code> 
* pour afficher une case du Sodoku
* </p>
* <p>
* Une instance de cette classe repr�sente donc une case du Sudoku. Elle permet 
* ainsi d'afficher le num�ro contenu dans la case. <b><code>CaseButton</code></b>
* fournit ainsi des m�thodes pour modifier ce nombre ou pour r�initialiser la 
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
	
	/** Le num�ro de ligne. */
	private int row;
	
	/** Le num�ro de colonne */
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
	 * Retourne le num�ro de colonne
	 *
	 * @return le num�ro de colonne
	 */
	public int getCol() 
	{
		return col;
	}

	/**
	 * Retourne le num�ro de ligne
	 *
	 * @return le num�ro de ligne
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
	 * Affecte une valeur � la case
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

