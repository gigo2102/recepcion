package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import me.tomassetti.model.Usuario;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class UsuarioCreateHandler extends AbstractRequestHandler<NewUsuarioPayload> {

    private Model sql2o_model;

    public UsuarioCreateHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewUsuarioPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewUsuarioPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("usuarios_create.ftl", value, errors);
		}
    	
    	Usuario usuario = new Usuario();
    	usuario.setNombre(value.getNombre());
    	usuario.setCorreo(value.getCorreo());
    	usuario.setPass(value.getPass());
    	Area area = sql2o_model.areasGetById(value.getAreaid());
    	usuario.setArea(area);
		sql2o_model.usuariosCreate(usuario);
		
		return redirect("/usuarios");
    }
}