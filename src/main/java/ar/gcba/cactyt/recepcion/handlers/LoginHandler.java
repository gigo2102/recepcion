package ar.gcba.cactyt.recepcion.handlers;

import java.util.ArrayList;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Usuario;
import ar.gcba.cactyt.recepcion.payloads.LoginPayload;

public class LoginHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
		Model model = getModel();
		LoginPayload value = getValue(LoginPayload.class);
    	Usuario usuario = model.usuariosGetByLogin(value.getUsuario(), value.getPassword());
    	if (usuario == null) {
    		ArrayList<String> errors = new ArrayList<String>();
    		errors.add("Usuario o clave incorrectos...");
			return view("ingreso.ftl", value, errors.toArray());
		}
    	setLoggedUser(getSession(), usuario.getUuid().toString(), usuario.getNombre());
		return redirect("/visitas");
    }
}
