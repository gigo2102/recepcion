package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Persona;
import me.tomassetti.model.TipoDocumento;
import me.tomassetti.model.TipoVisita;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PersonaCreateGetFormHandler extends AbstractRequestHandler<NewPersonaPayload> {

    private Model sql2o_model;

    public PersonaCreateGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewPersonaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewPersonaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	List<TipoDocumento> tipodocumentosList = sql2o_model.tipodocumentosList(null);
    	NewPersonaPayload viewModel = new NewPersonaPayload(tipodocumentosList);
    	return view("personas_create.ftl", viewModel);
    }
}
