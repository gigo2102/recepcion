package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Persona;
import ar.gcba.cactyt.recepcion.models.TipoDocumento;
import ar.gcba.cactyt.recepcion.payloads.NewPersonaPayload;

public class PersonasCreateHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	NewPersonaPayload value = getValue(NewPersonaPayload.class);
    	Model model = getModel();
		Object[] errors = value.validate(model);
    	if (errors.length > 0) {
        	List<TipoDocumento> tipodocumentosList = model.tipodocumentosList(null);
    		value.setTipoDocumentoList(tipodocumentosList);
			return view("personas_create.ftl", value, errors);
		}
    	
    	Persona persona = new Persona();
    	TipoDocumento tipodocumento = model.tipodocumentoGetById(value.getTipoDocumentoId());

    	persona.setNombre(value.getNombre());
    	persona.setTipoDocumento(tipodocumento);
		persona.setCorreo(value.getCorreo());
		persona.setTelefono(value.getTelefono());
		persona.setApellido(value.getApellido());
		persona.setValorDocumento(value.getValorDocumento());

		model.personasCreate(persona);
		
		return redirect("/personas");
    }
}

