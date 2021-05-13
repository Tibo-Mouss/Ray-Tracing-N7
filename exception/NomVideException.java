package exception;

public class NomVideException extends Exception{
	
	public static boolean estVide(String nom) {
		return nom == null || nom.length() == 0;
	}
	
	public NomVideException() {
		super();
	}
	
	public NomVideException(String message) {
		super(message);
	}

}
