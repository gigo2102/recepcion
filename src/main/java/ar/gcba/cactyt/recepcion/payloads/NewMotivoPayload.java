package ar.gcba.cactyt.recepcion.payloads;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import ar.gcba.cactyt.common.Validable;
import ar.gcba.cactyt.recepcion.models.Area;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Motivo;
import lombok.Data;

@Data
public class NewMotivoPayload extends Motivo implements Validable {
    private List<Area> areasList;
    private UUID areaId;
	
	public NewMotivoPayload(List<Area> areasList) {
		 
		this.setAreasList(areasList);
	}
	public NewMotivoPayload() {
		 
		this.setAreasList(new ArrayList<Area> ());
	}

	public Object[] validate(Model model) {
    	ArrayList<String> errors = new ArrayList<String>();
    	if(getNombre() == null || getNombre().isEmpty()) {
    		errors.add("Complete el nombre!");
    	}
    	if(model.motivosExisteNombre(getNombre())) {
    		errors.add("Ya existe el mismo nombre!");
    	}
    	
    	if(getAreaId() == null) {
    		errors.add("Complete el area");
    	}
        return errors.toArray();
    }
	public List<Area> getAreasList() {
		return areasList;
	}
	public void setAreasList(List<Area> areasList) {
		this.areasList = areasList;
	}
	public UUID getAreaId() {
		return areaId;
	}
	public void setAreaId(UUID areaId) {
		this.areaId = areaId;
	}
}