package Project;

import java.io.*;
import java.util.*;

/**
 * Clase que modela la clase mosaico
 * @author Andrea Brea Rodriguez
 * @version 2.0 17/06/2021
 */

public class Mosaico  implements Luminosity{

		// Atributos
	
		 public static TreeMap<Coordenada, Tesela> mapaTeselas = new TreeMap<Coordenada, Tesela>() ; //new OrdenarCoordenadas()
		 public static TreeMap<Coordenada, Tesela> copiaMapaTeselas = new TreeMap<Coordenada, Tesela>(); //Copia de seguridad
		 public ArrayList <RegionRectangular> regiones = new ArrayList <RegionRectangular>();

		//Atributos Estaticos
		
		static int filaMosaico;
		static int colMosaico;
		static int hTesela;
		static int wTesela;

		
		FileWriter archivo = null; //error
        String linea = null;
		Scanner entrada = null;
		
		//luminosidad y estado
		public int row = 0;
		public int column = 0;
		String regionName = null;
		int estado;
		
		//constructores

		
		/**
		 * Constructor vacio de mosaico
		 */
		public Mosaico(){ 	};
		
		/**
		 * Lee fichero que contiene mosaico 
		 * @param fichero archivo de texto que contiene las teselas
		 * @throws Exception llama excepcion cuando el fichero de lectura no se encuentra
		 */
		Mosaico (String fichero) throws Exception{		
			
			try {
				entrada = new Scanner(new FileInputStream(fichero));
			}catch (FileNotFoundException e){				
				
				try {
					archivo =new FileWriter ("Project/Error.txt");
					archivo.write("");
					archivo.write("FileNotFoundException\n");
					archivo.close();	
				
			}catch(Exception ex) {
				System.out.println("Mensaje de la excepcion del error: " + ex.getMessage());
				
			}
				System.out.println("-- No existe el fichero: TileMosaic.txt");
				System.exit(-1);
			}
			
	        while (entrada.hasNextLine()) { 
	        	
				linea = entrada.nextLine();
				
				if(linea.indexOf("*")==-1 && linea.indexOf("(")==-1) {
										
					String dimensionMosaico = linea.substring(0, linea.indexOf(",")).trim();
					String dimensionTesela = linea.substring(linea.indexOf(",")+1).trim();
					
					String[] fila_col_Mosaicos = dimensionMosaico.split("x");
					filaMosaico = Integer.parseInt(fila_col_Mosaicos[0]);
					colMosaico = Integer.parseInt(fila_col_Mosaicos[1]);
					
					String[] fila_col_Teselas = dimensionTesela.split("x");
					hTesela = Integer.parseInt(fila_col_Teselas[0]);
					wTesela = Integer.parseInt(fila_col_Teselas[1]);	
					
					inicializar();
					
				}else if((linea.indexOf("*") ==- 1)){ 
				
					String pos = linea.substring(linea.indexOf("(")+1, linea.indexOf(")")).trim();
					String []posicion = pos.split(",");
					int fila = Integer.parseInt(posicion[0])-1;
					int columna = Integer.parseInt(posicion[1])-1;
						
					String est = linea.substring(linea.indexOf(":")+1, linea.indexOf("{")).trim();
					int Estado = Integer.parseInt(est);
					String info = linea.substring(linea.indexOf("{")+1).trim();
					
					if((info.indexOf("REC")==-1) && (info.indexOf("CIR")==-1 && (info.indexOf("TRI")==-1))){ //indexOf devuelve -1 si no aparece
						//solo existe color 
						String color = info.substring(0, info.indexOf("}")).trim();

						int r = Integer.parseInt(color.substring(color.indexOf("R")+1, color.indexOf("G")));
	                    int g = Integer.parseInt(color.substring(color.indexOf("G")+1, color.indexOf("B")));
	                    int b = Integer.parseInt(color.substring(color.indexOf("B")+1));
	                    
	                    mapaTeselas.put(new Coordenada(fila+1,columna+1), new Tesela(Estado,new Color(r, g, b)));
	                    copiaMapaTeselas.put(new Coordenada(fila+1,columna+1), new Tesela(Estado,new Color(r, g, b)));
					
					}else if((info.indexOf("REC")!= 0) && (info.indexOf("CIR")==-1) && (info.indexOf("TRI")==-1)) { //REC

						String color = info.substring(0, info.indexOf(",")).trim();
						int r = Integer.parseInt(color.substring(color.indexOf("R")+1, color.indexOf("G")));
	                    int g = Integer.parseInt(color.substring(color.indexOf("G")+1, color.indexOf("B")));
	                    int b = Integer.parseInt(color.substring(color.indexOf("B")+1));               
	                    
	                    String Rectangulo = info.substring(info.indexOf("{")+1,info.indexOf("}")).trim();
	                    
	                    String []rec = Rectangulo.split(",");
	                    String colorRec = rec[0].trim();
	                    String posRec = rec[1].trim();
	                    String baseRec = rec[2].trim();
	                    String altRec = rec[3].trim();
	                    
	                    int recR = Integer.parseInt(colorRec.substring(colorRec.indexOf("R")+1, colorRec.indexOf("G")));
	                    int recG = Integer.parseInt(colorRec.substring(colorRec.indexOf("G")+1, colorRec.indexOf("B")));
	                    int recB = Integer.parseInt(colorRec.substring(colorRec.indexOf("B")+1));
	                    int base = Integer.parseInt(baseRec);
	                    int altura = Integer.parseInt(altRec);
	                    
	                    mapaTeselas.put(new Coordenada(fila+1, columna+1), new Tesela(Estado, new Color(r,g,b), new Rectangulo(base, altura, new Color(recR,recG,recB)),posRec));
	                    copiaMapaTeselas.put(new Coordenada(fila+1, columna+1), new Tesela(Estado, new Color(r,g,b), new Rectangulo(base, altura, new Color(recR,recG,recB)),posRec));
						
					}else if((info.indexOf("TRI")!= 0) && (info.indexOf("CIR")==-1)){ //TRI
						
						String color = info.substring(0, info.indexOf(",")).trim();
						int r = Integer.parseInt(color.substring(color.indexOf("R")+1, color.indexOf("G")));
	                    int g = Integer.parseInt(color.substring(color.indexOf("G")+1, color.indexOf("B")));
	                    int b = Integer.parseInt(color.substring(color.indexOf("B")+1));
	                    
	                    String Triangulo = info.substring(info.indexOf("{")+1,info.indexOf("}")).trim();
	                    String []rec = Triangulo.split(",");
	                    String colorTri = rec[0].trim();
	                    String posTri = rec[1].trim();
	                    String baseTri = rec[2].trim();
	                    String altTri = rec[3].trim();
	                    
	                    int triR = Integer.parseInt(colorTri.substring(colorTri.indexOf("R")+1, colorTri.indexOf("G")));
	                    int triG = Integer.parseInt(colorTri.substring(colorTri.indexOf("G")+1, colorTri.indexOf("B")));
	                    int triB = Integer.parseInt(colorTri.substring(colorTri.indexOf("B")+1));
	                    int base = Integer.parseInt(baseTri);
	                    int altura = Integer.parseInt(altTri);
	                    
	                    mapaTeselas.put(new Coordenada(fila+1, columna+1), new Tesela(Estado, new Color(r,g,b), new Triangulo(base, altura, new Color(triR,triG,triB)),posTri));
	                    copiaMapaTeselas.put(new Coordenada(fila+1, columna+1), new Tesela(Estado, new Color(r,g,b), new Triangulo(base, altura, new Color(triR,triG,triB)),posTri));
						
					}else { //CIR
						
						String color = info.substring(0, info.indexOf(",")).trim();
						
						int r = Integer.parseInt(color.substring(color.indexOf("R")+1, color.indexOf("G")));
	                    int g = Integer.parseInt(color.substring(color.indexOf("G")+1, color.indexOf("B")));
	                    int b = Integer.parseInt(color.substring(color.indexOf("B")+1));
	                    
	                    String circulo = info.substring(info.indexOf("{")+1,info.indexOf("}")).trim();
	                    	                    
	                    String []cir = circulo.split(",");
	                    String colorCir = cir[0].trim();
	                    String posCir = cir[1].trim();
	                    String radCir = cir[2].trim();	                    
	                    
	                    int cirR = Integer.parseInt(colorCir.substring(colorCir.indexOf("R")+1, colorCir.indexOf("G")));
	                    int cirG = Integer.parseInt(colorCir.substring(colorCir.indexOf("G")+1, colorCir.indexOf("B")));
	                    int cirB = Integer.parseInt(colorCir.substring(colorCir.indexOf("B")+1));	                    
	                    int radioC = Integer.parseInt(radCir);	
	                    
	                    mapaTeselas.put(new Coordenada(fila+1, columna+1), new Tesela(Estado,new Color(r,g,b), new Circulo(radioC, new Color(cirR,cirG,cirB)), posCir));
	                    copiaMapaTeselas.put(new Coordenada(fila+1, columna+1), new Tesela(Estado,new Color(r,g,b), new Circulo(radioC, new Color(cirR,cirG,cirB)), posCir));
	                    
					}	
				}else {
					continue;
				}
	        }
		}
		
