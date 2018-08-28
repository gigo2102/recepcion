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
import me.tomassetti.model.Usuario;

@Data
public class EditPersonaPayload extends Persona implements Validable {
    private List<TipoDocumento> tipodocumentosList;
    private UUID tipoDocumentoId;
	
	public EditPersonaPayload(List<TipoDocumento> tipodocumentosList) {
		 
		this.setTipoDocumentosList(tipodocumentosList);
	}
	public EditPersonaPayload() {
		 
		this.setTipoDocumentosList(new ArrayList<TipoDocumento> ());
	}

	public Object[] validate(Model model) {
    	ArrayList<String> errors = new ArrayList<String>();
    	if(getNombre() == null || getNombre().isEmpty()) {
    		errors.add("Complete el nombre!");
    	}
    	if(model.areasExisteNombre(getNombre())) {
    		errors.add("Ya existe el mismo nombre!");
    	}
        return errors.toArray();
    }
	public List<TipoDocumento> getTipoDocumentosList() {
		return tipodocumentosList;
	}
	public void setTipoDocumentosList(List<TipoDocumento> tipodocumentosList) {
		this.tipodocumentosList = tipodocumentosList;
	}
	public UUID getTipoDocumentoId() {
		return tipoDocumentoId;
	}
	public void setTipoDocumentoId(UUID tipodocumentoId) {
		this.tipoDocumentoId = tipodocumentoId;
	}
}