/**
 * 
 */
package elements3D;

import rayTracing .*;
import utilitaire .*;

import java.io.Serializable;
import java.lang.Math;

/** Cylindre est une implantation de l'interface Objet3D. Cette classe permet
 * de definir la forme 3D specifique d'un cylindre à partir d'un ensemble de
 * propriétés materielles (couleur...), d'une origine (point) et d'un rayon
 * de sa base inferieure (distance à l'origine de la base inferieur et
 * non superieure , on prendera cette convention) et une hauteur.
 * @author Aymane
 *
 */
public class Cylindre implements Objet3D, Serializable {
	
	private static final long serialVersionUID = -5841917298616385596L;
	private static int compteur = 0; // compteur pour les noms par défaut
	
	/** Ensembles des proprietes du cylindre.*/
	private Properties properties;
	
	/** Nom du cylindre */
	private String nom;
	
	/** rayon de la base du cylindre */
	private double rbase;
	
	/** Point d'origine de la base du cylindre.*/
	private Point origine;
	
	/** vecteur dirigeur du cylindre.*/
	private Vecteur vectdir;
	
	/** hauteur du cylindre.*/
	private double hauteur;
	
	/**
	 * Creer un cylindre à partir d'un point d'origine de sa
	 *  base, du rayon de sa base et d'un nom
	 * @param origine : coordonnees du centre 
	 * de la base du cylindre
	 * @param rbase : rayon de la base cylidre
	 * @param hauteur : hauteur du cylindre
	 * @param nom : nom du cylindre
	 * @pre origine != null && rayon > 0.0
	 */
	public Cylindre(Point origine, double rbase, double hauteur, Vecteur vectdir, String nom) {
		assert origine != null && rbase > 0.0;
		double x = origine.getX();
		double y = origine.getY();
		double z = origine.getZ();
		this.origine = new Point(x, y, z);
		this.rbase = rbase;
		this.hauteur = hauteur;
		this.vectdir = vectdir;
		this.properties = new Properties();
		this.nom = nom;
	}
	
	/**
	 * Creer un cylindre à partir d'un point d'origine de sa
	 *  base, du rayon de sa base .
	 * @param origine : coordonnees du centre 
	 * de la base du cylindre
	 * @param rbase : rayon de la base cylidre
	 * @param hauteur : hauteur du cylindre
	 * @pre origine != null && rayon > 0.0
	 */
	public Cylindre(Point origine, double rbase, double hauteur, Vecteur vectdir) {
		this(origine, rbase, hauteur, vectdir, "Cylindre" + ++compteur);
	}
	
	// obtenir un point p = p + dir*t pfv : point à travers le vecteur
	private Point pfv(Point p, Vecteur dir,double t) {
		Point po = new Point(dir.getX(),dir.getY(),dir.getZ());
		return p.sommer(po.multiplier(t));
	}
	
	@Override
	public Point estTraversePar(Rayon r) {
		assert r != null;
	
		// le rayon se décrit comme q = p + tv, avec :
		// p origine de q
		Point p = r.getOrigine();
		 // v vect directeur de r
		Vecteur v = r.getDirection();
		
		// l'équation du cylindre  est (q - pa - (va,q - pa)va)² - r² = 0 ,
		// avec pa le point originedu cylindre.
		Point pa = this.getOriginebase();

		//hauteur du cylindre 
		
		double h = this.getHauteur() ;
		// et va le vecteur de direction du cylindre 
		Vecteur va = this.getVectdir();
		va.normaliser();
		
		//obtenir le point du haut du cylindre : le centre de la deuxieme base 
		Point som = new Point(va.multiplication(h/va.module()).getX(),va.multiplication(h/va.module()).getY(),va.multiplication(h/va.module()).getZ());
		Point p2 = pa.sommer(som);
		

		//deltap : delp = p-pa
		Vecteur delp = new Vecteur(pa,p);
		
		//(.,.) designe le produit scalaire
		// alors l'existence de solutions t donnant 0,1 ou 2 points de r
		// traversant le cylindre dépend des solutions de l'équation
		// at² + bt + c = 0, avec :
		double a = Math.pow(v.soustraire(va.multiplication(v.produitScalaire(va))).module(), 2);
		double b = 2*(v.soustraire(va.multiplication(v.produitScalaire(va)))).produitScalaire(delp.soustraire(va.multiplication(delp.produitScalaire(va))));
		double c = Math.pow(delp.soustraire(va.multiplication(delp.produitScalaire(va))).module(),2) - this.rbase*this.rbase;
		
		double delta = b * b - 4 * a * c;
		if (delta >= 0.0) {
			// cas 2 racines ou 1 racine double:
			double t1 = (-b + Math.sqrt(delta)) / (2.0 * a);
			double t2 = (-b - Math.sqrt(delta)) / (2.0 * a);
			
			
			// on cherche la solution t positive la plus petite
			double tsol1 = 10e10;
			double tsol2 = 10e10;
			double tsol3 = 10e10;
			double tsol4 = 10e10;
			double t;
			
			Plan plan1 = new Plan(va,pa);
			Plan plan2 = new Plan(va,p2);
			
			Point inter1 = plan1.estTraversePar(r);
			Point inter2 = plan2.estTraversePar(r);
			
			if (inter1 != null ) {
				if (pa.distance(inter1) <= this.rbase) {
					tsol1 = (inter1.soustraire(p)).getX()/v.getX();
				}
			}
			if (inter2 != null ) {
				if (p2.distance(inter2) <= this.rbase) {
					tsol2 = (inter2.soustraire(p)).getX()/v.getX();
				}
			}
			

			if (t1 > 0.0 ) {
				Vecteur test1 = new Vecteur(pa,pfv(p, v, t1));
				Vecteur test2 = new Vecteur(p2,pfv(p, v, t1));
				if (va.produitScalaire(test1) > 0.0 && va.produitScalaire(test2) < 0.0  ) {
					tsol3 = t1;
				}
			}
			if (t2 > 0.0 ) {
				Vecteur test1 = new Vecteur(pa,pfv(p, v, t2));
				Vecteur test2 = new Vecteur(p2,pfv(p, v, t2));
				if (va.produitScalaire(test1) > 0.0 && va.produitScalaire(test2) < 0.0  ) {
					tsol4 = t2;
				} else {
					
				}
			}
			

			
				t = Math.min(Math.min(tsol1,tsol2), Math.min(tsol3,tsol4)); // min des 4 solutions
			
			if (t != 10e10) {
				return pfv(p, v, t);
			} else {
				return null;
			}
		}
		return null;
	}
	