		/**
		 * Metodo que comprueba que la tesela esta dentro de su rango
		 * @throws TileOutOfBoundsException Llama a la excepcion personalizada de fuera de rango de la tesela
		 * @throws IOException Error del API de java cuando no se encuentra archivo
		 */
		public void validarRango() throws TileOutOfBoundsException, IOException {
				
			for(Map.Entry<Coordenada, Tesela> entry : Mosaico.mapaTeselas.entrySet()) {	
			
				if(entry.getKey().getFila() < 1 || entry.getKey().getColumna() < 1 || entry.getKey().getFila() > filaMosaico || entry.getKey().getColumna() > colMosaico) {
					archivo =new FileWriter ("Project/error.txt"); 
					archivo.write("");
					archivo.write("TileOutOfBoundsException\n");
					archivo.close();
					throw new TileOutOfBoundsException("ERROR MOSAICO -- ValidarRango: TileOutOfBounds");
					}
			}
		}
		
		/**
		 * Metodo que introduce en los espacios en blanco del mosaico una tesela blanca con estado 1
		 * @throws Exception llama excepcion que hereda de color (RunTimeException)
		 */
		public void inicializar() throws Exception {
			for(int i = 0; i<filaMosaico; i++){	
				for(int j = 0;  j<colMosaico; j++){	
					mapaTeselas.put(new Coordenada(i+1, j+1), new Tesela(1,Color.BLANCO()));
					copiaMapaTeselas.put(new Coordenada(i+1, j+1), new Tesela(1,Color.BLANCO()));
						
				}
			}
		}
		
