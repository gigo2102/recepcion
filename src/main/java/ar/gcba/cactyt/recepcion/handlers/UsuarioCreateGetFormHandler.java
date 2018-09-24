package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.payloads.NewUsuarioPayload;

public class UsuarioCreateGetFormHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	Model model = getModel();
    	List<Area> areasList = model.areasList(null);
    	NewUsuarioPayload viewModel = new NewUsuarioPayload(areasList);
    	return view("usuarios_create.ftl", viewModel);
    }
}
