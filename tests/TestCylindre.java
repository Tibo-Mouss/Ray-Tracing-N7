package tests;

import org.junit.*;
import static org.junit.Assert.*;
import java.awt.Color;

import elements3D.*;
import utilitaire.*;
import rayTracing.*;

/** Teste le bon fonctionnement de la classe cylindre
 * @author Aymane
 *
 */
public class TestCylindre {
	
	// précision pour les comparaisons entre reels
	public final static double EPSILON = 0.001;
	
    private Cylindre cyl1, cyl2 ,cyl3;
    private double x, y, z;
    
	@Before
	public void setUp() {
		cyl1 = new Cylindre(new Point(0.0, 0.0, 0.0), 3.0, 5.0, new Vecteur(0,0,1),  "cyl1");
		cyl2 = new Cylindre(new Point(1.0, 0.0, 1.0), 5.0, 5.0, new Vecteur(2,0,1), "cyl2");
		cyl3 = new Cylindre(new Point(1.0, -2.0, 1.0), 3.0, 5.0, new Vecteur(0,0,2), "cyl3");
	}
	
	@Test
	public void testConstructeur() {
		assertEquals("Nom de cyl1 faux", cyl1.getNom(), "cyl1");
		assertEquals("Nom de cyl2 faux", cyl2.getNom(), "cyl2");
		
		x = cyl1.getOriginebase().getX();
		y = cyl1.getOriginebase().getY();
		z = cyl1.getOriginebase().getZ();
		assertEquals("Origine_x de cyl1 mauvaise", x, 0.0, TestCylindre.EPSILON);
		assertEquals("Origine_y de cyl1 mauvaise", y, 0.0, TestCylindre.EPSILON);
		assertEquals("Origine_z de cyl1 mauvaise", z, 0.0, TestCylindre.EPSILON);
		x = cyl2.getOriginebase().getX();
		y = cyl2.getOriginebase().getY();
		z = cyl2.getOriginebase().getZ();
		assertEquals("Origine_x de cyl2 mauvaise", x, 1.0, TestCylindre.EPSILON);
		assertEquals("Origine_y de cyl2 mauvaise", y, 0.0, TestCylindre.EPSILON);
		assertEquals("Origine_z de cyl2 mauvaise", z, 1.0, TestCylindre.EPSILON);
		
		double rayon = cyl1.getRayonbase();
		assertEquals("Rayon de cyl1 faux", rayon, 3.0, TestCylindre.EPSILON);
		rayon = cyl2.getRayonbase();
		assertEquals("Rayon de cyl2 faux", rayon, 5.0, TestCylindre.EPSILON);
	}
	
	@Test
	public void testConstructeurNomParDefaut() {
		Cylindre cyl3 = new Cylindre(new Point(0.0, 0.0, 0.0), 3.0, 5, new Vecteur(0,0,1));
		Cylindre cyl4 = new Cylindre(new Point(0.0, 0.0, 0.0), 3.0, 5, new Vecteur(0,0,1));
		
		assertEquals("Nom de cyl3 faux", cyl3.getNom(), "Cylindre1");
		assertEquals("Nom de cyl4 faux", cyl4.getNom(), "Cylindre2");
		
		x = cyl3.getOriginebase().getX();
		y = cyl3.getOriginebase().getY();
		z = cyl3.getOriginebase().getZ();
		assertEquals("Origine_x de cyl3 mauvaise", x, 0.0, TestCylindre.EPSILON);
		assertEquals("Origine_y de cyl3 mauvaise", y, 0.0, TestCylindre.EPSILON);
		assertEquals("Origine_z de cyl3 mauvaise", z, 0.0, TestCylindre.EPSILON);
		
		double rayon = cyl3.getRayonbase();
		assertEquals("Rayon de cyl3 faux", rayon, 3.0, TestCylindre.EPSILON);
	}
	
	@Test
	public void testTranslater() {
		cyl2.translater(1.0, 0.0, 2.0);
		x = cyl2.getOriginebase().getX();
		y = cyl2.getOriginebase().getY();
		z = cyl2.getOriginebase().getZ();
		assertEquals("Translation selon x mauvaise", x, 2.0, TestCylindre.EPSILON);
		assertEquals("Translation selon y mauvaise", y, 0.0, TestCylindre.EPSILON);
		assertEquals("Translation selon z mauvaise", z, 3.0, TestCylindre.EPSILON);
	}
	
	@Test
	public void testGetNormal1() {
		Point impact = new Point(3.0, 0.0, 0.0);
		Vecteur normal = cyl1.getNormal(impact, null);
		// vérifie que le vecteur est vertical:
		assertEquals("Coordonnée selon x de la normal est fausse", normal.getX(), 0.0, TestCylindre.EPSILON);
		assertEquals("Coordonnée selon y de la normal est fausse", normal.getY(), 0.0, TestCylindre.EPSILON);
		assertEquals("Coordonnée selon z de la normal est fausse", normal.getZ(), -1.0, TestCylindre.EPSILON);
	}

