package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
import me.tomassetti.model.Persona;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PersonasSearchHandler extends AbstractRequestHandler<EmptyPayload> {

    private Model model;

    public PersonasSearchHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EmptyPayload.class, model, freeMarkerEngine);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	String searchTerm = urlParams.get("term");
         List<Persona> l = model.personasList(searchTerm);
    	if (shouldReturnHtml) {
        	return view("personas_listado.ftl", l);
        } else {
        	return json(l);
        }
    }
}
