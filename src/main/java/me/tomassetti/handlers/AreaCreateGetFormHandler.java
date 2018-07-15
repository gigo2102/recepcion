package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class AreaCreateGetFormHandler extends AbstractRequestHandler<NewAreaPayload> {

    private Model sql2o_model;

    public AreaCreateGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewAreaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewAreaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
    	return view("areas_create.ftl", new Area());
    }
}
