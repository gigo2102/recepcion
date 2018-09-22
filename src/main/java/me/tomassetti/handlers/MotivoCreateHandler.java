package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class MotivoCreateHandler extends AbstractRequestHandler<NewMotivoPayload> {

    private Model sql2o_model;

    public MotivoCreateHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewMotivoPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewMotivoPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("motivos_create.ftl", value, errors);
		}
    	
    	Motivo motivo = new Motivo();
    	motivo.setNombre(value.getNombre());
    	Area area = sql2o_model.areasGetById(value.getAreaId());
    	motivo.setArea(area);
		sql2o_model.motivosCreate(motivo);
		
		return redirect("/motivos");
    }
}
