package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.TipoDocumento;

public class TipoDocumentoCreateGetFormHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	return view("tipodocumentos_create.ftl", new TipoDocumento());
    }
}
