/**
 *  
 */
package elements3D;

import java.io.Serializable;

import rayTracing .*;
import utilitaire .*;

/** Properties regroupe l'ensemble des Mat�riaux en une classe 
 * qui stocke les propri�t�s de chaque Objet3D.
 * @author Edgar
 */
public class Properties implements Serializable {
	
	private static final long serialVersionUID = 385803258376068043L;

	// nombre de mat�riaux impl�ment�s jusqu'alors
	public static final int NB_MATERIAUX = 2; 
	
	/** Liste de l'ensemble des mat�riaux que nous avons choisi de pouvoir 
	 * traiter dans notre application*/
	private Materiau[] materiaux = new Materiau[NB_MATERIAUX];
	
	/** Cr�er l'ensemble des propri�t�s d'un objet et initialiser chacune.*/
	public Properties() {
		// NUMERO COULEUR = 0
		this.materiaux[0] = new Couleur();
		this.materiaux[1] = new Reflectivite(1, false);
		// Ajouter ici l'initialisation d'un nouveau type de mat�riau
	}
	
	/** Obtenir un mat�riau contenant des informations sur l'objet
	 * @param num identificateur de mat�riau
	 * @return mat�riau demand� de l'objet
	 */
	public Materiau getMateriau(int num) {
		return this.materiaux[num];
	}
}
