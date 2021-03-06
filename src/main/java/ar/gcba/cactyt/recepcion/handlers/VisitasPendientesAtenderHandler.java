package ar.gcba.cactyt.recepcion.handlers;

import java.util.UUID;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;

public class VisitasPendientesAtenderHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	UUID uuid = UUID.fromString(getUrlParams().get("uuid"));
        Model model = getModel();
        model.visitaAtender(uuid);
        return redirect("/visitas");
    }
}

