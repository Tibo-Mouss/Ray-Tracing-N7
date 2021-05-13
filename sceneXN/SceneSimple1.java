package sceneXN;
import java.awt.Color;
import java.awt.image.BufferedImage;

import elements3D.Couleur;
import elements3D.Cube;
import elements3D.Sphere;
import elements3D.Cone;
import exception.LumiereHorsSceneException;
import exception.MaxRebondsNegatifException;
import exception.Objet3DHorsSceneException;
import utilitaire.Point;
import utilitaire.Vecteur;

import rayTracing .*;

public class SceneSimple1 {

	public static void main(String args[]) {
		// Création de la scène
		Scene scene = new Scene(300);
		scene.setCouleur(Color.WHITE);
		
			// Ajout d'un cube
		Cone cube = new Cone(new Point(0,0,0), new Vecteur(0,0,1), 5, 2, "Cone");
	    Couleur couleur = (Couleur)cube.getMateriau(0);
		couleur.set(0, 255, 0);
		try {
			scene.addObjet3D(cube);
		} catch (Objet3DHorsSceneException e) {
			e.printStackTrace();
		}
		
			// Ajout d'une lumière
		Lumiere lumiere = new LumierePonctuelle(new Point(10,50,0), new Color(255, 255, 255));
		try {
			scene.addLumiere(lumiere);
		} catch (LumiereHorsSceneException e) {
			e.printStackTrace();
		}
		
		// Création de la caméra
		Camera camera = new Camera(new Point(-15,7,-8),new Vecteur(10, -7, 8),500,500,new Vecteur(0,0,10));
		camera.printVecteursIterateurs();
		camera.printCoinsFenetre();
		
		
		// Lancement du ray tracing
		RayTracing raytracing;
		try {
			raytracing = new RayTracing(scene, camera, 10, true,false);
			raytracing.lancerRayTracing();
		} catch (MaxRebondsNegatifException e) {
			e.printStackTrace();
		}
		
		
		// Enregistrement de l'image
		BufferedImage img = camera.creerImage();
		camera.sauvegarderImage(img, "test_simple1");
		System.out.println("Rendu d'image termine");		
	}
}
