package IG_test;
import rayTracing.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * Classe permettant l'entregistrement de l'image.
 * Elle hérite de la classe FenetreEnregistrer que nous avons défini.
 * */

public class EnregistrerImage extends FenetreEnregistrer {
	
	final private RayTracing rt;
	
	public void actionenregistrer() {
		enregistrer.addActionListener(new ActionEnregistrer());
	}
	
	/**
	 * @param nom : nom que l'utilisateur choisit de donner à l'image.
	 * @param nrt : 
	 */
	public EnregistrerImage(String nom, RayTracing nrt) {
		super(nom);
		this.rt = nrt;
		this.pack();
		this.actionenregistrer();
	}
	
	/**
	 * Classe interne d'instance qui représente l'observateur pour le bouton d'enregistrement
	 * de l'image.
	 */
	public class ActionEnregistrer implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			rt.getCamera().sauvegarderImage(text.getText());
			System.out.printf("Image sauvegardée \n");
			dispose();
		}
	}

}
