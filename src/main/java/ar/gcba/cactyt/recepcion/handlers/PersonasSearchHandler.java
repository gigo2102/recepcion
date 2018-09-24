package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Persona;

public class PersonasSearchHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	String searchTerm = getUrlParams().get("term");
        Model model = getModel();
    	List<Persona> l = model.personasList(searchTerm);
    	if (isShouldReturnHtml()) {
        	return view("personas_listado.ftl", l);
        } else {
        	return json(l);
        }
    }
}