		/**
		 * Metodo que devuelve un String con la composicion de mosaico
		 */
		public String toString () {
			String texto=+filaMosaico+"x" +colMosaico +"," + wTesela +"x" + hTesela + "\n";
			for(Map.Entry<Coordenada,Tesela> tesela: mapaTeselas.entrySet()){
				if(tesela.getValue()==null){
					continue;
				}
				Coordenada coord=tesela.getKey();
				texto+="(" + (coord.getFila()) + "," + (coord.getColumna()) +"):"+ tesela.getValue() + "\n";	
			}
			return texto;	
		}
		
		/**
		 * Metodo que escribe en un .txt 
		 * @param file fichero donde va a ser guardado el mosaico de salida
		 */
		public void salvarAFichero (String file){

			PrintWriter output = null;
			try{
				output = new PrintWriter (new FileOutputStream(file));
			}catch (FileNotFoundException e) {
				System.out.println("ERROR mosaico -- Salvar Fichero");
				System.exit(-1);
			}
			output.print(this);
			output.close();
			
		}
		
		public void CopiarMapasCopia() { //copia aux al original
			mapaTeselas.putAll(copiaMapaTeselas);
		}
		
		public void CopiarMapasOriginal() { // copia el original al aux
			copiaMapaTeselas.putAll(mapaTeselas);
		}
		
		//FUNCIONES PARA REGIONES
		
