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
public class Reflectivite implements Propriete, Serializable {

	private static final long serialVersionUID = 4874702146396229178L;
	
	private double intensite; // entre 0 et 1 (1 = réfléchit tout)
	private double energie;
	private boolean on;
	
	Reflectivite(double reflectivite, boolean on) {
		assert (0 <= reflectivite  && reflectivite <= 1);
		this.intensite = reflectivite;
		this.on = on;
		this.energie = 0;
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
	
	@Override
	public double getEnergie() {
		return this.energie;
	}
	
	@Override
	public void setOn(boolean on) {
		this.on = on;
	}
	
	public void setIntensite(double intensite) {
		assert (0 <= intensite  && intensite <= 1);
		this.intensite = intensite;
	}
	
	public void setEnergie(double energie) {
		assert (0 <= energie  && energie <= 1);
		this.energie = energie;
		System.out.println(this.energie);
	}

	public ArrayList<Rayon> creerRayon(Rayon rayon, Point intersection, Objet3D objetIntersection) {
		assert (rayon != null && intersection != null && objetIntersection != null);
		Vecteur direction = objetIntersection.directionReflexion(rayon, intersection);
		ArrayList<Rayon> listeRayons = new ArrayList<Rayon>();
		listeRayons.add( new Rayon(direction, intersection, rayon, this.intensite, energie*rayon.getPartEnergie()) );
				// partEnergie à modifier 
		return listeRayons;
	}

}
