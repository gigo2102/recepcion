package ar.gcba.cactyt.recepcion.handlers;

import java.util.UUID;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;

public class AreasDeleteHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	Model model = getModel();
    	try {
    	model.areaDelete(uuid);
    	if (!hasRole("admin")) return redirect("/logout");
        return redirect("/areas");
    	} catch (RuntimeException e) {
        	return view("errors.ftl", e.getMessage());    		
    	}
        
    }}


