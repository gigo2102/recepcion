package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;

public class EmpleadosImportacionHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
    	if (!hasRole("admin")) return redirect("/logout");
        return view("personas_empleados_importacion.ftl", null);
    }
}