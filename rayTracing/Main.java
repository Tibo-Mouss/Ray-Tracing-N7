package rayTracing;

import java.awt.Color;

import elements3D .*;
import exception.LumiereHorsSceneException;
import exception.MaxRebondsNegatifException;
import exception.NomVideException;
import exception.Objet3DHorsSceneException;
import exception.SauvegarderFichierException;
import exception.TaillePixelsImageException;
import utilitaire .*;
import IG .*;

import java.awt.image.BufferedImage;

public class Main {

	/**
	 * @param args : Arguments donnés dans la ligne de commande.
	 */
	public static void main(String args[]) {
		
		    
			Camera camera;
			Scene scene;
			RayTracing raytracing;
			try {
				//Creation de la scene par defaut
				scene = defaultScene();
				
				//Creation de la camera par defaut
				camera = defaultCamera();
				
				// Lancement du ray tracing
				raytracing = new RayTracing(scene, camera, 0, false, false);
				raytracing.lancerRayTracing();
				
				// Enregistrement de l'image
				BufferedImage image = camera.creerImage();
				camera.sauvegarderImage(image, "test1");
				
				FenetrePrincipale fp = new FenetrePrincipale("MAIN", raytracing);
				fp.setVisible(true);
				
			} catch (MaxRebondsNegatifException e1) {
				e1.printStackTrace();
			} catch (LumiereHorsSceneException e) {
				e.printStackTrace();
			} catch (NomVideException e) {
				e.printStackTrace();
			} catch (Objet3DHorsSceneException e) {
				e.printStackTrace();
			} catch (TaillePixelsImageException e) {
				e.printStackTrace();
			}	catch (SauvegarderFichierException e) {
				e.printStackTrace();
			}
			
		}
	
	private static Scene defaultScene() throws LumiereHorsSceneException, NomVideException, Objet3DHorsSceneException {
		// Création de la scène
	    Scene scene = new Scene(300);
	    
	    // Ajout d'une sphère
	    Sphere sphere1;
	
		sphere1 = new Sphere(new Point(5,0,5), 1, "Sphere1");
		Couleur couleur1 = (Couleur)sphere1.getMateriau(0);
	    couleur1.set(0, 255, 0);
	    scene.addObjet3D(sphere1);
	    
	    	// Ajout d'une sphère
	    Sphere sphere2;
		sphere2 = new Sphere(new Point(0,5,5), 1, "Sphere2");
		Couleur couleur2 = (Couleur)sphere2.getMateriau(0);
	    couleur2.set(255, 255, 0);
	    scene.addObjet3D(sphere2);
	
	    
	    
	    	// Ajout d'un plan
	    Plan plan;
		plan = new Plan(new Vecteur(0,0,1), new Point(0,0,0),"plan");
		Couleur couleur3 = (Couleur)plan.getMateriau(0);
	    couleur3.set(0, 0, 255);
	    scene.addObjet3D(plan);
	    
	    	// Ajout d'une lumière
		Color couleurLumiere = new Color(255,255,255);
		Point pointLumiere = new Point(5,10,15);
		Lumiere lumiere = new LumierePonctuelle(pointLumiere, couleurLumiere);
		scene.addLumiere(lumiere);
		
	    
	    return scene;
	}
	
	private static Camera defaultCamera() throws TaillePixelsImageException {
		Point centreCamera = new Point(20,0,5);
		Vecteur vecteurCameraEcran = new Vecteur(-10,0,0);
		int pixelHauteur = 500;
		int pixelLongueur = 500;
		Vecteur VHaut = new Vecteur(0,0,10);
		
		Camera camera;
		camera = new Camera(centreCamera, vecteurCameraEcran, pixelHauteur, pixelLongueur, VHaut);
		
		return camera;
	}

}
