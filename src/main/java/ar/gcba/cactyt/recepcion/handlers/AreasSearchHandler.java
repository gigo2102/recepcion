package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;

public class AreasSearchHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	String nombre = getUrlParams().get("nombre");
    	Model model = getModel();
        return view("areas_listado.ftl", model.areasList(nombre));
    }
}
