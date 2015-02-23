/*
 * Projet de AAV - IUT Informatique Paris Descartes 2014/2015
 * Victor LE - Groupe Alternance
 */

package InOut.SudoGuiSwing;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JMenuBar;
import javax.swing.KeyStroke;

/**
* <p>
* La classe <b><code>Menu</code></b> est utilisée par <code>MainWindow</code> 
* pour afficher le menu de l'application Sudoku.
* </p>
* <p>
* Elle permet à l'utilisateur de l'application d'accéder à différentes
* fonctions via un menu déroulant. <b><code>Menu</code></b> gère ainsi les 
* évènements liés à ce menu comme la sélection/clic mais aussi les raccourcis 
* clavier.
* </p>
* 
*
* @author Victor LE
* @version 1.0
*
* @see MainWindow
* @see CaseButton
*
* @since 1.0
*/
public class Menu implements ActionListener {
    
    private MainWindow gui;
    
    /**
     * Instancie un Menu avec en paramètre un MainWindow pour permettre
     * d'intéragir avec le Sudoku
     *
     * @param gui the gui
     */
    public Menu (MainWindow gui)
    {
    	this.gui = gui;
    }

    
    
    /**
     * Créer un JMenuBar en enregistrant les évènements pour chaque item du
     * menu.
     *
     * @return le JMenuBar
     */
    public JMenuBar createMenuBar() {
        JMenuBar menuBar;
        JMenu menu, submenu;
        JMenuItem menuItem;

        menuBar = new JMenuBar();

        menu = new JMenu("Fichier");
        menuBar.add(menu);
        
        submenu = new JMenu("Nouvelle Partie");

        menuItem = new JMenuItem("Facile");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);

        menuItem = new JMenuItem("Moyen");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_M, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);
        
        menuItem = new JMenuItem("Difficile");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        submenu.add(menuItem);      
        
        menu.add(submenu);
        
        menuItem = new JMenuItem("Vider le Sudoku");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        menuItem = new JMenuItem("Résoudre le Sudoku");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        
        menu.addSeparator();
        
        menuItem = new JMenuItem("Quitter");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_Q, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);

        menu = new JMenu("Aide");
        menuItem.addActionListener(this);
        menuBar.add(menu);
        
        menuItem = new JMenuItem("Aide");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_F1, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        menu.addSeparator();
        
        menuItem = new JMenuItem("A propos");
        menuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_A, ActionEvent.ALT_MASK));
        menuItem.addActionListener(this);
        menu.add(menuItem);
        
        return menuBar;
    }
    
	
    /**
     * Méthode du à l'implémentation de ActionListener.
     * Permet de gérer les évènements liés au menu, et appelle les méthodes
     * adéquats dans MainWindow
     *
     */
	@Override
	public void actionPerformed(ActionEvent e) 
	{
		JMenuItem source = (JMenuItem)(e.getSource());
		String cmd = source.getActionCommand().toLowerCase();
		
		switch (cmd) {
		case "facile":
			gui.newGame(32);
			break;
		case "moyen":
			gui.newGame(18);
			break;
		case "difficile":
			gui.newGame(9);
			break;
		case "vider le sudoku":
			gui.clear();
			break;
		case "résoudre le sudoku":
			gui.solve();
			break;
		case "quitter":
			gui.quit();
			break;
		case "aide":
			gui.help();
			break;
		case "a propos":
			gui.about();
			break;
		default:
			break;
		}
		
	}
}
