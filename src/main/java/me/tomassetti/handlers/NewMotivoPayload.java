package me.tomassetti.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import me.tomassetti.Validable;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;

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