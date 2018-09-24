package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Motivo;
import ar.gcba.cactyt.recepcion.models.TipoVisita;
import ar.gcba.cactyt.recepcion.payloads.NewVisitaPayload;

public class VisitaCreateGetFormHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	Model model = getModel();
    	List<Area> areasList = model.areasList(null);
    	List<Motivo>  motivosList = model.motivosList();
    	List<TipoVisita> tipovisitasList = model.tipovisitaList();
    	NewVisitaPayload viewModel = new NewVisitaPayload(areasList, motivosList, tipovisitasList);    	
    	return view("visita_create.ftl", viewModel);
    }
}