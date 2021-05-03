package utilitaire;

/** Vecteur represente un vecteur par ses 3 coordonnees :
 * x (abscisse), y (ordonnee), z (hauteur). 
 * @author rapha
 */

public class Vecteur {
	
	/** Abscisse du vecteur.*/
	private double x;
	
	/** Ordonnee du vecteur.*/
	private double y;
	
	/** Hauteur du vecteur.*/
	private double z;
	
	/** Construction d'un vecteur a partir de 3 coordonees.
	 * @param nx : abscisse du vecteur
	 * @param ny : ordonnée du vecteur
	 * @param nz : hauteur du vecteur
	 */
	public Vecteur(double nx, double ny, double nz) {
		this.x = nx;
		this.y = ny;
		this.z = nz;
	}
	
	/**
	 * Construction d'un vecteur a partir de 2 points
	 * @param p1 : Premier point donne
	 * @param p2 : Deuxième point donne
	 */
	public Vecteur(Point p1, Point p2) {
		assert (p1 != null && p2 != null);
		this.x = p2.getX() - p1.getX();
		this.y = p2.getY() - p1.getY();
		this.z = p2.getZ() - p1.getZ();
	}
	
	/** Retourne la copie d'un point en une autre variable
	 * */
	public Vecteur copie() {
		return new Vecteur(this.x,this.y,this.z);
	}
	
	/** Modifier l'abscisse du vecteur.
	 * @param nx : nouvelle abscisse du vecteur.
	 */
	public void setX(double nx) {
		this.x = nx;
	}
	
	/** Modifier l'ordonnee du vecteur.
	 * @param ny : nouvelle ordonnée du vecteur.
	 */
	public void setY(double ny) {
		this.y = ny;
	}
	
	
	/** Modifier la hauteur du vecteur.
	 * @param nz : nouvelle hauteur du vecteur.
	 */
	public void setZ(double nz) {
		this.z = nz;
	}
	
	
	/** Obtenir l'abscisse du vecteur.
	 * @return abcisse du vecteur
	 */
	public double getX() {
		return this.x;
	}
	
	/** Obtenir l'ordonnee du vecteur.
	 * @return ordonnee du vecteur
	 */
	public double getY() {
		return this.y;
	}
	
	/** Obtenir la hauteur du vecteur. 
	 * @return hauteur du vecteur
	 */
	public double getZ() {
		return this.z;
	}
	
	/** Calculer le produit scalaire entre deux vecteurs.
	 * @param v : vecteur avec lequel on veut calculer le produit scalaire
	 * @return produit scalaire entre le recepteur (this) et v
	 */
	public double produitScalaire(Vecteur v) {
		assert (v != null);
		return this.x * v.x + this.y * v.y + this.z * v.z;
	}
	
	/** Obtenir le module d'un vecteur.
	 * @return module du vecteur
	 */
	public double module() {
		return Math.sqrt(this.produitScalaire(this));
	}
	
	/** Soustraire deux vecteurs.
	 * @param v : vecteur que l'on veut soustraire au recepteur (this)
	 * @return soustraction du vecteur recepteur (this) et v
	 */
	public Vecteur soustraire(Vecteur v) {
		assert (v != null);
		return new Vecteur(this.x - v.x, this.y - v.y, this.z - v.z);
	}
	
	/** Sommer deux vecteurs.
	 * @param v : vecteur que l'on veut sommer au recepteur (this)
	 * @return somme du vecteur v et du vecteur recepteur (this)
	 */
	public Vecteur sommer(Vecteur v) {
		assert (v != null);
		return new Vecteur(this.x + v.x, this.y + v.y, this.z + v.z);
	}
	
	/** Multiplier un vecteur par un scalaire.
	 * @param a : coefficient multiplicateur
	 * @return la multiplication entre le vecteur recepteur (this) et le scalaire a 
	 */
	public Vecteur multiplication(double a) {
		return new Vecteur(a * this.x, a * this.y, a * this.z);
	}

	/**
	 * Normaliser le vecteur recepteur (this).
	 */
	public void normaliser() {
		double norme = this.module();
		this.x = this.x/norme;
		this.y = this.y/norme;
		this.z = this.z/norme;
	}

	public void retirerProjection(Vecteur vecteur) {
		assert (vecteur != null);
		Vecteur vecteurCalcul = new Vecteur(this.getX(), this.getY(), this.getZ());
		double scalaire = vecteurCalcul.produitScalaire(vecteur) / (vecteur.module()*vecteur.module());
		vecteurCalcul = vecteurCalcul.soustraire( vecteur.multiplication(scalaire) );
		
		this.setX(vecteurCalcul.getX());
		this.setY(vecteurCalcul.getY());
		this.setZ(vecteurCalcul.getZ());
	}
	
