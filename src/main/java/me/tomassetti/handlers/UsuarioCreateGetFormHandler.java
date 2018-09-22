package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Usuario;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class UsuarioCreateGetFormHandler extends AbstractRequestHandler<NewUsuarioPayload> {

    private Model sql2o_model;

    public UsuarioCreateGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewUsuarioPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewUsuarioPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	List<Area> areasList = sql2o_model.areasList(null);
    	NewUsuarioPayload viewModel = new NewUsuarioPayload(areasList);
    	return view("usuarios_create.ftl", viewModel);
    }
}
