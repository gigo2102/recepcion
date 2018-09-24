package ar.gcba.cactyt.recepcion.handlers;

import java.util.UUID;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.payloads.EditTipoVisitaPayload;

public class TipoVisitaEditHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	EditTipoVisitaPayload value = getValue(EditTipoVisitaPayload.class);
    	value.setUuid(uuid);
    	Model model = getModel();
		Object[] errors = value.validate(model);
    	if (errors.length > 0) {
			return view("tipovisita_edit.ftl", value, errors);
		}
    	model.updateTipoVisita(value);
		return redirect("/tipovisitas");
    }


}
