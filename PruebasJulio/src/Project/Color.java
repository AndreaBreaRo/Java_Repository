package Project;

/**
 * Clase que modela los colores de las figuras y teselas
 * @author Andrea Brea Rodriguez
 * @version 2.0 17/06/2021
 */

public class Color{

	// Atributos
		public int r;
		public int g;
		public int b;

		// Atributos estaticos
		private static final int MAX = 255;

/**
 * Constructor de la clase Color
 * @param r Componente rojo
 * @param g Componente verde
 * @param b Componente azul
 * @throws Exception Lanza una excepcion (error no comprobado: RuntimeException) cuando el valor de r g o b es mayor a 255;
 */
		public Color(int r, int g, int b) throws Exception {


			if ((r > MAX) || (g > MAX) || (b > MAX)) {			
				throw new Exception("ERROR EN COLOR: valor de parametro de color mayor a 255");			
			} else {
				this.r = r;
				this.g = g;
				this.b = b;
			}
		}
		
/**
 * Constructor vacio de color
 */
		Color (){}; 
				
/**
 * Metodo estatico que genera el color blanco
 * @return Devuelve una clase Color con atributos estaticos 
 * @throws Exception Lanza excepcion (RunTimeException) cuando el color es mayor a 255 en lectura de mosaico
 */
		// Metodos estaticos o de Clase
		public static final Color BLANCO() throws Exception {
			return new Color(255, 255, 255);
		}
		
/**
 * Metodo que comprueba si el color pasado como parametro es igual al de la clase
 * @param color Clase compuesta por rgb
 * @return Devuelve un tipo boolean e indica si son iguales (true) o diferentes (false)
 */
		public boolean esIgualA(Color color) {
		
			if((this.getR() == color.getR()) && (this.getG() == color.getG()) && (this.getB() == color.getB())) {
				return true;
			}
			else return false;
		}
		
/**
 * Comprueba si la composicion de color en la luminancia es mayor a 255 o menor a 0 y la hace rotativa
 */
		public void valoresRojo() {
			if(this.r >= 256) { 
				this.r = this.r -256;
			}if (this.r < 0) {
				this.r = this.r +256;
			}
		}
		
/**
* Comprueba si la composicion de color en la luminancia es mayor a 255 o menor a 0 y la hace rotativa
*/
		public void valoresVerde() {
			if(this.g >= 256) { 
				this.g = this.g -256;
			}if (this.g < 0) {
				this.g = this.g +256;
			}
		}
/**
* Comprueba si la composicion de color en la luminancia es mayor a 255 o menor a 0 y la hace rotativa
*/		
		public void valoresAzul() {
			if(this.b >= 256) { 
				this.b = this.b -256;
			}if (this.b < 0) {
				this.b = this.b +256;
			}
		}	
/**
 * Metodo para representar el color
 * @return String con componentes de color
 */
		public String toString() {
			return "R" + r + "G" + g + "B" + b;
		}

		//Getters y Setters
/**
 * Metodo que obtiene valor de rojo
 * @return entero con componente rojo
 */
		public int getR() {
			return this.r;
		}
/**
 * Metodo que obtiene valor de verde
 * @return Entero que devuelve componente verde
 */
		public int getG() {
			return this.g;
		}
/**
 * Metodo que obtiene valor de azul
 * @return Entero que devuelve componente azul
 */
		public int getB() {
			return this.b;
		}
		
/**
 * Metodo que establece valor de rojo		
 * @param r Componente rojo
 * @return Cntero con valor de rojo
 */
		public int setR(int r) {
			return this.r = r;
		}
/**
 * Metodo que establece valor de verde	
 * @param g Componente de verde
 * @return Entero con valor de verde
 */
		public int setG(int g) {
			return this.g = g;
		}
/**
* Metodo que establece valor de azul	
* @param b Componente de azul
* @return Entero con valor de azul
*/
		public int setB(int b) {
			return this.b = b;
		}	
}