package tests;
import org.junit.*;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.*;

import utilitaire.*;

public class TestVecteur {
	
	public final static double EPSILON = 0.001;
	
	private Vecteur u, v, z;
	private Point A, B;
	
	/** Verifier si deux points ont memes coordonnees.
	  * @param p1 le premier point
	  * @param p2 le deuxieme point
	  */
	static void memesCoordonnees(String message, Vecteur v1, Vecteur v2) {
		assertEquals(message + " (x)", v1.getX(), v2.getX(), EPSILON);
		assertEquals(message + " (y)", v1.getY(), v2.getY(), EPSILON);
		assertEquals(message + " (y)", v1.getZ(), v2.getZ(), EPSILON);
	}
	
	static void bonnesCoordonnees(String message, Vecteur v1, double x, double y, double z) {
		assertEquals(message + " (x)", v1.getX(), x,  EPSILON);
		assertEquals(message + " (y)", v1.getY(), y, EPSILON);
		assertEquals(message + " (z)", v1.getZ(), z, EPSILON);
	}
	
	@Before public void setUp() {
		A = new Point(0.0,0.0,0.0);
		B = new Point(1.0,1.0,1.0);
	}
	
	@Test public void testerE12a() {
		Vecteur v = new Vecteur(A,B);
		memesCoordonnees( "E12 : mauvaise coordonnées", new Vecteur(1.0, 1.0, 1.0), v);

	}
	
	@Test public void testE12b() {
		Vecteur u = new Vecteur(1.0,-1.0,0.0);
		bonnesCoordonnees("E12 : mauvaise coordonnées", u , 1.0, -1.0, 0.0);
	}
	
	@Test public void E13a() {
		Vecteur u = new Vecteur(1.0,-1.0,0.0);
		Vecteur z = u.copie();
		memesCoordonnees("E13 : mauvaise coordonnées", u, z);
	}
	
	@Test public void E14a() {
		Vecteur u = new Vecteur(1.0,-1.0,0.0);
		u.setX(0.5);
		bonnesCoordonnees("E14 : problème set X", u, 0.5, -1.0, 0.0);
	}
	
	@Test public void E14b() {
		Vecteur u = new Vecteur(1.0,-1.0,0.0);
		u.setY(0.5);
		bonnesCoordonnees("E14 : problème set Y", u, 1.0, 0.5, 0.0);
	}
	
	@Test public void E14c() {
		Vecteur u = new Vecteur(1.0,-1.0,0.0);
		u.setZ(0.5);
		bonnesCoordonnees("E14 : problème set Z", u, 1.0, -1.0, 0.5);
	}
	
	@Test public void E15a() {
		Vecteur u = new Vecteur(1.0,-1.0,0.0);
		assertEquals("E15 : problème get X", u.getX(), 1.0, EPSILON);
	}
	
	@Test public void E15b() {
		Vecteur u = new Vecteur(1.0,-1.0,0.0);
		assertEquals("E15 : problème get Y", u.getY(), -1.0, EPSILON);
	}
	
	@Test public void E15c() {
		Vecteur u = new Vecteur(1.0,-1.0,0.0);
		assertEquals("E15 : problème get Z", u.getZ(), 0.0, EPSILON);
	}
	
	@Test public void E16a() {
		Vecteur u = new Vecteur(1.0, 0.0, 2.0);
		Vecteur z = new Vecteur(2.0, 1.0, 0.0);
		assertEquals("E16 : mauvais produit scalaire", u.produitScalaire(z), 2.0, EPSILON);
	}
	
	@Test public void E16b() {
		Vecteur u = new Vecteur(4.0, 0.0, 3.0);
		assertEquals("E16 : mauvais module", u.module(), 5.0, EPSILON);
	}
	
	@Test public void E17a() {
		Vecteur u = new Vecteur(4.0, 0.0, 3.0);
		Vecteur z = new Vecteur(2.0, 1.0, 0.0);
		memesCoordonnees("E17 : mauvaises coordonnées soustraction", u.soustraire(z), new Vecteur(2.0, -1.0, 3.0));
	}
	
	@Test public void E17b() {
		Vecteur u = new Vecteur(4.0, 0.0, 3.0);
		Vecteur z = new Vecteur(2.0, 1.0, 0.0);
		memesCoordonnees("E17 : mauvaises coordonnées somme", u.sommer(z), new Vecteur(6.0, 1.0, 3.0));
	}
	
	@Test public void E17c() {
		Vecteur u = new Vecteur(4.0, 0.0, 3.0);
		bonnesCoordonnees("E17 : mauvaises coordonnées multiplication", u.multiplication(-0.5), -2.0, 0.0, -1.5);
	}
	
	@Test public void E17d() {
		Vecteur u = new Vecteur(4.0, 0.0, 3.0);
		u.normaliser();
		bonnesCoordonnees("E17 : mauvaises coordonnées normaliser", u, 4.0/5, 0.0, 3.0/5);
	}
	
	@Test public void E18a() {
		Vecteur u = new Vecteur(1.0,0.0,0.0);
		memesCoordonnees("E18 : mauvaises coordonnées rotation X ", u.rotationX(Math.PI), new Vecteur(1.0, 0.0, 0.0));
	}

	@Test public void E18b() {
		Vecteur u = new Vecteur(1.0,0.0,0.0);
		memesCoordonnees("E18 : mauvaises coordonnées rotation Y ", u.rotationY(Math.PI), new Vecteur(-1.0, 0.0, 0.0));
	}
	
	@Test public void E18c() {
		Vecteur u = new Vecteur(1.0,0.0,0.0);
		memesCoordonnees("E18 : mauvaises coordonnées rotation Z ", u.rotationZ(Math.PI), new Vecteur(-1.0, 0.0, 0.0));
	}
	
	@Test public void E18d() {
		Vecteur u = new Vecteur(1.0,0.0,0.0);
		memesCoordonnees("E18 : mauvaises coordonnées rotation Axe ", u.rotationAxe(new Vecteur(0.0,0.0,1.0), Math.PI), new Vecteur(-1.0, 0.0, 0.0));
	}

	@Test public void E18e() {
		Vecteur u = new Vecteur(1.0,0.0,0.0);
		memesCoordonnees("E18 : mauvaises coordonnées rotation XYZ ", u.rotationXYZ(0,Math.PI,Math.PI), new Vecteur(1.0, 0.0, 0.0));
	}
	
	@Test public void E18f() {
		Vecteur u = new Vecteur(0.0,0.0,1.0);
		memesCoordonnees("E18 : mauvaises coordonnées rotation ZYX ", u.rotationZYX(0,Math.PI,Math.PI), new Vecteur(0.0, 0.0, 1.0));
	}
}
