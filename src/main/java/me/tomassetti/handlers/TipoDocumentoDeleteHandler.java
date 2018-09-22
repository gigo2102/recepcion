package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class TipoDocumentoDeleteHandler extends AbstractRequestHandler<EmptyPayload> {

    private Model model;

    public TipoDocumentoDeleteHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EmptyPayload.class, model, freeMarkerEngine);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	model.TipoDocumentoDelete(uuid);
        return redirect("/tipodocumentos");
    }
}