package rayTracing;
import elements3D .*;
import exception.*;
import utilitaire .*;
import java.awt.Color;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/** Scene represente l'espace dans lequel l'utilisateur peut deposer un 
 * ou plusieurs objets, des lumieres.
 */

public class Scene implements Serializable {

	private static final long serialVersionUID = -7132613511470735683L;

	/** Ensembles des objets 3D presents dans la scene */
	private List<Objet3D> objets3D;
	
	/** Ensembles des lumieres presentes dans la scene*/
	private List<Lumiere> lumieres; //faire une liste
	
	/** Couleur de fond de la scene TODO A verifier*/
	private Color color;
	
	/** Dimension de la scene*/
	private double dimension;
	
	/** Centre de la scène.*/
	private final Point centre = new Point(0, 0, 0);
	
	/** Creer une scene a partir des dimensions demandees et de valeurs par defaut.
	 * @param dimension
	 */
	public Scene(double dimension) {
		this.objets3D = new ArrayList<Objet3D>();
		this.lumieres = new ArrayList<Lumiere>();
		this.color = new Color(0, 0, 0);
		this.dimension = dimension;
	}
	
	/** Obtenir la liste des objets 3D presents dans la scene.
	 * @return liste contenant l'ensemble des objets 3D de la scene
	 */
	public List<Objet3D> getObjet3D() {
		return this.objets3D;
	}
	
	/** Obtenir les lumieres presentes dans la scene
	 * @return liste contenant l'ensemble des lumieres de la scene TODO a verifier
	 */
	public List<Lumiere> getLumiere() {
		return this.lumieres;
	}
	
	/** Obtenir la couleur de la scene TODO A verifier ?
	 * @return couleur de fond de la scene
	 */
	public Color getColour() {
		return this.color;
	}
	
	/** Obtenir les dimensions de la scene
	 * @return dimension de la scene
	 */
	public double getDimension() {
		return this.dimension;
	}

	/** Ajouter un objet à la scène.
	 * @param objet que l'on ajouter à la scène
	 * @pre objet != null
	 */
	public void addObjet3D(Objet3D objet) throws Objet3DHorsSceneException {
		assert objet != null;
		this.objets3D.add(objet);
		if (objet.estHorsScene(this.dimension, this.centre)) {
			throw new Objet3DHorsSceneException();
		}
	}


	/** Ajouter une lumière à la scène.
	 * @param lumiere que l'on veut ajouter à la scène
	 */
	public void addLumiere(Lumiere lumiere) throws LumiereHorsSceneException {
		assert lumiere != null;
		this.lumieres.add(lumiere);
		if (lumiere.estHorsScene(this.dimension, this.centre)) {
			throw new LumiereHorsSceneException(lumiere);
		}
	}
	
	/** Déplacer la scene dans l'espace
	 * @param dx : translation selon l'axe des x
	 * @param dy : translation selon l'axe des y
	 * @param dz : translation selon l'axe des y
	 */
    public void translationGenerale(double dx, double dy, double dz) {

    }
    
	/** Rotater la scene dans l'espace
	 * @param rx : rotation autour de l'axe des x
	 * @param ry : rotation autour de l'axe des y
	 * @param rz : rotation autour de l'axe des z
	 */
	public void rotationGenerale(double rx, double ry, double rz) {
		
	}
	
	/** Indiquer si le rayon touche le fond de la scene 
	 * TODO ? Est-ce que le type de retour n'est pas un boolean ?
	 */
	public void touchLeFond(double rayon) {
		
	}
	

	/** Modifier la couleur de la scène.
	 * 
	 * @param couleur la nouvelle couleur de la scène
	 */
	public void setCouleur(Color couleur) {
		this.color = couleur;
	}

	/** Modifier les dimensions de la scène.
	 * 
	 * @param dimension la nouvelle dimension de la scène
	 */
	public void setDimension(double dimension) {
		this.dimension = dimension;
	}
}
