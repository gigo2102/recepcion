package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.payloads.NewMotivoPayload;

public class MotivoCreateGetFormHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	if (!hasRole("admin")) return redirect("/logout");
    	Model model = getModel();
    	List<Area> areasList = model.areasList(null);
    	NewMotivoPayload viewModel = new NewMotivoPayload(areasList);
    	return view("motivos_create.ftl", viewModel);
    }
}