	public double getRayonbase() {
		return this.rbase;
	}
	
	public Point getOriginebase() {
		return new Point(this.origine.getX(), this.origine.getY(), this.origine.getZ());
	}
	
	public double getHauteur() {
		return this.hauteur;
	}
	
	public Vecteur getVectdir() {
		return this.vectdir;
	}
	
	@Override
	public Vecteur directionReflexion(Rayon r, Point p) {
		assert r != null && p != null;
		Vecteur projection = new Vecteur(r.getDirection().getX(),r.getDirection().getY(),r.getDirection().getZ());
		projection.retirerProjection(this.getNormal(p, null));
		
		Vecteur dir_reflexion = r.getDirection().soustraire(projection.multiplication(2));
		dir_reflexion = dir_reflexion.soustraire(dir_reflexion.multiplication(2));
		dir_reflexion.afficher();
		return dir_reflexion;
	}
	
	@Override
	public boolean getSelfOmbre(Point impact, Rayon rayon, Lumiere lumiere) {
		assert impact != null && lumiere != null; // rayon peut être null pour le cylindre
		// le cylindre fait de l'ombre au point si le vecteur qui va de la
		// lumière vers l'impact traverse un autre point de la sphère
		Rayon lumVerscylindre = new Rayon(new Vecteur(lumiere.getCentre(), impact), lumiere.getCentre());
		Point impactReel = this.estTraversePar(lumVerscylindre);
		return !((impactReel == null) || (impactReel.equals(impact,Objet3D.EPSILON)));
	}
	
	@Override
	public void translater(double dx, double dy, double dz) {
		this.origine.translater(dx, dy, dz);
	}

	@Override
	public void rotation(double rx, double ry, double rz) {
		// La rotation d'un cylindre pas encore implanté
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
		assert impact != null; // rayon peut être null pour le cylindre
		Point pa = this.getOriginebase();
		double h = this.getHauteur();
		Vecteur va = this.getVectdir();
		//obtenir le point du haut du cylindre : le centre de la deuxiele base 
		Point som = new Point(va.multiplication(h/va.module()).getX(),va.multiplication(h/va.module()).getY(),va.multiplication(h/va.module()).getZ());
		Point p2 = pa.sommer(som);
		

		double distance1 = Math.pow(this.getOriginebase().distance(impact), 2)-Math.pow(this.getRayonbase(), 2);
		double distance2 = Math.pow(p2.distance(impact), 2)-Math.pow(this.getRayonbase(), 2);
		double distance;
		if (distance1 > 0.0 && distance2 > 0.0) {
			distance = Math.sqrt(Math.pow(this.getOriginebase().distance(impact), 2)-Math.pow(this.getRayonbase(), 2));
		} else if (distance1 <= 0.0 && distance2 > 0.0) {
			return va.multiplication(-1);
		} else if (distance2 <= 0.0 && distance1 > 0.0 ) {
			return va;
		} else {
			if (distance1 < distance2) {
				return va.multiplication(-1);
			} else {
				return va;
			}
		}
		// calcul du vecteur normal à la surface verticale du cylindre 
		Point p = new Point(impact.getX(),impact.getY(),impact.getZ());
		p.translater(this.getVectdir().multiplication(-distance/this.getVectdir().module()));
		
		return new Vecteur(this.getOriginebase(),p);
	}
	
	@Override
	public String toString() {
		return "Cylindre(" + this.nom + ")@(base : (" + this.origine.getX() + ", " + this.origine.getY() 
		+ ", " + this.origine.getZ() + ") rayon de base : " + this.getRayonbase() + ") hauteur :" 
				+ this.getHauteur() + ")";
	}

	@Override
	public boolean estHorsScene(double dimension, Point centre) {
		return this.origine.distance(centre) > dimension;
	}

}
