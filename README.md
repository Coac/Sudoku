# Sudoku
Bibliothèque facilitant les applications de Sudoku.
Elle facilite les tests de performance en temps pour les algorithmes de générations/résolution de Sudoku

## Le core - package Sudoku
Dans le package Sudoku, nous avons le coeur de la bibliothèque.
En effet, il contient la classe Sudoku, qui implémente la grille de Sudoku, avec différentes méthodes associées pour la génération ou la résolution.

Il encapsule AbstractSudoGen et AbstractSudoSolve qui représente respectivement les classes de Génération et de Résolution.

En héritant vos classes contenant vos algorithmes de génération/résolution à AbstractSudoGen/AbstractSudoSolve, vos classes seront compatibles avec la librairie.

La documentation est disponible dans le répertoire "doc".

## Algorithmes déjà implémentés
### Génération
Algorithme inspiré de http://www.mathspace.com/comap/Training_Materials/Team2975_ProblemB.pdf
Une grille de Sudoku est généré préalablement à partir d'une matrice seed,  mais dont les Blocs se ressemblent, puisqu'ils sont seront basés sur le seed. Les blocs sont en effet de simple permutation de cette matrice.

Puis on applique shake() qui permet d'échanger les colonnes et lignes aléatoirement, ce qui permet de réduire la ressemblance des blocs.

Et enfin, on enlève un nombre de solutions pour avoir un nombre de cases déjà résolues définit en paramètre.

### Résolution
Algorithme du backtracking, implémentant quelques améliorations.

## Interface graphique - package InOut.SudoGuiSwing
La bibliothèque intègre une interface graphique sous Swing.
Ce GUI permet notamment à l'utilisateur de résoudre le Sudoku manuellement.
Mais il facilite aussi l'implémentation de différents Sudokus, en insérant chaque chiffre.
Cependant, ce GUI ne calcule pas encore la vitesse de résolution/génération. Il faudra pour cela passer par la Classe "Console".

