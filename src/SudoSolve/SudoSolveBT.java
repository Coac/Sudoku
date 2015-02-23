/*
 * Projet de AAV - IUT Informatique Paris Descartes 2014/2015
 * Victor LE - Groupe Alternance
 */
package SudoSolve;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import Sudoku.AbstractSudoSolve;
import Sudoku.Sudoku;

/**
* <p>
* La classe <b><code>SudoSolveBT</code></b> hérite de <code>AbstractSudoSolve</code> 
* et peut donc être utilisé en tant que solveur de Sudoku par <code>Sudoku</code> 
* </p>
* <p>
* Elle permet donc de résoudre des Sudokus grâce à un algorithme de backtracking.
* SudoSolveBT implémente toutes les méthodes de <code>AbstractSudoSolve</code> 
* qui permet entre autres de choisir combien l'on veut renvoyer de solutions.
* 
* </p>
* 
*
* @author Victor LE
* @version 1.0
*
* @see Sudoku
* @see AbstractSudoSolve
* @see CaseNbPossible
*
* @since 1.0
*/
public class SudoSolveBT extends AbstractSudoSolve{
	
	/** Les solutions seront stockés dans cette liste. */
	private List<Sudoku> solutions;
	
	/**
	 * Instancie un <b>SudoSolveBT</b> qui va permettre de résoudre 
	 * le sudoku passé en paramètre.
	 * 
	 * @param sudoku le sudoku
	 *
	 */
	public SudoSolveBT(Sudoku sudoku) {
		super(sudoku);
	}

	/** Permettent de savoir si une case est libre en respectant les 3 contraintes */
	private boolean existeSurLigne[][];
	private boolean existeSurColonne[][];
	private boolean existeSurBloc[][];
	
	/** Permet de savoir la position des cases à tester. */
	private ArrayList<CaseNbPossible> positionToTest;
	private int nbSol;
	
	
	
	/**
	 * Permet de résoudre le sudoku avec la première solution trouvé.
	 * Si le Sudoku n'est pas valide, la méthode retourne une exception
	 */
	@Override
	public void solve() {
		if(!sudoku.isValid())
			throw new RuntimeException("Le sudoku n'est pas valide !");
		initExist();
		initPositionToTest();
		estValide(0);
		
	}
	
	/**
	 * Méthode privée qui initialise les cases à remplir, classé dans l'ordre
	 * de nombre de solutions possibles.
	 *
	 * @see CaseNbPossible
	 * @see {@link #estValide(int)}
	 */
	private void initPositionToTest() {
		positionToTest = new ArrayList<CaseNbPossible>();
		
		for (int row=0; row < Sudoku.NB_ROW; row++)
	        for (int col=0; col < Sudoku.NB_COL; col++)
	            if (sudoku.getValue(row, col) == 0 )
	            	positionToTest.add(new CaseNbPossible(row, col, getNbPossible(row, col)) );
		
		Collections.sort(positionToTest);
		
	}

	/**
	 * Initialise les listes existeSurLigne, existeSurColonne et existeSurBloc en
	 * fonction des cases déjà remplie
	 */
	private void initExist() {
		existeSurLigne = new boolean[Sudoku.NB_ROW][Sudoku.MAX_VALUE];
		existeSurColonne = new boolean[Sudoku.NB_COL][Sudoku.MAX_VALUE];
		existeSurBloc = new boolean[Sudoku.NB_BLOC][Sudoku.MAX_VALUE];
		
	    int val;
	    for (int row=0; row < Sudoku.NB_ROW; row++)
	        for (int col=0; col < Sudoku.NB_COL; col++)
	            if ( (val = sudoku.getValue(row, col)) != 0) {
	                existeSurLigne[row][val-1] = true;
	                existeSurColonne[col][val-1] = true;
	                existeSurBloc[Sudoku.SIZE*(row/Sudoku.SIZE)+(col/Sudoku.SIZE)][val-1] = true;
	            }
	}
	
	
	
	
	
	/**
	 * Méthode récursif : algorithme du backtracking permettant de résoudre
	 * le sudoku avec la première solution
	 *
	 *
	 * @param position la position à tester
	 * @return true, Si c'est valide
	 */
	private boolean estValide (int position) {
	    if (position >= positionToTest.size()){
	        return true;
	    }


	    int i = positionToTest.get(position).getRow();
	    int j = positionToTest.get(position).getCol();

	    if (sudoku.getValue(i, j) != 0)
	        return estValide(position+1);
	    
	    for (int k=0; k < Sudoku.MAX_VALUE; k++)
	    {
	        // Vérifie dans les tableaux si la valeur est déjà présente
	        if ( !existeSurLigne[i][k] && !existeSurColonne[j][k] && !existeSurBloc[3*(i/3)+(j/3)][k] )
	        {
	            // Ajoute k aux valeurs enregistrées
	            existeSurLigne[i][k] = existeSurColonne[j][k] = existeSurBloc[3*(i/3)+(j/3)][k] = true;
     
	            if ( estValide(position+1) )
	            {
	            	// Ecrit le choix valide dans la grille
	            	sudoku.setValue(i,j,k+1);
	            	
	            	return true;
	            }
	            // Supprime k des valeurs enregistrées
	            existeSurLigne[i][k] = existeSurColonne[j][k] = existeSurBloc[3*(i/3)+(j/3)][k] = false;
	        }
	    }
	    sudoku.setValue(i, j, 0);

	    return false;
	}
	
	
	/**
	 * Calcule le nombre de valeurs possibles pour une case vide
	 *
	 * @param row la ligne
	 * @param col la colonne
	 * @return le nombre de valeurs possibles
	 */
	private int getNbPossible (int row, int col) {
	    int ret = 0;
	    for (int val=0; val < Sudoku.MAX_VALUE; val++)
	        if ( !existeSurLigne[row][val] && !existeSurColonne[col][val] && !existeSurBloc[3*(row/Sudoku.SIZE)+(col/Sudoku.SIZE)][val] )
	            ret++;
	    return ret;
	}

