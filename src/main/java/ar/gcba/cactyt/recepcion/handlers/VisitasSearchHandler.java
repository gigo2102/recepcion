package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Visita;

public class VisitasSearchHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
        Model model = getModel();
        String searchTerm = getUrlParams().get("term");
    	List<Visita> l = model.visitasList(searchTerm);
    	if (isShouldReturnHtml()) {
        	return view("visitas_listado.ftl", l);
        } else {
        	return json(l);
        }
    }
}
