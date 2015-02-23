/*
 * Projet de AAV - IUT Informatique Paris Descartes 2014/2015
 * Victor LE - Groupe Alternance
 */
package SudoGen;
import java.util.ArrayList;
import java.util.Random;

import Sudoku.AbstractSudoGen;
import Sudoku.Sudoku;

/**
* <p>
* La classe <b><code>SudoGen</code></b> h�rite de <code>AbstractSudoGen</code> 
* et peut donc �tre utilis� en tant que g�n�rateur de Sudoku par la classe <code>Sudoku</code> 
* </p>
* <p>
* Elle permet donc de g�n�rer al�atoirement des Sudokus (seul les tailles 9x9
* sont support�s pour le moment).
* Le principe de la g�n�ration est exppliqu� dans le m�thode generate.
* </p>
* 
*
* @author Victor LE
* @version 1.0
*
* @see Sudoku
* @see AbstractSudoGen
*
* @since 1.0
*/
public class SudoGen extends AbstractSudoGen {
	
	private static int NB_SWAP = 100;
	
	
	/**
	 * Instancie un <b>SudoGen</b> qui va permettre de g�n�rer 
	 * al�atoirement des grilles de Sudoku contenu dans le sudoku en param�tre
	 * 
	 * @param sudoku le sudoku
	 *
	 */
	public SudoGen(Sudoku sudoku) {
		super(sudoku);
	}
	
	/**
	 * G�n�re une grille de Sudoku 9x9 avec un nombre de solutions d�j� r�solues
	 * sp�cifi�s en param�tres
	 * 
	 * L'algorithme de g�n�ration a �t� inspir� par ce<a href="http://www.mathspace.com/comap/Training_Materials/Team2975_ProblemB.pdf">
	 * PDF</a>
	 * 
	 * Une grille de Sudoku est g�n�r� pr�alablement � partir d'une matrice seed, 
	 * mais dont les Blocs se ressemblent, puisqu'ils sont seront bas�s sur
	 * le seed. Les blocs sont en effet de simple permutation de cette matrice.
	 * 
	 * Puis on applique shake() qui permet d'�changer les colonnes et lignes
	 * al�atoirement, ce qui permet de r�duire la ressemblance des blocs.
	 * 
	 * Et enfin, on enl�ve un nombre de solutions pour avoir un nombre
	 * de cases d�j� r�solues d�finit en param�tre.
	 *
	 * @param n le nombre de solutions de cases d�j� r�solues
	 * 
	 * @see #randomize()
	 * @see #shake()
	 * @see #removeCases(int)
	 * 
	 */
	@Override
	public void generate(int n) {
		sudoku.reset();
		
		randomize();
		
		shake();
		removeCases(Sudoku.NB_ROW * Sudoku.NB_COL - n);
	}
	
	/**
	 * Enl�ve un nombre d�termin� de case al�atoirement � une grille 
	 * de Sudoku d�j� rempli
	 *
	 * @param nbCases le nombre de case � enlever
	 */
	private void removeCases(int nbCases) {
		int nbRemoved = 0;
		while(nbRemoved < nbCases) {
			int i = randInt(0, Sudoku.NB_ROW-1);
			int j = randInt(0, Sudoku.NB_COL-1);
			if(sudoku.getValue(i, j) != 0) {
				sudoku.setValue(i, j, 0);
				++nbRemoved;
			}
		}
	}
	
	
	/**
	 * Applique des �changes de lignes et de colonnes
	 * al�atoirement au sudoku
	 * 
	 * @see #swapCol()
	 * @see #swapRow()
	 *
	 */
	private void shake() {
		for (int i = 0; i < NB_SWAP; i++) {
			swapRow();
			swapCol();
		}
	}
	
	/**
	 * Echange al�atoirement deux lignes d'un m�me Bloc
	 *
	 */
	private void swapRow() {
		int row = randInt(1, Sudoku.SIZE) * randInt(1, Sudoku.SIZE);
		int rowToSwap;
		if(row % Sudoku.SIZE == 0) 
			rowToSwap = row - randInt(1, Sudoku.SIZE -1);
		else if(row % Sudoku.SIZE == 1) 
			rowToSwap = row + randInt(1, Sudoku.SIZE -1);
		else 
			rowToSwap = row + randInt(-1, Sudoku.SIZE - 2);
		
		for (int i = 0; i < Sudoku.NB_COL; i++) {
			int temp = sudoku.getValue(rowToSwap - 1, i);
			sudoku.setValue(rowToSwap -1, i, sudoku.getValue(row - 1, i));
			sudoku.setValue(row - 1, i , temp);
		}
	}
	
	
	/**
	 * Echange al�atoirement deux colonnes d'un m�me Bloc
	 *
	 */
	private void swapCol() {
		int col = randInt(1, Sudoku.SIZE) * randInt(1, Sudoku.SIZE);
		int colToSwap;
		if(col % Sudoku.SIZE == 0) 
			colToSwap = col - randInt(1, Sudoku.SIZE -1);
		else if(col % Sudoku.SIZE == 1) 
			colToSwap = col + randInt(1, Sudoku.SIZE -1);
		else 
			colToSwap = col + randInt(-1, Sudoku.SIZE - 2);
		
		for (int i = 0; i < Sudoku.NB_COL; i++) {
			int temp = sudoku.getValue(i,colToSwap - 1);
			sudoku.setValue(i,colToSwap -1, sudoku.getValue(i, col - 1));
			sudoku.setValue(i,col - 1, temp);
		}
	}
	
