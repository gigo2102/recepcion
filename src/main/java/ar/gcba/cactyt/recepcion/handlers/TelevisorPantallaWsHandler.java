package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Visita;

public class TelevisorPantallaWsHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
        Model model = getModel();
    	List<Visita> l = model.visitasList(null, true, null, true);
		return json(l);
    }
}
