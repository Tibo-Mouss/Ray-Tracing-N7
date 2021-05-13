/**
 * 
 */
package elements3D;

import java.io.Serializable;
import java.util.ArrayList;

import rayTracing.Rayon;
import utilitaire.Vecteur;
import utilitaire.Point;

/**
 * @author Christophe
 */
public class Refraction implements Propriete, Serializable {

	private static final long serialVersionUID = 4874702146396229178L;
	
	private double indice_refraction;
	private double intensite; // entre 0 et 1 (1 = réfléchit tout)
	private double energie; // entre 0 et 1 
	private boolean on;
	
	Refraction(double indice, double intensite, boolean on) {
		assert (0 <= intensite  && intensite <= 1);
		assert (1 <= indice);
		this.indice_refraction = indice;
		this.intensite = intensite;
		this.energie = 0;
		this.on = on;
	}
	
	@Override
	public boolean isOn() {
		// TODO Auto-generated method stub
		return on;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub

	}
	
	public void setOn(boolean on) {
		this.on = on;
	}
	
	public void setIndice(double indice) {
		assert (1 <= indice);
		this.indice_refraction = indice;
	}
	
	public void setIntensite(double intensite) {
		assert (0 <= intensite  && intensite <= 1);
		this.intensite = intensite;
	}
	
	public void setEnergie(double energie) {
		assert (0 <= energie  && energie <= 1);
		this.energie = energie;
	}

	public ArrayList<Rayon> creerRayon(Rayon rayon, Point intersection, Objet3D objetIntersection) {
		assert (rayon != null && intersection != null && objetIntersection != null);
		Vecteur direction = objetIntersection.directionReflexion(rayon, intersection);
		ArrayList<Rayon> listeRayons = new ArrayList<Rayon>();
		listeRayons.add( new Rayon(direction, intersection, rayon, this.intensite, rayon.getPartEnergie()) );
				// partEnergie à modifier 
		return listeRayons;
	}

}
