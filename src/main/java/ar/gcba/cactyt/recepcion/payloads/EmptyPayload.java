package ar.gcba.cactyt.recepcion.payloads;

import ar.gcba.cactyt.common.Validable;
import ar.gcba.cactyt.recepcion.models.Model;


public class EmptyPayload implements Validable {
    @Override
    public Object[] validate(Model model) {
        return new String[0];
    }
}
