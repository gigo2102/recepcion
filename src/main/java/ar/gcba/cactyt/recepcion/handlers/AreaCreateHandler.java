package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.payloads.NewAreaPayload;

public class AreaCreateHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	NewAreaPayload value = getValue(NewAreaPayload.class);
    	Model model = getModel();
		Object[] errors = value.validate(model);
    	if (errors.length > 0) {
			return view("areas_create.ftl", value, errors);
		}
		model.areasCreate(value);
		return redirect("/areas");
    }
}
