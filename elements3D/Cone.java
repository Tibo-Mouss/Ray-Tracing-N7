package elements3D;
import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;

import rayTracing.Rayon;
import utilitaire.Point;
import utilitaire.Vecteur;

import java.io.Serializable;

import rayTracing.Lumiere;

/**
 * Classe permettant d'utiliser / manipuler une pyramide pour le raytracing
 * @author tibo, en partenariat avec ses erreurs de programmation
 *
 */

public class Cone implements Objet3D, Serializable {
	
	
	private static final long serialVersionUID = -789255897531917008L;

	private static int compteur = 0; // compteur pour les noms par défaut

	/** Ensembles des proprietes de la pyramide.*/
	private Properties properties;
	
	/** Nom de la pyramide */
	private String nom;
	
	/** Centre du cercle de la pyramide */
	private Point centreBase;
	
	/** Hauteur de la pyramide */
	private double hauteur;
	
	/** rayon de la base de la pyramide */
	private double rayon;
	
	/** Hauteur de la pyramide */
	private Vecteur VHaut;
	
	/** Plan associe a la base du cercle */
	private Plan planCercle;
	

	public Cone(Point centreBase, Vecteur VHaut, double hauteur, double rayon, String nom) {
		this.properties = new Properties();
		this.nom = nom;
		
		this.centreBase = centreBase.copie();
		this.planCercle = new Plan(VHaut, centreBase, "Plan associe a la base du cercle");
		
		this.VHaut = VHaut.copie();
		this.VHaut.normaliser();
		
		this.hauteur = hauteur;
		this.rayon = rayon;
	}
	
	public Cone(Point centre_base, Vecteur VHaut, double hauteur, double rayon) {
		this(centre_base, VHaut, hauteur, rayon, "Pyramide" + ++compteur);
	}
	
	//----------------------------------------------------------------------
	// Methodes get
	
	@Override
	public Materiau getMateriau(int num) {
		assert 0 <= num && num < Properties.NB_MATERIAUX;
		return this.properties.getMateriau(num);
	}
	
	@Override
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Retourne la normale au point d'impact et aussi son sens grace au rayon
	 * 
	 * Deux cas :
	 * 		Si il touche la base du cercle, easy
	 * 		Si il touche le reste :
	 * 		
	 * @param impact : Point d'impact du rayon qui appartient a la pyramide
	 * @param rayon : rayon qui va peruter la pyramide
	 */
	@Override
	public Vecteur getNormal(Point impact, Rayon rayon) {
		assert impact != null && rayon != null;
		int indice = appartientPyramide(impact);
		
		//Assert pt d'impact appartient a la pyramide
		if (indice == 0) {
			System.out.println("getNormal : Le point n'appartient pas a la pyramide");
		} else if( indice == 1) {
			return this.planCercle.getNormal(impact, rayon);
		} else if( indice == 2) {
			Vecteur normale = new Vecteur(this.centreBase, impact);
			normale.retirerProjection(VHaut);
			return normale;
		}
		
		return null;
	}
	
	/**
	 * Retourne true si l'objet se fait de l'ombre lui meme
	 * 
	 * On deduit la face qui est en jeu. Et paf ça fait des chocapics
	 * 
	 * @param impact : Point d'impact du rayon qui appartient a la pyramide
	 * @param rayon : rayon qui va percuter la pyramide
	 * @param lumiere : Lumiere de la scene
	 **/
	@Override
	public boolean getSelfOmbre(Point impact, Rayon rayon, Lumiere lumiere) {
		assert impact != null && rayon != null && lumiere != null;
		int indice = appartientPyramide(impact);
		
		//Assert pt d'impact appartient a la pyramide
		if (indice == 0) {
			System.out.println("getSelfOmbre : Le point n'appartient pas a la pyramide");
			return false;
		} else if (indice == 1) {
			return this.planCercle.getSelfOmbre(impact, rayon, lumiere);
		} else if (indice == 2) {
			Vecteur normale = getNormal(impact, rayon);
			Plan plan = new Plan(normale, impact);
			return plan.getSelfOmbre(impact, rayon, lumiere);
			//A verifier si ca donne des resultats corrects
		}
		
		return false;
	}
	
	
	//----------------------------------------------------------------------
	//Autres methodes

