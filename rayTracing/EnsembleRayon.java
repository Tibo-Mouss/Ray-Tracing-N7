package rayTracing;
import elements3D .*;
import utilitaire .*;

public interface EnsembleRayon {
	
	public Rayon getSuivant();
	
	public void ajouterSuivant(Rayon rayon);
	
}
