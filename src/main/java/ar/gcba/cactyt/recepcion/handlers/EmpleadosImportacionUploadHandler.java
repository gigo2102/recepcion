package ar.gcba.cactyt.recepcion.handlers;

import javax.servlet.http.Part;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Empleados;
import ar.gcba.cactyt.recepcion.models.Model;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class EmpleadosImportacionUploadHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	try {
	    	Part filePart = getPart("myfile");
	
	        try (InputStream inputStream = filePart.getInputStream()) {
	        	try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
	        		Model model = getModel();
	                List<Empleados> empleados = readEmpleadosFromCSV(reader);
	                for (Empleados empleado : empleados) {                	  
	                	model.empleadosUpdater(empleado);
					}
	                return redirect("/personas");
	        	}
	        }
        } catch (Exception e) {
			e.printStackTrace();
	        return view("personas_empleados_importacion.ftl", "Hubo un error: " + e.getMessage());
		}
    }

	public static List<Empleados> readEmpleadosFromCSV(BufferedReader br) { 
		List<Empleados> empleados = new ArrayList<>();

		try {
	     	String line;
			line = br.readLine();
			if (line != null) {
				//salteamos el encabezado
				line = br.readLine();
			}
	     	while (line != null) { 
	    	   String[] attributes = line.split(";", Integer.MAX_VALUE);
	    	   Empleados empleado = createEmpleado(attributes); 
	    	   empleados.add(empleado);
	    	   line = br.readLine();
	     	}
		} catch (IOException e) {
			e.printStackTrace();
		}
     	return empleados;    
	}

	private static String nullOrEmpty(String data) {
		return data == null ? "" : data;
	}
	
	private static Empleados createEmpleado(String[] metadata) { 
		String apellido = nullOrEmpty(metadata[0]);
		String nombre = nullOrEmpty(metadata[1]); 
		String cuil = nullOrEmpty(metadata[2]);
		String dni = nullOrEmpty(metadata[3]);
		String correo = nullOrEmpty(metadata[4]);
		String telparticular = nullOrEmpty(metadata[5]);
		String celular = nullOrEmpty(metadata[6]);
		String ht = nullOrEmpty(metadata[7]);
		
		return new Empleados (nombre, apellido, cuil, dni, correo, telparticular,celular,ht);
    }
}