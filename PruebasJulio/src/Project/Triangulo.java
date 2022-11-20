package Project;

/**
 * Clase que modela el triangulo contenido en la clase abstracta figura
 * @author Andrea Brea Rodriguez
 * @version 2.0 17/06/2021
 */

public class Triangulo extends Figura {

	private int w; //valor porcentual del ancho
	private int h; // valor porcentual de la altura
	
	public Triangulo(int w, int h, Color color) {
		
		if(w > 100) this.w = 100;
		else if (w < 0) this.w = 0;
		else if (h > 100) this.h =100;
		else if (h < 0) this.h = 0;
		else {
			this.w = w;
			this.h = h;
			
			super.color = color;
		}
	}
	
	/**
	 * Metodo que calcula el area del triangulo
	 */
	
	public double area() {
		return w*h/(2*100);
	}

	/**
	 * Metodo que compara si el objeto triangulo pasado por el argumento es igual al de la clase
	 */
	public boolean esIgualA(Object obj) {
		if((this.w) == (((Triangulo)obj).w) && (this.h) == (((Triangulo)obj).h) && this.getColor().esIgualA(((Triangulo)obj).getColor())){
			return true;	
			}else {
			return false;
			}
	}
	
	
	/**
	 * Metodo que devuelve el triangulo en forma de cadena
	 */
	
	public String toString() {
		return "Base: " + w + ", Altura: "+ h + " Color: "+ super.getColor();
	}
	
	//getters y setters
	
	/**
	 * Metodo que devuelve un entero con la base del triangulo
	 * @return base del triangulo
	 */
	public int getw() {
		return this.w;
	}
	
	/**
	 * Metodo que devuelve la altura del triangulo
	 * @return altura del triangulo
	 */
	public int geth() {
		return this.h;
	}
	
	/**
	 * Metodo que devuelve el color del triangulo
	 * @return color del triangulo
	 */
	public Color getColor() {
		return this.color;
	}

}