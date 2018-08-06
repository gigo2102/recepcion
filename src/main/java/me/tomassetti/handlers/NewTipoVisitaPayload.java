package me.tomassetti.handlers;

import java.util.ArrayList;

import lombok.Data;
import me.tomassetti.Validable;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.TipoVisita;

@Data
public class NewTipoVisitaPayload extends TipoVisita implements Validable {
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