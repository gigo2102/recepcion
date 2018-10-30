package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.payloads.NewTipoDocumentoPayload;

public class TipoDocumentoCreateHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	if (!hasRole("admin")) return redirect("/logout");
		NewTipoDocumentoPayload value = getValue(NewTipoDocumentoPayload.class);
		Model model = getModel();
    	Object[] errors = value.validate(model);
    	if (errors.length > 0) {
			return view("tipodocumentos_create.ftl", value, errors);
		}
		model.tipodocumentoCreate(value);
		return redirect("/tipodocumentos");
    }
}
