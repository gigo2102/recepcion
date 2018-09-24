package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.TipoDocumento;
import ar.gcba.cactyt.recepcion.payloads.NewPersonaPayload;

public class PersonaCreateGetFormHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	Model model = getModel();
    	List<TipoDocumento> tipodocumentosList = model.tipodocumentosList(null);
    	NewPersonaPayload viewModel = new NewPersonaPayload(tipodocumentosList);
    	return view("personas_create.ftl", viewModel);
    }
}
