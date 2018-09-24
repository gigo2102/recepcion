package ar.gcba.cactyt.recepcion.handlers;

import java.util.UUID;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Persona;
import ar.gcba.cactyt.recepcion.models.TipoDocumento;
import ar.gcba.cactyt.recepcion.payloads.EditPersonaPayload;

public class PersonaEditHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	EditPersonaPayload value = getValue(EditPersonaPayload.class);
    	value.setUuid(uuid);
    	Model model = getModel();
		Object[] errors = value.validate(model);
    	if (errors.length > 0) {
			return view("personas_edit.ftl", value, errors);
		}
    	
    	Persona persona = model.personasGetById(uuid);
    	persona.setCorreo(value.getCorreo());
    	persona.setApellido(value.getApellido());
    	persona.setNombre(value.getNombre());
    	persona.setValorDocumento(value.getValorDocumento());
    	persona.setTelefono(value.getTelefono());
    	TipoDocumento tipodocumento = model.tipodocumentosGetById(value.getTipoDocumentoId());
    	persona.setTipoDocumento(tipodocumento);
    	model.updatePersona(persona);
    	
		return redirect("/personas"); 
    }


}
