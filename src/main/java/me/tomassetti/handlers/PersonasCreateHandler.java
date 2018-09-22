package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import me.tomassetti.model.Persona;
import me.tomassetti.model.TipoDocumento;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public class PersonasCreateHandler extends AbstractRequestHandler<NewPersonaPayload> {

    private Model sql2o_model;

    public PersonasCreateHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewPersonaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewPersonaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml, Session session) {
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
        	List<TipoDocumento> tipodocumentosList = sql2o_model.tipodocumentosList(null);
    		value.setTipoDocumentoList(tipodocumentosList);
			return view("personas_create.ftl", value, errors);
		}
    	
    	Persona persona = new Persona();
    	TipoDocumento tipodocumento = sql2o_model.tipodocumentoGetById(value.getTipoDocumentoId());

    	persona.setNombre(value.getNombre());
    	persona.setTipoDocumento(tipodocumento);
		persona.setCorreo(value.getCorreo());
		persona.setTelefono(value.getTelefono());
		persona.setApellido(value.getApellido());
		persona.setValorDocumento(value.getValorDocumento());

		sql2o_model.personasCreate(persona);
		
		return redirect("/personas");
    }
}

