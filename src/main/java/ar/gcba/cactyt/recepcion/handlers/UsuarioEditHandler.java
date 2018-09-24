package ar.gcba.cactyt.recepcion.handlers;

import java.util.UUID;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Usuario;
import ar.gcba.cactyt.recepcion.payloads.EditUsuarioPayload;

public class UsuarioEditHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	EditUsuarioPayload value = getValue(EditUsuarioPayload.class);
    	value.setUuid(uuid);
		Model model = getModel();
    	Object[] errors = value.validate(model);
    	if (errors.length > 0) {
			return view("usuarios_edit.ftl", value, errors);
		}
    	
    	Usuario usuario = model.usuariosGetById(uuid);
    	usuario.setCorreo(value.getCorreo());
    	usuario.setPass(value.getNombre());
    	usuario.setNombre(value.getPass());
    	Area area = model.areasGetById(value.getAreaId());
    	usuario.setArea(area);
    	model.updateUsuario(usuario);
    	
		return redirect("/usuarios"); 
    }


}
