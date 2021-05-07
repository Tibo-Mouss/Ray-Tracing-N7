package rayTracing;
import exception .*;
import utilitaire .*;
import java.awt.Color;

//Ces imports servent a sauvegarder l'image en tant que fichier
import java.io.File;
import java.io.IOException;
import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;

/** Camera decrit une retine et un ecran. Cette classe met en place l'image dans 
 * l'environment 3D pour que le programme de ray tracing puisse acceder aux positions (3D) 
 * des pixels de l'ecran.
 * @author Tibo, sponsorise par les commentaires de Krystof
 */

public class Camera {
	
	/** Centre de la camera */
	private Point centreCamera;
	
	/** Vecteur allant du centre de la camera vers l'ecran */
	private Vecteur vecteurCameraEcran; 
	
	/** Dimensions de l'ecran en pixel */
	private int pixelHauteur, pixelLongueur;
	
	/** Vecteur indiquant par ou c'est le haut pour l'ecran
	 * Son module indique la taille de l'ecran 
	 * (son module = distance(centreEcran <-> bordHautEcran) )
	 * */
	private Vecteur VHaut;
	
	/** Image composee d'un ensemble de pixels*/
	private Pixel[][] image;
	
	/** Centre de l'ecran */
	private Point centreEcran;
	
	/** Vecteurs horizontal/vertical (sur le plan de l'ecran) 
	 * qui sera utile pour passer de pixel en pixel 
	 * Par defaut le vertical va vers le haut et l'horizontal va vers la droite*/
	private Vecteur vecteurItH, vecteurItV; 

	/** ptHG : point en gauche à gauche de l'écran
	 * ptBD : point en bas à droite de l'écran*/
	private Point ptHG, ptBD;  //Points en Haut a Gauche et en bas a droite de l'ecran
	
	/** Creer une camera
	 * @param centreCamera : centre de la Caméra
	 * @param vecteurCameraEcran : vecteur allant du centre de la camera vers l'ecran
	 * @param pixelHauteur : 
	 * @param pixelLongueur
	 * @param VHaut : 
	 * @throws TaillePixelsImageException 
	 */
	public Camera(Point centreCamera, Vecteur vecteurCameraEcran, int pixelHauteur, int pixelLongueur, Vecteur VHaut) throws TaillePixelsImageException {
		this.centreCamera = centreCamera.copie();
		this.vecteurCameraEcran = vecteurCameraEcran.copie();
		if (pixelHauteur <= 0) {
			throw new TaillePixelsImageException("Le nombre de pixel de hauteur de la caméra est négatif ou nul.");
		}
		this.pixelHauteur = pixelHauteur;
		this.pixelLongueur = pixelLongueur;
		this.VHaut = VHaut.copie();
		
		this.image = new Pixel[pixelHauteur][pixelLongueur];
		
		
		//La creation des autres elements se fait dans une methode a part, puisque si on change
		// un parametre tel que la distance focale, il va falloir re executer le code suivant.
		initialiserImage();
	}
	
	//----------------------------------------------------------------------------------------------
	// Methodes get... et d'affichage
	
	/** Obtenir le centre de la camera.
	 * @return point/centre de la camera
	 */
	public Point getCentre() {
		return this.centreCamera;
	}
	
	/** Obtenir la hauteur de l'ecran
	 * @return valeur de la hauteur de l'ecran
	 */
	public int getPixelHauteur() {
		return this.pixelHauteur;
	}
	
	/** Obtenir la longueur de l'ecran
	 * @return valeur de la longueur de l'ecran
	 */
	public int getPixelLongueur() {
		return this.pixelLongueur;
	}
	
	/** Obtenir la focale de la camera
	 * @return valeur de la focale de la camera
	 */
	public double getFocale() {
		return this.vecteurCameraEcran.module();
	}
	
	/** Obtenir l'image
	 * @return tableau à double entrée de pixels correspondant à l'image
	 */
	public Pixel[][] getImage() {
		return this.image;
	}
	
