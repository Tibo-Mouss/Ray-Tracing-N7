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
public class Materiau implements Serializable {
	
	private static final long serialVersionUID = 385803258376068043L;

	// nombre de mat�riaux impl�ment�s jusqu'alors
	public static final int NB_PROPRIETES = 2; 
	
	/** Liste de l'ensemble des mat�riaux que nous avons choisi de pouvoir 
	 * traiter dans notre application*/
	private Propriete[] proprietes = new Propriete[NB_PROPRIETES];
	
	/** Cr�er l'ensemble des propri�t�s d'un objet et initialiser chacune.*/
	public Materiau() {
		this.proprietes[0] = new Reflectivite(1, false);
		this.proprietes[1] = new Refraction(1, 1, false);
		// Ajouter ici l'initialisation d'un nouveau type de mat�riau
	}
	
	/** Obtenir un mat�riau contenant des informations sur l'objet
	 * @param num identificateur de mat�riau
	 * @return mat�riau demand� de l'objet
	 */
	public Propriete getMateriau(int num) {
		return this.proprietes[num];
	}

	public void setOnReflectivite() {
		Propriete re = this.proprietes[0];
		Propriete ra = this.proprietes[1];
		re.setOn(true);
		if (ra.isOn()) {
			re.setEnergie(0.5);
			ra.setEnergie(0.5);
		} else {
			re.setEnergie(1);
		}
	}
	
	public void setOnRefraction() {
		Propriete re = this.proprietes[0];
		Propriete ra = this.proprietes[1];
		ra.setOn(true);
		if (re.isOn()) {
			re.setEnergie(0.5);
			ra.setEnergie(0.5);
		} else {
			ra.setEnergie(1);
		}
	}
	
	public void setOffReflectivite() {
		this.proprietes[0].setOn(false);
	}
	
	public void setOffRefraction() {
		this.proprietes[1].setOn(false);
	}
	
	public void setEnergieRefraction(double energie) {
		Propriete re = this.proprietes[0];
		Propriete ra = this.proprietes[1];
		double energie_reflexion = re.getEnergie();
		if (energie_reflexion + energie <= 1) {
			ra.setEnergie(energie);
		} //Rajouter le else avec une erreur

	}
	
	public void setEnergieReflexion(double energie) {
		Propriete re = this.proprietes[0];
		Propriete ra = this.proprietes[1];
		double energie_refraction = ra.getEnergie();
		if (energie_refraction + energie <= 1) {
			re.setEnergie(energie);
		} //Rajouter le else avec une erreur

	}

	
}
