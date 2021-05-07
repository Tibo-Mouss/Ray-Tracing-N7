package exception;

/** Exception qui indique si se déclenche lorsque la focale rentrée est négative */
public class FocaleNegativeException extends Exception {

	public FocaleNegativeException() {
		super();
	}
	
	public FocaleNegativeException(String message) {
		super(message);
	}
	
}
