package ar.gcba.cactyt.recepcion.payloads;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ar.gcba.cactyt.common.Validable;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Motivo;
import ar.gcba.cactyt.recepcion.models.Persona;
import ar.gcba.cactyt.recepcion.models.TipoDocumento;
import lombok.Data;

@Data
public class LogoutPayload implements Validable {

	@Override
	public Object[] validate(Model model) {
		
		 return null;
	}
	
}