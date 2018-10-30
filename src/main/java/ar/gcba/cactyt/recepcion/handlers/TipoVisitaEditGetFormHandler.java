package ar.gcba.cactyt.recepcion.handlers;

import java.util.UUID;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.TipoVisita;

public class TipoVisitaEditGetFormHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	Model model = getModel();
    	TipoVisita elTipoVisita = model.tipovisitasGetById(uuid);
    	if (!hasRole("admin")) return redirect("/logout");
		return view("tipovisita_edit.ftl", elTipoVisita);
    }


}

