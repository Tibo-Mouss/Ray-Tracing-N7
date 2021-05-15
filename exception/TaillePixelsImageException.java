package exception;

/** Exception qui indique que le nombre de pixels en hauteur ou largeur n'est pas valide. */
public class TaillePixelsImageException extends Exception {

	public TaillePixelsImageException() {
		super();
	}
	
	public TaillePixelsImageException(String message) {
		super(message);
	}

}