	@Test
	public void testGetNormal2() {
		Point impact = new Point(3.0, 0.0, 1.0);
		Vecteur normal = cyl1.getNormal(impact, null);
		// vérifie que le vecteur est vertical:
		assertEquals("Coordonnée selon x de la normal est fausse", normal.getX(), 3.0, TestCylindre.EPSILON);
		assertEquals("Coordonnée selon y de la normal est fausse", normal.getY(), 0.0, TestCylindre.EPSILON);
		assertEquals("Coordonnée selon z de la normal est fausse", normal.getZ(), 0.0, TestCylindre.EPSILON);
	}

	
	@Test
	public void testEstTraversePar1() {
		Vecteur dir = new Vecteur(0.0, -1.0, 0.0);
		Point origine = new Point(0.0, 8.0, 3.0);
		Rayon lum = new Rayon(dir, origine);
		
		Point impact = cyl1.estTraversePar(lum);
		// l'impact devrait être en (0.0, 3.0, 3.0):
		x = impact.getX();
		y = impact.getY();
		z = impact.getZ();
		assertEquals("Coord. selon x mauvaise", x, 0.0, TestCylindre.EPSILON);
		assertEquals("Coord. selon y mauvaise", y, 3.0, TestCylindre.EPSILON);
		assertEquals("Coord. selon z mauvaise", z, 3.0, TestCylindre.EPSILON);
	}
	
	@Test
	public void testEstTraversePar2() {
		Vecteur dir = new Vecteur(-2.0, -4.0, 0.0);
		Point origine = new Point(1.0, 8.0, 3.0);
		Rayon lum = new Rayon(dir, origine);
		
		Point impact = cyl1.estTraversePar(lum);
		// l'impact devrait être en (-1.8, 2.4, 3.0):
		x = impact.getX();
		y = impact.getY();
		z = impact.getZ();
		assertEquals("Coord. selon x mauvaise", x, -1.8, TestCylindre.EPSILON);
		assertEquals("Coord. selon y mauvaise", y, 2.4, TestCylindre.EPSILON);
		assertEquals("Coord. selon z mauvaise", z, 3.0, TestCylindre.EPSILON);
	}
	
	@Test
	public void testEstTraversePar3() {
		Vecteur dir = new Vecteur(-1, -3.0, -2.0);
		Point origine = new Point(3.0, 7.0, 9.0);
		Rayon lum = new Rayon(dir, origine);
		
		Point impact = cyl3.estTraversePar(lum);
		// l'impact devrait être en (2.54, 4.93, 2.23):
		x = impact.getX();
		y = impact.getY();
		z = impact.getZ();
		assertEquals("Coord. selon x mauvaise", x, 1.0, TestCylindre.EPSILON);
		assertEquals("Coord. selon y mauvaise", y, 1.0, TestCylindre.EPSILON);
		assertEquals("Coord. selon z mauvaise", z, 5.0, TestCylindre.EPSILON);
	}
	
	@Test
	public void testEstTraversePar4() {
		Vecteur dir = new Vecteur(-1, -3.0, -2.0);
		Point origine = new Point(3.0, 7.0, 9.0);
		Rayon lum = new Rayon(dir, origine);
		
		Point impact = cyl2.estTraversePar(lum);
		// l'impact devrait être en (2.54, 4.93, 2.23):
		x = impact.getX();
		y = impact.getY();
		z = impact.getZ();
		assertEquals("Coord. selon x mauvaise", x, 1.526, TestCylindre.EPSILON);
		assertEquals("Coord. selon y mauvaise", y, 2.578, TestCylindre.EPSILON);
		assertEquals("Coord. selon z mauvaise", z, 6.052, TestCylindre.EPSILON);
	}
	
	
	
	public void testGetSelfOmbre() {
		Point impact;
		Point origineLum = new Point(-4.0, 0.0, 3.0);
		LumierePonctuelle lum = new LumierePonctuelle(origineLum, Color.WHITE);
		
		// cas où l'objet n'est pas entre l'impact et la lumière (pas d'ombre):
		impact = new Point(-3.0, 0.0, 3.0);
		assertTrue("L'objet ne devrait pas faire de l'ombre au point d'impact", !cyl1.getSelfOmbre(impact, null, lum));
		
		// cas où l'objet est entre l'impact et la lumière (ombre):
		impact = new Point(3.0, 0.0, 3.0);
		assertTrue("L'objet devrait faire de l'ombre au point d'impact", cyl1.getSelfOmbre(impact, null, lum));
	}
}
