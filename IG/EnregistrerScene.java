package IG;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import IG.EnregistrerImage.ActionEnregistrer;
import rayTracing.RayTracing;

public class EnregistrerScene extends FenetreEnregistrer {
	
	final private RayTracing rt;
	
	public void actionenregistrer() {
		enregistrer.addActionListener(new ActionEnregistrer());
	}
	
	/**
	 * @param nom : nom que l'utilisateur choisit de donner à l'image.
	 * @param nrt : 
	 */
	public EnregistrerScene(String nom, RayTracing nrt) {
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
			//rt.getCamera().getScene().enregistrer(text.getText());
			System.out.printf("Image sauvegardée \n");
			dispose();
		}
	}

}
