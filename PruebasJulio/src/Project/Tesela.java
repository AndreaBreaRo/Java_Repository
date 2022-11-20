package Project;

/**
 * Clase que modela la tesela contenida en un mosaico
 * @author Andrea Brea Rodriguez
 * @version 2.0 17/06/2021
 */

public class Tesela  implements Luminosity{

	//Atributos
	
		public Color color;
		private Figura figura;
		public String posicion; 
		public int estado;
		public static int luminosityChange = 0; 
		
		public static int wTesela;  
		public static int hTesela;

		/**
		 * Constructor de tesela vacia
		 */
		public Tesela() {
		}
			
		/**
		 * Constructor de la tesela la cual no contiene figura en su interior
		 * @param estado Es el estado que tiene la tesela
		 * @param color Clase color que tiene la tesela
		 */
		public Tesela(int estado ,Color color){
			
			this.color = color;
			this.estado = estado;
			this.posicion = null;
			this.figura = null;
		}
			
		
		/**
		 * Constructor de la tesela que contiene una figura en su interior
		 * @param estado Es el estado de la tesela
		 * @param color El color que es modelado por la clase color de la tesela
		 * @param figura La figura que contiene la tesela
		 * @param posicion String con la posicion que la figura respecto la tesela
		 */
		public Tesela(int estado, Color color, Figura figura, String posicion ){
			
			this.estado = estado;
			this.color = color;
			this.figura = figura;
			this.posicion = posicion;
		}
	
		/**
		 * Metodo que devuelve si la figura contiene o no una tesela en el interior
		 * @return Devuelve un booleano si contiene o no figura
		 */
		public boolean contieneFigura() { 
				
			if(this.figura == null) { 
				return false;	
			}else {
				return true;
			}
		}
		
		/**
		 * Metodo que devuelve la cadena de la tesela con o sin figura
		 */
		public String toString() {
			if(this.contieneFigura()== false) {
				return estado +"{" + color + "}"; 
			}else if(this.figura.getClass().getSimpleName().equals("Circulo")) {
				return estado + "{" + color +",CIR:{" + figura.getColor() + "," + posicion + "," + ((Circulo)figura).getRadio() + "}}";
			}else if(this.figura.getClass().getSimpleName().equals("Rectangulo")){
				return estado + "{" + color + ",REC:{"+ figura.getColor() + "," + posicion + "," + ((Rectangulo)figura).getb() + "," + ((Rectangulo)figura).geth() + "}}";
			}else{
				return estado + "{" + color + ",TRI:{"+ figura.getColor() + "," + posicion + "," + ((Triangulo)figura).getw() + "," + ((Triangulo)figura).geth() + "}}";
			}
		}
		
		/**
		 * Metodo que apaga figuras dependiendo del estado que contengan
		 */
		public void apagaFiguras() {
			
			if(this.estado == 0) {
				this.color.setR(0);
				this.color.setG(0);
				this.color.setB(0);
				if(this.contieneFigura()== true) {
					this.figura.color.setR(0);
					this.figura.color.setG(0);
					this.figura.color.setB(0);
				}
			}else if(this.estado == 2) { 
				if(this.contieneFigura()== true) {
					this.figura.color.setR(0);
					this.figura.color.setG(0);
					this.figura.color.setB(0);
				}
			}else if(this.estado == 3) {
				if(this.contieneFigura()== true) {
					this.color.setR(luminosityChange);
					this.color.setG(luminosityChange);
					this.color.setB(luminosityChange);
				}
				this.color.setR(0);
				this.color.setG(0);
				this.color.setB(0);
			}
		}
	

		//getters y setters
		/**
		 * Metodo que devuelve el color de la tesela
		 * @return Color de la tesela
		 */
		public Color getColor(){
	        return this.color;
	    }
		
		/**
		 * Metodo que devuelve la figura de la tesela
		 * @return Devuelve la figura de la tesela
		 */
		public Figura getFigura(){
			return this.figura;
		}
	    
		/**
		 * Metodo que devuelve la cadena con la posicion de la tesela
		 * @return Devuelve el string con la posicion
		 */
	    public String getPoscion(){
	        return this.posicion;
	    }
		
	    /**
	     * Metodo que devuelve el ancho de la tesela
	     * @return Devuelve ancho de la tesela
	     */
		public int getAncho() {
		 return wTesela;	
		}
		
		/**
		 * Metodo que devuelve el alto de la tesela
		 * @return Devuelve el alto de la tesela
		 */
		public int getAlto() {
		 return hTesela;
		}
		
		/**
		 * Metodo que devuelve el estado de la tesela
		 * @return Devuelve el estado de la tesela
		 */
		public int getEstado() {
			return this.estado;
		}
		
		/**
		 * Metodo que fija el estado de la tesela
		 * @param e estado que tiene la tesela
		 */
		public void setEstado(int e) {
			this.estado = e;
		}

			
}