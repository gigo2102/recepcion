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
import me.tomassetti.model.Visita;

@Data
public class NewVisitaPayload extends Visita implements Validable {
    private List<Area> areasList;
    private List<Motivo> motivosList;
    private List<Persona> personasList;
    private UUID areaId;
    private UUID motivoId;
    private UUID personasId;
	


	
	
 public NewVisitaPayload(List<Area> areasList , List <Motivo> motivosList , List <Persona> personasList) {
		
		this.setAreasList(areasList);
		this.setMotivosList(motivosList);
		this.setPersonasList(personasList);
		
	}
	public NewVisitaPayload() {
		 
		this.setAreasList(new ArrayList<Area> ());
		this.setMotivosList(new ArrayList<Motivo> ());
		this.setPersonasList(new ArrayList<Persona> ());
		
	}
	
        public Object[] validate(Model model) {
    	ArrayList<String> errors = new ArrayList<String>();
    	if(getTipovisita() == null || getTipovisita().isEmpty()) {
    		errors.add("Complete el tipo de visita");
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
	
	public List<Motivo> getMotivoList() {
		return motivosList;
	}
	public void setMotivosList(List<Motivo> motivosList) {
		this.motivosList = motivosList;
	}
	
	public UUID getMotivoId() {
		return motivoId;
	}
	public void setMotivoId(UUID motivoId) {
		this.motivoId = motivoId;
	}
	
	public List<Persona> getPersonaList() {
		return personasList;
	}
	public void setPersonasList(List<Persona> personasList) {
		this.personasList = personasList;
	}
	
	public UUID getPersonasId() {
		return personasId;
	}
	
	
	
}