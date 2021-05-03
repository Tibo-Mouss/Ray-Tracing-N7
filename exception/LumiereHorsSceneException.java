package exception;

import rayTracing.Lumiere;

/** Exception qui indique qu'un élément est hors de la scene. */

public class LumiereHorsSceneException extends Exception {

	private Lumiere lumiere;
	
	public LumiereHorsSceneException(Lumiere lumiere) {
		super();
		this.lumiere = lumiere;
	}
	
	public LumiereHorsSceneException(Lumiere lumiere, String message) {
		super(message);
		this.lumiere = lumiere;
	}
	
	public Lumiere getLumiere() {
		return this.lumiere;
	}

}
