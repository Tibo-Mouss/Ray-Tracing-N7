/**
 * 
 */
package elements3D;

import rayTracing .*;
import utilitaire .*;
import exception.NomVideException;

/** Objet3D représente le type general des objets 3D presents dans une scene
 * @author Edgar
 */
public interface Objet3D {
	
	public static final double EPSILON = 10e-4;
	
	/** Obtenir le nom de l'objet
	 * @return
	 */
	public String getNom();
	
	/** redéfinir le nom de l'objet
	 * @param nom : nouveau nom
	 */
	public void setNom(String nom) throws NomVideException;
	
	/** Indiquer si une objet est traversé par un rayon, et si oui en quel point
	 * @param r : rayon lumineux
	 * @pre r != null
	 * @return point de contact (vaut null si le rayon ne traverse pas l'objet)
	 */
	public Point estTraversePar(Rayon r);
	
	/** Calculer le vecteur directeur du rayon r réfléchi sur l'objet
	 * @param r : rayon incident
	 * @param p : point d'impact/d'incidence
	 * @pre r != null && p != null
	 * @return vecteur directeur du rayon réfléchi
	 */
	public Vecteur directionReflexion(Rayon r, Point p);
	
	/** Indique si l'objet fait de l'ombre au point
	 * @param impact : point considéré
	 * @param rayon : rayon qui touche l'objet en impact
	 * @param lumiere : source à considérer
	 * @pre impact != null && rayon != null && lumiere != null
	 * @return
	 */
	public boolean getSelfOmbre(Point impact, Rayon rayon, Lumiere lumiere);
	
	/** Déplacer un objet dans l'espace
	 * @param dx : translation selon l'axe des x
	 * @param dy : translation selon l'axe des y
	 * @param dz : translation selon l'axe des y
	 */
	public void translater(double dx, double dy, double dz);
	
	/** Appliquer une rotation de l'objet dans l'espace
	 * @param rx : rotation autour de l'axe des x
	 * @param ry : rotation autour de l'axe des y
	 * @param rz : rotation autour de l'axe des z
	 */
	public void rotation(double rx, double ry, double rz);
	
	/** Obtenir un matériau contenant des informations sur l'objet
	 * @param num identificateur de matériau
	 * @pre 0 <= num < Properties.NB_MATERIAUX
	 * @return matériau demandé de l'objet
	 */
	public Propriete getMateriau(int num);
	
	/** Obtenir le vecteur normal à l'objet au point d'impact
	 * @param impact : point d'impact
	 * @pre impact != null && rayon != null
	 * @return vecteur normal
	 */
	public Vecteur getNormal(Point impact, Rayon rayon);
	
	/** Indique si l'objet se trouve hors de la scène en fonction de sa taille
	 * @param dimension : dimension de la scène
	 * @param centre : centre de la scène
	 * @return
	 */
	public boolean estHorsScene(double dimension, Point centre);
}
