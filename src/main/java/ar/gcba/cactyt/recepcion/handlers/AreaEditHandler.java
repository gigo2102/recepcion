package ar.gcba.cactyt.recepcion.handlers;

import java.util.UUID;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.payloads.EditAreaPayload;

public class AreaEditHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	EditAreaPayload value = getValue(EditAreaPayload.class);
    	value.setUuid(uuid);
		Model model = getModel();
		if (!hasRole("admin")) return redirect("/logout");
		Object[] errors = value.validate(model);
    	if (errors.length > 0) {
			return view("areas_edit.ftl", value, errors);
		}
    	model.updateArea(value);
		return redirect("/areas");
    }


}
