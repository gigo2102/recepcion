package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Persona;
import ar.gcba.cactyt.recepcion.models.Visita;

public class TelevisorPantallaHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	return view("televisor.ftl", null);
    }
}
