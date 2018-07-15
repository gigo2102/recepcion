package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class AreaEditHandler extends AbstractRequestHandler<EditAreaPayload> {

    private Model sql2o_model;

    public AreaEditHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EditAreaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(EditAreaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	value.setUuid(uuid);
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("areas_edit.ftl", value, errors);
		}
    	sql2o_model.updateArea(value);
		return redirect("/areas");
    }


}
