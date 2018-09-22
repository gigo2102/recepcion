package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
import me.tomassetti.model.Persona;
import me.tomassetti.model.Visita;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class VisitasSearchHandler extends AbstractRequestHandler<EmptyPayload> {

    private Model model;

    public VisitasSearchHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EmptyPayload.class, model, freeMarkerEngine);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
        List<Visita> l = model.visitasList();
    	if (shouldReturnHtml) {
        	return view("visitas_listado.ftl", l);
        } else {
        	return json(l);
        }
    }
}
