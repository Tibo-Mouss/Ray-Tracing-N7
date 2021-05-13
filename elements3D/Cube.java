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
 * Classe permettant d'utiliser / manipuler un plan pour le raytracing
 * @author tibo, en partenariat avec ses erreurs de programmation
 *
 */

public class Cube implements Objet3D, Serializable {
	
	private static final long serialVersionUID = 5907153455163217165L;
	private static int compteur = 0; // compteur pour les noms par défaut

	/** Ensembles des proprietes du plan.*/
	private Materiau properties;
	
	/** Nom du plan */
	private String nom;
	
	/** Centre du cube */
	private Point centre;
	
	/** Arete du cube */
	private double arete;
	
	/** Liste des plans constituants le cube 
	 * Toutes les normales sont dirigees vers l'exterieur du cube */
	private ArrayList<Plan> plans;

	public Cube(Point centre, double arete , String nom) {
		this.properties = new Materiau();
		this.nom = nom;
		
		this.arete = arete;
		this.centre = centre.copie();
		
		Plan plan;
		Point point;
		this.plans = new ArrayList<Plan>();
		
		point = new Point(this.centre.getX() + this.arete/2, this.centre.getY(), this.centre.getZ());
		plan = new Plan(new Vecteur(1,0,0), point, "0", this.properties);
		plans.add(plan.copie());
		
		point = new Point(this.centre.getX() - this.arete/2, this.centre.getY(), this.centre.getZ());
		plan = new Plan(new Vecteur(-1,0,0), point, "1", this.properties);
		plans.add(plan.copie());
		
		point = new Point(this.centre.getX(), this.centre.getY() + this.arete/2, this.centre.getZ());
		plan = new Plan(new Vecteur(0,1,0), point, "2", this.properties);
		plans.add(plan.copie());
		
		point = new Point(this.centre.getX(), this.centre.getY() - this.arete/2, this.centre.getZ());
		plan = new Plan(new Vecteur(0,-1,0), point, "3", this.properties);
		plans.add(plan.copie());
		
		point = new Point(this.centre.getX(), this.centre.getY(), this.centre.getZ() + this.arete/2);
		plan = new Plan(new Vecteur(0,0,1), point, "4", this.properties);
		plans.add(plan.copie());
		
		point = new Point(this.centre.getX(), this.centre.getY(), this.centre.getZ() - this.arete/2);
		plan = new Plan(new Vecteur(0,0,-1), point, "5", this.properties);
		plans.add(plan.copie());
	}
	
	public Cube(Point centre, double arete) {
		this(centre, arete, "Cube" + ++compteur);
	}
	
	//----------------------------------------------------------------------
	// Methodes get
	
	@Override
	public Propriete getMateriau(int num) {
		assert 0 <= num && num < Materiau.NB_PROPRIETES;
		return this.properties.getMateriau(num);
	}
	
	@Override
	public String getNom() {
		return this.nom;
	}
	
	/**
	 * Retourne la normale au point d'impact et aussi son sens grace au rayon
	 * @param impact : Point d'impact du rayon qui appartient au plan
	 * @param rayon : rayon qui va peruter le plan
	 */
	@Override
	public Vecteur getNormal(Point impact, Rayon rayon) {
		assert impact != null && rayon != null;
		int indice = appartientCube(impact);
		
		//Assert pt d'impact appartient au cube
		if (indice == -1) {
			System.out.println("getNormal : Le point n'appartient pas au cube");
			return null;
		}
		
		return this.plans.get(indice).getNormal(impact, rayon);
	}
	
	/**
	 * Retourne true si l'objet se fait de l'ombre lui meme
	 * 
	 * On deduit la face qui est en jeu. Et paf ça fait des chocapics
	 * 
	 * @param impact : Point d'impact du rayon qui appartient au plan
	 * @param rayon : rayon qui va percuter le plan
	 * @param lumiere : Lumiere de la scene
	 **/
	@Override
	public boolean getSelfOmbre(Point impact, Rayon rayon, Lumiere lumiere) {
		assert impact != null && rayon != null && lumiere != null;
		int indice = appartientCube(impact);
		
		//Assert pt d'impact appartient au cube
		if (indice == -1) {
			System.out.println("getSelfOmbre : Le point n'appartient pas au cube");
			return false;
		}
		
		return this.plans.get(indice).getSelfOmbre(impact, rayon, lumiere);
	}
	
	
	//----------------------------------------------------------------------
	//Autres methodes

