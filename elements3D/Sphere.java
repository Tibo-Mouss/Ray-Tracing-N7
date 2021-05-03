/**
 * 
 */
package elements3D;

import rayTracing .*;
import utilitaire .*;

import java.io.Serializable;
import java.lang.Math;

/** Sphere est une implantation de l'interface Objet3D. Cette classe permet
 * de definir la forme 3D specifique d'une sphere à partir d'un ensemble de
 * propriétés materielles (couleur...), d'une origine (point) et d'un rayon
 * (distance à l'origine).
 * @author Edgar
 *
 */
public class Sphere implements Objet3D, Serializable {
	
	private static final long serialVersionUID = -5841917298616385596L;
	private static int compteur = 0; // compteur pour les noms par défaut
	
	/** Ensembles des proprietes de la sphere.*/
	private Properties properties;
	
	/** Nom de la sphère */
	private String nom;
	
	/** Point d'origine de la sphere.*/
	private Point origine;
	
	/** Rayon de la sphere.*/
	private double rayon;
	
	/**
	 * Creer une sphere à partir d'un point d'origine, d'un rayon et d'un nom
	 * @param origine : coordonnees du centre de la sphere
	 * @param rayon : rayon de la sphere
	 * @pre origine != null && rayon > 0.0
	 */
	public Sphere(Point origine, double rayon, String nom) {
		assert origine != null && rayon > 0.0;
		double x = origine.getX();
		double y = origine.getY();
		double z = origine.getZ();
		this.origine = new Point(x, y, z);
		this.rayon = rayon;
		this.properties = new Properties();
		this.nom = nom;
	}
	
	/**
	 * Creer une sphere à partir d'un point d'origine et d'un rayon
	 * @param origine : coordonnees du centre de la sphere
	 * @param rayon : rayon de la sphere
	 * @pre origine != null && rayon > 0.0
	 */
	public Sphere(Point origine, double rayon) {
		this(origine, rayon, "Sphere" + ++compteur);
	}
	
	@Override
	public Point estTraversePar(Rayon r) {
		assert r != null;
		// le rayon se décrit comme r = p + td, avec :
		// p origine de r
		double px = r.getOrigine().getX();
		double py = r.getOrigine().getY();
		double pz = r.getOrigine().getZ();
		 // d vect directeur de r
		double dx = r.getDirection().getX();
		double dy = r.getDirection().getY();
		double dz = r.getDirection().getZ();
		
		// l'équation de la sphère est (x-l)²+ (y-m)²+ (z-n)² = rayon²,
		// avec l,m,n les coordonnées de son origine.
		double l = this.origine.getX();
		double m = this.origine.getY();
		double n = this.origine.getZ();
		
		// alors l'existence de solutions t donnant 0,1 ou 2 points de r
		// traversant la sphère dépend des solutions de l'équation
		// at² + bt + c = 0, avec :
		double a = dx*dx + dy*dy + dz*dz;
		double b = 2.0 * (dx * (px - l) + dy * (py - m) + dz * (pz - n));
		double c = Math.pow(px - l, 2) + Math.pow(py - m, 2) + Math.pow(pz - n, 2) - this.rayon*this.rayon;
		
		double delta = b * b - 4 * a * c;
		if (delta >= 0.0) {
			
			// cas 2 racines ou 1 racine double:
			double t1 = (-b + Math.sqrt(delta)) / (2.0 * a);
			double t2 = (-b - Math.sqrt(delta)) / (2.0 * a);
			
			// on cherche la solution t positive la plus petite
			double t;
			// (car on cherche dans le même sens que celui du rayon)
			if (t1 < 0.0 && t2 < 0.0) {
				return null; // pas de solution t positive
			} else if (t1 >= 0.0 && t2 >= 0.0) {
				t = Math.min(t1, t2); // 2 solutions positives
			} else {
				t = Math.max(t1, t2); // 2 solutions de signe opposé
			}
			Point intersection = new Point(px, py, pz);
			Vecteur deplacement = new Vecteur(dx, dy, dz);
			deplacement = deplacement.multiplication(t);
			// on récupère bien le point d'intersection à p+t*deplacement
			intersection.translater(deplacement);
			if (intersection.equals(r.getOrigine(), 0.001)) {
				return null;
			} else {
				return intersection;
			}
		} else {
			// cas où il n'y a pas de solution:
			return null;
		}
	}
	
	public double getRayon() {
		return this.rayon;
	}
	
	public Point getOrigine() {
		return new Point(this.origine.getX(), this.origine.getY(), this.origine.getZ());
	}
	
	@Override
	public Vecteur directionReflexion(Rayon r, Point p) {
		assert r != null && p != null;
		Vecteur projection = new Vecteur(r.getDirection().getX(),r.getDirection().getY(),r.getDirection().getZ());
		projection.retirerProjection(this.getNormal(p, null));
		
		Vecteur dir_reflexion = r.getDirection().soustraire(projection.multiplication(2));
		dir_reflexion = dir_reflexion.soustraire(dir_reflexion.multiplication(2));
		return dir_reflexion;
	}
	
	@Override
	public boolean getSelfOmbre(Point impact, Rayon rayon, Lumiere lumiere) {
		assert impact != null && lumiere != null; // rayon peut être null pour la sphère
		// la sphére fait de l'ombre au point si le vecteur qui va de la
		// lumière vers l'impact traverse un autre point de la sphère
		Rayon lumVersSphere = new Rayon(new Vecteur(lumiere.getCentre(), impact), lumiere.getCentre());
		Point impactReel = this.estTraversePar(lumVersSphere);
		return !((impactReel == null) || (impactReel.equals(impact,Objet3D.EPSILON)));
	}
	
	@Override
	public void translater(double dx, double dy, double dz) {
		this.origine.translater(dx, dy, dz);
	}

	@Override
	public void rotation(double rx, double ry, double rz) {
		// La rotation d'une sphère n'implique pas de changement
	}

	@Override
	public Materiau getMateriau(int num) {
		assert 0 <= num && num < Properties.NB_MATERIAUX;
		return this.properties.getMateriau(num);
	}

	@Override
	public String getNom() {
		return this.nom;
	}

	@Override
	public void setNom(String newNom) {
		this.nom = newNom;
	}

	@Override
	public Vecteur getNormal(Point impact, Rayon rayon) {
		assert impact != null; // rayon peut être null pour la sphere
		return new Vecteur(this.origine, impact);
	}
	
	@Override
	public String toString() {
		return "Sphere(" + this.nom + ")@(" + this.origine.getX() + ", " + this.origine.getY() + ", " + this.origine.getZ() + ")";
	}

	@Override
	public boolean estHorsScene(double dimension, Point centre) {
		return this.origine.distance(centre) > dimension;
	}

}