		/**
		 * Anhade una nueva region al arrayList regiones
		 * @param r corresponde a la region nueva a anhadir
		 */
		public void anhadirRegion(RegionRectangular r) {
			regiones.add(r);
		}
		
		/**
		 * Ordena regiones por area y nombre en forma ascendente, necesita de la clase OrdenarRegiones
		 * @throws Exception Contempla la posibilidad de ordenar un array de regiones vacio
		 * @see OrdenarRegiones
		 */
		public void ordenarRegionesXAreaAsc() throws Exception {
			if(regiones.isEmpty()== true) {
				throw new Exception("El array list de region esta vacio");
			}
			Collections.sort(regiones, new OrdenarRegiones());
		}
		
		
		public void Contar() throws Exception {
		
			int c=0;
			for(int i=0; i<regiones.size(); i++) {
				c++;
			}
			System.out.println("REGIONES CONTADAS: ");	
			System.out.println(c);	
			
			
			System.out.println("REGIONES CON AREA MAYOR QUE: ");	
			int a=0;
			System.out.println(regiones.getClass());
			
			for(int i=0; i<regiones.size(); i++) {
				System.out.println(regiones.get(i));
				
				if(regiones.get(i).coordenadas.size() > 6) {
					regiones.get(i);
					a++;
				}
			}
			
			System.out.println("REGIONES CON AREA MAYOR QUE 6: ");
			System.out.println(a);	
			
			
			int y =0;
			for(Map.Entry<Coordenada, Tesela> entry : Mosaico.mapaTeselas.entrySet()) {
				//System.out.println( entry);
				
				if(entry.getValue().color.getR() == 255){
					if(entry.getValue().color.getG() == 255){
						if(entry.getValue().color.getB() == 255){
					}
						y++;
					}
				}
			}
			
			
			System.out.println("En el mosaico hay " + y + " teselas blancas: ");
			
			
			
			int p = 0;
			for(Map.Entry<Coordenada, Tesela> entry : Mosaico.mapaTeselas.entrySet()) {
				
				if(entry.getValue().contieneFigura() == true) {
					
					if(entry.getValue().getFigura().getColor().getR() == 255) {
						if(entry.getValue().getFigura().getColor().getG() == 0) {
							if(entry.getValue().getFigura().getColor().getB() == 0) {
							p++;
							}
						}
					}
				}	
			}
			
			System.out.println("En el mosaico hay " + p + " teselas con figuras rojas ");
			
			
			
			//MOSAICOS QUE CONTIENEN UNA FIGURA::
			
			int r = 0;
			for(Map.Entry<Coordenada, Tesela> entry : Mosaico.mapaTeselas.entrySet()) {
				 if(entry.getValue().contieneFigura() == true) {
					
					 System.out.println(entry.getValue().getFigura().area());
					 
					 if(entry.getValue().getFigura().area() >= 50) {
						 r++;
					 }
					 
				 }
			}
			
			System.out.println("En el mosaico hay " + r + " teselas con area mayor a 50 % ");
			
			
			//Regiones con area mayor que
			
			
			int l=0;
			
			for(int i=0; i<regiones.size(); i++) {
				
				System.out.println("Area Region " + i + ": " +regiones.get(i).Area());
				
				if(regiones.get(i).Area() < 6) {
					//System.out.println("1");
					l++;
				}
			}
			
			System.out.println("Hay " + l + " regiones con area menor que 6");
			
			
			
		}
		
		
		/**
		 * Clase que implementa el comparador de java para ordenar regiones por area y nombre
		 */
		public class OrdenarRegiones implements Comparator<RegionRectangular>{

			@Override
			public int compare(RegionRectangular obj1, RegionRectangular obj2) {
				// TODO Auto-generated method stub
				if(obj1.Area() != obj2.Area()) //0: if (x==y)
					if (obj1.Area() > obj2.Area()) return 1; // 1: if (x > y)
					else return -1; //-1: if (x < y)
				else 
					return obj1.getNombre().compareTo(obj2.getNombre());
			}
			
		}
		
		/**
		 * Metodo para la representacion en forma de cadena de caracteres de una region
		 * @return Cadena de tipo String con la representacion de una region
		 */
		
