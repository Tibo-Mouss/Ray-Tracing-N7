package utilitaire;

import java.io.*;

import elements3D.*;
import rayTracing.*;

/** Permet d'enregistrer et r�cup�rer des instances de Scene et Objet3D
 * ATTENTION : n�cessite un r�pertoire /saves situ� dans le r�pertoire courant
 *  inspir� de la doc: https://beginwithjava.blogspot.com/2011/04/java-file-save-and-file-load-objects.html
 * @author Edgar
 * 
 */
public class ObjectSaver {
	
	/** Enregistre une scene au format .scene
	 * @param scene : instance enregistr�e
	 * @param nom : enregistr� sous la forme nom.scene (�viter les espaces)
	 */
	public static void saveScene(Scene scene, String nom) {
		try {
			// ENREGISTREMENT :
			// ouverture du fichier :
			FileOutputStream saveFile = new FileOutputStream("saves/" + nom + ".scene");
			// ouverture d'un stream objet vers le fichier :
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			try {
				// enregistrement de la scene:
				save.writeObject(scene);
			} finally {
				// fermeture du fichier : 
				save.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("absence de r�pertoire d'enregistrement");
		} catch (IOException e) {
			System.out.println("erreur d'enregistrement");
		}
	}
	
	/** Ouvre une scene enregistr�e dans un fichier .scene
	 * @param nom : le fichier stock� s'appelle nom.scene
	 * @return instance enregistr�e
	 */
	public static Scene openScene(String nom) {
		Scene scene = null;
		try {
			// LECTURE :
			// ouverture du fichier :
			FileInputStream savefile = new FileInputStream("saves/" + nom + ".scene");
			// ouverture d'un stream objet venant du fichier :
			ObjectInputStream restore = new ObjectInputStream(savefile);
			try {
				// r�cup�ration de l'objet :
				scene = (Scene) restore.readObject();
			} finally {
				// fermeture du fichier :
				restore.close();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("le fichier n'a pas pu �tre ouvert");
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println("assignation de l'objet lu � un type incompatible");
		}
		return scene;
	}
	
	/** Enregistre un Objet3D au format .objet
	 * @param obj : instance enregistr�e
	 * @param nom : enregistr� sous la forme nom.objet (�viter les espaces)
	 */
	public static void saveObjet(Objet3D obj, String nom) {
		try {
			// ENREGISTREMENT :
			// ouverture du fichier :
			FileOutputStream saveFile = new FileOutputStream("saves/" + nom + ".objet");
			// ouverture d'un stream objet vers le fichier :
			ObjectOutputStream save = new ObjectOutputStream(saveFile);
			try {
				// enregistrement de l'objet3D :
				save.writeObject(obj);
			} finally {
				// fermeture du fichier : 
				save.close();
			}
		} catch (FileNotFoundException e) {
			System.out.println("absence de r�pertoire d'enregistrement");
		} catch (IOException e) {
			System.out.println("erreur d'enregistrement");
		}
	}
	
	public static Objet3D openObjet(String nom) {
		Objet3D obj = null;
		try {
			// LECTURE :
			// ouverture du fichier :
			FileInputStream savefile = new FileInputStream("saves/" + nom + ".objet");
			// ouverture d'un stream objet venant du fichier :
			ObjectInputStream restore = new ObjectInputStream(savefile);
			try {
				// r�cup�ration de l'objet :
				obj = (Objet3D) restore.readObject();
			} finally {
				// fermeture du fichier :
				restore.close();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("le fichier n'a pas pu �tre ouvert");
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println("assignation de l'objet lu � un type incompatible");
		}
		return obj;
	}
}
