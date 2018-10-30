package ar.gcba.cactyt.recepcion.handlers;

import java.util.UUID;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.TipoDocumento;

public class TipoDocumentoEditGetFormHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	if (!hasRole("admin")) return redirect("/logout");
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	Model model = getModel();
    	TipoDocumento elTipoDocumento = model.tipodocumentoGetById(uuid);
		return view("tipodocumentos_edit.ftl", elTipoDocumento);
    }


}

