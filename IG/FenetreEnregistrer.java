package IG;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

/** 
 * Classe permettant l'ouverture de la fen�tre de l'interface graphique
 * qui permet d'enregistrer l'image de la sc�ne et la sc�ne-elle-m�me.
 * Elle h�rite de la classe de la biblioth�que JavaSwing, JFrame.
 * */

public abstract class FenetreEnregistrer extends JFrame {
	
	final static JButton enregistrer = new JButton("Enregistrer");
	final static JButton annuler = new JButton("Annuler");
	
	final static JLabel nom = new JLabel("Nom : ");
	final static JTextField  text = new JTextField ("nom");
	
	/**
	 * Cr�ation de la fen�tre enregistrer
	 * @param titre 
	 */
	public FenetreEnregistrer(String titre) {
		super(titre);
		
		JPanel contenu = new JPanel();
		
		contenu.add(nom,BorderLayout.PAGE_START);
		text.setPreferredSize(new Dimension(150, 25));
		contenu.add(text, BorderLayout.CENTER);
		
		contenu.add(enregistrer,BorderLayout.PAGE_END);
		
		annuler.addActionListener(new ActionAnnuler());
		contenu.add(annuler);
		
		this.add(contenu);
	}
	
	
	public class ActionAnnuler implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			dispose();
		}
	}
}
