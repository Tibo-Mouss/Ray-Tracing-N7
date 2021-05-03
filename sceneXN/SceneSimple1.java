package sceneXN;
import java.awt.Color;
import java.awt.image.BufferedImage;

import elements3D.Couleur;
import elements3D.Cube;
import elements3D.Sphere;
import utilitaire.Point;
import utilitaire.Vecteur;

import rayTracing .*;

public class SceneSimple1 {

	public static void main(String args[]) {
		
		// Création de la scène
		Scene scene = new Scene(300);
		scene.setCouleur(Color.WHITE);
		
			// Ajout d'un cube
		Sphere cube = new Sphere(new Point(0,0,0), 4, "Cube");
	    Couleur couleur = (Couleur)cube.getMateriau(0);
		couleur.set(255, 0, 0);
		scene.addObjet3D(cube);
		
			// Ajout d'une lumière
		Lumiere lumiere = new LumierePonctuelle(new Point(10,50,0), new Color(255, 255, 255));
		scene.addLumiere(lumiere);
		
		// Création de la caméra
		Camera camera = new Camera(new Point(-15,7,8),new Vecteur(10, -7, -8),500,500,new Vecteur(0,0,10));
		
		// Lancement du ray tracing
		RayTracing raytracing = new RayTracing(scene, camera, 10, true,true);
		raytracing.lancerRayTracing();
		
		// Enregistrement de l'image
		BufferedImage img = camera.creerImage();
		camera.sauvegarderImage(img, "test_simple1");
		System.out.println("Rendu d'image termine");
	}
}
