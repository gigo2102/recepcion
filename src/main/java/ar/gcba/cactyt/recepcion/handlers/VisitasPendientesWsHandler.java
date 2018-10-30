package ar.gcba.cactyt.recepcion.handlers;

import java.util.List;
import java.util.UUID;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Persona;
import ar.gcba.cactyt.recepcion.models.Usuario;
import ar.gcba.cactyt.recepcion.models.Visita;

public class VisitasPendientesWsHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
        Model model = getModel();
        LoggedUser loggedUser = getLoggedUser();
        UUID usuarioUuid = UUID.fromString(loggedUser.getUserUuid());
        Usuario usuario = model.usuariosGetById(usuarioUuid);
        Area area = usuario.getArea();
        UUID areaUuid = area.getUuid();
        List<Visita> visitasPendientesDelAreaDelUsuario = model.visitasList(null, false, areaUuid);
        return json(visitasPendientesDelAreaDelUsuario);
    }
}

