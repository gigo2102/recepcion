package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import me.tomassetti.model.Persona;
import me.tomassetti.model.TipoDocumento;
import me.tomassetti.model.Usuario;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class PersonaEditHandler extends AbstractRequestHandler<EditPersonaPayload> {

    private Model sql2o_model;

    public PersonaEditHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(EditPersonaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(EditPersonaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
    	UUID uuid = UUID.fromString(urlParams.get(":uuid"));
    	value.setUuid(uuid);
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("personas_edit.ftl", value, errors);
		}
    	
    	Persona persona = sql2o_model.personasGetById(uuid);
    	persona.setCorreo(value.getCorreo());
    	persona.setApellido(value.getApellido());
    	persona.setNombre(value.getNombre());
    	persona.setValorDocumento(value.getValorDocumento());
    	persona.setTelefono(value.getTelefono());
    	TipoDocumento tipodocumento = sql2o_model.tipodocumentosGetById(value.getTipoDocumentoId());
    	persona.setTipoDocumento(tipodocumento);
    	sql2o_model.updatePersona(persona);
    	
		return redirect("/personas"); 
    }


}
