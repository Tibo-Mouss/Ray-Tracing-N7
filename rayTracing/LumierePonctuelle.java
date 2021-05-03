package rayTracing;
import utilitaire .*;
import elements3D .*;
import java.awt.Color;

/** LumierePonctuelle modélise une source de lumière associé à un point dans l'espace.
 * Cette classe hérite de Lumière que nous avons définie. 
 * */

public class LumierePonctuelle extends Lumiere{

	private static int compteur = 0;
	
	/**
	 * Initialiser une lumière ponctuelle à partir de son centre et sa couleur.
	 * @param centre : Centre de la lumière ponctuelle.
	 * @param couleur : Couleur de la lumière ponctuelle.
	 */
	public LumierePonctuelle(Point centre, Color couleur) {
		super(centre,couleur,"LumierePonctuelle" + ++compteur);

	}
	
	/**
	 * Initialiser une lumière ponctuelle à partir de son centre, sa couleur et son nom.
	 * @param centre : Centre de la lumière ponctuelle.
	 * @param couleur : Couleur de la lumière ponctuelle.
	 * @param nom : Nom de la lumière ponctuelle.
	 */
	public LumierePonctuelle(Point centre, Color couleur, String nom) {
		super(centre,couleur,nom);
		compteur++;

	}

	@Override
	public Point getSource(Point impact) {
		return this.getCentre();
	}

	@Override
	public boolean eclaire(Point impact) {
		return true;
	}
	
	@Override
	public boolean estHorsScene(double dimension, Point centre) {
		return this.getCentre().distance(centre) > dimension;
	}

}
