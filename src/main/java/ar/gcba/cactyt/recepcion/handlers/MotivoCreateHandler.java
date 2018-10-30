package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Motivo;
import ar.gcba.cactyt.recepcion.payloads.NewMotivoPayload;

public class MotivoCreateHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	if (!hasRole("admin")) return redirect("/logout");
    	NewMotivoPayload value = getValue(NewMotivoPayload.class);
    	Model model = getModel();
    	Object[] errors = value.validate(model);
    	if (errors.length > 0) {
			return view("motivos_create.ftl", value, errors);
		}
    	
    	Motivo motivo = new Motivo();
    	motivo.setNombre(value.getNombre());
    	Area area = model.areasGetById(value.getAreaId());
    	motivo.setArea(area);
		model.motivosCreate(motivo);
		
		return redirect("/motivos");
    }
}
