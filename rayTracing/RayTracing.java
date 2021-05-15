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
	
	/** Indique si l'on représente les ombres pour le rendu.*/
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
	 * Obtenir la scène.
	 * @return Scène sur laquelle le Ray Tracing opère.
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
	 * Obtenir si les ombres sont activ�es.
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
	 * Obtenir si le shadding est activ�e.
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
	
	
	/** Modifier la couleur d'un rayon fils par rapport à sa couleur et à la couleur de son père.
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
		int pixelHauteur = this.camera.getPixelHauteur(); //le nombre de pixels sur la hauteur de l'écran
		int pixelLongueur = this.camera.getPixelLongueur(); //le nombre de pixels sur la largeur de l'écran
		
		//  Constantes de la boucle
		Pixel pixelCourant; //pixel dont on calcule la couleur
		Rayon rayonCourant; //rayon père que l'on envoie à partir du pixel père pixelCourant
		Color couleurCourante; //couleur du pixel pixelCourant
		ArrayList<Rayon> listeRayonsFinaux; //liste des rayons fils finaux lancés à partir de pixelCourant
																			//Peut-être changer la collection
		
		// Parcours de tous les pixels de l'écran
		for (int i=0; i < pixelHauteur; i++) {
			for (int j=0; j <pixelLongueur; j++) {
				
				listeRayonsFinaux = new ArrayList<Rayon>();
				
				pixelCourant = camera.getPixel(i,j);
				rayonCourant = new Rayon(camera.getCentre(), pixelCourant.getCoordonnee(), pixelCourant);
				rayonCourant.setCouleur(255, 255, 255); // on initialise la couleur du rayon à BLANC
				
				//lancement du rayon, ie recherche d'une intersection avec un objet
				this.lancerRayon(rayonCourant,0,listeRayonsFinaux);
				
				//calcul de la couleur finale à partir des rayons fils
				couleurCourante = getCouleurFinale(listeRayonsFinaux); // à faire

				//mise à jour de la couleur du pixel
				pixelCourant.setCouleur(couleurCourante);
			}
		}
	}
	
	/** Lancer un rayon, ie recherche d'une intersection avec un objet.
	 * @param rayon
	 * @param compteur le nombre de rebond déjà effectués par rayon
	 * @param listeRayonsFinaux liste des rayons finaux associés au pixel père de rayon
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
			
			// On détermine si l'objet est plus proche de l'origine du rayon
			if (intersectionCourante != null) {
				distanceCourante = intersectionCourante.distance(rayon.getOrigine()); //distance à ajouter dans Point
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

		
			
			// Détection de l'ombre
			if (this.ombreIsOn) {
				Vecteur normal = objetIntersection.getNormal(intersection, rayon);
				lancerShadowRay(rayon, couleurObjetInt, rayon.getDirection(), normal, intersection, objetIntersection);
			} else {
				setCouleurRayon(rayon, couleurOI);	
			}

			//listeRayonsFinaux.add(rayon); // à modifier après la première itération
			
			// Lancer des rayons issus de rayon selon les propriétés de l'objet intersecté
			if (compteur < this.maxRebond) {
				
				boolean allOff = true;
				
				//Parcours des Materiau, ie propriétés de l'objet intersecté
				for (int materiau = 1 ; materiau < Materiau.NB_PROPRIETES ; materiau++) {
					materiauCourant = objetIntersection.getMateriau(materiau);
					if (materiauCourant.isOn()) {
						
						allOff = false;
						
						// Lancé du rayon issu de la propriété materiauCourant de l'objet intersecté
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
	 * Détermination de l'ombre et du shadding.
	 * @param rayon rayon à partir duquel on lance un shadow ray
	 * @param couleurIntersection couleur de l'objet intersecté
	 * @param normal vecteur normal à la surface de l'objet intersecté au point d'impact
	 * @param intersectionObjet objet intersecté
	 */
	private void lancerShadowRay(Rayon rayon, Couleur couleurIntersection, Vecteur incident, Vecteur normal, Point intersectionObjet, Objet3D objetIntersection) {

		// Couleur ambiante � fournir (caract�ristique de la sc�ne)
		Couleur ambiante = new Couleur( Color.WHITE );
		
		// Param�tre : brillance au point d'intersection
		double brillance = 100;
		double importanceSpeculaire = 0.7;
		double importanceAmbiante = 0.1;
		double importanceDiffuse = 1.0;
		
		
		//paramètres de la boucle for
		Rayon shadowRay;
		Color couleurLumiere;
		// Lumiere lumiereCourante;
		double distanceLumiere;
		double distanceCourante;

		//paramètres de la boucle while
		int objet;
		boolean ombre;
		Objet3D objetCourant;
		Point intersectionCourante;
		
		// TODO : Modifier API de couleur pour que ces op�rations y figurent
		double composanteDiffuseRouge = 0.0;
		double composanteDiffuseVerte = 0.0;
		double composanteDiffuseBleue = 0.0;
		double composanteSpeculaireRouge = 0.0;
		double composanteSpeculaireVerte = 0.0;
		double composanteSpeculaireBleue = 0.0;
		double composanteAmbianteRouge = importanceAmbiante * ambiante.getRed();
		double composanteAmbianteVerte = importanceAmbiante * ambiante.getGreen();
		double composanteAmbianteBleue = importanceAmbiante * ambiante.getBlue();

		//constante de la méthode
		List<Lumiere> listeLumieres=  this.scene.getLumiere();
		int nbLumieres = listeLumieres.size();
		int nbObjets = this.scene.getObjet3D().size();
		double diffusion, specularite;

		// On parcourt toutes les lumières
		for (Lumiere lumiere : listeLumieres) {
			
			// lumiereCourante = listeLumieres.get(lumiere);
			distanceLumiere = intersectionObjet.distance(lumiere.getCentre());
			
			// Création du shadow ray pour la lumière courante
			Vecteur vecteurLumiere = new Vecteur(intersectionObjet, lumiere.getCentre());
			// à modifier par getDirection à ajouter dans Lumiere
			shadowRay = new Rayon(vecteurLumiere, intersectionObjet);
			//!!!\ faire une classe shadowRay ??

			objet = 0; // indice de la boucle while
			//ombre = false; // indique si l'objet est à l'ombre par rapport à la lumière courante
			couleurLumiere = lumiere.getCouleur();
			
			// On vérifie que l'objet ne se fait pas de l'ombre lui-même 
			ombre = objetIntersection.getSelfOmbre(intersectionObjet, rayon, lumiere);
			
			if (!ombre) {
				// On parcourt tous les objets pour déterminer si l'objet est à l'ombre
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

			// Si l'objet n'est pas à l'ombre par rapport à la lumière courante
			if (!ombre) {
	
				// calcul du coefficient de shading
				if (this.shadingIsOn) {
					vecteurLumiere.normaliser();
					normal.normaliser();
					incident.normaliser(); 
					double cosTheta = normal.produitScalaire(vecteurLumiere);
					// Calcul du vecteur lumi�re r�fl�chi
					// TODO : Modifier API de vecteur pour que cette op�ration y figure
					Vecteur reflexionLumiere = normal.multiplication(2.0 * cosTheta).soustraire(vecteurLumiere);
					reflexionLumiere.normaliser();
					// D�termine la force de la sp�cularit� en ce point pour cette lumi�re
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
				
				// Id�e : mod�liser la dispersion de la lumi�re (pr�voir un coefficient de dispersion ambiant)
				double distance = intersectionObjet.distance(lumiere.getCentre());
				
				double absorbtion = 1; //100/(distance*distance);
				
				double eclairementRouge = absorbtion * couleurLumiere.getRed();
				double eclairementVert = absorbtion * couleurLumiere.getGreen();
				double eclairementBleu = absorbtion * couleurLumiere.getBlue();
				
				// Accumuler la composante sp�culaire de chaque source de lumi�re
				// composanteSpeculaire += couleurLumiere * specularite;
				composanteSpeculaireRouge += specularite * eclairementRouge;				
				composanteSpeculaireVerte += specularite * eclairementVert;
				composanteSpeculaireBleue += specularite * eclairementBleu;
				
				// Accumuler la composante diffuse de chaque source de lumi�re
				// composanteDiffuse += couleurLumiere * diffusion;
				composanteDiffuseRouge += diffusion * eclairementRouge;				
				composanteDiffuseVerte += diffusion * eclairementVert;
				composanteDiffuseBleue += diffusion * eclairementBleu;
				
				// mise à jour de la couleur du rayon
				// couleurRayon = rayon.getCouleur();
				
			}
			
		}
		
		// Filtrage de la lumi�re re�u par l'objet : prise en compte de la couleur de l'objet
		double composanteRouge = Math.min(composanteAmbianteRouge + composanteDiffuseRouge, couleurIntersection.getRed());
		double composanteVerte = Math.min(composanteAmbianteVerte + composanteDiffuseVerte, couleurIntersection.getGreen());
		double composanteBleue = Math.min(composanteAmbianteBleue + composanteDiffuseBleue, couleurIntersection.getBlue());
		
		// Prise en compte du maximum de chaque composante
		// TODO : faire un mod�le plus r�aliste de la perception logarithmique par l'oeil
		// Id�e : calculer l'�nergie re�ue par chaque point
		// Mettre � l'�chelle 0..255 � partir de l'�nergie maximale et d'une �chelle logarithmique
		// Calculer le logarithme de l'�nergie re�ue
		// Normaliser en 0 et 255
		// Pr�voir un seuil de saturation pour �viter que rien ne soit blanc
		composanteRouge = Math.min(composanteRouge + composanteSpeculaireRouge, 255);
		composanteVerte = Math.min(composanteVerte + composanteSpeculaireVerte, 255);
		composanteBleue = Math.min(composanteBleue + composanteSpeculaireBleue, 255);
		// Si l'objet est à l'ombre pour toutes les lumières, le rayon est noir.
		
		// couleurPoint = Min( Blanc, Min((composanteAmbiante + composanteDiffuse), couleurObjet) + composanteSpeculaire);
		
		Color couleurRayon = new Color( 
				(int) Math.round(composanteRouge),
				(int) Math.round(composanteVerte),
				(int) Math.round(composanteBleue)
				);

		setCouleurRayon(rayon, couleurRayon);
			
	}
}