	/** https://www.geometrictools.com/Documentation/IntersectionLineCone.pdf
	 * Determine si la pyramide est traversee par un rayon et son premier point d'impact
	 * (il y en aura presque toujours deux)
	 * @param r : rayon
	 */
	@Override
	public Point estTraversePar(Rayon r) {
		assert r != null;
		
		//Testons si il traverse la base :
		Point impactBase = this.planCercle.estTraversePar(r);
		if ( impactBase != null && impactBase.distance(this.centreBase) > this.rayon) {
			impactBase = null;
		}
		
		//Test si il traverse le reste
		Vecteur D = this.VHaut.multiplication(-1);
		Vecteur U = r.getDirection();
		double gamma = this.hauteur / Math.sqrt(this.hauteur*this.hauteur + this.rayon*this.rayon);
		Point P = r.getOrigine();
		Point V = this.centreBase.copie();
		V.translater(VHaut.multiplication(hauteur));
		Vecteur delta = new Vecteur(V,P);
		double c2 = Math.pow(D.produitScalaire(U), 2) - gamma * gamma * U.produitScalaire(U);
		double c1 = (D.produitScalaire(U))*(D.produitScalaire(delta)) - gamma*gamma * U.produitScalaire(delta);
		double c0 = Math.pow(D.produitScalaire(delta), 2) - gamma*gamma * delta.produitScalaire(delta);
		
		double t1 = 0;
		double t2 = 0;
		
		if (Math.abs(c2) > Objet3D.EPSILON) {
			double petitDelta = c1*c1 - c0*c2;
			if (petitDelta > 0) {
				//Cela veut dire que le rayon intersecte bien le double-sided cone
				t1 = -c1 + Math.sqrt(petitDelta)/c2;
				t2 = -c1 - Math.sqrt(petitDelta)/c2;
			}
		} else if (Math.abs(c2) < Objet3D.EPSILON & Math.abs(c1) > Objet3D.EPSILON) {
			t1 = -c0 / (2*c1);
			t2 = t1;
		} else if (Math.abs(c2) < Objet3D.EPSILON & Math.abs(c1) < Objet3D.EPSILON) {
			if (Math.abs(c0) < Objet3D.EPSILON) {
				//System.out.println("Rayon se confond avec le cote du cone");
			}
		}
		
		if (t1 < 0 | t2 < 0) {
			System.out.println("t1 ou t2 est negatif chef : "+t1+"   "+t2);
		}
		
		Point point1 = null;
		if (t1 > Objet3D.EPSILON) {
			point1 = r.getOrigine();
			point1.translater(r.getDirection().multiplication(t1));
			if (appartientPyramide(point1) != 2) {
				point1 = null;
			}
		}
		
		Point point2 = null;
		if (t2 > Objet3D.EPSILON) {
			point2 = r.getOrigine();
			point2.translater(r.getDirection().multiplication(t2));
			if (appartientPyramide(point2) != 2) {
				point2 = null;
			}
		}
			
		//J'ai tous les points, il faut donc tester lequel est le plus proche de l'origine
		double distanceMin = 0;
		Point lePlusProche = null;
		if (impactBase != null) {
			double distanceBase = impactBase.distance(r.getOrigine());
			lePlusProche = impactBase;
			distanceMin = distanceBase;
		}
		if (point1 != null) {
			double distancep1 = point1.distance(r.getOrigine());
			if (distanceMin < Objet3D.EPSILON | distanceMin > distancep1) {
				lePlusProche = point1;
				distanceMin = distancep1;
			}
		}
		if (point2 != null) {
			double distancep2 = point2.distance(r.getOrigine());
			if (distanceMin < Objet3D.EPSILON | distanceMin > distancep2) {
				lePlusProche = point2;
				distanceMin = distancep2;
			}
		}
		
		return lePlusProche;
	}

