package tests;
import org.junit.*;
import static org.junit.Assert.*;

import java.awt.Color;

import utilitaire .*;
import rayTracing .*;

/** @author tibo */

public class TestCamera {

	// précision pour les comparaisons entre reels
	public final static double EPSILON = 0.001;
	
	private Point centrecam1, centrecam2;
	private Vecteur vecteurCameraEcran1, VHaut1, vecteurCameraEcran2, VHaut2;
	private int pixelHauteur, pixelLongueur;
	
	
	@Before public void setUp() {
		centrecam1 = new Point(-1,0,0);
		vecteurCameraEcran1 = new Vecteur(1,0,0);
		pixelHauteur = 10;
		pixelLongueur = 20;
		VHaut1 = new Vecteur(0,0,1);
		
		centrecam2 = new Point(-1,-1,0);
		vecteurCameraEcran2 = new Vecteur(1,1,0);
		VHaut2 = new Vecteur(0,0,1);
	}
	
	@Test public void testConstructeur() {
		Camera cam = new Camera(centrecam1, vecteurCameraEcran1, pixelHauteur, pixelLongueur, VHaut1);
		/*
		cam.printVecteursIterateurs();
		cam.printCoins();
		System.out.println("Pixel en haut a gauche :");
		System.out.println(cam.getPixel(0, 0).getCoordonnee().getY()+ "<- Y    Z ->" + cam.getPixel(0, 0).getCoordonnee().getZ());
		System.out.println("Pixel en bas a droite :");
		System.out.println(cam.getPixel(pixelHauteur-1, pixelLongueur-1).getCoordonnee().getY()+ "<- Y    Z ->" + cam.getPixel(pixelHauteur-1, pixelLongueur-1).getCoordonnee().getZ());
		*/
		
		assertEquals("Profondeur Pixel HG fausse",cam.getPixel(0, 0).getCoordonnee().getX(),0,EPSILON);
		assertEquals("Abs Pixel HG fausse",cam.getPixel(0, 0).getCoordonnee().getY(),-1.9,EPSILON);
		assertEquals("Ordonnee Pixel HG fausse",cam.getPixel(0, 0).getCoordonnee().getZ(),0.9,EPSILON);
		assertEquals("Profondeur Pixel HG fausse",cam.getPixel(pixelHauteur-1, pixelLongueur-1).getCoordonnee().getX(),0,EPSILON);
		assertEquals("Abs Pixel HG fausse",cam.getPixel(pixelHauteur-1, pixelLongueur-1).getCoordonnee().getY(),1.9,EPSILON);
		assertEquals("Ordonnee Pixel HG fausse",cam.getPixel(pixelHauteur-1, pixelLongueur-1).getCoordonnee().getZ(),-0.9,EPSILON);
		
		assertEquals("Focale fausse", cam.getFocale(),1, EPSILON);
	}
	
	@Test public void testConstructeur2() {
		Camera cam = new Camera(centrecam2, vecteurCameraEcran2, pixelHauteur, pixelLongueur, VHaut2);
		
		cam.printCoinsImage();
		cam.printCoinsFenetre();
		cam.printVecteursIterateurs();
	}
	
}
