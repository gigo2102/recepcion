package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class PersonasDeleteHandler extends AbstractRequestHandler<EmptyPayload> {

    private Model model;

    public PersonasDeleteHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EmptyPayload.class, model, freeMarkerEngine);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	model.personaDelete(uuid);
        return redirect("/personas");
    }
}