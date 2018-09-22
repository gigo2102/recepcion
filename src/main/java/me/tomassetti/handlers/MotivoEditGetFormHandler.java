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

import org.sql2o.Connection;

public class MotivoEditGetFormHandler extends AbstractRequestHandler<EditMotivoPayload> {

    private Model sql2o_model;

    public MotivoEditGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EditMotivoPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(EditMotivoPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	Motivo elMotivo = model.motivosGetById(uuid);

    	List<Area> areasList = sql2o_model.areasList(null);
    	EditMotivoPayload viewModel = new EditMotivoPayload(areasList);
    	viewModel.setUuid(elMotivo.getUuid());
    	viewModel.setNombre(elMotivo.getNombre());
    	viewModel.setAreaId(elMotivo.getArea().getUuid());
    	
		return view("motivos_edit.ftl", viewModel);
    }


}

