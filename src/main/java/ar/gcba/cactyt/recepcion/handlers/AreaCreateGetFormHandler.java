package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Area;

public class AreaCreateGetFormHandler extends AbstractRequestHandler {
    @Override
	public Answer process() {
    	return view("areas_create.ftl", new Area());
    }
}
