package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Motivo;
import ar.gcba.cactyt.recepcion.models.TipoVisita;
import ar.gcba.cactyt.recepcion.models.Usuario;
import ar.gcba.cactyt.recepcion.models.Visita;
import ar.gcba.cactyt.recepcion.payloads.NewUsuarioPayload;

public class UsuarioCreateHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
		NewUsuarioPayload value = getValue(NewUsuarioPayload.class);
		if (!hasRole("admin")) return redirect("/logout");
		Model model = getModel();
    	Object[] errors = value.validate(model);
    	if (errors.length > 0) {
			return view("usuarios_create.ftl", value, errors);
		}
    	
    	Usuario usuario = new Usuario();
    	usuario.setNombre(value.getNombre());
    	usuario.setCorreo(value.getCorreo());
    	usuario.setPass(value.getPass());
    	usuario.setEsAdmin(value.getEsAdmin());
    	Area area = model.areasGetById(value.getAreaid());
    	usuario.setArea(area);
		model.usuariosCreate(usuario);
		
		return redirect("/usuarios");
    }
}


