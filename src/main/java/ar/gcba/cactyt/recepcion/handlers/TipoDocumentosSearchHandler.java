package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;

public class TipoDocumentosSearchHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	String nombre = getUrlParams().get("nombre");
    	if (!hasRole("admin")) return redirect("/logout");
    	Model model = getModel();
        return view("tipodocumentos_listado.ftl", model.tipodocumentosList(nombre));
    }
}

