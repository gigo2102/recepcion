package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.TipoDocumento;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class TipoDocumentoCreateGetFormHandler extends AbstractRequestHandler<NewTipoDocumentoPayload> {

    private Model sql2o_model;

    public TipoDocumentoCreateGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewTipoDocumentoPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewTipoDocumentoPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	return view("tipodocumentos_create.ftl", new TipoDocumento());
    }
}
