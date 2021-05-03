package tests;
import org.junit.*;

import static org.junit.Assert.assertEquals;

import java.awt.Color;

import rayTracing.LumierePonctuelle;
import utilitaire.Point;

import org.junit.jupiter.api.Test;

class TestLumierePonctuelle {
	
	public final static double EPSILON = 0.001;

	private LumierePonctuelle lum1 = new LumierePonctuelle(new Point(10,20,35.5), Color.BLACK);
	private LumierePonctuelle lum2  = new LumierePonctuelle(new Point(25.3,0.0,42), Color.DARK_GRAY);
	
	
	@Test
	void testConstructeur() {
		Point centre1 = lum1.getCentre();
		Point correct = new Point(10,20,35.5);
		assertEquals("Coordonnée en x fausse",centre1.getX(),correct.getX(),EPSILON);
		assertEquals("Coordonnée en y fausse",centre1.getY(),correct.getY(),EPSILON);
		assertEquals("Coordonnée en z fausse",centre1.getZ(),correct.getZ(),EPSILON);
		assertEquals("Couleur fausse", lum1.getCouleur(), Color.BLACK);
	}
	
	@Test
	void testSetCentre() {
		Point new_centre = lum1.getCentre();
		new_centre.setX(24);
		lum2.setCentre(new_centre);
		assertEquals("Coordonnée en x fausse",lum2.getCentre().getX(),24,EPSILON);
		assertEquals("Coordonnée en y fausse",lum2.getCentre().getY(),20,EPSILON);
		assertEquals("Coordonnée en z fausse",lum2.getCentre().getZ(),35.5,EPSILON);
		assertEquals("Coordonnée en x modifiée",lum1.getCentre().getX(),10,EPSILON);
		assertEquals("Coordonnée en y modifiée",lum1.getCentre().getY(),20,EPSILON);
		assertEquals("Coordonnée en z modifiée",lum1.getCentre().getZ(),35.5,EPSILON);
		
	}
	
	@Test
	void testSetCouleur() {
		lum2.setCouleur(Color.RED);
		assertEquals("Couleur fausse", lum2.getCouleur(), Color.RED);
	}

}
