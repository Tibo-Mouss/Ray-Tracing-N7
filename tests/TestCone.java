package tests;
import org.junit.*;
import static org.junit.Assert.*;

import java.awt.Color;

import utilitaire .*;
import rayTracing .*;
import elements3D .*;

/** @author tibo */

public class TestCone {
	// précision pour les comparaisons entre reels
	public final static double EPSILON = 0.001;
	
	private Point centreBase;
	private Vecteur VHaut;
	private double hauteur, rayon;
	private String nom;
	
	private Point p1;
	
	private Point p2;
	private Vecteur v2;
	private Rayon r2;
	
	private Point pointLumiere;
	private Lumiere lumiere;
	
	@Before public void setUp() {
		centreBase = new Point(0,0,0);
		VHaut = new Vecteur(0,0,1);
		hauteur = 2;
		rayon = 2;
		nom = "Cone de test";
		
		p1 = new Point(0,1,1);
		
		p2 = new Point(-1,2,1);
		v2 = new Vecteur(1,-1,0);
		r2 = new Rayon(v2, p2);
		
		pointLumiere = new Point(-50, -50, 50);		
		lumiere = new LumierePonctuelle(pointLumiere, Color.white, "Theo de Silverberg");
	}
	
	@Test public void test() {
		Cone cone = new Cone(centreBase, VHaut, hauteur, rayon, nom);
		
		System.out.println( cone.toString() );
		
		assertEquals("Methode appartientPyramide pose pb",cone.appartientPyramide(p1), 2);
		
		Vecteur result2 = cone.directionReflexion(r2, p1);
		result2.afficher();
		
		Point result3 = cone.estTraversePar(r2);
		result3.afficher();
	}
		
}
