package exception;

/** Exception qui indique que le maximum de rebonds indiqué est négatif. */
public class MaxRebondsNegatifException extends Exception {

	public MaxRebondsNegatifException() {
		super();
	}

	public MaxRebondsNegatifException(String message) {
		super(message);
	}

}
