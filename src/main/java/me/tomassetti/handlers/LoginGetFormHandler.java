package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LoginGetFormHandler extends AbstractRequestHandler<LoginPayload> {

    private Model sql2o_model;

    public LoginGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(LoginPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(LoginPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	return view("ingreso.ftl", new LoginPayload());
    }
}
