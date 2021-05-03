package tests;
import org.junit.*;
import static org.junit.Assert.*;

import java.awt.Color;

import utilitaire .*;
import rayTracing .*;
import elements3D .*;

public class TestCube {
	// précision pour les comparaisons entre reels
	public final static double EPSILON = 0.001;
	
	private Point centreCube, pointRayon;
	private Vecteur directionRayon;
	private double arete;
	private Rayon rayon;
	private Pixel pixelPere;
	
	private Cube cube;
	private Vecteur v1,v2,v3;
	private Point p2,p3,p4;
	private Rayon r2;
	private Lumiere lumiere;
	private boolean selfOmbre;
	
	@Before public void setUp() {
		centreCube = new Point(1,1,1);
		arete = 2.0;
		
		pointRayon = new Point(0,1,0.5);
		directionRayon = new Vecteur(1,2,1);
		pixelPere = new Pixel(new Point(0,0,0));
		rayon = new Rayon(directionRayon,pointRayon,pixelPere);
		
		v2 = new Vecteur(1,0.25,0.25);
		p2 = new Point(-1,0.5,0.5);
		r2 = new Rayon(v2,p2,pixelPere);
		p4 = new Point(2.4,2.4,5);
		
		lumiere = new LumierePonctuelle(p2, Color.white, "Theo de Silverberg");
	}
	
	@Test public void test() {
		cube = new Cube(centreCube, arete, "Jostoffe le cube");
		
		//cube.afficherCollection();
		
		selfOmbre = cube.getSelfOmbre(pointRayon, rayon, lumiere);
		assertEquals("selfombre marche po", selfOmbre,false);
		
		p3 = cube.estTraversePar(r2);
		p3.afficher();
		assertEquals("Abs de p3 fausse", p3.getX(), 0.0, EPSILON);
		assertEquals("Ordonnee de p3 fausse", p3.getY(), 0.75, EPSILON);
		assertEquals("Profondeur de p3 fausse", p3.getZ(), 0.75, EPSILON);
		
		v3 = cube.directionReflexion(r2, p3);
		assertEquals("Abs de v3 fausse", v3.getX(), -1.0, EPSILON);
		assertEquals("Ordonnee de v3 fausse", v3.getY(), 0.25, EPSILON);
		assertEquals("Profondeur de v3 fausse", v3.getZ(), 0.25, EPSILON);
		
		Point p4 = new Point(2.0,2.5,2.0);
		cube.afficherCollection();
		int indice = cube.appartientCube(p4);
		System.out.println(indice);
	}
		
}
