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
 * @author Edgar
 *
 */
public class Reflectivite implements Materiau, Serializable {

	private static final long serialVersionUID = 4874702146396229178L;
	
	private double intensite; // entre 0 et 1 (1 = réfléchit tout)
	private boolean on;
	
	Reflectivite(double reflectivite, boolean on) {
		this.intensite = reflectivite;
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
	
	public void setIntensite(double intensite) {
		this.intensite = intensite;
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
