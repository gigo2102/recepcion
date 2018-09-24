package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import java.util.UUID;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Persona;
import ar.gcba.cactyt.recepcion.models.TipoDocumento;
import ar.gcba.cactyt.recepcion.payloads.EditPersonaPayload;

public class PersonaEditGetFormHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	Model model = getModel();
    	Persona unPersona = model.personasGetById(uuid);

    	List<TipoDocumento> tipodocumentosList = model.tipodocumentosList(null);
    	EditPersonaPayload viewModel = new EditPersonaPayload(tipodocumentosList);
    	viewModel.setUuid(unPersona.getUuid());
    	viewModel.setNombre(unPersona.getNombre());
    	viewModel.setApellido(unPersona.getApellido());
    	viewModel.setCorreo(unPersona.getCorreo());
    	viewModel.setTelefono(unPersona.getTelefono());
    	viewModel.setValorDocumento(unPersona.getValorDocumento());
    	viewModel.setTipoDocumentoId(unPersona.getTipoDocumento().getUuid());
    	
		return view("personas_edit.ftl", viewModel);
    }


}

