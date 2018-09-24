package ar.gcba.cactyt.recepcion.handlers;

import java.util.UUID;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Motivo;
import ar.gcba.cactyt.recepcion.payloads.EditMotivoPayload;

public class MotivoEditHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	EditMotivoPayload value = getValue(EditMotivoPayload.class);
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	value.setUuid(uuid);
    	Model model = getModel();
		Object[] errors = value.validate(model);
    	if (errors.length > 0) {
			return view("motivos_edit.ftl", value, errors);
		}
    	
    	Motivo motivo = model.motivosGetById(uuid);
    	motivo.setNombre(value.getNombre());
    	Area area = model.areasGetById(value.getAreaId());
    	motivo.setArea(area);
    	model.updateMotivo(motivo);
    	
		return redirect("/motivos"); 
    }


}
