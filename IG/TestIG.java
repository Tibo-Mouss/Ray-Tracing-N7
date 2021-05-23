package IG;
import java.awt.Color;
import java.awt.color.*;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import IG.FenetrePrincipale;
import rayTracing.*;
import elements3D.*;
import exception.LumiereHorsSceneException;
import exception.MaxRebondsNegatifException;
import utilitaire.*;


public class TestIG {

		public static void main(String args[]) throws MaxRebondsNegatifException, LumiereHorsSceneException {
			
			// Cr�ation de la sc�ne
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
		    
		    	// Ajout d'une lumi�re
		    Point coorLumiere = new Point(5,0,15); // au dessus de la sph�re
		    //Point coorLumiere = new Point(5,15,15); // � droite de la sph�re
			Lumieres lumiere = new LumierePonctuelle(coorLumiere, new Color(255, 255, 255));
			scene.addLumiere(lumiere);
		    
				// Cr�ation de la cam�ra:
		    Camera camera = new Camera(new Point(20,0,5),new Vecteur(-10,0,0),500,500,new Vecteur(0,0,10));
			
			
			// Lancement du ray tracing
			RayTracing raytracing = new RayTracing(scene, camera, 0, true, true);

				
			FenetrePrincipale fp = new FenetrePrincipale("TEST", raytracing);
			
			fp.setVisible(true);
			
			}
}