package ar.gcba.cactyt.recepcion.payloads;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ar.gcba.cactyt.common.Validable;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Empleados;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.TipoDocumento;
import ar.gcba.cactyt.recepcion.models.TipoVisita;
import lombok.Data;

@Data
public class NewEmpleadoPayload extends Empleados implements Validable {


	public NewEmpleadoPayload(String nombre, String apellido, String cuil, String dni, String correo,
			String telparticular, String celular, String ht) {
		super(nombre, apellido, cuil, dni, correo, telparticular, celular, ht);
		// TODO Auto-generated constructor stub
	}

	public Object[] validate(Model model) {
    	ArrayList<String> errors = new ArrayList<String>();
    	if(getNombre() == null || getNombre().isEmpty()) {
    		errors.add("Complete el nombre!");
    	}
    	
        return errors.toArray();
    }
}