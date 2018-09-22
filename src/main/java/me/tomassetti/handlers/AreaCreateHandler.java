package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class AreaCreateHandler extends AbstractRequestHandler<NewAreaPayload> {

    private Model sql2o_model;

    public AreaCreateHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewAreaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewAreaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("areas_create.ftl", value, errors);
		}
		sql2o_model.areasCreate(value);
		return redirect("/areas");
    }
}
