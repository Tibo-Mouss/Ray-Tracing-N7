package exception;

/** Se d�clenche quand il yn a une erreur de sauvegarde */
public class SauvegarderFichierException extends Exception {

	public SauvegarderFichierException() {
		super();
	}
	
	public SauvegarderFichierException(String message) {
		super(message);
	}
	

}
