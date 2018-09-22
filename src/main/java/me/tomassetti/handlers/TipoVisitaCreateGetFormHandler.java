package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.TipoVisita;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class TipoVisitaCreateGetFormHandler extends AbstractRequestHandler<NewTipoVisitaPayload> {

    private Model sql2o_model;

    public TipoVisitaCreateGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewTipoVisitaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewTipoVisitaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	return view("tipovisita_create.ftl", new TipoVisita());
    }
}
