package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import me.tomassetti.model.Persona;
import me.tomassetti.model.TipoDocumento;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class PersonasCreateHandler extends AbstractRequestHandler<NewPersonaPayload> {

    private Model sql2o_model;

    public PersonasCreateHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
        super(NewPersonaPayload.class, model, freeMarkerEngine);
        this.sql2o_model = model;
    }

    @Override
    protected Answer processImpl(NewPersonaPayload value, Map<String, String> urlParams, boolean shouldReturnHtml) {
		Object[] errors = value.validate(sql2o_model);
    	if (errors.length > 0) {
			return view("personas_create.ftl", value, errors);
		}
    	
    	Persona persona = new Persona();
    	persona.setNombre(value.getNombre());
    	TipoDocumento tipodocumento = sql2o_model.tipodocumentoGetById(value.getTipoDocumentosid());
    	persona.setTipoDocumento(tipodocumento);
		sql2o_model.personasCreate(persona);
		
		return redirect("/personas");
    }
}

