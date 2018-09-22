package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class TipoDocumentoEditHandler extends AbstractRequestHandler<EditTipoDocumentoPayload> {

    private Model sql2o_model;

    public TipoDocumentoEditHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EditTipoDocumentoPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(EditTipoDocumentoPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	value.setUuid(uuid);
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("tipodocumentos_edit.ftl", value, errors);
		}
    	sql2o_model.updateTipoDocumento(value);
		return redirect("/tipodocumentos");
    }


}
