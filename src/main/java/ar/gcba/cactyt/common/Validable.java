package ar.gcba.cactyt.common;

import ar.gcba.cactyt.recepcion.models.Model;

public interface Validable {
	Object[] validate(Model model);
}
