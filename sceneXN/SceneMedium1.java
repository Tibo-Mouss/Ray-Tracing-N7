package sceneXN;

import java.awt.Color;
import java.awt.image.BufferedImage;

import elements3D .*;
import utilitaire .*;
import rayTracing .*;

public class SceneMedium1 {
    /**
     * @author yliu6
     * @param args
     */
	public static void main(String args[]) {
			
		try {
			// Création de la scène
		    Scene scene = new Scene(300);
		    
		    	// Ajout d'une sphère
		    Sphere sphere1 = new Sphere(new Point(5,0,5), 1, "Sphere1");
		    Couleur couleur1 = (Couleur)sphere1.getMateriau(0);
		    couleur1.set(0, 255, 0);
		    scene.addObjet3D(sphere1);
		    
		    	// Ajout d'une sphère
		    Sphere sphere2 = new Sphere(new Point(0,5,5), 1, "Sphere2");
		    Couleur couleur2 = (Couleur)sphere2.getMateriau(0);
		    couleur2.set(255, 255, 0);
		    scene.addObjet3D(sphere2);
		    
		    	// Ajout d'un plan
		    Plan plan = new Plan(new Vecteur(0,0,1), new Point(0,0,0),"plan");
		    Couleur couleur3 = (Couleur)plan.getMateriau(0);
		    couleur3.set(0, 0, 255);
		    scene.addObjet3D(plan);
		    
		    	// Ajout d'une lumière
		    Lumiere lumiere = new LumierePonctuelle(new Point(5,10,15),new Color(255,255,255));
		    scene.addLumiere(lumiere);
		    
		    // Création de la caméra
		    Camera camera = new Camera(new Point(20,0,5),new Vecteur(-10,0,0),1000,1000,new Vecteur(0,0,10)); //vHaut = (0,0,10) sur l'exemple geogebra
			
		    // Lancement du ray tracing
			RayTracing raytracing = new RayTracing(scene, camera, 10, true, true);
			raytracing.lancerRayTracing();
			
			// Enregistrement de l'image
			BufferedImage img = camera.creerImage();
			camera.sauvegarderImage("test1");
		} catch (Exception e) {
			System.out.println("Erreur dans la gestion de la scène.");
		}
		
	}	

}
