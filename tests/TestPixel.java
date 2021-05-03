package tests;
import elements3D .*;
import utilitaire .*;

import static org.junit.Assert.*;

import java.awt.Color;

import org.junit.*;
import org.junit.Assert;

import utilitaire .*;
import rayTracing .*;

/**
 * Classe de test du type Pixel;
 */

public class TestPixel {
	
	// Variables utilisées pour les tests 
		Point A = new Point(1,2,3);
		Point B = new Point(1,1,1); 
		Point C = new Point(6,6,6);
		Vecteur V = new Vecteur(2,2,2);
		
	// Pixel à tester
		Pixel A1;
		Pixel A2;
	
	/** Vérifier si deux points ont mêmes coordonnées.
	  * @param p1 le premier point
	  * @param p2 le deuxieme point
	 */
	private static void memesCoordonnees(Point p1, Point p2) {
		assertTrue(p1.getX() == p2.getX());
		assertTrue(p1.getY() == p2.getY());
		assertTrue(p1.getZ() == p2.getZ());
	}
		
	@Before
	public void setUp()  {
		// Construire les pixels avec les deux constructeurs définis
		this.A1 = new Pixel(A);
		this.A2 = new Pixel(B, Color.BLUE);
	}

	@Test
	public void testPixelPoint1() {
		memesCoordonnees(A,A1.getCoordonnee());
		assertTrue(A1.getCouleur() == Color.BLACK);
	}

	@Test
	public void testPixelPoint2() {
		memesCoordonnees(B,A2.getCoordonnee());
		assertTrue(A2.getCouleur() == Color.BLUE);
	}

	
	@Test
	public void testTranslater() {
		A1.translater(V);
		memesCoordonnees(A1.getCoordonnee(),new Point(3,4,5));

	}

	@Test
	public void testSetCouleur() {
		A1.setCouleur(Color.GREEN);
		A2.setCouleur(Color.CYAN);
		assertTrue(A1.getCouleur() == Color.GREEN);
		assertTrue(A2.getCouleur() == Color.CYAN);
	}

	@Test
	public void testSetCoordonnee() {
		A1.setCoordonnee(C);
		A2.setCoordonnee(A);
		memesCoordonnees(A1.getCoordonnee(),C);
		memesCoordonnees(A2.getCoordonnee(),A);
	}

}
