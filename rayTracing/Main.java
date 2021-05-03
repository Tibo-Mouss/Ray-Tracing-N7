package rayTracing;

import java.awt.Color;

import elements3D .*;
import utilitaire .*;
import IG .*;

public class Main {

	/**
	 * @param args : Arguments donnés dans la ligne de commande.
	 */
	public static void main(String args[]) {
		
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
		    Camera camera = new Camera(new Point(20,0,5),new Vecteur(-10,0,0),500,500,new Vecteur(0,0,10)); //vHaut = (0,0,10) sur l'exemple geogebra
			
		    // Lancement du ray tracing
			RayTracing raytracing = new RayTracing(scene, camera, 0, false, false);
			raytracing.lancerRayTracing();
			
			// Enregistrement de l'image
			camera.sauvegarderImage("test1");
			
			
			FenetrePrincipale fp = new FenetrePrincipale("MAIN", raytracing);
			
			fp.setVisible(true);
		}

}
