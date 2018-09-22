package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

import org.sql2o.Connection;

public class AreaEditGetFormHandler extends AbstractRequestHandler<EditAreaPayload> {

    private Model sql2o_model;

    public AreaEditGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EditAreaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(EditAreaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	Area elArea = model.areasGetById(uuid);
		return view("areas_edit.ftl", elArea);
    }


}

