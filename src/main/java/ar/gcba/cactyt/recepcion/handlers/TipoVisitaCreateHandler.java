package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.payloads.NewTipoVisitaPayload;

public class TipoVisitaCreateHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
		NewTipoVisitaPayload value = getValue(NewTipoVisitaPayload.class);
		Model model = getModel();
    	Object[] errors = value.validate(model);
    	if (errors.length > 0) {
			return view("tipovisita_create.ftl", value, errors);
		}
		model.tipovisitasCreate(value);
		if (!hasRole("admin")) return redirect("/logout");
		return redirect("/tipovisitas");
    }
}
