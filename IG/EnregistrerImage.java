package IG;
import rayTracing.*;
import java.awt.*;
import java.awt.event.*;

/** 
 * Classe permettant l'entregistrement de l'image.
 * Elle h�rite de la classe FenetreEnregistrer que nous avons d�fini.
 * */

public class EnregistrerImage extends FenetreEnregistrer {
	
	final private RayTracing rt;
	
	public void actionenregistrer() {
		enregistrer.addActionListener(new ActionEnregistrer());
	}
	
	/**
	 * @param nom : nom que l'utilisateur choisit de donner � l'image.
	 * @param nrt : 
	 */
	public EnregistrerImage(String nom, RayTracing nrt) {
		super(nom);
		this.rt = nrt;
		this.pack();
		this.actionenregistrer();
	}
	
	/**
	 * Classe interne d'instance qui repr�sente l'observateur pour le bouton d'enregistrement
	 * de l'image.
	 */
	public class ActionEnregistrer implements ActionListener {
		public void actionPerformed (ActionEvent ev) {
			rt.getCamera().sauvegarderImage(text.getText());
			System.out.printf("Image sauvegard�e \n");
			dispose();
		}
	}

}