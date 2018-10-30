package ar.gcba.cactyt.recepcion.handlers;

import java.util.UUID;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;

public class UsuariosDeleteHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	if (!hasRole("admin")) return redirect("/logout");
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	Model model = getModel();
    	model.usuarioDelete(uuid);
        return redirect("/usuarios");
    }
}