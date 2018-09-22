package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
import me.tomassetti.model.Visita;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class VisitaCreateHandler extends AbstractRequestHandler<NewVisitaPayload> {

    private Model sql2o_model;

    public VisitaCreateHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewVisitaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewVisitaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("visitas_create.ftl", value, errors);
		}
    	
    	Visita v = new Visita();
    	v.setTipovisita(model.tipovisitasGetById(value.getTipovisitaId()));
    	v.setArea(model.areasGetById(value.getAreaId()));
    	v.setMotivo(model.motivosGetById(value.getMotivoId()));
    	v.setPersona(model.personasGetById(value.getPersonaId()));
    	v.setObservaciones(value.getObservaciones());

    	sql2o_model.visitasCreate(v);
		return redirect("/visitas");
    }
}