	/**
	 * Afficher les 3 coordonnées du Vecteur.
	 */
	public void afficher() {
		System.out.println("X:"+this.getX() +"   Y:"+this.getY()+"   Z:"+this.getZ());
	}
	
	/**
	 * Rotater le vecteur autour de l'axe des X.
	 * @param rx : angle de rotation autour de X.
	 * @return Vecteur qui a subi la rotation
	 */
	public Vecteur rotationX(double rx) {
		double nx = this.x;
		double ny = Math.cos(rx)*this.y - Math.sin(rx)*this.z;
		double nz = Math.sin(rx)*this.y + Math.cos(rx)*this.z;
		return new Vecteur(nx,ny,nz);
	}
	
	/**
	 * Rotater le vecteur autour de l'axe des Y.
	 * @param ry : angle de rotation autour de Y.
	 * @return Vecteur qui a subi la rotation
	 */
	public Vecteur rotationY(double ry) {
		double ny = this.y;
		double nx = Math.cos(ry)*this.x + Math.sin(ry)*this.z;
		double nz = -Math.sin(ry)*this.x + Math.cos(ry)*this.z;
		return new Vecteur(nx,ny,nz);
	}
	
	/**
	 * Rotater le vecteur autour de l'axe des Z.
	 * @param rz : angle de rotation autour de Z.
	 * @return Vecteur qui a subi la rotation
	 */
	public Vecteur rotationZ(double rz) {
		double nz = this.z;
		double nx = Math.cos(rz)*this.x - Math.sin(rz)*this.y;
		double ny = Math.sin(rz)*this.x + Math.cos(rz)*this.y;
		return new Vecteur(nx,ny,nz);
	}
	
	public Vecteur rotationXYZ(double rx, double ry, double rz) {
		Vecteur vecteur = this.rotationX(rx);
		vecteur = vecteur.rotationY(ry);
		vecteur = vecteur.rotationZ(rz);
		return vecteur;
	}
	
	public Vecteur rotationZYX(double rz, double ry, double rx) {
		Vecteur vecteur = this.rotationZ(rz);
		vecteur = vecteur.rotationY(ry);
		vecteur = vecteur.rotationX(rx);
		return vecteur;
	}
	
	public Vecteur rotationAxe(Vecteur axe, double angle) {
		assert (axe != null);
		double c = Math.cos(angle);
		double s = Math.sin(angle);
		
		double xy = axe.getX() * axe.getY();
		double yz = axe.getY() * axe.getZ();
		double zx = axe.getZ() * axe.getX();
		double xx = axe.getX() * axe.getX();
		double yy = axe.getY() * axe.getY();
		double zz = axe.getZ() * axe.getZ();
		
		double nx = (xx * (1 - c) + c) * this.x + (xy * (1 - c) + s * axe.getZ()) * this.y + (zx * (1 - c) + s * axe.getY()) * this.z;
		double ny = (xy * (1 - c) + s * axe.getZ()) * this.x + (yy * (1 - c) + c) * this.y + (yz * (1 - c) + s * axe.getX()) * this.z;
 		double nz = (zx * (1 - c) + s * axe.getY()) * this.x + (yz * (1 - c) + s * axe.getX()) * this.y + (zz * (1 - c) + c) * this.z;
 		
 		return new Vecteur(nx,ny,nz);	
	}
	
	/**
	 * Indiquer si 2 vecteurs sont egaux ou non 
	 * (le vecteur récepteur this et un vecteur fourni dans les parametres)
	 * @param vecteur : vecteur avec lequel on teste l'egalite
	 * @param precision : precision du test
	 * @return boolean qui indique l'egalite
	 */
	public boolean equals(Vecteur vecteur, double precision) {
		assert (vecteur != null);
		double x2 = vecteur.getX();
		double y2 = vecteur.getY();
		double z2 = vecteur.getZ();
		return (Math.abs(this.x - x2) < precision) && (Math.abs(this.y - y2) < precision) && (Math.abs(this.z - z2) < precision);
	}
	
	/**
	 * Mets toutes les coordonnees d'un vecteur positives
	 */
	public Vecteur abs() {
		return new Vecteur(Math.abs(this.getX()), Math.abs(this.getY()), Math.abs(this.getZ()));
	}
	
}
