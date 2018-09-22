package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class TipoVisitaCreateHandler extends AbstractRequestHandler<NewTipoVisitaPayload> {

    private Model sql2o_model;

    public TipoVisitaCreateHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewTipoVisitaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewTipoVisitaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("tiposvisita_create.ftl", value, errors);
		}
		sql2o_model.tipovisitasCreate(value);
		return redirect("/tipovisitas");
    }
}
