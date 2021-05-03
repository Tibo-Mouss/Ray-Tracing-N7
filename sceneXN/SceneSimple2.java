package sceneXN;

import java.awt.Color;
import rayTracing .*;

import elements3D.Couleur;
import elements3D.Plan;
import elements3D.Sphere;
import utilitaire.Point;
import utilitaire.Vecteur;

public class SceneSimple2 {

	public static void main(String args[]) {
		Scene scene = new Scene(300);
		//Creation camera:
		Point centreCamera = new Point(-5,0,0);
		Vecteur vecteurCameraEcran = new Vecteur(3,0,0);
		int pixelHauteur = 300;
		int pixelLongueur = 300;
		Vecteur VHaut = new Vecteur(0,0,2);
		Camera camera = new Camera(centreCamera, vecteurCameraEcran,pixelHauteur, pixelLongueur, VHaut);
		//System.out.println(camera.getFocale());
		camera.printCoinsFenetre();
		camera.printCoinsImage();
		
		
		//creation d'une sphere
		Sphere sphere = new Sphere(new Point(2,0,0), 2, "Sphere");
	    Couleur couleur = (Couleur)sphere.getMateriau(0);
		couleur.set(0, 255, 0);
		scene.addObjet3D(sphere);
		
		//Creation d'un plan :
		Plan plan = new Plan(new Vecteur(0,0,1), new Point(0,0,-100),"Planche en bois");
	    Couleur couleur3 = (Couleur)plan.getMateriau(0);
	    couleur3.set(0, 0, 255);
	    scene.addObjet3D(plan);
		
		Lumiere lumiere = new LumierePonctuelle(new Point(100,0,30), new Color(255, 255, 255));
		scene.addLumiere(lumiere);
		
		RayTracing raytracing = new RayTracing(scene, camera, 1, true, false);
		raytracing.lancerRayTracing();
		camera.sauvegarderImage("test_simple2");
		System.out.println("Traitement d'image fini !");
	}
}
