/**
 * 
 */
package elements3D;

import java.util.ArrayList;

import rayTracing .*;
import utilitaire .*;

/** Materiau repr�sente le type g�n�ral de n'importe quel mat�riau des objets 3D
 * @author Edgar
 */
public interface Propriete {
		
	/** Indiquer si le mat�riau d'un objet est � prendre en compte
	 * @return statut du mat�riau
	 */
	public boolean isOn();
	
	/** R�initialise le mat�riau � ses valeurs par d�faut
	 */
	public void reset();
	
	public void setOn(boolean on);
	
	public void setEnergie(double energie);
	
	public double getEnergie();
	
	/** Cr�er les rayons fils issus de la collision un objet. */
	public ArrayList<Rayon> creerRayon(Rayon rayon, Point intersection, Objet3D objetIntersection);
}
