package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;

public class UsuarioSearchHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	if (!hasRole("admin")) return redirect("/logout");
    	Model model = getModel();
        return view("usuario_listado.ftl", model.usuariosList());
    }
}
