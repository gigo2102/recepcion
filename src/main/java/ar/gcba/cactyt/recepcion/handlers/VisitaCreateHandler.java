package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Motivo;
import ar.gcba.cactyt.recepcion.models.TipoVisita;
import ar.gcba.cactyt.recepcion.models.Visita;
import ar.gcba.cactyt.recepcion.payloads.NewVisitaPayload;

public class VisitaCreateHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
		Model model = getModel();
		NewVisitaPayload value = getValue(NewVisitaPayload.class);
		
    	Object[] errors = value.validate(model);
    	if (errors.length > 0) {
        	List<Motivo>  motivosList = model.motivosList();
        	List<TipoVisita> tipovisitasList = model.tipovisitaList();
        	
        	value.setMotivosList(motivosList);
        	value.setTipovisitasList(tipovisitasList);

    		return view("visita_create.ftl", value, errors);
		}
    	
    	Visita v = new Visita();
    	v.setTipovisita(model.tipovisitasGetById(value.getTipovisitaId()));
    	Motivo motivo = model.motivosGetById(value.getMotivoId());
    	v.setArea(motivo.getArea());
    	v.setMotivo(motivo);
    	v.setPersona(model.personasGetById(value.getPersonaId()));
    	v.setObservaciones(value.getObservaciones());

    	model.visitasCreate(v);
		return redirect("/visitas");
    }
}
