package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.TipoVisita;

public class TipoVisitaCreateGetFormHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	return view("tipovisita_create.ftl", new TipoVisita());
    }
}
