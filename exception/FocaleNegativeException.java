package exception;

/** Exception qui indique si se d�clenche lorsque la focale rentr�e est n�gative */
public class FocaleNegativeException extends Exception {

	public FocaleNegativeException() {
		super();
	}
	
	public FocaleNegativeException(String message) {
		super(message);
	}
	
}
