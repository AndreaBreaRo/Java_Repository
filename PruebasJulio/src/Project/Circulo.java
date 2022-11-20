package Project;

/**
 * Clase que modela el circulo contenido en la clase figura
 * @author Andrea Brea Rodriguez
 * @version 2.0 17/06/2021
 */

public class Circulo extends Figura {

	//Atributos
	private int radio; //valor porcentual del radio
	
	//Atributos estaticos
	private static final double PI = Math.PI;
	
	/**
	 * Constructor de  la clase circulo
	 * @param radio relativo a la clase Tesela contenido entre 0 y 100
	 * @param color hace referencia a la clase Color
	 */
	
	public Circulo (int radio, Color color){
		if(radio > 100) this.radio = 100;
		else if (radio < 0) this.radio = 0;
		else this.radio = radio;
		super.color = color;
	}
	
	//Metodos no estaticos o de instancia
	
	/**
	 * Metodo que calcula el area de un circulo relativa a la tesela.
	 * @return tipo double con radio de la circunferencia
	 */
		public double area() { 
			return PI * Math.pow(radio, 2)/100; 
		}
		
	/**
	 * Metodo que devuelve un booleano si el circulo pasado como parametro y el del objeto son iguales
	 * @return un tipo boleano
	 */
		public boolean esIgualA (Object obj) {
			if(this.getRadio() == (((Circulo)obj).getRadio()) && (this.color.esIgualA(((Circulo)obj).getColor()))) {
				return true;
			}else {
				return false;
			}
		}
		
		/**
		 * Metodo que devuelve el radio en forma de cadena
		 * @return un String
		 */
		public String toString() {
			return "Radio: " + radio + ", Color: "+ color;
		}
		
		//getters y setters
		/**
		 * Metodo que devuelve el valor del radio
		 * @return tipo int con valor de radio
		 */
		public int getRadio() {
			return this.radio;
		}
		
		/**
		 * Metodo que devuelve la clase color del circulo
		 * @return clase color
		 */
		public Color getColor() {
			return this.color;
		}
		
	
}