package ar.gcba.cactyt.recepcion.handlers;

import java.util.UUID;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.payloads.EditTipoDocumentoPayload;

public class TipoDocumentoEditHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	EditTipoDocumentoPayload value = getValue(EditTipoDocumentoPayload.class);
    	value.setUuid(uuid);
    	if (!hasRole("admin")) return redirect("/logout");
    	Model model = getModel();
		Object[] errors = value.validate(model);
    	if (errors.length > 0) {
			return view("tipodocumentos_edit.ftl", value, errors);
		}
    	model.updateTipoDocumento(value);
		return redirect("/tipodocumentos");
    }


}
