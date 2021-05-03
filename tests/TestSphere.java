package tests;

import org.junit.*;
import static org.junit.Assert.*;
import java.awt.Color;

import elements3D.*;
import utilitaire.*;
import rayTracing.*;

/** Teste le bon fonctionnement de la classe Sphere
 * @author Edgar
 *
 */
public class TestSphere {
	
	// précision pour les comparaisons entre reels
	public final static double EPSILON = 0.001;
	
    private Sphere sph1, sph2;
    private double x, y, z;
    
	@Before
	public void setUp() {
		sph1 = new Sphere(new Point(0.0, 0.0, 0.0), 2.0, "sph1");
		sph2 = new Sphere(new Point(1.0, 2.0, 3.0), 3.0, "sph2");
	}
	
	@Test
	public void testConstructeur() {
		assertEquals("Nom de sph1 faux", sph1.getNom(), "sph1");
		assertEquals("Nom de sph2 faux", sph2.getNom(), "sph2");
		
		x = sph1.getOrigine().getX();
		y = sph1.getOrigine().getY();
		z = sph1.getOrigine().getZ();
		assertEquals("Origine_x de sph1 mauvaise", x, 0.0, TestSphere.EPSILON);
		assertEquals("Origine_y de sph1 mauvaise", y, 0.0, TestSphere.EPSILON);
		assertEquals("Origine_z de sph1 mauvaise", z, 0.0, TestSphere.EPSILON);
		x = sph2.getOrigine().getX();
		y = sph2.getOrigine().getY();
		z = sph2.getOrigine().getZ();
		assertEquals("Origine_x de sph2 mauvaise", x, 1.0, TestSphere.EPSILON);
		assertEquals("Origine_y de sph2 mauvaise", y, 2.0, TestSphere.EPSILON);
		assertEquals("Origine_z de sph2 mauvaise", z, 3.0, TestSphere.EPSILON);
		
		double rayon = sph1.getRayon();
		assertEquals("Rayon de sph1 faux", rayon, 2.0, TestSphere.EPSILON);
		rayon = sph2.getRayon();
		assertEquals("Rayon de sph2 faux", rayon, 3.0, TestSphere.EPSILON);
	}
	
	@Test
	public void testConstructeurNomParDefaut() {
		Sphere sph3 = new Sphere(new Point(0.0, 0.0, 0.0), 1.0);
		Sphere sph4 = new Sphere(new Point(0.0, 0.0, 0.0), 1.0);
		
		assertEquals("Nom de sph3 faux", sph3.getNom(), "Sphere1");
		assertEquals("Nom de sph4 faux", sph4.getNom(), "Sphere2");
		
		x = sph3.getOrigine().getX();
		y = sph3.getOrigine().getY();
		z = sph3.getOrigine().getZ();
		assertEquals("Origine_x de sph3 mauvaise", x, 0.0, TestSphere.EPSILON);
		assertEquals("Origine_y de sph3 mauvaise", y, 0.0, TestSphere.EPSILON);
		assertEquals("Origine_z de sph3 mauvaise", z, 0.0, TestSphere.EPSILON);
		
		double rayon = sph3.getRayon();
		assertEquals("Rayon de sph3 faux", rayon, 1.0, TestSphere.EPSILON);
	}
	
	@Test
	public void testTranslater() {
		sph2.translater(1.0, 0.0, 2.0);
		x = sph2.getOrigine().getX();
		y = sph2.getOrigine().getY();
		z = sph2.getOrigine().getZ();
		assertEquals("Translation selon x mauvaise", x, 2.0, TestSphere.EPSILON);
		assertEquals("Translation selon y mauvaise", y, 2.0, TestSphere.EPSILON);
		assertEquals("Translation selon z mauvaise", z, 5.0, TestSphere.EPSILON);
	}
	
	@Test
	public void testGetNormal() {
		Point impact = new Point(1.0, 2.0, 6.0);
		Vecteur normal = sph2.getNormal(impact, null);
		// vérifie que le vecteur est vertical:
		assertEquals("Coordonnée selon x de la normal est fausse", normal.getX(), 0.0, TestSphere.EPSILON);
		assertEquals("Coordonnée selon y de la normal est fausse", normal.getY(), 0.0, TestSphere.EPSILON);
		assertTrue("Coordonnée selon z de la normal est fausse", normal.getZ() > 0.0);
	}

	@Test
	public void testEstTraversePar() {
		Vecteur dir = new Vecteur(0.0, -1.0, 0.0);
		Point origine = new Point(1.0, 8.0, 3.0);
		Rayon lum = new Rayon(dir, origine);
		
		Point impact = sph2.estTraversePar(lum);
		// l'impact devrait être en (1.0, 5.0, 3.0):
		x = impact.getX();
		y = impact.getY();
		z = impact.getZ();
		assertEquals("Coord. selon x mauvaise", x, 1.0, TestSphere.EPSILON);
		assertEquals("Coord. selon y mauvaise", y, 5.0, TestSphere.EPSILON);
		assertEquals("Coord. selon z mauvaise", z, 3.0, TestSphere.EPSILON);
	}
	
	@Test
	public void testDirectionReflexion() {
		Vecteur dir = new Vecteur(0.0, -1.0, 1.0);
		Point origine = new Point(1.0, 6.0, 2.0);
		Rayon lum = new Rayon(dir, origine);
		
		Point impact = new Point(1.0, 6.0, 3.0);
		
		Vecteur reflexion = sph2.directionReflexion(lum, impact);
		// le vecteur réfléchi devrait être colinéaire à (0, 1, 1):
		assertTrue("Mauvais vecteur réfléchi", reflexion.equals(new Vecteur(0.0, 1.0, 1.0), TestSphere.EPSILON));
	}
	
	@Test
	public void testGetSelfOmbre() {
		Point impact;
		Point origineLum = new Point(1.0, -2.0, 3);
		LumierePonctuelle lum = new LumierePonctuelle(origineLum, Color.WHITE);
		
		// cas où l'objet n'est pas entre l'impact et la lumière (pas d'ombre):
		impact = new Point(1.0, -1.0, 3.0);
		assertTrue("L'objet ne devrait pas faire de l'ombre au point d'impact", !sph2.getSelfOmbre(impact, null, lum));
		
		// cas où l'objet est entre l'impact et la lumière (ombre):
		impact = new Point(1.0, 5.0, 3.0);
		assertTrue("L'objet devrait faire de l'ombre au point d'impact", sph2.getSelfOmbre(impact, null, lum));
	}
}
