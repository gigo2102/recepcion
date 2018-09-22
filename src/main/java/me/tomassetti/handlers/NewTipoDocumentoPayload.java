package me.tomassetti.handlers;

import java.util.ArrayList;

import lombok.Data;
import me.tomassetti.Validable;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.TipoDocumento;

@Data
public class NewTipoDocumentoPayload extends TipoDocumento implements Validable {
    public Object[] validate(Model model) {
    	ArrayList<String> errors = new ArrayList<String>();
    	if(getnombre() == null || getnombre().isEmpty()) {
    		errors.add("Complete el nombre!");
    	}
    	if(model.tipodocumentosExisteNombre(getnombre())) {
    		errors.add("Ya existe un tipo de documento con el mismo nombre!");
    	}
        return errors.toArray();
    }
}