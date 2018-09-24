package ar.gcba.cactyt.recepcion.payloads;

import java.util.ArrayList;

import ar.gcba.cactyt.common.Validable;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import lombok.Data;

@Data
public class NewAreaPayload extends Area implements Validable {
    public Object[] validate(Model model) {
    	ArrayList<String> errors = new ArrayList<String>();
    	if(getNombre() == null || getNombre().isEmpty()) {
    		errors.add("Complete el nombre!");
    	}
    	if(model.areasExisteNombre(getNombre())) {
    		errors.add("Ya existe un area con el mismo nombre!");
    	}
        return errors.toArray();
    }
}