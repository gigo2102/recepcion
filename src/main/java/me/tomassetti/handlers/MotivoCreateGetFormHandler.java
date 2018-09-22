package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class MotivoCreateGetFormHandler extends AbstractRequestHandler<NewMotivoPayload> {

    private Model sql2o_model;

    public MotivoCreateGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewMotivoPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewMotivoPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	List<Area> areasList = sql2o_model.areasList(null);
    	NewMotivoPayload viewModel = new NewMotivoPayload(areasList);
    	return view("motivos_create.ftl", viewModel);
    }
}
