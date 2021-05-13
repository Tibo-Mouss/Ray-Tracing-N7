/**
 * 
 */
package elements3D;

import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;

import rayTracing.Rayon;
import utilitaire.Point;

/** Couleur definit le materiau representant la couleur des objets 3D
 * qui sont manipulés dans la scene.
 * @author Edgar
 *
 */
public class Couleur implements Propriete, Serializable {
	
	private static final long serialVersionUID = 1341431705338909785L;

	/** Couleur par défaut définie comme gris foncé.*/
	private static final Color COULEUR_DEFAUT = Color.DARK_GRAY; 
	
	private Color couleur;
	
	/** Creer une couleur a partir de valeurs RGB
	 * @param r : valeur de la composante red/rouge
	 * @param g : valeur de la composante green/verte
	 * @param b : valeur de la composante blue/bleu
	 */
	public Couleur(int r, int g, int b) {
		this.couleur = new Color(r, g, b);
	}
	
	/** Creer une couleur a partir d'une instance de Color
	 * @param c
	 */
	public Couleur(Color c) {
		this.couleur = new Color(c.getRed(), c.getGreen(), c.getBlue());
	}
	
	/** Creer une couleur à partir de la valeur par défaut définie.
	 */
	public Couleur() {
		this(COULEUR_DEFAUT);
	}
	
	/** Obtenir la valeur de la composante Red/rouge de la couleur du récepteur (this).
	 * @return valeur de la composante rouge entière (comprise entre 0 et 255)
	 */
	public int getRed() {
		return this.couleur.getRed();
	}
	
	/** Obtenir la valeur de la composante green/vert de la couleur du récepteur (this).
	 * @return valeur de la composante verte entière (comprise entre 0 et 255)
	 */
	public int getGreen() {
		return this.couleur.getGreen();
	}
	
	/** Obtenir la valeur de la composante green/vert de la couleur du récepteur (this).
	 * @return valeur de la composante verte entière (comprise entre 0 et 255)
	 */
	public int getBlue() {
		return this.couleur.getBlue();
	}
	
	/**
	 * Attribuer une nouvelle valeur RGB a la couleur
	 * @param r : nouvelle valeur de la composante Rouge (comprise entre 0 et 255)
	 * @param g : nouvelle valeur de la composante Verte (comprise entre 0 et 255)
	 * @param b : nouvelle valeur de la composante Bleu (comprise entre 0 et 255)
	 */
	public void set(int r, int g, int b) {
		this.couleur = new Color(r, g, b);
	}
	
	/**
	 * Attribuer une nouvelle Couleur
	 * @param c
	 */
	public void set(Color c) {
		this.couleur = new Color(c.getRed(), c.getGreen(), c.getBlue());
	}
	
	/**
	 * Obtenir la Couleur
	 */
	public Color get() {
		return new Color(this.couleur.getRed(), this.couleur.getGreen(), this.couleur.getBlue());
	}
	
	@Override
	public boolean isOn() {
		return true; // la couleur est un materiau toujours actif
	}
	
	@Override
	public void reset() {
		Color c = COULEUR_DEFAUT;
		this.couleur = c;
	}

	@Override
	public ArrayList<Rayon> creerRayon(Rayon rayon, Point intersection, Objet3D objetIntersection) {
		return null;
	}

	@Override
	public void setOn(boolean on) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setEnergie(double energie) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getEnergie() {
		// TODO Auto-generated method stub
		return 0;
	}

}
