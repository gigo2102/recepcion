package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class UsuarioSearchHandler extends AbstractRequestHandler<EmptyPayload> {

    private Model model;

    public UsuarioSearchHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EmptyPayload.class, model, freeMarkerEngine);
        this.model = model;
    }

    @Override
    protected Answer processImpl(EmptyPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
    	String nombre = urlParams.get("nombre");
        return view("usuario_listado.ftl", model.usuariosList(nombre));
    }
}