	/** Obtenir le pixel a la position (ligne,colonne)
	 * @param ligne : abscisse du pixel sur l'image
	 * @param colonne : ordonnee du pixel sur l'image
	 * @return pixel a la position (ligne,colone)
	 * 0 <= ligne < pixelHauteur
	 * 0 <= colonne < pixelLongueur
	 */
	public Pixel getPixel(int ligne, int colonne) {
		//On pourra faire une exception ici
		if (ligne >= this.pixelHauteur | ligne < 0) {
			System.out.println("Attention le numero de ligne : "+ ligne +" est en dehors du cadre de l'image");
		}
		else if (colonne >= this.pixelLongueur | colonne < 0) {
			System.out.println("Attention le numero de colonne : "+ colonne +" est en dehors du cadre de l'image");
		}
		return image[ligne][colonne];
	}
	
	// Ces methodes suivantes ne sont utiles que pour le debug
	
	public void printVecteursIterateurs() {
		System.out.println("Vecteur iterateur horizontal :");
		System.out.println("X:"+vecteurItH.getX() +"   Y:"+vecteurItH.getY()+"   Z:"+vecteurItH.getZ());
		System.out.println("Vecteur iterateur vertical :");
		System.out.println("X:"+vecteurItV.getX() +"   Y:"+vecteurItV.getY()+"   Z:"+vecteurItV.getZ());
	}
	
	public void printCoinsFenetre() {
		System.out.println("Coin en haut a gauche :");
		System.out.println("X:"+ptHG.getX() +"   Y:"+ptHG.getY()+"   Z:"+ptHG.getZ());
		System.out.println("Coin en bas a droite :");
		System.out.println("X:"+ptBD.getX() +"   Y:"+ptBD.getY()+"   Z:"+ptBD.getZ());
	}
	
	public void printCoinsImage() {
		System.out.println("Coin en haut a gauche :");
		this.image[0][0].getCoordonnee().afficher();
		System.out.println("Coin en bas a droite :");
		this.image[this.pixelHauteur-1][this.pixelLongueur-1].getCoordonnee().afficher();;
	}
	
	//----------------------------------------------------------------------------------------------
	// Methodes autres
	
	
	/** Initialiser l'image/ecran sur laquelle on va afficher le resultat du Ray Tracing.
	 */
	private void initialiserImage() {
		
		this.centreEcran = new Point(centreCamera.getX(), centreCamera.getY(), centreCamera.getZ());
		this.centreEcran.translater(vecteurCameraEcran);
		
		//On va creer le vecteur iterateur vertical a partir du vecteur VHaut 
		// Vecteur iterateur vertical = VHaut - produitScalaire(VHaut sur vecteurCameraEcran)/norm(vecteurCE)² *vecteurCE
		this.vecteurItV = new Vecteur(this.VHaut.getX(), this.VHaut.getY(), this.VHaut.getZ());
		this.vecteurItV.retirerProjection(this.vecteurCameraEcran);
		//La on ajuste sa taille. On veut que le vecteurItV soit de module = module(VHaut)/(pixelHauteur/2)
		this.vecteurItV = this.vecteurItV.multiplication( this.VHaut.module() / this.vecteurItV.module() / (this.pixelHauteur/2) );
		
		
		//On prend un vecteur au pif et on applique gram schmidt avec VHaut et vecteurCameraEcran
		this.vecteurItH = new Vecteur(1.5, 2.0, 3.0);
		
		//On enleve la composante sur l'axe vecteurCameraEcran :
		this.vecteurItH.retirerProjection(this.vecteurCameraEcran);
		
		//On enleve la composante sur l'axe Vhaut :
		this.vecteurItH.retirerProjection(this.VHaut);
		
		//La on ajuste sa taille. On veut que le vecteurItH soit de meme module que VecteurItV
		this.vecteurItH = this.vecteurItH.multiplication( this.vecteurItV.module() / this.vecteurItH.module() );
		
		
		//Pour situer les points en haut a gauche et en haut a droite il va falloir leur appliquer :
		// ptHG.translater( pixelHauteur/2 * VecteurItV - pixelLongueur/2 * VecteurItH )
		
		this.ptHG = new Point(this.centreEcran.getX(), this.centreEcran.getY(), this.centreEcran.getZ());
		this.ptHG.translater( this.vecteurItV.multiplication( this.pixelHauteur /2) );
		this.ptHG.translater( this.vecteurItH.multiplication(-this.pixelLongueur/2) );
		
		this.ptBD = new Point(this.centreEcran.getX(), this.centreEcran.getY(), this.centreEcran.getZ());
		this.ptBD.translater( this.vecteurItV.multiplication(-this.pixelHauteur/2 ));
		this.ptBD.translater( this.vecteurItH.multiplication( this.pixelLongueur/2) );
		
		
		//On initialise l'iterateur en haut a gauche, et on va initialiser les pixels de l'ecran en
		// le parcourant en 3D avec les vecteurs iterateur vertical/horizontal
		Point initial = new Point(this.ptHG.getX(), this.ptHG.getY(), this.ptHG.getZ());
		
		// Ici on mets le point bien au centre du premier pixel
		initial.translater(vecteurItH.multiplication(0.5));
		initial.translater(vecteurItV.multiplication(-0.5));
		
		int l,c;		
		for (l = 0; l < this.pixelHauteur; l++) {
			for (c = 0; c < this.pixelLongueur; c++) {
				//On cree le Pixel tout en ajoutant la bonne translation horizontale et verticale
				image[l][c] = new Pixel(initial);
				image[l][c].translater( vecteurItH.multiplication(c) );
				image[l][c].translater( vecteurItV.multiplication(-l) );
			}
		}
		
	}
	
