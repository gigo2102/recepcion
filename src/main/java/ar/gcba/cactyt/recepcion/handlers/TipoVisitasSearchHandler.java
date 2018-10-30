package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;

public class TipoVisitasSearchHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    //	if (!hasRole("admin")) return redirect("/logout");
    	Model model = getModel();
    	if (!hasRole("admin")) return redirect("/logout");
        return view("tipovisita_listado.ftl", model.tipovisitaList());
    }
}
