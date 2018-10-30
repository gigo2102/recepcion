package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Persona;
import ar.gcba.cactyt.recepcion.models.Visita;

public class VisitasSearchKendoHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	if (isShouldReturnHtml()) {
        	return view("visitas_listado_kendo.ftl", null);
        } else {
            Model model = getModel();
    		//para los que usan como webservice por ejemplo para autocompletes
        	String searchTerm = getUrlParams().get("term");
        	List<Visita> l = model.visitasList(searchTerm, null, null);
    		return json(l);
        }
    }
}
