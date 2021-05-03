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
public interface Materiau {
		
	/** Indiquer si le matériau d'un objet est à prendre en compte
	 * @return statut du matériau
	 */
	public boolean isOn();
	
	/** Réinitialise le matériau à ses valeurs par défaut
	 */
	public void reset();
	
	/** Créer les rayons fils issus de la collision un objet. */
	public ArrayList<Rayon> creerRayon(Rayon rayon, Point intersection, Objet3D objetIntersection);
}
