package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import java.util.UUID;
import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Usuario;
import ar.gcba.cactyt.recepcion.payloads.EditUsuarioPayload;

public class UsuarioEditGetFormHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	UUID uuid = UUID.fromString(getUrlParams().get(":uuid"));
    	if (!hasRole("admin")) return redirect("/logout");
    	Model model = getModel();
    	Usuario elUsuario = model.usuariosGetById(uuid);

    	List<Area> areasList = model.areasList(null);
    	EditUsuarioPayload viewModel = new EditUsuarioPayload(areasList);
    	viewModel.setUuid(elUsuario.getUuid());
    	viewModel.setNombre(elUsuario.getNombre());
    	viewModel.setCorreo(elUsuario.getCorreo());
    	viewModel.setPass(elUsuario.getPass());
    	viewModel.setAreaId(elUsuario.getArea().getUuid());
    	viewModel.setEsAdmin(elUsuario.getEsAdmin());
    	viewModel.setEsRecepcionista(elUsuario.getEsRecepcionista());
    	
		return view("usuarios_edit.ftl", viewModel);
    }


}

