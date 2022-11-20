package Project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.*;

import Project.Mosaico.RegionRectangular;

/**
 * Clase principal del proyecto mosaico. Modela todas las funciones anteriores al introducir un fichero introducido por el usuario"
 *@author Andrea Brea Rodriguez
 *@version 2.0 17/06/2021
 */
public class MosaicProject {

	/**
	 * Instancia de la clase Mosaico
	 */
	static Mosaico mosaico = new Mosaico ();
	
	/**
	 * Metodo main de MosaicProject que procesa las instrucciones introducidas como argumento del metodo main.
	 * @param args Argumento del metodo main. Funciona como un array. 
	 * @throws Exception Exceptiones contempladas en los diferentes casos que se pueden dar.
	 */
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		
		Scanner entrada = null;
		String linea = null;
		
		String argumento = args[0].trim();
		//System.out.println("Leer archivo: "+argumento);
		
		
		try { //lee el nombre del fichero que contiene las instrucciones "Instructions.txt"
			entrada = new Scanner(new FileInputStream("Project/"+argumento)); 
		}catch (FileNotFoundException e){		
			
				FileWriter archivo = null; 
				archivo =new FileWriter ("Project/error.txt");
				archivo.write("");
				archivo.write("FileNotFoundException");
				archivo.close();	
				System.out.println("EXCEPTION MOSAICPROJECT-- No existe el fichero: "+argumento); 
				System.exit(-1);

		}
		
		//System.out.println("Instrucciones leidas con exito");
		
try {
		 while (entrada.hasNextLine()) { 
			 linea = entrada.nextLine(); 
			 
			 if(linea.contains("ReadMosaic")) {
				 String EntradaFichero = linea.substring(10).trim(); //Nombre del archivo de texto es Entradafichero
				 System.out.println(EntradaFichero);
				 mosaico = new Mosaico ("Project/"+EntradaFichero);  
				 mosaico.regiones = new ArrayList<RegionRectangular>();
				 
				 try {
					 mosaico.validarRango();
				 }catch(TileOutOfBoundsException e) {
					System.out.println("EXCEPTION MOSAICPROJECT-- Error en rango: TileOutOfBoundsException"); 
					System.exit(0);
				 }

				 //System.out.println("Mosaico leido con exito");
				 
			 }else if(linea.contains("CreateRegion")) {
				 
				 String datos = linea.substring(12).trim();
				 String []datos1 = datos.split(",");
				 String nombre = datos1[0];
				 int r0 = Integer.parseInt(datos1[1]);
				 int c0 = Integer.parseInt(datos1[2]);
				 int h = Integer.parseInt(datos1[3]);
				 int w = Integer.parseInt(datos1[4]);
				
				 try {
				 
					 Mosaico.RegionRectangular region = mosaico.new RegionRectangular(nombre,r0,c0,h,w);
					 mosaico.anhadirRegion(region);					 
				 }catch(Exception e) { 
				 	System.out.println("EXCEPTION MOSAICPROJECT-- Clase mosaico no encontrada");
				 }
				 
			 }else if(linea.contains("ChangeLuminosityMosaic")) {

				 String datos = linea.substring(22).trim();
				 int valor = Integer.parseInt(datos);
				 
				 mosaico.regionName = null;
				 mosaico.row = 0;
				 mosaico.column = 0;
				 
				 mosaico.changeLuminosity(valor);
				 mosaico.CopiarMapasOriginal(); //actualiza la copia
				 
			 }else if(linea.contains("ChangeLuminosityRegion")) {

				 String datos = linea.substring(22).trim();
				 String[] datos1 = datos.split(",");
				 int valor = Integer.parseInt(datos1[0]);
				 String region_name = datos1[1];
				 
				 mosaico.regionName = region_name;
				 mosaico.row = 0;
				 mosaico.column = 0;
				 
				 mosaico.changeLuminosity(valor);
				 mosaico.CopiarMapasOriginal(); // actualiza la copia
				 
			 }else if(linea.contains("ChangeLuminosityTile")) {
				String datos = linea.substring(21).trim(); 
				String[] datos1 = datos.split(",");
				int valor = Integer.parseInt(datos1[0]);
				int fila = Integer.parseInt(datos1[1]);
				int col = Integer.parseInt(datos1[2]);

				mosaico.regionName = null;
				mosaico.row = fila;
				mosaico.column = col;
				
				mosaico.changeLuminosity(valor);
				mosaico.CopiarMapasOriginal(); //actualiza la copia
				
			 }else if(linea.contains("ChangeStatusMosaic")) {
				 
				 
				 String status0 = linea.substring(19).trim(); 
				 int status = Integer.parseInt(status0);
				 
				 mosaico.regionName = null;
				 mosaico.row = 0;
				 mosaico.column = 0;
				 mosaico.estado=status;
				 
				 mosaico.ChangeStatus(status);
 
			 }else if(linea.contains("ChangeStatusRegion")) {
				 
				 String status0 = linea.substring(19).trim(); 
				 String[] datos1 = status0.split(",");
				 int status = Integer.parseInt(datos1[0]);
				 String region_name = datos1[1];
				 
				 mosaico.column = 0;
				 mosaico.row = 0;
				 mosaico.regionName = region_name;
				 mosaico.estado = status;
				 
				 mosaico.ChangeStatus(status);
			
			 }else if(linea.contains("ChangeStatusTile")) {
				 
				 String status0 = linea.substring(16).trim(); 
				 String[] datos1 = status0.split(",");
				 int status = Integer.parseInt(datos1[0]);
				 int fila = Integer.parseInt(datos1[1]);
				 int col = Integer.parseInt(datos1[2]);
				 
				 mosaico.estado = status;
				 mosaico.column = col;
				 mosaico.row = fila;
				 mosaico.regionName = null;
				 
				 mosaico.ChangeStatus(status);
				 
			 }else if(linea.contains("SortRegionsByArea")) {
				 FileWriter archivo = null; //error
				 String ficheroRegiones = linea.substring(17).trim();				 
				 mosaico.ordenarRegionesXAreaAsc();
				 
				 for(int i=0; i<mosaico.regiones.size(); i++) {
					 mosaico.getRegiones().get(i).Area();
				 }
				 try {
						archivo =new FileWriter ("Project/"+ficheroRegiones);
						for(int i=0; i<mosaico.regiones.size(); i++) {
							archivo.write(mosaico.regiones.get(i).toString()+ "\n");
						}
						archivo.close();	
						
					}catch(Exception ex) {
						System.out.println("EXCEPTION MOSAICPROJECT-- Mensaje excepcion: " + ex.getMessage());
					}	
				 
			 }else if(linea.contains("Contar")) {
				 
				 System.out.println("AREAS contadas: ");
				 mosaico.Contar();
				 
				 
			 
			 
			 }else if(linea.contains("SaveMosaic")) {
				 
				 for(Map.Entry<Coordenada, Tesela> entry : Mosaico.mapaTeselas.entrySet()) {
					 
					 if(entry.getValue().getEstado() == 0 || entry.getValue().getEstado() == 2 || entry.getValue().getEstado() == 3) {
						 entry.getValue().apagaFiguras(); 
					 }
					 
				 }
				 String ficheroRegiones = linea.substring(10).trim();
				 mosaico.salvarAFichero("Project/"+ficheroRegiones); 
				 
				 mosaico.CopiarMapasCopia();
			 }
		 }
	}catch(Exception e) {
		System.out.println("EXCEPTION MOSAICPROJECT-- Mensaje excepcion : " + e.getMessage());
	}
	
	}
}