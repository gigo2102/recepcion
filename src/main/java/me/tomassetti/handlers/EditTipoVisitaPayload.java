package me.tomassetti.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import me.tomassetti.Validable;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import me.tomassetti.model.TipoVisita;

@Data
public class EditTipoVisitaPayload extends TipoVisita implements Validable {
    private List<TipoVisita> tipovisitasList;
    private UUID tipovisitaId;
	
	public EditTipoVisitaPayload(List<Area> areasList) {
		 
		this.setTipoVisitasList(tipovisitasList);
	}
	public EditTipoVisitaPayload() {
		 
		this.setTipoVisitasList(new ArrayList<TipoVisita> ());
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
	public List<TipoVisita> getTipoVisitasList() {
		return tipovisitasList;
	}
	public void setTipoVisitasList(List<TipoVisita> tipovisitasList) {
		this.tipovisitasList = tipovisitasList;
	}
	public UUID getTipoVisitaId() {
		return tipovisitaId;
	}
	public void setTipoVisitaId(UUID tipovisitaId) {
		this.tipovisitaId = tipovisitaId;
	}
}