	/**
	 * Determine si le cube est traverse par un rayon et son premier point d'impact
	 * (il y en aura presque toujours deux)
	 * @param r : rayon
	 */
	@Override
	public Point estTraversePar(Rayon r) {
		assert r != null;
		int i, indice;
		double distanceMin = 0.0;
		Point point;
		Point pointReturn = null;
		
		for (i = 0; i<6; i++) {
			point = this.plans.get(i).estTraversePar(r);
			indice = this.appartientCube(point);
			
			if (point != null & indice != -1) {
				if ((distanceMin > point.distance(r.getOrigine()) | distanceMin < Objet3D.EPSILON) ) {
					pointReturn = point.copie();
					distanceMin = pointReturn.distance(r.getOrigine());
				}
			}
		}
		
		return pointReturn;
	}

	/** Determine la direction et sens du rayon reflechi contre le cube au point d'impact p
	 * @param r : rayon allant frapper le cube
	 * @param p : point où a lieu la collision
	 */
	@Override
	public Vecteur directionReflexion(Rayon r, Point p) {
		assert r != null && p != null;
		int indice = appartientCube(p);
		
		//Assert pt d'impact appartient au cube
		if (indice == -1) {
			System.out.println("Dir Reflexion : Le point n'appartient pas au cube");
			return null;
		}
		
		return this.plans.get(indice).directionReflexion(r, p);
	}

	/**Translate le cube de dx, dy, dz
	 * @param dx,dy,dz : De combien sur chaque axe le plan va etre translate
	 */
	@Override
	public void translater(double dx, double dy, double dz) {
		int i;
		
		for (i = 0; i<6; i++) {
			this.plans.get(i).translater(dx, dy, dz);
		}
	}

	/**Effectue une rotation autour des axes x, y, z classiques
	 * @param rx : premiere roation effectuee
	 * @param ry : deuxieme roation effectuee
	 * @param rz : troisieme roation effectuee
	 */
	@Override
	public void rotation(double rx, double ry, double rz) {
		int i;
		
		for (i = 0; i<6; i++) {
			this.plans.get(i).rotation(rx, ry, rz);
		}
		//Ca me parait un peu foireux comme methode faudra voir apres ave l'interface graphique si
		//ça marche comme ça.
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
	 * @return Renvoie l'indice du plan correspondant
	 *  			Si le point n'appartient pas au cube --> renvoie -1
	 * 				Si le point appartient à une arete/coin --> renvoie un plan au hasard
	 * 					entre ceux qu'il touche
	 */
	public int appartientCube(Point point) {
		int i;
		Vecteur relatifPointPlan;
		double produitScalaire;
		
		if (point == null) {
			return -1;
		}
		
		for (i = 0 ; i<6 ; i++) {
			relatifPointPlan = new Vecteur(point, this.plans.get(i).getPoint());
			produitScalaire = this.plans.get(i).getNormale().produitScalaire(relatifPointPlan);
			
			if ( Math.abs(produitScalaire) < Objet3D.EPSILON) {
				//on sait que le point appartient au plan, mais on ne sait pas si il appartient
				//a la face du carre
								
				Vecteur vecteurDeTest = new Vecteur(1.0, 1.0, 1.0);
				vecteurDeTest = vecteurDeTest.soustraire(this.plans.get(i).getNormale().abs());
				//VecteurDeTest va servir a pouvoir tester sur les deux autres coordonnees autres
				//que la normale
				
				//Check si il appartient au segment [-arete ; arete] sur chaque coordonnee
				if ( 	(vecteurDeTest.getX() < Objet3D.EPSILON | 
							Math.abs(relatifPointPlan.getX()) < this.arete/2 ) &
						(vecteurDeTest.getY() < Objet3D.EPSILON | 
							Math.abs(relatifPointPlan.getY()) < this.arete/2 ) &
						(vecteurDeTest.getZ() < Objet3D.EPSILON | 
							Math.abs(relatifPointPlan.getZ()) < this.arete/2 ) ) {
					return i;
				}
				
			}
		}
		
		return -1;
	}
	
	
	
	/**
	 * Affiche les attributs du cube dans la console
	 */
	@Override
	public String toString() {
		return "Cube(" + this.nom + ")@(" + this.centre.getX() + ", " + this.centre.getY() + ", " + this.centre.getZ() + ") arête : "+this.arete;
	}
	
	public void afficherCollection() {
		Iterator iterator = this.plans.iterator();
	    while (iterator.hasNext()) {
	      System.out.println(iterator.next());
	    }
	}

	@Override
	public boolean estHorsScene(double dimension, Point centreScene) {
		return this.centre.distance(centreScene) > dimension;
	}
	
	
	
	

}
