package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.TipoVisita;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

import org.sql2o.Connection;

public class TipoVisitaEditGetFormHandler extends AbstractRequestHandler<EditTipoVisitaPayload> {

    private Model sql2o_model;

    public TipoVisitaEditGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EditTipoVisitaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(EditTipoVisitaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	TipoVisita elTipoVisita = model.tipovisitasGetById(uuid);
		return view("tipovisita_edit.ftl", elTipoVisita);
    }


}

