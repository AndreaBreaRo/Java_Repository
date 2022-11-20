package Project;

/**
 * Clase que modela el rectangulo contenido en la tesela, que es una subclase de Figura
 * @author Andrea Brea Rodriguez
 * @version 2.0 17/06/2021
 */

public class Rectangulo extends Figura {

	//atributos
	
		private int b; // valor porcentual de la altura
		private int h; //valores porcentuales altura
		
		/**
		 * Constructor de la clase rectangulo
		 * @param b base porcentual del rectangulo
		 * @param h altura porcentual del rectangulo
		 * @param color clase color que modela el color del rectangulo contenido en la figura
		 */
		public Rectangulo(int b, int h, Color color){
			
			if(b > 100) this.b = 100;
			else if (b < 0) this.b = 0;
			else if (h > 100) this.h =100;
			else if (h < 0) this.h = 0;
			else {
				this.b = b;
				this.h = h;
				
				super.color = color;
			}
		}
		
		
		/**
		 * Metodo que calcula el area de un rectangulo
		 */
		public double area() {
			return (b*h)/100;  
		}
		
		/**
		 * Metodo que compara si el objeto rectangulo pasado por el metodo es igual al de la clase
		 */
		public boolean esIgualA (Object obj) {
			if((this.b) == (((Rectangulo)obj).b) && (this.h) == (((Rectangulo)obj).h) && this.getColor().esIgualA(((Rectangulo)obj).getColor())){
			return true;	
			}else {
			return false;
			}
		}
		
		/**
		 * Metodo que devuelve el rectangulo en forma de cadena 
		 */
		public String toString() {
			return "Base: " + b + ", Altura: "+ h + " Color: "+ super.getColor();
		}
		
		//getters y setters
		
		/**
		 * Metodo que devuelve un entero con la base del rectangulo
		 * @return base del rectangulo
		 */
		public int getb() {
			return this.b;
		}
		/**
		 * Metodo que devuelve la altura del rectangulo
		 * @return altura del rectangulo
		 */
		public int geth() {
			return this.h;
		}
		/**
		 * Metodo que devuelve el color del rectangulo
		 * @return color del rectangulo
		 */
		public Color getColor() {
			return this.color;
		}
}