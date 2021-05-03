package rayTracing;
import elements3D .*;
import utilitaire .*;

import java.awt.Color;

/**
 * La classe rayon modelise un rayon de lumi�re.
 * 
 * @author Tibo
 */

public class Rayon {
	
	/** Direction du rayon, contenant aussi son sens*/
	private Vecteur direction;
	
	/** Point d'origine du rayon */
	private Point origine;
	
	/** Couleur du rayon */
	private Color couleur; 
	
	/** Pixel de l'image dont le rayon va determiner la couleur 
	 *  */
	private Pixel pixelPere;  
	
	/** Rayon p�re, ayant engendr� ce rayon apres collision*/
	private Rayon rayonPere; 
	
	/** Nombre de rebonds limite*/
	private int nbRebond;
	
	/** Part de la couleur du rayon fils par rapport � la couleur du rayon p�re, entre 0 et 1. */
	private double pCouleur;
	
	/** Part de la couleur du rayon fils par rapport � la couleur du pixel p�re, entre 0 et 1. */
	private double pEnergie;
	
	/** Cr�er les rayons issus directement d'un pixel.
	 * @param cameraCoor les coordonn�es de la cam�ra
	 * @param pixelCoor les coordonn�es du pixel p�re
	 * @param pixelDaron : Pixel de l'image dont le rayon va d�terminer la couleur 
	 */
	public Rayon(Point cameraCoor, Point pixelCoor, Pixel pixelPere) {
		assert ( cameraCoor!=null && pixelCoor!=null && pixelPere!=null );
		this.direction = new Vecteur(cameraCoor, pixelCoor);
		this.origine = cameraCoor.copie();
		this.pixelPere = pixelPere;
		this.rayonPere = this;
		this.couleur = null;
		this.nbRebond = 0;
		this.pCouleur = 1;
		this.pEnergie = 1;
	}
	
	//Constructeur non utilis�
	/** Cr�er les rayons issus directement d'un pixel.
	 * @param direction le vecteur directeur du rayon
	 * @param origine le point d'origine du rayon
	 * @param pixelDaron : Pixel de l'image dont le rayon va determiner la couleur
	 */
	public Rayon(Vecteur direction, Point origine, Pixel pixelPere) {
		assert (direction != null && origine != null && pixelPere != null);
		this.direction = direction.copie();
		this.origine = origine.copie();
		this.pixelPere = pixelPere.copie();
		this.rayonPere = this;
		this.couleur = null;
		this.nbRebond = 0;
		this.pCouleur = 0;
		this.pEnergie = 1;
	}
	
	//Constructeur non utilis�
	/** Creer les rayons fils.
	 * @param origine : le point de d�part du rayon 
	 * @param impact : un point du rayon
	 * @param pixelPere : Pixel de l'image dont le rayon va determiner la couleur 
	 * @param rayonPere : Rayon p�re, ayant engendr� ce rayon apres collision
	 * @param couleur : Couleur du Rayon
	 */
	public Rayon(Point origine, Point impact, Pixel pixelPere, Rayon rayonPere, Color couleur) {
		assert (origine != null && impact != null && pixelPere != null && rayonPere != null && couleur != null);
		this.direction = new Vecteur(origine, impact);
		this.origine = origine.copie();
		this.pixelPere = pixelPere;
		this.rayonPere = rayonPere;
		this.couleur = couleur;
		this.nbRebond = rayonPere.getNbRebonds() + 1;
	}
	
