package sceneXN;

import java.awt.Color;
import java.awt.image.BufferedImage;

import rayTracing .*;

import elements3D.Couleur;
import elements3D.Plan;
import elements3D.Sphere;
import utilitaire.Point;
import utilitaire.Vecteur;

public class SceneSimple3 {

	public static void main(String args[]) {
		try {
		// Création de la scène
		Scene scene = new Scene(300);
		
			// Ajout d'une sphere
		Sphere sphere = new Sphere(new Point(5,0,5), 5, "Sphere");
	    Couleur couleur = (Couleur)sphere.getMateriau(0);
		couleur.set(0, 255, 0);
		scene.addObjet3D(sphere);
		
			// Ajout d'un plan :
		Plan plan = new Plan(new Vecteur(0,0,1), new Point(0,0,0),"Planche en bois");
	    Couleur couleur3 = (Couleur)plan.getMateriau(0);
	    couleur3.set(0, 0, 255);
	    scene.addObjet3D(plan);
	    
	    	// Ajout d'une lumière
	    //Point coorLumiere = new Point(5,0,15); // au dessus de la sphère
	    Point coorLumiere = new Point(5,15,15); // à droite de la sphère
		Lumiere lumiere = new LumierePonctuelle(coorLumiere, new Color(255, 255, 255));
		scene.addLumiere(lumiere);
	    
			// Création de la caméra:
	    Camera camera = new Camera(new Point(20,0,5),new Vecteur(-10,0,0),500,500,new Vecteur(0,0,10));
		
		

		

		// Lancement du ray tracing
		RayTracing raytracing = new RayTracing(scene, camera, 0, true, true);
		raytracing.lancerRayTracing();
		
		// Enregistrement de l'image
		BufferedImage img = camera.creerImage();
		camera.sauvegarderImage("test_simple3");
		} catch (Exception e) {
			System.out.println("Erreur dans la gestion de la scène");
		}
	}
}
