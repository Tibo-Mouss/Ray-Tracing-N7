package utilitaire;
import elements3D .*;
import utilitaire .*;


/** Point modélise un point géométrique dans un plan équipé d'un
 * repère cartésien.
 * @author rapha
 */

public class Point {
	
	/**Abscisse.*/
	private double x;
	
	/**Ordonnee.*/
	private double y;
	
	/**Hauteur.*/
	private double z;
	
	/** Construit un point avec pour coordonnees x, y et z.
	 * @param nx : absiccse du point
	 * @param ny : ordonnee du point
	 * @param nz : hauteur du point
	 */
	public Point(double nx, double ny, double nz) {
		this.x = nx;
		this.y = ny;
		this.z = nz;
	}
	
	/** Retourne la copie d'un point en une autre variable
	 * */
	public Point copie() {
		return new Point(x,y,z);
	}
	
	
	/** Obtenir l'abscisse d'un point.
	 * @return  l'abscisse du point
	 */
	public double getX() {
		return this.x;
	}
	
	/** Obtenir l'ordonnée d'un point.
	 * @return ordonnee du point
	 */
	public double getY() {
		return this.y;
	}
	
	/** Obtenir la hauteur d'un point.
	 * @return hauteur du point
	 */
	public double getZ() {
		return this.z;
	}
	
	/** Modifier l'abscisse du point.
	 * @param nx : nouvelle abscisse du point
	 */
	public void setX(double nx) {
		this.x = nx;
	}
	
	/** Modifier l'ordonnee du point.
	 * @param ny : nouvelle ordonnee du point
	 */
	public void setY(double ny) {
		this.y = ny;
	}
	
	/** Modifier la hauteur du point.
	 * @param nz : nouvelle hauteur du point
	 */
	public void setZ(double nz) {
		this.z = nz;
	}
	
	
	/** Translater un point.
	 * @param dx : translation selon l'axe des x
	 * @param dy : translation selon l'axe des y
	 * @param dz : translation selon l'axe des z
	 */
	public void translater(double dx, double dy, double dz) {
		this.x += dx;
		this.y += dy;
		this.z += dz;
	}

	/** Translater un point à partir d'un vecteur.
	 * @param vecteur : vecteur de translation
	 */
	public void translater(Vecteur vecteur) {
		assert (vecteur != null);
		this.x = this.x + vecteur.getX();
		this.y = this.y + vecteur.getY();
		this.z = this.z + vecteur.getZ();
	}
	
	
	/** Obtenir le module d'un point.
	 * @return le module du point
	 */
	public double getModule() {
		return Math.sqrt(x * x + y * y + z * z);
	}
	
	/** 
	 * TODO
	 * Obtenir la distance entre un point et l'objet.
	 * @param Point2
	 * @return la distance
	 */
	public double distance(Point Point2) {
		assert (Point2 != null);
		double dx = this.x - Point2.x;
		double dy = this.y - Point2.y;
		double dz = this.z - Point2.z;
		return Math.sqrt(dx * dx + dy * dy + dz * dz);
	}
	
	/** Faire la somme entre deux points.
	 * @param p : point que l'on veut sommer au recepteur (this)
	 * @return somme du point p et du point recepteur (this)
	 */
	public Point sommer(Point p) {
		assert (p != null);
		return new Point(this.x + p.x, this.y + p.y, this.z + p.z);
	}
	
	/** Faire la soustraction entre deux points.
	 * @param p : point que l'on veut soustraire au recepteur (this)
	 * @return soustraction du point recepteur (this) et du point p
	 */
	public Point soustraire(Point p) {
		assert (p != null);
		return new Point(this.x - p.x, this.y - p.y, this.z - p.z);
	}
	
	/** Multiplier un point par un scalaire.
	 * @param a : coefficient multiplicateur
	 * @return la multiplication entre le point recepteur (this) et le scalaire a 
	 */
	public Point multiplier(double a) {
		return new Point( a * this.x, a * this.y, a * this.z);
	}
	
	public void afficher() {
		System.out.println("X:"+this.x+"    Y:"+this.y+"      Z:"+this.z);
	}
	
	public boolean equals(Point point, double precision) {
		assert (point != null);
		double x2 = point.getX();
		double y2 = point.getY();
		double z2 = point.getZ();
		return (Math.abs(this.x - x2) < precision) && (Math.abs(this.y - y2) < precision) && (Math.abs(this.z - z2) < precision);
	}
}