	/**
	 * Retourne les solutions du sodukus
	 * 
	 * @param nbSolMax le nombre de solutions maximales a retourner
	 * @return les solutions sous forme de liste
	 * @see AbstractSudoSolve
	 */
	@Override
	public List<Sudoku> solveAll(int nbSolMax) {
		if(!sudoku.isValid())
			throw new RuntimeException("Le sudoku n'est pas valide !");
		initExist();
		initPositionToTest();
		solutions = new LinkedList<Sudoku>();
		solveAll(0,nbSolMax);
		return solutions;
	}
	
	/**
	 * Retourne les solutions du sudoku dans un fichier texte
	 * 
	 * @param chemin le chemin du fichier texte
	 * @param nbSolMax le nombre de solutions maximales a retourner
	 * @throws FileNotFoundException 
	 * 
	 */
	@Override
	public void solveAll(String chemin, int nbSolMax) throws FileNotFoundException {
		if(!sudoku.isValid())
			throw new RuntimeException("Le sudoku n'est pas valide !");
		initExist();
		initPositionToTest();
		this.nbSol = 0;
		PrintWriter out = new PrintWriter(new FileOutputStream(new File(chemin), true));
		solveAllString(0,0,nbSolMax, chemin, out);
		out.close();
	
	}
	
	/**
	 * Méthode récursif qui va trouver toutes les solutions du sudoku et les
	 * stocker dans un fichier texte}
	 *
	 * @param position the position
	 * @param nbSol the nb sol
	 * @param chemin le chemin du fichier texte
	 * @param out 
	 * 
	 */
	private void solveAllString(int position, int nbSol, int nbSolMax, String chemin, PrintWriter out) throws FileNotFoundException {
		if(nbSolMax == this.nbSol)
			return;
		if (position >= positionToTest.size()){
	        return;
	    }

	    int i = positionToTest.get(position).getRow();
	    int j = positionToTest.get(position).getCol();

	    if (sudoku.getValue(i, j) != 0) {
	    	solveAllString(position+1, nbSol, nbSolMax, chemin, out);
	         return;
	    }
	    
	    for (int k=0; k < Sudoku.MAX_VALUE; k++)
	    {
	    	
	        // Vérifie dans les tableaux si la valeur est déjà présente
	        if ( !existeSurLigne[i][k] && !existeSurColonne[j][k] && !existeSurBloc[3*(i/3)+(j/3)][k] )
	        {
	            // Ajoute k aux valeurs enregistrées
	            existeSurLigne[i][k] = existeSurColonne[j][k] = existeSurBloc[3*(i/3)+(j/3)][k] = true;

	            
	            sudoku.setValue(i,j,k+1);
	            solveAllString(position, nbSol, nbSolMax, chemin, out);

            	
            	if(sudoku.isComplete()) {
            		out.println(sudoku.toString());
					this.nbSol++;

            	}
            		
            		
	            // Supprime k des valeurs enregistrées
	            existeSurLigne[i][k] = existeSurColonne[j][k] = existeSurBloc[3*(i/3)+(j/3)][k] = false;

	        }
	        
	    }
	    sudoku.setValue(i, j, 0);
		
	}

	/**
	 * Méthode récursif qui va trouver toutes les solutions du sudoku et les
	 * stocker dans {@link #solutions}
	 *
	 * @param position the position
	 * @param nbSol the nb sol
	 */
	public void solveAll(int position, int nbSol) {
		if(solutions.size() == nbSol)
			return;
		
		if (position >= positionToTest.size()){
	        return;
	    }


	    int i = positionToTest.get(position).getRow();
	    int j = positionToTest.get(position).getCol();

	    if (sudoku.getValue(i, j) != 0) {
	    	solveAll(position+1, nbSol);
	         return;
	    }
	    
	    for (int k=0; k < Sudoku.MAX_VALUE; k++)
	    {
	    	
	        // Vérifie dans les tableaux si la valeur est déjà présente
	        if ( !existeSurLigne[i][k] && !existeSurColonne[j][k] && !existeSurBloc[3*(i/3)+(j/3)][k] )
	        {
	            // Ajoute k aux valeurs enregistrées
	            existeSurLigne[i][k] = existeSurColonne[j][k] = existeSurBloc[3*(i/3)+(j/3)][k] = true;

	            
	            sudoku.setValue(i,j,k+1);
	            solveAll(position, nbSol);

            	
            	if(sudoku.isComplete()) {
            		solutions.add(sudoku.clone());
            		
            	}
            
	            // Supprime k des valeurs enregistrées
	            existeSurLigne[i][k] = existeSurColonne[j][k] = existeSurBloc[3*(i/3)+(j/3)][k] = false;
	            
	            
	    
	        }
	        
	    }
	    sudoku.setValue(i, j, 0);

	}
	
	
	
	

}