		public String toStringRegiones(){
			String salida = regiones.get(0).toString() + "\n"; 
			for(int i=1; i<regiones.size(); i++){
				salida = salida + regiones.get(i).toString() + "\n"; 
			}
			return salida;
		}
		
		//Para Luminosidad

		/**
		 * Metodo que modifica el valor de luminosidad de un mosaico entero, una tesela del mosaico o una region del mosaico.
		 * @param value representa el valor de la luminosidad a modificar en un mosaico
		 * @throws Exception Para contemplar la posibilidad de cambiar la luminosidad a una region vacia
		 */
		public void changeLuminosity(int value) throws Exception {
			
			Tesela.luminosityChange= Tesela.luminosityChange+value;
			
			int filaOrigen=0;
			int colOrigen=0;
			int wRegion=0; 
			int hRegion=0;
			
			if(value != 0 && regionName == null && row == 0) { //MOSAICO
				
				//System.out.println("CAMBIO LUMINOSIDAD MOSAICO");
				
				for(Map.Entry<Coordenada, Tesela> entry : Mosaico.mapaTeselas.entrySet()) {
					
					entry.getValue().color.r = entry.getValue().color.r + value;
					entry.getValue().color.valoresRojo();	
					
					entry.getValue().color.g = entry.getValue().color.g + value;
					entry.getValue().color.valoresVerde();
				
					entry.getValue().color.b = entry.getValue().color.b + value;
					entry.getValue().color.valoresAzul();

					if(entry.getValue().contieneFigura() == true) {
					
						entry.getValue().getFigura().color.r = entry.getValue().getFigura().color.r + value;
						entry.getValue().getFigura().color.valoresRojo();
						
						entry.getValue().getFigura().color.g = entry.getValue().getFigura().color.g + value;
						entry.getValue().getFigura().color.valoresVerde();
						
						entry.getValue().getFigura().color.b = entry.getValue().getFigura().color.b + value;
						entry.getValue().getFigura().color.valoresAzul();
					}
				}
			} else if(value != 0 && row != 0 && column != 0) {	//TESELA
				
				//System.out.println("CAMBIO LUMINOSIDAD TESELA");
				
				for(Map.Entry<Coordenada, Tesela> entry : Mosaico.mapaTeselas.entrySet()) {
					 
					if(entry.getKey().getFila() == row) {
						if(entry.getKey().getColumna() == column) {
							
							entry.getValue().color.r = entry.getValue().color.r + value;
							entry.getValue().color.valoresRojo();	
							
							entry.getValue().color.g = entry.getValue().color.g + value;
							entry.getValue().color.valoresVerde();	
							
							entry.getValue().color.b = entry.getValue().color.b + value;
							entry.getValue().color.valoresAzul();	
							
							if(entry.getValue().contieneFigura() == true) {
								
								entry.getValue().getFigura().color.r = entry.getValue().getFigura().color.r + value;
								entry.getValue().getFigura().color.valoresRojo();
								
								entry.getValue().getFigura().color.g = entry.getValue().getFigura().color.g + value;
								entry.getValue().getFigura().color.valoresVerde();
								
								entry.getValue().getFigura().color.b = entry.getValue().getFigura().color.b + value;
								entry.getValue().getFigura().color.valoresAzul();
								
							}
						}
					} 
				}
			}else if(value != 0 && regionName != null){ //REGION
				
				//System.out.println("CAMBIO LUMINOSIDAD REGION");
				
				if(regiones.isEmpty()== true) {
					throw new Exception("EXCEPTION Mosaico -- Region de ChangeLuminosityRegion vacio");
				}
				
				for (int i=0;i<regiones.size();i++) {
					if(regiones.get(i).nombre.equals(regionName)) {
				      filaOrigen = regiones.get(i).origen.getFila();
				      colOrigen = regiones.get(i).origen.getColumna();
				      wRegion = regiones.get(i).w;
				      hRegion = regiones.get(i).h;
				     
					}  
				}
				
				for(Map.Entry<Coordenada, Tesela> entry : Mosaico.mapaTeselas.entrySet()) {	
					 if(entry.getKey().getFila() >= filaOrigen && entry.getKey().getFila() < filaOrigen+wRegion) { 
						 if(entry.getKey().getColumna() >= colOrigen && entry.getKey().getColumna() < colOrigen+hRegion) {
							 	
								entry.getValue().color.r = entry.getValue().color.r + value;
								entry.getValue().color.valoresRojo();	
								
								entry.getValue().color.g = entry.getValue().color.g + value;
								entry.getValue().color.valoresVerde();	
								
								entry.getValue().color.b = entry.getValue().color.b + value;
								entry.getValue().color.valoresAzul();
								
								if(entry.getValue().contieneFigura() == true) {
									
									entry.getValue().getFigura().color.r = entry.getValue().getFigura().color.r + value;
									entry.getValue().getFigura().color.valoresRojo();
								
									entry.getValue().getFigura().color.g = entry.getValue().getFigura().color.g + value;
									entry.getValue().getFigura().color.valoresVerde();
									
									entry.getValue().getFigura().color.b = entry.getValue().getFigura().color.b + value;
									entry.getValue().getFigura().color.valoresAzul();
								
								}
						 }
					 }
				}
			}
		}
	
