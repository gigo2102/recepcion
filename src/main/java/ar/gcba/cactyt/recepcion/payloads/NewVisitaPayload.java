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
import ar.gcba.cactyt.recepcion.models.TipoVisita;
import ar.gcba.cactyt.recepcion.models.Visita;
import lombok.Data;

@Data
public class NewVisitaPayload extends Visita implements Validable {
    private List<Area> areasList;
    private List<Motivo> motivosList;
    private List<TipoVisita> tipovisitasList;
    private List<Motivo> motivodeareaList;
    private UUID areaId;
    private UUID motivoId;
    private UUID personaId;
    private UUID tipovisitaId;
    private String nombre;
	


	
	
 public NewVisitaPayload(List<Area> areasList , List <Motivo> motivosList, List <TipoVisita> tipovisitasList) {
		
		this.setAreasList(areasList);
		this.setMotivosList(motivosList);
		this.setTipovisitasList(tipovisitasList);
	
		
	}
	public NewVisitaPayload() {
		 
		this.setAreasList(new ArrayList<Area> ());
		this.setMotivosList(new ArrayList<Motivo> ());
		this.setTipovisitasList(new ArrayList<TipoVisita> ());
	
		
	}
	
	public Object[] validate(Model model) {   
    	ArrayList<String> errors = new ArrayList<String>();
    	
    	if(getTipovisitaId() == null) {
    		errors.add("Complete el tipo de visita");
    	}
    	
    	if(getAreaId() == null) {
    		errors.add("Complete el area");
    	}
    	
    	if(getMotivoId() == null) {
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
	
	public List<Motivo> getMotivosList() {
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

	public UUID getPersonaId() {
		return personaId;
	}
	public void setPersonaId(UUID id) {
		personaId = id;
	}
	public UUID getTipovisitaId() {
		return tipovisitaId;
	}
	public void setTipovisitaId(UUID tipovisitasId) {
		this.tipovisitaId = tipovisitasId;
	}
	public List<TipoVisita> getTipovisitasList() {
		return tipovisitasList;
	}
	public void setTipovisitasList(List<TipoVisita> tipovisitasList) {
		this.tipovisitasList = tipovisitasList;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	
	
}