package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
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
    protected Answer processImpl(NewVisitaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("visitas_create.ftl", value, errors);
		}
//		sql2o_model.visitasCreate(value);
		return redirect("/visitas");
    }
}
