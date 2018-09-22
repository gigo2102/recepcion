package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import me.tomassetti.model.Usuario;
import me.tomassetti.model.Visita;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class LoginHandler extends AbstractRequestHandler<LoginPayload> {

    private Model sql2o_model;

    public LoginHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(LoginPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(LoginPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
		Usuario usuario = sql2o_model.usuariosGetByLogin(value.getUsuario(), value.getPassword());
    	if (usuario == null) {
    		ArrayList<String> errors = new ArrayList<String>();
    		errors.add("Usuario o clave incorrectos...");
			return view("ingreso.ftl", value, errors.toArray());
		}
    	setLoggedUser(session, usuario.getUuid().toString(), usuario.getNombre());
		return redirect("/visitas");
    }
}
