package rayTracing;
import java.awt.Color;
import java.awt.color.*;
import elements3D .*;
import utilitaire .*;

public class Pixel {

	/** Couleur du pixel*/
	private Color couleur;
	
	/** */
	private Point coordonnee; 
	
	public Pixel (Point ncoordonnee) {
		this(ncoordonnee, Color.BLACK);
	}
	
	public Pixel (Point ncoordonnee, Color ncouleur) {
		assert ncoordonnee != null;
		assert ncouleur != null;
		this.couleur = ncouleur;
		this.coordonnee = new Point (ncoordonnee.getX(), ncoordonnee.getY(), ncoordonnee.getZ());
	}
	
	public Pixel copie() {
		return new Pixel(this.coordonnee,this.couleur);
	}
	
	public void setCouleur (Color ncouleur) {
		assert ncouleur != null;
		this.couleur = ncouleur; 
	}
	
	public void setCoordonnee(Point ncoordonnee) {
		assert ncoordonnee != null;
		this.coordonnee = new Point (ncoordonnee.getX(), ncoordonnee.getY(), ncoordonnee.getZ());
	}
	
	
	public Color getCouleur() {
		return this.couleur;
	}
	
	public Point getCoordonnee() {
		return new Point(this.coordonnee.getX(), this.coordonnee.getY(), this.coordonnee.getZ());
	}
	
	public void translater(Vecteur vecteur) {
		this.coordonnee.translater(vecteur);
	}
}
