package utilitaire;

import java.io.*;

import elements3D.*;
import rayTracing.*;

/** Permet d'enregistrer et récupérer des instances de Scene et Objet3D
 * ATTENTION : nécessite un répertoire /saves situé dans le répertoire courant
 *  inspiré de la doc: https://beginwithjava.blogspot.com/2011/04/java-file-save-and-file-load-objects.html
 * @author Edgar
 * 
 */
public class ObjectSaver {
	
	/** Enregistre une scene au format .scene
	 * @param scene : instance enregistrée
	 * @param nom : enregistré sous la forme nom.scene (éviter les espaces)
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
			System.out.println("absence de répertoire d'enregistrement");
		} catch (IOException e) {
			System.out.println("erreur d'enregistrement");
		}
	}
	
	/** Ouvre une scene enregistrée dans un fichier .scene
	 * @param nom : le fichier stocké s'appelle nom.scene
	 * @return instance enregistrée
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
				// récupération de l'objet :
				scene = (Scene) restore.readObject();
			} finally {
				// fermeture du fichier :
				restore.close();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("le fichier n'a pas pu être ouvert");
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println("assignation de l'objet lu à un type incompatible");
		}
		return scene;
	}
	
	/** Enregistre un Objet3D au format .objet
	 * @param obj : instance enregistrée
	 * @param nom : enregistré sous la forme nom.objet (éviter les espaces)
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
			System.out.println("absence de répertoire d'enregistrement");
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
				// récupération de l'objet :
				obj = (Objet3D) restore.readObject();
			} finally {
				// fermeture du fichier :
				restore.close();
			}
			
		} catch (FileNotFoundException e) {
			System.out.println("le fichier n'a pas pu être ouvert");
		} catch (IOException e) {
			System.out.println(e);
		} catch (ClassNotFoundException e) {
			System.out.println("assignation de l'objet lu à un type incompatible");
		}
		return obj;
	}
}
