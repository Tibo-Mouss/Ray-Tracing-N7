package tests;


import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.awt.Color;

import org.junit.*;
import org.junit.jupiter.api.Test;

import rayTracing.Pixel;
import rayTracing.Rayon;
import utilitaire.Point;
import utilitaire.Vecteur;

class TestRayon {
	public static final double EPSILON = 1e-8;
	
	private Rayon r1;
	private Rayon r2;
	private Pixel p1;


	@Before
	public void setUp() {



	}
	
	static void memesCoordonnees(String message, Point p1, Point p2) {
		assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
		assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
		assertEquals(message + " (z)", p1.getZ(), p2.getZ(), EPSILON);
	}
	
	static void memesCoordonnees(String message, Vecteur p1, Vecteur p2) {
		assertEquals(message + " (x)", p1.getX(), p2.getX(), EPSILON);
		assertEquals(message + " (y)", p1.getY(), p2.getY(), EPSILON);
		assertEquals(message + " (z)", p1.getZ(), p2.getZ(), EPSILON);
	}
	
	@Test
	public void testConstructeur1() {
		p1 = new Pixel(new Point(0,0,0));
		r1 = new Rayon(new Point(1,0,1), new Point(2,1,0),p1);
		assertNotNull(p1);
		memesCoordonnees("erreur", new Vecteur(1,1,-1), r1.getDirection());
		memesCoordonnees("erreur", new Point(1,0,1),  r1.getOrigine());
		memesCoordonnees("erreur", new Point(0,0,0), r1.getPixelPere().getCoordonnee());
		assertTrue(r1.getNbRebonds() == 0);
	}
	
	@Test
	public void testConstructeur2() {
		p1 = new Pixel(new Point(1,0,2));
		r1 = new Rayon(new Point(1,0,1), new Point(2,1,0),p1);
		r2 = new Rayon(new Point(2,-1,3), new Point(1,2,-1), p1, r1, Color.green);
		assertNotNull(p1);
		memesCoordonnees("erreur", new Vecteur(-1,3,-4), r2.getDirection());
		memesCoordonnees("erreur", new Point(2,-1,3),  r2.getOrigine());
		memesCoordonnees("erreur", new Point(1,0,2), r2.getPixelPere().getCoordonnee());
		assertEquals(Color.green, r2.getCouleur());
		assertTrue(r2.getNbRebonds() == 1);
	}
	
	@Test
	public void testConstructeur3() {
		p1 = new Pixel(new Point(1,0,2));
		r1 = new Rayon(new Point(1,0,1), new Point(2,1,0),p1);
		r2 = new Rayon(new Vecteur(-1,3,-4),new Point(0,0,0), p1, r1, Color.green);
		assertNotNull(p1);
		memesCoordonnees("erreur", new Vecteur(-1,3,-4), r2.getDirection());
		memesCoordonnees("erreur", new Point(0,0,0),  r2.getOrigine());
		memesCoordonnees("erreur", new Point(1,0,2), r2.getPixelPere().getCoordonnee());
		assertEquals(Color.green, r2.getCouleur());
		assertTrue(r2.getNbRebonds() == 1);
	}
	
	@Test
	public void testSetMoyenneCouleur() {
		p1 = new Pixel(new Point(1,0,2));
		r1 = new Rayon(new Point(1,0,1), new Point(2,1,0),p1);
		r1.setCouleur(255, 0, 0);
		r2 = new Rayon(new Vecteur(-1,3,-4),new Point(0,0,0), p1, r1, Color.green);
		assertNotNull(p1);
		assertEquals(Color.red, r1.getCouleur());
		assertEquals(Color.green, r2.getCouleur());
		assertEquals(new Color(127,127,0),r1.moyenneCouleur(r2));
	}
	


}