		/**
		 * Modifica el estado de la tesela, mosaico o region 
		 * @param Estado es el valor de estado de la Tesela
		 * @throws Exception hereda la clase excepcion de color y contempla la posibilidad de cambiar el estado de una region vacia
		 */
		public void ChangeStatus(int Estado) throws Exception {

			int filaOrigen =0; 
			int colOrigen=0;
			int wRegion=0;
			int hRegion=0;

			if (estado < 0 || estado > 3){
				throw new Exception("EXCEPTION MOSAICO -- ChangeStatus: El valor de Estado no esta comprendido entre 0 y 3");	
			}

			if(regionName == null && row == 0) { //MOSAICO
	
				//System.out.println("CAMBIO ESTADO MOSAICO");
				
				for(Map.Entry<Coordenada, Tesela> entry : Mosaico.mapaTeselas.entrySet()) {
						entry.getValue().setEstado(estado);
				}

			}else if(row != 0) { //TESELA

				//System.out.println("CAMBIO ESTADO TESELA");
				
				for(Map.Entry<Coordenada, Tesela> entry : Mosaico.mapaTeselas.entrySet()) {
					
					if(entry.getKey().getFila() == row) {
						if(entry.getKey().getColumna() == column) {
							entry.getValue().setEstado(estado);							
						}
					}
				}
			}else if(regionName != null && row == 0) { //REGION

				//System.out.println("CAMBIO ESTADO REGION");
				
				if(regiones.isEmpty()== true) {
					throw new Exception("EXCEPTION Mosaico -- Region de ChangeEstatusRegion vacio");
				}
				
				for (int i=0;i<regiones.size();i++) {
					
					if(regiones.get(i).nombre.equals(regionName)) {
						filaOrigen = regiones.get(i).origen.getFila();
						colOrigen = regiones.get(i).origen.getColumna();
						wRegion = regiones.get(i).w;
						hRegion = regiones.get(i).h;
					}  
				}
				for(Map.Entry<Coordenada, Tesela> entry : Mosaico.mapaTeselas.entrySet()) {	
					 if(entry.getKey().getFila() >= filaOrigen && entry.getKey().getFila() < filaOrigen+wRegion) {
						 if(entry.getKey().getColumna() >= colOrigen && entry.getKey().getColumna() < colOrigen+hRegion) {
							 entry.getValue().setEstado(estado); 
						 }
					 }
				}
			}
		} 
				
		//getters y setters

		
		/**
		 * Metodo que devuelve el listado de teselas y coordenadas 
		 * @return mapa con las coordenadas y teselas
		 */
		public TreeMap<Coordenada, Tesela> getteselas() {
			return mapaTeselas;
		}
		
		/**
		 * Metodo que devuelve la lista con las regiones del mosaico
		 * @return regiones de mosaico
		 */
		public ArrayList <RegionRectangular> getRegiones(){
			return regiones;
		}
		
