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
	
	/** Scene dans laquelle sont definies les objets et les lumieres */
	private Scene scene;
	
	/** Camera associee a la scene */
	private Camera camera;
	
	/** Nombre maximum de rebonds que peut faire un rayon*/
	private int maxRebond;
	
	/** Indique si l'on repr�sente les ombres pour le rendu.*/
	private boolean ombreIsOn;
	
	/** Indique si l'on utilise la technique de shadding pour le rendu.*/
	private boolean shaddingisOn;

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
		this.shaddingisOn = shadding;
	}
	/**
	 * Obtenir la sc�ne.
	 * @return Sc�ne sur laquelle le Ray Tracing op�re.
	 */
	public Scene getScene() {
		return this.scene;
	}
	
	/**
	 * Obtenir la cam�re.
	 * @return Cam�ra avec laquelle Ray Tracing op�re.
	 */
	public Camera getCamera() {
		return this.camera;
	}
	
	/** Obtenir la couleur finale d'un pixel � partir de ses rayons fils.
	 * @param listeRayonsFinaux la liste des rayons fils finaux
	 * @return la couleur finale du pixel
	 */
	private static Color getCouleurFinale(ArrayList <Rayon> listeRayonsFinaux) {
		return listeRayonsFinaux.get(0).getCouleur(); //seulement pour it�ration 1 car peu de Properties
	}
	
	
	/** Modifier la couleur d'un rayon fils par rapport � sa couleur et � la couleur de son p�re.
	 * @param rayon le rayon dont on veut modifier la couleur
	 * @param couleur la couleur du rayon
	*/
	private static void setCouleurRayon(Rayon rayon, Color couleur) {
		
		double pC = rayon.getPartCouleur();
		double r = Math.min( rayon.getCouleur().getRed(), pC * couleur.getRed());
		double g = Math.min( rayon.getCouleur().getGreen(), pC * couleur.getGreen());
		double b = Math.min( rayon.getCouleur().getBlue(), pC * couleur.getBlue());
		rayon.setCouleur((int)r, (int)g, (int)b);
		
	}

	/** 
	 * Lance le calcul du rendu en utilisant la technique de ray tracing.
	 */
	public void lancerRayTracing() {
		int pixelHauteur = this.camera.getPixelHauteur(); //le nombre de pixels sur la hauteur de l'�cran
		int pixelLongueur = this.camera.getPixelLongueur(); //le nombre de pixels sur la largeur de l'�cran
		
		//  Constantes de la boucle
		Pixel pixelCourant; //pixel dont on calcule la couleur
		Rayon rayonCourant; //rayon p�re que l'on envoie � partir du pixel p�re pixelCourant
		Color couleurCourante; //couleur du pixel pixelCourant
		ArrayList<Rayon> listeRayonsFinaux; //liste des rayons fils finaux lanc�s � partir de pixelCourant
																			//Peut-�tre changer la collection
		
		// Parcours de tous les pixels de l'�cran
		for (int i=0; i < pixelHauteur; i++) {
			for (int j=0; j <pixelLongueur; j++) {
				
				listeRayonsFinaux = new ArrayList<Rayon>();
				
				pixelCourant = camera.getPixel(i,j);
				rayonCourant = new Rayon(camera.getCentre(), pixelCourant.getCoordonnee(), pixelCourant);
				rayonCourant.setCouleur(255, 255, 255); // on initialise la couleur du rayon � BLANC
				
				//lancement du rayon, ie recherche d'une intersection avec un objet
				this.lancerRayon(rayonCourant,0,listeRayonsFinaux);
				
				//calcul de la couleur finale � partir des rayons fils
				couleurCourante = getCouleurFinale(listeRayonsFinaux); // � faire

				//mise � jour de la couleur du pixel
				pixelCourant.setCouleur(couleurCourante);
			}
		}
	}
	
	/** Lancer un rayon, ie recherche d'une intersection avec un objet.
	 * @param rayon
	 * @param compteur le nombre de rebond d�j� effectu�s par rayon
	 * @param listeRayonsFinaux liste des rayons finaux associ�s au pixel p�re de rayon
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
		Materiau materiauCourant;
		
		//Parcours des objets de la scene
		for (int k=0; k < this.scene.getObjet3D().size(); k++) {
			
			// Objet que l'on teste
			objetCourant = this.scene.getObjet3D().get(k);
			intersectionCourante = objetCourant.estTraversePar(rayon);
			
			// On d�termine si l'objet est plus proche de l'origine du rayon
			if (intersectionCourante != null) {
				distanceCourante = intersectionCourante.distance(rayon.getOrigine()); //distance � ajouter dans Point
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
			Color couleurOI = new Color(couleurObjetInt.getRed(), couleurObjetInt.getGreen(), couleurObjetInt.getBlue());

			//!\\ ne marche que pour maxRebonds = 0 -> setCouleur � modifier ? ou ajoouter une fonction et un pourcentage
			//ajouter un attribut pourcentage ;  modifier setCouleur() dans Rayon ; dans le constructeur : pourcentage = 1 
			
			// D�tection de l'ombre
			if (this.ombreIsOn) {
				Vecteur normal = objetIntersection.getNormal(intersection, rayon);
				lancerShadowRay(rayon, couleurObjetInt, normal, intersection, objetIntersection);
			} else {
				setCouleurRayon(rayon, couleurOI);	
			}

			//listeRayonsFinaux.add(rayon); // � modifier apr�s la premi�re it�ration
			
			// Lancer des rayons issus de rayon selon les propri�t�s de l'objet intersect�
			if (compteur < this.maxRebond) {
				
				boolean allOff = true;
				
				//Parcours des Materiau, ie propri�t�s de l'objet intersect�
				for (int materiau = 1 ; materiau < Properties.NB_MATERIAUX ; materiau++) {
					materiauCourant = objetIntersection.getMateriau(materiau);
					if (materiauCourant.isOn()) {
						
						allOff = false;
						
						// Lanc� du rayon issu de la propri�t� materiauCourant de l'objet intersect�
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
	 * D�termination de l'ombre et du shadding.
	 * @param rayon rayon � partir duquel on lance un shadow ray
	 * @param couleurIntersection couleur de l'objet intersect�
	 * @param normal vecteur normal � la surface de l'objet intersect� au point d'impact
	 * @param intersectionObjet objet intersect�
	 */
	private void lancerShadowRay(Rayon rayon, Couleur couleurIntersection, Vecteur normal, Point intersectionObjet, Objet3D objetIntersection) {

		
		//param�tres de la boucle for
		Rayon shadowRay;
		Color couleurLumiereCourante;
		Lumiere lumiereCourante;
		double distanceLumiere;
		double distanceCourante;

		//param�tres de la boucle while
		int objet;
		boolean ombre;
		Objet3D objetCourant;
		Point intersectionCourante;
		Color couleurCourante = Color.BLACK;
		Color couleurRayon = rayon.getCouleur();

		//constante de la m�thode
		List<Lumiere> listeLumieres=  this.scene.getLumiere();
		int nbLumieres = listeLumieres.size();
		int nbObjets = this.scene.getObjet3D().size();
		double shadding;

		// On parcourt toutes les lumi�res
		for (int lumiere = 0 ; lumiere < nbLumieres ; lumiere++) {
			
			lumiereCourante = listeLumieres.get(lumiere);
			distanceLumiere = intersectionObjet.distance(lumiereCourante.getCentre());
			
			// Cr�ation du shadow ray pour la lumi�re courante
			Vecteur vecteurLumiere = new Vecteur(intersectionObjet, lumiereCourante.getCentre());
			// � modifier par getDirection � ajouter dans Lumiere
			shadowRay = new Rayon(vecteurLumiere, intersectionObjet);
			//!!!\ faire une classe shadowRay ??

			objet = 0; // indice de la boucle while
			//ombre = false; // indique si l'objet est � l'ombre par rapport � la lumi�re courante
			couleurLumiereCourante = lumiereCourante.getCouleur();
			
			// On v�rifie que l'objet ne se fait pas de l'ombre lui-m�me 
			ombre = objetIntersection.getSelfOmbre(intersectionObjet, rayon, lumiereCourante);
			
			if (!ombre) {
				// On parcourt tous les objets pour d�terminer si l'objet est � l'ombre
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

			// Si l'objet n'est pas � l'ombre par rapport � la lumi�re courante
			if (!ombre) {
	
				// calcul du coefficient de shadding
				if (this.shaddingisOn) {
					vecteurLumiere.normaliser();
					normal.normaliser();
					shadding = Math.max(0.0, normal.produitScalaire(vecteurLumiere));
				
				} else {
					shadding = 1;
				}
				
				// mise � jour de la couleur du rayon
				couleurRayon = rayon.getCouleur();
				
				double nr = Math.min(shadding*couleurLumiereCourante.getRed(), couleurIntersection.getRed());				
				double ng = Math.min(Math.floor(shadding*couleurLumiereCourante.getGreen()), couleurIntersection.getGreen());
				double nb = Math.min(Math.floor(shadding*couleurLumiereCourante.getBlue()), couleurIntersection.getBlue());
				
				double ar = couleurCourante.getRed();
				double ag = couleurCourante.getGreen();
				double ab = couleurCourante.getBlue();
				
				// /!\ � modifier apr�s maj de setCouleur() dans Rayon
				double r = Math.max(nr, ar);
				double g = Math.max(ng, ag);
				double b = Math.max(nb, ab);
				
				
				couleurCourante = new Color((int)r, (int)g, (int)b);
			}
		}
		// Si l'objet est � l'ombre pour toutes les lumi�res, le rayon est noir.
		

		setCouleurRayon(rayon, couleurCourante);
			
	}
}
