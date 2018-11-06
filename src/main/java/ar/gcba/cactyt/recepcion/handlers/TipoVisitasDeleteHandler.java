package ar.gcba.cactyt.recepcion.handlers;

import java.util.UUID;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;

public class TipoVisitasDeleteHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	Model model = getModel();
    	try {
    	model.tipovisitasDelete(uuid);
    	if (!hasRole("admin")) return redirect("/logout");
        return redirect("/tipovisitas");
    	} catch (RuntimeException e) {
    		return view("errors.ftl", e.getMessage());    		
    	}
    	}}

