package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import java.util.UUID;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Motivo;
import ar.gcba.cactyt.recepcion.payloads.EditMotivoPayload;

public class MotivoEditGetFormHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	Model model = getModel();
    	Motivo elMotivo = model.motivosGetById(uuid);

    	List<Area> areasList = model.areasList(null);
    	EditMotivoPayload viewModel = new EditMotivoPayload(areasList);
    	viewModel.setUuid(elMotivo.getUuid());
    	viewModel.setNombre(elMotivo.getNombre());
    	viewModel.setAreaId(elMotivo.getArea().getUuid());
    	
		return view("motivos_edit.ftl", viewModel);
    }


}

