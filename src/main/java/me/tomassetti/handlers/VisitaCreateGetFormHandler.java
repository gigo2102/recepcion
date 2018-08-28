package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import me.tomassetti.model.Persona;
import me.tomassetti.model.TipoDocumento;
import me.tomassetti.model.TipoVisita;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class VisitaCreateGetFormHandler extends AbstractRequestHandler<NewVisitaPayload> {

    private Model sql2o_model;

    public VisitaCreateGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewVisitaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewVisitaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
    	List<Area> areasList = sql2o_model.areasList(null);
    	List<Motivo>  motivosList = sql2o_model.motivosList();
    	List<Persona> personasList = sql2o_model.personasList();
    	List<TipoVisita> tipovisitasList = sql2o_model.tipovisitaList();
    	NewVisitaPayload viewModel = new NewVisitaPayload(areasList, motivosList , personasList, tipovisitasList);
    	
    	return view("visita_create.ftl", viewModel);
    }
}