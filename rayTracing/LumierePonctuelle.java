package rayTracing;
import utilitaire .*;
import elements3D .*;
import java.awt.Color;

/** LumierePonctuelle mod�lise une source de lumi�re associ� � un point dans l'espace.
 * Cette classe h�rite de Lumi�re que nous avons d�finie. 
 * */

public class LumierePonctuelle extends Lumiere{

	private static int compteur = 0;
	
	/**
	 * Initialiser une lumi�re ponctuelle � partir de son centre et sa couleur.
	 * @param centre : Centre de la lumi�re ponctuelle.
	 * @param couleur : Couleur de la lumi�re ponctuelle.
	 */
	public LumierePonctuelle(Point centre, Color couleur) {
		super(centre,couleur,"LumierePonctuelle" + ++compteur);

	}
	
	/**
	 * Initialiser une lumi�re ponctuelle � partir de son centre, sa couleur et son nom.
	 * @param centre : Centre de la lumi�re ponctuelle.
	 * @param couleur : Couleur de la lumi�re ponctuelle.
	 * @param nom : Nom de la lumi�re ponctuelle.
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
