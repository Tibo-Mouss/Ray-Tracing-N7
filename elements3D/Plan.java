package elements3D;

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

public class Plan implements Objet3D, Serializable {
	
	private static final long serialVersionUID = 5907153455163217165L;
	private static int compteur = 0; // compteur pour les noms par défaut

	/** Ensembles des proprietes du plan.*/
	private Properties properties;
	
	/** Vecteur normal au plan*/
	private Vecteur normale;
	
	/** Point par lequel le plan passe*/
	private Point point;
	
	/** Nom du plan */
	private String nom;

	public Plan(Vecteur normale, Point point, String nom) {
		this.normale = normale.copie();
		this.point = point.copie();
		this.properties = new Properties();
		this.nom = nom;
	}
	
	public Plan(Vecteur normale, Point point) {
		this(normale, point, "Plan" + ++compteur);
	}
	
	public Plan(Vecteur normale, Point point, String nom, Properties proprietes) {
		this.normale = normale.copie();
		this.point = point.copie();
		this.properties = proprietes;
		this.nom = nom;
	}
	
	//----------------------------------------------------------------------
	// Méthodes get
	
	@Override
	public Materiau getMateriau(int num) {
		assert 0 <= num && num < Properties.NB_MATERIAUX;
		return this.properties.getMateriau(num);
	}
	
	@Override
	public String getNom() {
		return this.nom;
	}
	
	public Point getPoint() {
		return this.point;
	}
	
	public Vecteur getNormale() {
		return this.normale;
	}
	
	/**
	 * Retourne la normale au point d'impact et aussi son sens grace au rayon
	 * @param impact : Point d'impact du rayon qui appartient au plan
	 * @param rayon : rayon qui va peruter le plan
	 */
	@Override
	public Vecteur getNormal(Point impact, Rayon rayon) {
		assert impact != null && rayon != null;
		//Assert impact appartient au plan
		Vecteur vPtPlanPtImpact = new Vecteur(impact, this.point);
		if (Math.abs(vPtPlanPtImpact.produitScalaire(this.normale)) > Objet3D.EPSILON) {
			System.out.println("Le point d'impact n'appartient pas au plan");
			return null;
		}
		
		Vecteur direction = rayon.getDirection();
		double dot = direction.produitScalaire(this.normale);
		Vecteur retour = new Vecteur(this.normale.getX(), this.normale.getY(), this.normale.getZ());
		
		// peut-ï¿½tre un problï¿½me si le produit scalaire est nul
		if (dot > 0) {
			retour.multiplication(-1);
		}
		return retour;
	}
	
	/**
	 * Retourne true si l'objet se fait de l'ombre lui meme
	 * @param impact : Point d'impact du rayon qui appartient au plan
	 * @param rayon : rayon qui va percuter le plan
	 * @param lumiere : Lumiere de la scene
	 **/
	@Override
	public boolean getSelfOmbre(Point impact, Rayon rayon, Lumiere lumiere) {
		assert impact != null && rayon != null && lumiere != null;
		//Assert impact appartient au plan
		Vecteur vPtPlanPtImpact = new Vecteur(impact, this.point);
		if (Math.abs(vPtPlanPtImpact.produitScalaire(this.normale)) > Objet3D.EPSILON) {
			System.out.println("Le point d'impact n'appartient pas au plan");
			return false;
		}
		
		Vecteur directionRayon = rayon.getDirection();
		double dotRayon = directionRayon.produitScalaire(this.normale);
		
		Vecteur directionLumiere = new Vecteur(lumiere.getCentre(), this.point);
		double dotLumiere = directionLumiere.produitScalaire(this.normale);
		
		return !(Math.signum(dotLumiere) == Math.signum(dotRayon));
	}
	
	
	//----------------------------------------------------------------------
	//Autres methodes

	/** http://nguyen.univ-tln.fr/share/Infographie3D/trans_raytracing.pdf
	 * Determine sir le plan est traverse par un rayon
	 * @param r : rayon
	 */
	@Override
	public Point estTraversePar(Rayon r) {
		assert r != null;
		double a,b,c,d,i,j,k,originerx,originery,originerz;
		a = this.normale.getX();
		b = this.normale.getY();
		c = this.normale.getZ();
		d = -( a*point.getX() + b*point.getY() + c*point.getZ() );
		
		i = r.getDirection().getX();
		j = r.getDirection().getY();
		k = r.getDirection().getZ();
		
		originerx = r.getOrigine().getX();
		originery = r.getOrigine().getY();
		originerz = r.getOrigine().getZ();
		
		double denominateur = a*i + b*j + c*k;
		if (Math.abs(denominateur) < Objet3D.EPSILON) {
			//System.out.println("Rayon est parallele au plan");
			return null;
		}
		double t = -(a*originerx + b*originery + c*originerz + d) / denominateur;
		if (t < 0) {
			//System.out.println("Le plan est pas dans le sens du vecteur");
			return null;
		}
		
		Point pointDeCollision = new Point(originerx,originery,originerz);
		pointDeCollision.translater(r.getDirection().multiplication(t));
		if (pointDeCollision.equals(r.getOrigine(), 0.001)) {
			return null;
		} else {
			return pointDeCollision;
		}
	}

	/** Determine la direction et sens du rayon reflechi contre le plan au point d'impact p
	 * @param r : rayon allant frapper le plan
	 * @param p : ici inutile puisque la direction de reflexion ne depend pas du point d'impact
	 * pour un plan
	 */
	@Override
	public Vecteur directionReflexion(Rayon r, Point p) {
		assert r != null && p != null;
		Vecteur projection = new Vecteur(r.getDirection().getX(),r.getDirection().getY(),r.getDirection().getZ());
		projection.retirerProjection(this.normale);
		
		Vecteur dirReflexion = r.getDirection().soustraire(projection.multiplication(2));
		dirReflexion = dirReflexion.soustraire(dirReflexion.multiplication(2));
		return dirReflexion;
	}

	/**Translate le plan de dx, dy, dz
	 * @param dx,dy,dz : De combien sur chaque axe le plan va etre translate
	 */
	@Override
	public void translater(double dx, double dy, double dz) {
		this.point.translater(dx, dy, dz);
	}

	/**Effectue une rotation autour des axes x, y, z classiques
	 * @param rx : premiere roation effectuee
	 * @param ry : deuxieme roation effectuee
	 * @param rz : troisieme roation effectuee
	 */
	@Override
	public void rotation(double rx, double ry, double rz) {
		this.normale.rotationXYZ(rx,ry,rz);
	}

	/**
	 * Attribut un nom au plan
	 * @param nom : mon instinct me dit que ce parametre designe le nom de l'objet
	 */
	@Override
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	/**
	 * Affiche les attributs du plan dans la console
	 */
	@Override
	public String toString() {
		return "Plan(" + this.nom + ")@(" + this.point.getX() + ", " + this.point.getY() + ", " + this.point.getZ() + ") de normale : (" + this.normale.getX() + ", " + this.normale.getY() + ", " + this.normale.getZ() + ")";
	}
	
	/**
	 * Retourne la copie du plan pour la robustesse
	 */
	public Plan copie() {
		return new Plan(this.normale, this.point, this.nom, this.properties);
	}

	@Override
	public boolean estHorsScene(double dimension, Point centre) {
		return false;
	}

}
