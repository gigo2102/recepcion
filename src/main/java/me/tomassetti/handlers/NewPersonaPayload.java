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
public class NewPersonaPayload extends Persona implements Validable {
    private List<TipoDocumento> tipodocumentosList;
    private UUID tipodocumentosid;
	
	public NewPersonaPayload(List<TipoDocumento> tipodocumentosList) {
		 
		this.setTipoDocumentoList(tipodocumentosList);
	}
	public NewPersonaPayload() {
		 
		this.setTipoDocumentoList(new ArrayList<TipoDocumento> ());
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
		return getTipoDocumentosList();
	}
	public void setTipoDocumentoList(List<TipoDocumento> tipodocumentosList) {
		this.setTipodocumentosList(tipodocumentosList);
	}
	public UUID getTipoDocumentosid() {
		return tipodocumentosid;
	}
	public void setTipoDocumentosid(UUID tipodocumentosid) {
		this.tipodocumentosid = tipodocumentosid;
	}
	public List<TipoDocumento> getTipodocumentosList() {
		return tipodocumentosList;
	}
	public void setTipodocumentosList(List<TipoDocumento> tipodocumentosList) {
		this.tipodocumentosList = tipodocumentosList;
	}
}