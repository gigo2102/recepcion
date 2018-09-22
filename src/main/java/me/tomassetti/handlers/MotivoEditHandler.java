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

public class MotivoEditHandler extends AbstractRequestHandler<EditMotivoPayload> {

    private Model sql2o_model;

    public MotivoEditHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EditMotivoPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(EditMotivoPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	value.setUuid(uuid);
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("motivos_edit.ftl", value, errors);
		}
    	
    	Motivo motivo = sql2o_model.motivosGetById(uuid);
    	motivo.setNombre(value.getNombre());
    	Area area = sql2o_model.areasGetById(value.getAreaId());
    	motivo.setArea(area);
    	sql2o_model.updateMotivo(motivo);
    	
		return redirect("/motivos"); 
    }


}
