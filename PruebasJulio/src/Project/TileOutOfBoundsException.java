package Project;

/**
 * Clase que modela el error personalizado cuando la tesela esta fuera de rango
 * @author Andrea Brea Rodriguez
 * @version 2.0 17/06/2021
 */

public class TileOutOfBoundsException extends Exception{
	
	private static final long serialVersionUID = 700L;

	 public TileOutOfBoundsException(String s){
		super(s);	
	}
	
	
}