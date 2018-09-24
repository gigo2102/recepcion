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
public class NewPersonaPayload extends Persona implements Validable {
    private List<TipoDocumento> tipodocumentosList;
    private UUID tipoDocumentoId;
	
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
    	
    	if(getTipoDocumentoId() == null ) {
    		errors.add("Complete el tipo de documento!");
    	}
    	
    	if(getApellido() == null || getApellido().isEmpty()) {
    		errors.add("Complete el apellido!");
    	}
    	
    	if(getTelefono() == null || getTelefono().isEmpty()) {
    		errors.add("Complete el telefono!");
    	}
    	
    	if(getValorDocumento() == null || getValorDocumento().isEmpty()) {
    		errors.add("Complete el valor de documento!");
    	}
    	
    
    	if(model.personaExisteDocumento(getValorDocumento())) {
    		errors.add("Ya existe una persona con el mismo documento!");
    	}
        return errors.toArray();
    }
	public List<TipoDocumento> getTipoDocumentosList() {
		return getTipoDocumentosList();
	}
	public void setTipoDocumentoList(List<TipoDocumento> tipodocumentosList) {
		this.setTipodocumentosList(tipodocumentosList);
	}
	public UUID getTipoDocumentoId() {
		return tipoDocumentoId;
	}
	public void setTipoDocumentoId(UUID tipodocumentosid) {
		this.tipoDocumentoId = tipodocumentosid;
	}
	public List<TipoDocumento> getTipodocumentosList() {
		return tipodocumentosList;
	}
	public void setTipodocumentosList(List<TipoDocumento> tipodocumentosList) {
		this.tipodocumentosList = tipodocumentosList;
	}
}