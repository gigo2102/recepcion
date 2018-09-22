package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class TipoVisitaEditHandler extends AbstractRequestHandler<EditTipoVisitaPayload> {

    private Model sql2o_model;

    public TipoVisitaEditHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EditTipoVisitaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(EditTipoVisitaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	value.setUuid(uuid);
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("tipovisita_edit.ftl", value, errors);
		}
    	sql2o_model.updateTipoVisita(value);
		return redirect("/tipovisitas");
    }


}
