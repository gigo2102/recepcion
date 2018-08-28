package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import me.tomassetti.model.Usuario;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.sql2o.Connection;

public class UsuarioEditGetFormHandler extends AbstractRequestHandler<EditUsuarioPayload> {

    private Model sql2o_model;

    public UsuarioEditGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EditUsuarioPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(EditUsuarioPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	Usuario elUsuario = model.usuariosGetById(uuid);

    	List<Area> areasList = sql2o_model.areasList(null);
    	EditUsuarioPayload viewModel = new EditUsuarioPayload(areasList);
    	viewModel.setUuid(elUsuario.getUuid());
    	viewModel.setNombre(elUsuario.getNombre());
    	viewModel.setCorreo(elUsuario.getCorreo());
    	viewModel.setPass(elUsuario.getPass());
    	viewModel.setAreaId(elUsuario.getArea().getUuid());
    	
		return view("usuarios_edit.ftl", viewModel);
    }


}

