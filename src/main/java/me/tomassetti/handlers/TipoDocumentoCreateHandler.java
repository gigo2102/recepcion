package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class TipoDocumentoCreateHandler extends AbstractRequestHandler<NewTipoDocumentoPayload> {

    private Model sql2o_model;

    public TipoDocumentoCreateHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewTipoDocumentoPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewTipoDocumentoPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("tipodocumentos_create.ftl", value, errors);
		}
		sql2o_model.tipodocumentoCreate(value);
		return redirect("/tipodocumentos");
    }
}
