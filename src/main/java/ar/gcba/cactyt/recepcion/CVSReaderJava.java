package ar.gcba.cactyt.recepcion;
import java.io.BufferedReader;
import java.io.IOException; 
import java.nio.charset.StandardCharsets; 
import java.nio.file.Files; 
import java.nio.file.Path;
import java.nio.file.Paths; 
import java.util.ArrayList; 
import java.util.List;


import ar.gcba.cactyt.recepcion.models.Empleados;



public class CVSReaderJava {
	
	public static void main(String args[]) { 
		
		List<Empleados> empleados = readEmpleadosFromCSV("empleados.csv"); 
		
		//for (Empleados e : empleados) {

}

	private static List<Empleados> readEmpleadosFromCSV(String fileName) { 
		List<Empleados> empleados = new ArrayList<>();
		Path pathToFile = Paths.get(fileName); 
		
		
		try (BufferedReader br = Files.newBufferedReader(pathToFile,
                StandardCharsets.US_ASCII)) {

			// read the first line from the text file
         	String line = br.readLine();
         	
         // loop until all lines are read

       while (line != null) { 
    	   
    	   String[] attributes = line.split(","); 
    	   Empleados empleado = createEmpleado(attributes); 
    	   empleados.add(empleado);
    	     line = br.readLine();
       }
       
		 } catch (IOException ioe) {
	            ioe.printStackTrace();
	        }

	      return empleados;
	    
       }
	
	private static Empleados createEmpleado(String[] metadata) { 
		String nombre = metadata[0];
		String apellido = metadata[1]; 
		String cuil = metadata[2];
		String dni = metadata[3];
		String correo = metadata[4];
		String telparticular = metadata[5];
		String celular = metadata[6];
		String ht = metadata[7];
		
	     return new Empleados (nombre, apellido, cuil, dni, correo, telparticular,celular,ht);
	    }

}



	
	


