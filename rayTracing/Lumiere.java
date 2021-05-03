package rayTracing;
import elements3D .*;
import utilitaire .*;
import java.awt.Color;
import java.io.Serializable;

/**
 * Lumiere est une classe abstraite d�crivant une source de lumi�re.
 * @author jaden
 */
public abstract class Lumiere implements Serializable {

	private static final long serialVersionUID = -3292404395438711430L;
	
	/** Les coordonn�es du centre de la lumi�re.*/
	private Point centre;
	
	/** La couleur de la lumi�re.*/
	private Color couleur;
	
	/** Nom de la lumiere.*/
	private String nom;
	
	/**
	 * Cr�er une lumi�re � partir de son centre, sa couleur et son nom.
	 * @param centre Point de d�part de la lumi�re.
	 * @param couleur Couleur de la lumi�re.
	 * @param nom Nom de la lumi�re.
	 */
	public Lumiere(Point centre, Color couleur, String nom) {
		assert centre != null;
		assert couleur != null;
		assert nom != null;
		assert ! nom.equals("");
		this.centre = centre;
		this.couleur = couleur;
		this.nom = nom;
	}
	
	/** Obtenir le centre de la lumi�re.*/
	public Point getCentre() {
		return this.centre.copie();
	}
	
	/** Obtenir la couleur de la lumi�re.*/
	public Color getCouleur() {
		return this.couleur;
	}
	
	/** Obtenir le nom de la lumi�re.*/
	public String getNom() {
		return this.nom;
	}
	
	/** Modifier le centre de la lumi�re.
	 * @param new_centre : Nouveau centre attribu� � la lumi�re.
	 * */
	public void setCentre(Point new_centre) {
		this.centre = new_centre.copie();
	}
	
	/** Modifier la couleur de la lumi�re.
	 * @param couleur : Nouvelle couleur attribu�e � la lumi�re.
	 * */
	public void setCouleur(Color couleur) {
		this.couleur = couleur;
	}
	
	/** Modifier le nom de la lumi�re.
	 * @param nom : Nouveau nom attribu� � la lumi�re
	 * */
	public void setNom(String nom) {
		assert nom != null;
		assert !nom.equals("");
		this.nom = nom;
	}
	
	/** Translater la lumi�re selon les trois axes x, y et z.
	 * 
	 * @param dx translation en x
	 * @param dy translation en y
	 * @param dz translation en z
	 */
	public void translater(double dx, double dy, double dz) {
		this.centre.translater(dx, dy, dz);
	}
	
	/** Translater la lumi�re d'un vecteur.
	 * 
	 * @param translation le vecteur de translation 
	 */
	public void translater(Vecteur translation) {
		this.centre.translater(translation);
	}
	
	/** Renvoie le point pr�cis appartenant � la lumi�re �clairant le point d'impact. 
	 * @param impact le point d'impact du rayon sur un objet
	 * @return le point de la lumi�re �clairant impact
	 */
	public abstract Point getSource(Point impact);
	
	/** Indique si le point impact est dans le champ de la lumi�re.
	 * 
	 * @param impact le point d'impact du rayon sur un objet
	 * @return true si impact peut �tre �clair� par la lumi�re
	 */
	public abstract boolean eclaire(Point impact);
	
	/** Indique si la lumi�re est hors de la scene.
	 * 
	 * @param dimension le rayon de la scene
	 * @parma centre le centre de la scene
	 * @return true si la lumiere est hors de la scene, false sinon.
	 */
	public abstract boolean estHorsScene(double dimension, Point centre);

}
