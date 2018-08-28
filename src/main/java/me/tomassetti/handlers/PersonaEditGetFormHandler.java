package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import me.tomassetti.model.Persona;
import me.tomassetti.model.TipoDocumento;
import me.tomassetti.model.Usuario;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.sql2o.Connection;

public class PersonaEditGetFormHandler extends AbstractRequestHandler<EditPersonaPayload> {

    private Model sql2o_model;

    public PersonaEditGetFormHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EditPersonaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(EditPersonaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	Persona unPersona = model.personasGetById(uuid);

    	List<TipoDocumento> tipodocumentosList = sql2o_model.tipodocumentosList(null);
    	EditPersonaPayload viewModel = new EditPersonaPayload(tipodocumentosList);
    	viewModel.setUuid(unPersona.getUuid());
    	viewModel.setNombre(unPersona.getNombre());
    	viewModel.setApellido(unPersona.getApellido());
    	viewModel.setCorreo(unPersona.getCorreo());
    	viewModel.setTelefono(unPersona.getTelefono());
    	viewModel.setValorDocumento(unPersona.getValorDocumento());
    	viewModel.setTipoDocumentoId(unPersona.getTipoDocumento().getUuid());
    	
		return view("personas_edit.ftl", viewModel);
    }


}

