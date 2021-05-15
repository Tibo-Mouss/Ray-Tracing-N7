/**
 * 
 */
package rayTracing;
import elements3D .*;
import exception.MaxRebondsNegatifException;
import utilitaire .*;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author Chris
 *
 */
public class RayTracing {
	
	private static final boolean DEBUG = false;
	
	/** Scene dans laquelle sont definies les objets et les lumieres */
	private Scene scene;
	
	/** Camera associee a la scene */
	private Camera camera;
	
	/** Nombre maximum de rebonds que peut faire un rayon*/
	private int maxRebond;
	
	/** Indique si l'on reprÃ©sente les ombres pour le rendu.*/
	private boolean ombreIsOn;
	
	/** Indique si l'on utilise la technique de shadding pour le rendu.*/
	private boolean shadingIsOn;

	/** 
	 * @param nscene : Scene dans laquelle sont definies les objets et les lumieres
	 * @param ncamera : Camera associee a la scene
	 * @param rebonds : Nombre maximum de rebonds que peut faire un rayon
	 */
	public RayTracing(Scene nscene, Camera ncamera, int rebonds, boolean ombre, boolean shadding) 
												throws MaxRebondsNegatifException {
		if (rebonds < 0) {
			throw new MaxRebondsNegatifException();
		};

		assert scene != null;
		assert camera != null;

		this.scene = nscene;
		this.camera = ncamera;
		this.maxRebond = rebonds;
		this.ombreIsOn = ombre;
		this.shadingIsOn = shadding;
	}
	/**
	 * Obtenir la scÃ¨ne.
	 * @return ScÃ¨ne sur laquelle le Ray Tracing opÃ¨re.
	 */
	public Scene getScene() {
		return this.scene;
	}
	
	/**
	 * Obtenir la camera.
	 * @return Camera avec laquelle Ray Tracing opere.
	 */
	public Camera getCamera() {
		return this.camera;
	}
	
	/**
	 * Obtenir si les ombres sont activées.
	 * @return etat des ombres.
	 */
	public boolean getOmbre() {
		return this.ombreIsOn;
	}
	
	/**
	 * Obtenir le nombre de rebonds
	 * @return nombre de rebonds.
	 */
	public int getRebond() {
		return this.maxRebond;
	}
	
	
	/**
	 * Obtenir si le shadding est activée.
	 * @return shadding actif ou non.
	 */
	public boolean getShadding() {
		return this.shaddingisOn;
	}
	
	/**
	 * Modifier ombreIsOn.
	 * @param o
	 */
	public void setOmbre(boolean o) {
		this.ombreIsOn = o;
	}
	
	/**
	 * Obtenir le nombre de rebonds.
	 * @param r.
	 */
	public void setRebond(int r) {
		this.maxRebond = r;
	}
	
	/**
	 * Modifier shadding.
	 * @param o
	 */
	public void setShadding(boolean o) {
		this.shaddingisOn = o;
	}
	
	/** Obtenir la couleur finale d'un pixel a partir de ses rayons fils.
	 * @param listeRayonsFinaux la liste des rayons fils finaux
	 * @return la couleur finale du pixel
	 */
	private static Color getCouleurFinale(ArrayList <Rayon> listeRayonsFinaux) {
		Color couleurFinale = Color.black;
		double pE;
		double r, g, b;
		int tailleListeRayons = listeRayonsFinaux.size();
		Rayon rayon;
		for (int i = 0; i < tailleListeRayons; i++) {
			rayon = listeRayonsFinaux.get(i);
			pE = rayon.getPartEnergie();
			r = Math.max(couleurFinale.getRed(), pE * rayon.getCouleur().getRed());
			g = Math.max(couleurFinale.getGreen(), pE * rayon.getCouleur().getGreen());
			b = Math.max(couleurFinale.getBlue(), pE * rayon.getCouleur().getBlue());
			couleurFinale = new Color((int)r,(int)g,(int)b);
			//System.out.println(pE);
		}
		return couleurFinale;
	}
	
	
	/** Modifier la couleur d'un rayon fils par rapport Ã  sa couleur et Ã  la couleur de son pÃ¨re.
	 * @param rayon le rayon dont on veut modifier la couleur
	 * @param couleur la couleur du rayon
	*/
	private static void setCouleurRayon(Rayon rayon, Color couleur) {
		
		double pC = rayon.getPartCouleur();
		double r = Math.min(rayon.getCouleur().getRed(), pC * couleur.getRed());
		double g = Math.min( rayon.getCouleur().getGreen(), pC * couleur.getGreen());
		double b = Math.min( rayon.getCouleur().getBlue(), pC * couleur.getBlue());
		rayon.setCouleur((int)r, (int)g, (int)b);
		
	}