		/**
		 * Metodo que quecorre el nombre de las regiones y las compara.
		 * @param nombre de la region rectangular, region
		 * @return devuelve la posicion cuando las posiciones son iguales
		 */
		public RegionRectangular getRegion(String nombre){
			int cont = 0;
			for(int i = 0; i < regiones.size() - 1; i++){
				if(regiones.get(i).getNombre().equals(nombre)){
					break;
				}	
				cont++;
			}
				return regiones.get(cont); 
		}
		
		/**
		 * Metodo que devuelve el numero de filas de un mosaico
		 * @return fila del mosaico
		 */
		static int getFilas(){
			return filaMosaico;
		}
		
		/**
		 * Metodo que devuelve el numero de columnas de un metodo
		 * @return columna del mosaico
		 */
		static int getColumnas(){
			return colMosaico;
		}
		
		/**
		 * Metodo que devuelve la altura de la tesela
		 * @return alto de la tesela
		 */
		static int getHtesela() {
			return hTesela;
		}
		
		/**
		 * Metodo de devuelve el ancho de la tesela
		 * @return ancho de la tesela
		 */
		static int getWtesela() {
			return wTesela;
		}	

		
		/**
		 * Clase interna que modela las regiones del mosaico
		 * @author Andrea Brea Rodriguez
		 *
		 */
				public class RegionRectangular {
					
					
					//Atributos
					private String nombre;
					private Coordenada origen;
					private int w;
					private int h;
					private ArrayList <Coordenada> coordenadas;

					/**
					 * Constructor de la region vacio
					 */
					RegionRectangular(){
						this.nombre = null;
					}
					
					/**
					 * Constructor de la region rectangular 
					 * @param nombre nombre que se le asigna a la region
					 * @param f0 fila donde comienza la region
					 * @param c0 columna donde comienza la region
					 * @param w ancho de la region
					 * @param h alto de la region
					 * @throws TileOutOfBoundsException hereda la excepcion si esta fuera de rango
					 */
					RegionRectangular (String nombre, int f0, int c0, int w, int h) throws TileOutOfBoundsException{
						coordenadas = new ArrayList <Coordenada>();
						this.nombre = nombre;
						
						if((f0 < mapaTeselas.firstKey().getFila()) || (c0 < mapaTeselas.firstKey().getColumna())){
							 throw new TileOutOfBoundsException("Numeros fuera del intervalo");							
						}else {							
							this.origen = new Coordenada(f0, c0);
								if( (f0 + w - 1) > mapaTeselas.lastKey().getColumna()){ 
									this.w = mapaTeselas.lastKey().getColumna() + 1 - f0;
								}else{
									this.w = w;
								}
								if( (c0 + h - 1) > mapaTeselas.lastKey().getFila()){ 
									this.h =  mapaTeselas.lastKey().getFila() +1 - c0; 
								}else{
									this.h = h;
								}
							for(int i = 0; i < this.w; i++){
								for(int j = 0; j < this.h; j++){
								coordenadas.add(new Coordenada(f0+i, c0+j));	
								}
							}
						}
					}
					
					/**
					 *Metodo que modela la cadena con las coordenadas
					 */
					public String toString(){
						return nombre + ":" + origen + "," + w + "-" + h+":"+ coordenadas; 
					}

					/**
					 * Metodo que modela el area de una region 
					 * @return devuleve el area
					 */
					public int Area(){
					 	return coordenadas.size();
					 }
					
					/**
					 * Metodo que devuelve las coordenadas
					 * @return devuelve las coordenadas
					 */
					public Collection <Coordenada> getCoordenadas(){
						return coordenadas;
					}
					
					/**
					 * Metodo que devuelve el nombre de una region
					 * @return  String con el nombre de una region
					 */
					public String getNombre(){
						return this.nombre;
					}
				
					/**
					 * Metodo que devuelve el ancho (w) de la region
					 * @return Devuelve entero con ancho de la region
					 */
					public int getW() {
						return this.w;
					}
					
					/**
					 * Metodo que devuelve el alto (h) de la region
					 * @return Devuelve entero con alto de la region
					 */
					public int getH() {
						return this.h;
					}	
				}
}			