	/**
	 * G�n�re al�atoirement un sudoku valide mais dont
	 * les blocs se ressemblent : le premier bloc (seed) est g�n�r�
	 * al�atoirement, des permutations seront r�alis�s pour former les 
	 * autres blocs.
	 *
	 * Cette m�thode ne fonctionne que pour le sudoku classique de 9*9
	 */
	private void randomize() {
		int[][] seed = randomBloc();
		ArrayList<int[][]> blocs = new ArrayList<int[][]>();
		
		blocs.add(seed);
		blocs.add(multiplyMatrix((new int[][]{
											  {0, 1, 0},
											  {0, 0, 1},
											  {1, 0, 0} }) , seed));
		blocs.add(multiplyMatrix((new int[][]{
											  {0, 0, 1},
											  {1, 0, 0},
											  {0, 1, 0} }) , seed));
		blocs.add(multiplyMatrix(seed, (new int[][]{
													  {0, 0, 1},
													  {1, 0, 0},
													  {0, 1, 0} })));
		blocs.add(multiplyMatrix((new int[][]{
											  {0, 1, 0},
											  {0, 0, 1},
											  {1, 0, 0} }) , blocs.get(3)));
		blocs.add(multiplyMatrix((new int[][]{
											  {0, 0, 1},
											  {1, 0, 0},
											  {0, 1, 0} }) , blocs.get(3)));
		blocs.add(multiplyMatrix(seed, (new int[][]{
													  {0, 1, 0},
													  {0, 0, 1},
													  {1, 0, 0} })));

		blocs.add(multiplyMatrix((new int[][]{
											  {0, 1, 0},
											  {0, 0, 1},
											  {1, 0, 0} }) , blocs.get(6)));
		blocs.add(multiplyMatrix((new int[][]{
											  {0, 0, 1},
											  {1, 0, 0},
											  {0, 1, 0} }) , blocs.get(6)));
		matrixBlocToSudoku(seed,0,0);
		matrixBlocToSudoku(blocs.get(1),0,3);
		matrixBlocToSudoku(blocs.get(2),0,6);
		matrixBlocToSudoku(blocs.get(3),3,0);
		matrixBlocToSudoku(blocs.get(4),3,3);
		matrixBlocToSudoku(blocs.get(5),3,6);
		matrixBlocToSudoku(blocs.get(6),6,0);
		matrixBlocToSudoku(blocs.get(7),6,3);
		matrixBlocToSudoku(blocs.get(8),6,6);
	}
	
	
	/**
	 * Retourne un nombre pseudo al�atoire entre min et max, inclusive.
	 *
	 * @param min Entier Minimum
	 * @param max Entier Maximum.  Doit �tre sup�rieur � min.
	 * @return Integer entre min et max, inclusive.
	 */
	private static int randInt(int min, int max) {
	    Random rand = new Random();
	    int randomNum = rand.nextInt((max - min) + 1) + min;

	    return randomNum;
	}
	
	
	/**
	 * Retourne le produit matriciel de deux matrices
	 *
	 * @param A Matrice
	 * @param B Matrice
	 * @return A*B
	 */
	private static int[][] multiplyMatrix(int[][] A, int[][] B) {
        int mA = A.length;
        int nA = A[0].length;
        int mB = B.length;
        int nB = A[0].length;
        if (nA != mB) throw new RuntimeException("Illegal matrix dimensions.");
        int[][] C = new int[mA][nB];
        for (int i = 0; i < mA; i++)
            for (int j = 0; j < nB; j++)
                for (int k = 0; k < nA; k++)
                    C[i][j] += (A[i][k] * B[k][j]);
        return C;
    }
	
	/**
	 * Retourne une matrice de taille Sudoku.Size*Sudoku.Size
	 * qui correspond � un bloc d'un Sudoku
	 *
	 * @return Le bloc
	 */
	private static int[][] randomBloc() {
		int[][] bloc = new int[Sudoku.SIZE][Sudoku.SIZE];
		ArrayList<Integer> nbDejaPlace = new ArrayList<Integer>();
		
		for (int i = 0; i < Sudoku.SIZE; i++) {
			for (int j = 0; j < Sudoku.SIZE; j++) {
				int rnd;
				do {
					rnd = randInt(1,Sudoku.MAX_VALUE);
				} 
				while(nbDejaPlace.contains(rnd));
				nbDejaPlace.add(rnd);
				bloc[i][j] = rnd;
				
			}
		}
		
		return bloc;
		
	}
	
	/**
	 * Rentre les valeurs d'une matrice de taille Sudoku.SIZE*Sudoku.SIZE 
	 * dans le sudoku � une position donn�e en param�tres
	 *
	 * @param matrix Matrice
	 * @param row Ligne 
	 * @param col Colonne
	 */
	private void matrixBlocToSudoku(int[][] matrix, int row, int col) {
		if(matrix.length != Sudoku.SIZE || matrix[0].length != Sudoku.SIZE)
			throw new RuntimeException("La matrice doit �tre de la m�me taille qu'un Bloc de Sudoku");
		if(row <0 || col<0 || row>Sudoku.NB_ROW - Sudoku.SIZE  || col>Sudoku.NB_COL - Sudoku.SIZE)
			throw new RuntimeException("row/col incorrecte");
		for (int i = 0; i < Sudoku.SIZE; i++) {
			for (int j = 0; j < Sudoku.SIZE; j++) {
				sudoku.setValue(i + row, j + col, matrix[i][j]);
			}
		}
		
	}
	

}