	/** Determine la direction et sens du rayon reflechi contre la pyramide au point d'impact p
	 * @param r : rayon allant frapper la pyramide
	 * @param p : point où a lieu la collision
	 */
	@Override
	public Vecteur directionReflexion(Rayon r, Point p) {
		assert r != null && p != null;
		int indice = appartientPyramide(p);
		
		//Assert pt d'impact appartient au cube
		if (indice == 0) {
			System.out.println("Dir Reflexion : Le point n'appartient pas a la pyramide");
			return null;
		} else if (indice == 1) {
			return this.planCercle.directionReflexion(r, p);
		} else if (indice == 2) {
			Vecteur projection = new Vecteur(r.getDirection().getX(),r.getDirection().getY(),r.getDirection().getZ());
			projection.retirerProjection(getNormal(p, r));
			
			Vecteur dirReflexion = r.getDirection().soustraire(projection.multiplication(2));
			dirReflexion = dirReflexion.soustraire(dirReflexion.multiplication(2));
			return dirReflexion;
		}
		return null;
	}

	/**Translate la pyramide de dx, dy, dz
	 * @param dx,dy,dz : De combien sur chaque axe la pyramide va etre translate
	 */
	@Override
	public void translater(double dx, double dy, double dz) {
		Vecteur deplacement = new Vecteur(dx,dy,dz);
		
		
		this.centreBase.translater(deplacement);
		this.planCercle.translater(dx, dy, dz);
	}

	/**Effectue une rotation autour des axes x, y, z classiques
	 * @param rx : premiere roation effectuee
	 * @param ry : deuxieme roation effectuee
	 * @param rz : troisieme roation effectuee
	 */
	@Override
	public void rotation(double rx, double ry, double rz) {
		//TODO
	}

	/**
	 * Attribut un nom au cube
	 * @param nom : mon instinct me dit que ce parametre designe le nom de l'objet
	 */
	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * @param point : point d'impact
	 * @return Renvoie l'indice de la pyramide correspondant
	 * 				Si le point n'appartient pas a la pyramide --> renvoie 0
	 *  			Si le point appartient a la base --> renvoie 1
	 * 				Si le point appartient au reste --> renvoie 2
	 */
	public int appartientPyramide(Point point) {
		//Test si ça appartient a la base circulaire
		Vecteur relatif_PointCentreBase = new Vecteur(this.centreBase,point);
		if (this.VHaut.produitScalaire(relatif_PointCentreBase) < Objet3D.EPSILON) {
			//On sait qu'on est dans le plan
			if (point.distance(this.centreBase) > this.rayon) {
				return 1;
			}
		}
		
		//Test si ca appartient au reste
		double abscisse = this.VHaut.produitScalaire(relatif_PointCentreBase);
		Vecteur vecteurOrdonnee = new Vecteur(this.centreBase, point);
		vecteurOrdonnee.retirerProjection(VHaut);
		double ordonnee = vecteurOrdonnee.module();
		if ( abscisse > 0 & abscisse < this.hauteur &
		  Math.abs( ordonnee - (this.rayon - abscisse * this.rayon/this.hauteur)) < Objet3D.EPSILON) {
			return 2;
		}
		
		return 0;
	}
	
	
	
	/**
	 * Affiche les attributs de la pyramide dans la console
	 */
	@Override
	public String toString() {
		return "Pyramide(" + this.nom + ")@(" + this.centreBase.getX() + ", " + this.centreBase.getY() + ", " + this.centreBase.getZ() + ") hauteur : "+this.hauteur;
	}
	
	

	@Override
	public boolean estHorsScene(double dimension, Point centreScene) {
		return this.centreBase.distance(centreScene) > dimension;
	}
	
	
	
	

}