	/** 
	 * Lance le calcul du rendu en utilisant la technique de ray tracing.
	 */
	public void lancerRayTracing() {
		int pixelHauteur = this.camera.getPixelHauteur(); //le nombre de pixels sur la hauteur de l'Ã©cran
		int pixelLongueur = this.camera.getPixelLongueur(); //le nombre de pixels sur la largeur de l'Ã©cran
		
		//  Constantes de la boucle
		Pixel pixelCourant; //pixel dont on calcule la couleur
		Rayon rayonCourant; //rayon pÃ¨re que l'on envoie Ã  partir du pixel pÃ¨re pixelCourant
		Color couleurCourante; //couleur du pixel pixelCourant
		ArrayList<Rayon> listeRayonsFinaux; //liste des rayons fils finaux lancÃ©s Ã  partir de pixelCourant
																			//Peut-Ãªtre changer la collection
		
		// Parcours de tous les pixels de l'Ã©cran
		for (int i=0; i < pixelHauteur; i++) {
			for (int j=0; j <pixelLongueur; j++) {
				
				listeRayonsFinaux = new ArrayList<Rayon>();
				
				pixelCourant = camera.getPixel(i,j);
				rayonCourant = new Rayon(camera.getCentre(), pixelCourant.getCoordonnee(), pixelCourant);
				rayonCourant.setCouleur(255, 255, 255); // on initialise la couleur du rayon Ã  BLANC
				
				//lancement du rayon, ie recherche d'une intersection avec un objet
				this.lancerRayon(rayonCourant,0,listeRayonsFinaux);
				
				//calcul de la couleur finale Ã  partir des rayons fils
				couleurCourante = getCouleurFinale(listeRayonsFinaux); // Ã  faire

				//mise Ã  jour de la couleur du pixel
				pixelCourant.setCouleur(couleurCourante);
			}
		}
	}
	
	/** Lancer un rayon, ie recherche d'une intersection avec un objet.
	 * @param rayon
	 * @param compteur le nombre de rebond dÃ©jÃ  effectuÃ©s par rayon
	 * @param listeRayonsFinaux liste des rayons finaux associÃ©s au pixel pÃ¨re de rayon
	 */
	private void lancerRayon(Rayon rayon, int compteur, ArrayList <Rayon> listeRayonsFinaux) {

		
		// Intersection la plus proche avec le rayon
		Objet3D objetIntersection = null;
		Point intersection = null;
		double distanceMin = this.scene.getDimension();
		
		// Objet que l'on teste
		Objet3D objetCourant;
		Point intersectionCourante = null;
		double distanceCourante;
		
		//parcours des Materiau
		Propriete materiauCourant;
		
		//Parcours des objets de la scene
		for (int k=0; k < this.scene.getObjet3D().size(); k++) {
			
			// Objet que l'on teste
			objetCourant = this.scene.getObjet3D().get(k);
			intersectionCourante = objetCourant.estTraversePar(rayon);
			
			// On dÃ©termine si l'objet est plus proche de l'origine du rayon
			if (intersectionCourante != null) {
				distanceCourante = intersectionCourante.distance(rayon.getOrigine()); //distance Ã  ajouter dans Point
				if (distanceCourante < distanceMin) {
					objetIntersection = objetCourant;
					intersection = intersectionCourante;
					distanceMin = distanceCourante;
				}
			}
		}
		
		// Traitement de l'impact du rayon sur l'objet 
		if (objetIntersection != null && intersection != null) {

			Couleur couleurObjetInt = (Couleur)objetIntersection.getMateriau(0);
			Color couleurOI = couleurObjetInt.get();

		
			
			// DÃ©tection de l'ombre
			if (this.ombreIsOn) {
				Vecteur normal = objetIntersection.getNormal(intersection, rayon);
				lancerShadowRay(rayon, couleurObjetInt, rayon.getDirection(), normal, intersection, objetIntersection);
			} else {
				setCouleurRayon(rayon, couleurOI);	
			}

			//listeRayonsFinaux.add(rayon); // Ã  modifier aprÃ¨s la premiÃ¨re itÃ©ration
			
			// Lancer des rayons issus de rayon selon les propriÃ©tÃ©s de l'objet intersectÃ©
			if (compteur < this.maxRebond) {
				
				boolean allOff = true;
				
				//Parcours des Materiau, ie propriÃ©tÃ©s de l'objet intersectÃ©
				for (int materiau = 1 ; materiau < Materiau.NB_PROPRIETES ; materiau++) {
					materiauCourant = objetIntersection.getMateriau(materiau);
					if (materiauCourant.isOn()) {
						
						allOff = false;
						
						// LancÃ© du rayon issu de la propriÃ©tÃ© materiauCourant de l'objet intersectÃ©
						ArrayList<Rayon> listeRayonsMateriau = materiauCourant.creerRayon(rayon, intersection, objetIntersection);
						for (Rayon rayonFils : listeRayonsMateriau) {
							lancerRayon(rayonFils, compteur +1, listeRayonsFinaux);
						}
						
					}
				}
				
				if (allOff) {
					listeRayonsFinaux.add(rayon);	
				}

			} else {
				listeRayonsFinaux.add(rayon);
			}
		} else {
			setCouleurRayon(rayon, this.scene.getColour());
			listeRayonsFinaux.add(rayon);
		}
	}