	/** Cr�er un rayons fils.
	 * @param direction : le vecteur directeur du rayon
	 * @param origine l'origine du rayon, g�n�ralement un point d'impact avec un objet
	 * @param pixelPere : Pixel de l'image dont le rayon va d�terminer la couleur 
	 * @param rayonPere : Rayon p�re, ayant engendr� ce rayon apr�s collision
	 * @param pCouleur part de la couleur du rayon par rapport � la couleur de rayon p�re
	 * @param pEnergie part de la couleur du rayon par rapport � la couleur du pixel p�re 
	 */
	public Rayon(Vecteur direction, Point origine, Rayon rayonPere, double pCouleur, double pEnergie) {
		assert (direction != null && origine != null && rayonPere != null); // TODO : double ?
		this.direction = direction.copie();
		this.origine = origine.copie();
		this.pixelPere = rayonPere.getPixelPere();
		this.rayonPere = rayonPere;
		this.couleur = rayonPere.getCouleur();
		this.nbRebond = rayonPere.getNbRebonds() + 1;
		this.pCouleur = pCouleur;
		this.pEnergie = pEnergie;
	}



	/** Constructeur PARTIEL uniquement utile pour certaines variables locales
	 * @param direction
	 * @param origine
	 */
	public Rayon(Vecteur direction, Point origine) {
		assert (direction != null && origine != null);
		this.direction = direction.copie();
		this.origine = origine.copie();
		this.pixelPere = null;
		this.rayonPere = null;
		this.nbRebond = 0;
	}
	//-------------------------------------------------------------------------------------------
	
	/** Obtenir la vecteur directeur du rayon
	 * @return Vecteur directeur du rayon
	 */
	public Vecteur getDirection() {
		return this.direction;
	}
	
	/** Obtenir la couleur du rayon
	 * @return Couleur du rayon
	 */
	public Color getCouleur() {
		return this.couleur;
	}
	
	/**
	 * Modifier la couleur du rayon
	 * @param r : nouvelle valeur de la composante red/rouge
	 * @param g : nouvelle valeur de la composante green/verte
	 * @param b : nouvelle valeur de la composante blue/bleu
	 */
	public void setCouleur(int r, int v, int b) {
		// TODO assert int ? 
		this.couleur = new Color(r,v,b);
	}
	
	/** Obtenir l'origine du rayon
	 * @return point d'origine du rayon
	 */
	public Point getOrigine() {
		return this.origine;
	}
	
	/** Obtenir le nombre de rebonds realise par le rayon
	 * @return nombre de rebonds du rayon
	 */
	public int getNbRebonds() {
		return this.nbRebond;
	}
	
	/** Obtenir le pixel de depart du rayon depuis l'ecran
	 * @return pixel Pere
	 */
	public Pixel getPixelPere() {
		return this.pixelPere;
	}
	
	/** Obtenir la part de la couleur du rayon fils par rapport � la couleur du rayon p�re. 
	 * @return partCouleur la part de la couleur du rayon fils par rapport � la couleur du rayon p�re */
	public double getPartCouleur() {
		return this.pCouleur;
	}
	
	/** Obtenir la part de la couleur du rayon fils par rapport � la couleur du pixel p�re. 
	 * @return partEnergie la part de la couleur du rayon fils par rapport � la couleur du pixel p�re */
	public double getPartEnergie() {
		return this.pEnergie;
	}
	
	/** Modifier la part de la couleur du rayon fils par rapport � la couleur du rayon p�re. 
	 * @param partCouleur la part de la couleur du rayon fils par rapport � la couleur du rayon p�re */
	public void setPartCouleur(double partCouleur) {
		this.pCouleur = partCouleur;
	}
	
	/** Obtenir la part de la couleur du rayon fils par rapport � la couleur du pixel p�re. 
	 * @param partCouleur la part de la couleur du rayon fils par rapport � la couleur du pixel p�re */
	public void setPartEnergie(double partEnergie) {
		this.pEnergie = partEnergie;
	}
	
	/** Fonction donnant la moyenne d'une couleur entre deux rayons
	 * Elle ne sera surement pas exploit�e ici
	 */
	public Color moyenneCouleur( Rayon rayon ) {
		assert (rayon != null);
		int rouge = (this.couleur.getRed() + rayon.getCouleur().getRed()) / 2;
		int vert = (this.couleur.getGreen() + rayon.getCouleur().getGreen()) / 2;
		int bleu = (this.couleur.getBlue() + rayon.getCouleur().getBlue()) / 2;
		return new Color(rouge, vert, bleu);
	}

}
