package ar.gcba.cactyt.recepcion.payloads;

import java.util.ArrayList;

import ar.gcba.cactyt.common.Validable;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.TipoDocumento;
import lombok.Data;

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