	/** 
	 * DÃ©termination de l'ombre et du shadding.
	 * @param rayon rayon Ã  partir duquel on lance un shadow ray
	 * @param couleurIntersection couleur de l'objet intersectÃ©
	 * @param normal vecteur normal Ã  la surface de l'objet intersectÃ© au point d'impact
	 * @param intersectionObjet objet intersectÃ©
	 */
	private void lancerShadowRay(Rayon rayon, Couleur couleurIntersection, Vecteur incident, Vecteur normal, Point intersectionObjet, Objet3D objetIntersection) {

		// Couleur ambiante à fournir (caractéristique de la scène)
		Couleur ambiante = new Couleur( Color.WHITE );
		
		// Paramètre : brillance au point d'intersection
		double brillance = 100;
		double importanceSpeculaire = 0.7;
		double importanceAmbiante = 0.1;
		double importanceDiffuse = 1.0;
		
		
		//paramÃ¨tres de la boucle for
		Rayon shadowRay;
		Color couleurLumiere;
		// Lumiere lumiereCourante;
		double distanceLumiere;
		double distanceCourante;

		//paramÃ¨tres de la boucle while
		int objet;
		boolean ombre;
		Objet3D objetCourant;
		Point intersectionCourante;
		
		// TODO : Modifier API de couleur pour que ces opérations y figurent
		double composanteDiffuseRouge = 0.0;
		double composanteDiffuseVerte = 0.0;
		double composanteDiffuseBleue = 0.0;
		double composanteSpeculaireRouge = 0.0;
		double composanteSpeculaireVerte = 0.0;
		double composanteSpeculaireBleue = 0.0;
		double composanteAmbianteRouge = importanceAmbiante * ambiante.getRed();
		double composanteAmbianteVerte = importanceAmbiante * ambiante.getGreen();
		double composanteAmbianteBleue = importanceAmbiante * ambiante.getBlue();

		//constante de la mÃ©thode
		List<Lumiere> listeLumieres=  this.scene.getLumiere();
		int nbLumieres = listeLumieres.size();
		int nbObjets = this.scene.getObjet3D().size();
		double diffusion, specularite;

		// On parcourt toutes les lumiÃ¨res
		for (Lumiere lumiere : listeLumieres) {
			
			// lumiereCourante = listeLumieres.get(lumiere);
			distanceLumiere = intersectionObjet.distance(lumiere.getCentre());
			
			// CrÃ©ation du shadow ray pour la lumiÃ¨re courante
			Vecteur vecteurLumiere = new Vecteur(intersectionObjet, lumiere.getCentre());
			// Ã  modifier par getDirection Ã  ajouter dans Lumiere
			shadowRay = new Rayon(vecteurLumiere, intersectionObjet);
			//!!!\ faire une classe shadowRay ??

			objet = 0; // indice de la boucle while
			//ombre = false; // indique si l'objet est Ã  l'ombre par rapport Ã  la lumiÃ¨re courante
			couleurLumiere = lumiere.getCouleur();
			
			// On vÃ©rifie que l'objet ne se fait pas de l'ombre lui-mÃªme 
			ombre = objetIntersection.getSelfOmbre(intersectionObjet, rayon, lumiere);
			
			if (!ombre) {
				// On parcourt tous les objets pour dÃ©terminer si l'objet est Ã  l'ombre
				while (objet < nbObjets && !ombre) {
					objetCourant = this.scene.getObjet3D().get(objet);
					intersectionCourante = objetCourant.estTraversePar(shadowRay);
					if (intersectionCourante != null && objetCourant != objetIntersection) {
						distanceCourante = intersectionCourante.distance(shadowRay.getOrigine());
						if (distanceCourante < distanceLumiere) {
							ombre = true;
						}
					}
					objet++;
				}
			}

			// Si l'objet n'est pas Ã  l'ombre par rapport Ã  la lumiÃ¨re courante
			if (!ombre) {
	
				// calcul du coefficient de shading
				if (this.shadingIsOn) {
					vecteurLumiere.normaliser();
					normal.normaliser();
					incident.normaliser(); 
					double cosTheta = normal.produitScalaire(vecteurLumiere);
					// Calcul du vecteur lumière réfléchi
					// TODO : Modifier API de vecteur pour que cette opération y figure
					Vecteur reflexionLumiere = normal.multiplication(2.0 * cosTheta).soustraire(vecteurLumiere);
					reflexionLumiere.normaliser();
					// Détermine la force de la spécularité en ce point pour cette lumière
					double cosOmega = - incident.produitScalaire(reflexionLumiere);
					diffusion = Math.max(0.0, cosTheta);
					specularite = Math.max(0.0, importanceSpeculaire * Math.pow(cosOmega, brillance));
					if (DEBUG) {
						if (specularite > 0.5) {
							System.out.println(
									" 2 x " + cosTheta + " * ( " + normal.getX() + ", " + normal.getY() + ", " + normal.getZ() + ") "
									+ "- ( " + vecteurLumiere.getX() + ", " + vecteurLumiere.getY() + ", " + vecteurLumiere.getZ() + ") "
									+ " = ( " + reflexionLumiere.getX() + ", " + reflexionLumiere.getY() + ", " + reflexionLumiere.getZ() + ") "
											+ specularite);
						}
					}
				} else {
					diffusion = 1;
					specularite = 0;
				}
				
				// Idée : modéliser la dispersion de la lumière (prévoir un coefficient de dispersion ambiant)
				double distance = intersectionObjet.distance(lumiere.getCentre());
				
				double absorbtion = 1; //100/(distance*distance);
				
				double eclairementRouge = absorbtion * couleurLumiere.getRed();
				double eclairementVert = absorbtion * couleurLumiere.getGreen();
				double eclairementBleu = absorbtion * couleurLumiere.getBlue();
				
				// Accumuler la composante spéculaire de chaque source de lumière
				// composanteSpeculaire += couleurLumiere * specularite;
				composanteSpeculaireRouge += specularite * eclairementRouge;				
				composanteSpeculaireVerte += specularite * eclairementVert;
				composanteSpeculaireBleue += specularite * eclairementBleu;
				
				// Accumuler la composante diffuse de chaque source de lumière
				// composanteDiffuse += couleurLumiere * diffusion;
				composanteDiffuseRouge += diffusion * eclairementRouge;				
				composanteDiffuseVerte += diffusion * eclairementVert;
				composanteDiffuseBleue += diffusion * eclairementBleu;
				
				// mise Ã  jour de la couleur du rayon
				// couleurRayon = rayon.getCouleur();
				
			}
			
		}
		
		// Filtrage de la lumière reçu par l'objet : prise en compte de la couleur de l'objet
		double composanteRouge = Math.min(composanteAmbianteRouge + composanteDiffuseRouge, couleurIntersection.getRed());
		double composanteVerte = Math.min(composanteAmbianteVerte + composanteDiffuseVerte, couleurIntersection.getGreen());
		double composanteBleue = Math.min(composanteAmbianteBleue + composanteDiffuseBleue, couleurIntersection.getBlue());
		
		// Prise en compte du maximum de chaque composante
		// TODO : faire un modèle plus réaliste de la perception logarithmique par l'oeil
		// Idée : calculer l'énergie reçue par chaque point
		// Mettre à l'échelle 0..255 à partir de l'énergie maximale et d'une échelle logarithmique
		// Calculer le logarithme de l'énergie reçue
		// Normaliser en 0 et 255
		// Prévoir un seuil de saturation pour éviter que rien ne soit blanc
		composanteRouge = Math.min(composanteRouge + composanteSpeculaireRouge, 255);
		composanteVerte = Math.min(composanteVerte + composanteSpeculaireVerte, 255);
		composanteBleue = Math.min(composanteBleue + composanteSpeculaireBleue, 255);
		// Si l'objet est Ã  l'ombre pour toutes les lumiÃ¨res, le rayon est noir.
		
		// couleurPoint = Min( Blanc, Min((composanteAmbiante + composanteDiffuse), couleurObjet) + composanteSpeculaire);
		
		Color couleurRayon = new Color( 
				(int) Math.round(composanteRouge),
				(int) Math.round(composanteVerte),
				(int) Math.round(composanteBleue)
				);

		setCouleurRayon(rayon, couleurRayon);
			
	}
}
