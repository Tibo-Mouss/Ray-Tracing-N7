/**
 * 
 */
package elements3D;

import java.util.ArrayList;

import rayTracing .*;
import utilitaire .*;

/** Materiau représente le type général de n'importe quel matériau des objets 3D
 * @author Edgar
 */
public interface Propriete {
		
	/** Indiquer si le matériau d'un objet est à prendre en compte
	 * @return statut du matériau
	 */
	public boolean isOn();
	
	/** Réinitialise le matériau à ses valeurs par défaut
	 */
	public void reset();
	
	public void setOn(boolean on);
	
	public void setEnergie(double energie);
	
	public double getEnergie();
	
	/** Créer les rayons fils issus de la collision un objet. */
	public ArrayList<Rayon> creerRayon(Rayon rayon, Point intersection, Objet3D objetIntersection);
}
