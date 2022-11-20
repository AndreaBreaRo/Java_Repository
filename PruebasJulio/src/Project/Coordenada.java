package Project;

/**
 * Clase que modela la coordenada que compone las regiones y mosaicos
 * @author Andrea Brea Rodriguez
 * @version 2.0 17/06/2021
 */

public class Coordenada implements Comparable<Coordenada>,Luminosity{

	//ATRIBUTOS
	private int fila;
	private int columna;
	
	/**
	 * Constructor de la clase Coordenada (x,y)
	 * @param fila Fila que pertenece a la coordenada o x
	 * @param columna Columna a la que pertenece la coordenada o y
	 */
	public Coordenada(int fila, int columna) {
		this.fila = fila;
		this.columna = columna;
	}
	
	/**
	 * Metodo que devuelve una cadena con las coordenadas en formato (x,y)
	 */
	public String toString(){
		return "("+fila+","+columna+")";
	}

	/**
	 * Getter que devuelve el valor de fila
	 * @return entero con el valor de fila o x
	 */
	
	public int getFila() {
		return this.fila;
	}
	
	/**
	 * Getter que devuelve el valor de columna
	 * @return entero con el valor de columna o y
	 */
	public int getColumna() {
		return this.columna;
	}
	
	/**
	 * Metodo compareTo que devuelve un entero igual a 0 si es igual,  menor que 0 si mas pequenho  mayor que 0 mas grande.
	 * @return entero el cual devuelve un entero igual a 0 si es igual, menor que 0 si mas pequenho mayor que 0 mas grande.
	 */
	
	public int compareTo(Coordenada o) {
		if(this.getFila() != o.getFila()) {
			if(this.getFila() > o.getFila()) {
				return 1;
			}else {
				return -1;
			}
		}else {
			if(this.getColumna() > o.getColumna()) {
				return 1;
			}else if (this.getColumna() < o.getColumna()) {
				return -1;
			}else {
				return 0;
			}
		}
	}	
	
}