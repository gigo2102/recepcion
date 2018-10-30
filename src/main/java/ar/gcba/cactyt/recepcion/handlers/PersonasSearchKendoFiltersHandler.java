package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Persona;

public class PersonasSearchKendoFiltersHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
		//para los que usan como webservice por ejemplo para autocompletes
    	
        Model model = getModel();
		return json(model.kendoSearch(Persona.class, "select uuid,nombre,apellido,telefono,valorDocumento from personas", getUrlParams()));
    }
}
