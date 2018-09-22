package me.tomassetti.handlers;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import me.tomassetti.Validable;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import me.tomassetti.model.Persona;
import me.tomassetti.model.TipoDocumento;

@Data
public class LogoutPayload implements Validable {

	@Override
	public Object[] validate(Model model) {
		
		 return null;
	}
	
}