	/** Modifier la valeur du centre de la camera et la reinitialise
	 * @param point : nouvelle point/centre de la camera
	 */
	public void setCoordonnees(Point point) {
		this.centreCamera = point;
		initialiserImage();
	}
	
	/** Modifier la valeur de focale de la camera et la reinitialise
	 * @param focale : nouvelle valeur de la focale de la camera
	 * @throws FocaleNegativeException 
	 */
	public void setFocale(double focale) throws FocaleNegativeException {
		if (focale <= 0) {
			throw new FocaleNegativeException("Focale négative.");
		}
		this.vecteurCameraEcran = this.vecteurCameraEcran.multiplication(focale/this.vecteurCameraEcran.module());
		initialiserImage();
	}
	
	/** Modifier la couleur d'un pixel
	 * @param hauteur : ligne position du pixel
	 * @param longueur : colonne position du pixel
	 * @param couleur : couleur que l'on associe a ce pixel
	 */
	public void setPixel(int hauteur, int longueur, Color couleur) {
		this.image[hauteur][longueur].setCouleur(couleur);
	}
	
	/**
	 * Créer l'image qui sera en sortie de notre programme.
	 * @return BufferedImage : tableau de pixels placé en mémoire que l'on exporte pour 
	 * obtenir l'image finale.
	 */
	public BufferedImage creerImage() {
		BufferedImage img = new BufferedImage(this.pixelLongueur, this.pixelHauteur, BufferedImage.TYPE_INT_ARGB);
		
		//On creee une image sous un autre type
		for(int y = 0; y < this.pixelHauteur; y++){
			for(int x = 0; x < this.pixelLongueur; x++){
				int a = 255; //alpha
		        int r = this.image[y][x].getCouleur().getRed(); //red
		        int g = this.image[y][x].getCouleur().getGreen(); //green
		        int b = this.image[y][x].getCouleur().getBlue();; //blue
		 
		        int p = (a<<24) | (r<<16) | (g<<8) | b; //pixel
		 
		        img.setRGB(x, y, p);
			}
		}
		
		return img;
	}
	
	/**
	 * Sauvegarde l'image en mémoire pour l'obtenir au format png en sortie.
	 * @param img Image en mémoire.
	 * @param nom Nom de l'image.
	 * @throws SauvegarderFichierException 
	 */
	public void sauvegarderImage(BufferedImage img,String nom) throws SauvegarderFichierException {		
		if (nom.isEmpty()) {
			throw new SauvegarderFichierException("Nom invalide, il faut au moins un charactère");
		}
		
		//img.toString();
		File f = null;
		
		//Sauvegarde de l'image creee
	     try{
	       f = new File(".\\"+nom+".png");
	       ImageIO.write(img, "png", f);
	     }catch(IOException e){
	       System.out.println("Error: " + e);
	     }
	}
	
	
}
