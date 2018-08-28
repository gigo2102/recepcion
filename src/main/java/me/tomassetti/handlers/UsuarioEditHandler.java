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

public class UsuarioEditHandler extends AbstractRequestHandler<EditUsuarioPayload> {

    private Model sql2o_model;

    public UsuarioEditHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EditUsuarioPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(EditUsuarioPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	value.setUuid(uuid);
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("usuarios_edit.ftl", value, errors);
		}
    	
    	Usuario usuario = sql2o_model.usuariosGetById(uuid);
    	usuario.setCorreo(value.getCorreo());
    	usuario.setPass(value.getNombre());
    	usuario.setNombre(value.getPass());
    	Area area = sql2o_model.areasGetById(value.getAreaId());
    	usuario.setArea(area);
    	sql2o_model.updateUsuario(usuario);
    	
		return redirect("/usuarios"); 
    }


}
