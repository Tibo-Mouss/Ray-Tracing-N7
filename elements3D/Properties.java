/**
 *  
 */
package elements3D;

import java.io.Serializable;

import rayTracing .*;
import utilitaire .*;

/** Properties regroupe l'ensemble des Matériaux en une classe 
 * qui stocke les propriétés de chaque Objet3D.
 * @author Edgar
 */
public class Properties implements Serializable {
	
	private static final long serialVersionUID = 385803258376068043L;

	// nombre de matériaux implémentés jusqu'alors
	public static final int NB_MATERIAUX = 2; 
	
	/** Liste de l'ensemble des matériaux que nous avons choisi de pouvoir 
	 * traiter dans notre application*/
	private Materiau[] materiaux = new Materiau[NB_MATERIAUX];
	
	/** Créer l'ensemble des propriétés d'un objet et initialiser chacune.*/
	public Properties() {
		// NUMERO COULEUR = 0
		this.materiaux[0] = new Couleur();
		this.materiaux[1] = new Reflectivite(1, false);
		// Ajouter ici l'initialisation d'un nouveau type de matériau
	}
	
	/** Obtenir un matériau contenant des informations sur l'objet
	 * @param num identificateur de matériau
	 * @return matériau demandé de l'objet
	 */
	public Materiau getMateriau(int num) {
		return this.materiaux[num];
	}
}
