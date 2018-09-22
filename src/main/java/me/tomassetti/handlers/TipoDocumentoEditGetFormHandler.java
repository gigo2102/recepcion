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

import org.sql2o.Connection;

public class TipoDocumentoEditGetFormHandler extends AbstractRequestHandler<EditTipoDocumentoPayload> {

    private Model sql2o_model;

    public TipoDocumentoEditGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EditTipoDocumentoPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(EditTipoDocumentoPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	TipoDocumento elTipoDocumento = model.tipodocumentoGetById(uuid);
		return view("tipodocumentos_edit.ftl", elTipoDocumento);
    }


}

