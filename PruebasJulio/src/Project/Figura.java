package Project;

/**
 * Clase abstracta que modela las clases Circulo y Rectangulo
 * @author Andrea Brea Rodriguez
 * @version 2.0 17/06/2021
 */
public abstract class Figura{

	public Color color;
	protected int area;
	
	/**
	 * Metodo de figura vacio visible en esta clase y sus subclases
	 */
	protected Figura(){
	}

	/**
	 * Devuelve los valores de los atributos de la clase color pertenecientes a la figura
	 * @return Devuelve la clase color perteneciente a figura
	 */
	public Color getColor(){
		return this.color;
	}

	/**
	 *Metodo para area 
	 * @return double que contiene area
	 */
	public abstract double area();

	/**
	 * Metodo que devuelve la configuracion de color en forma de String
	 */
	public String toString () {
		return "Color: " + color;
	}

	public abstract boolean esIgualA(Object obj);
	
}