package me.tomassetti.handlers;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import me.tomassetti.Validable;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import me.tomassetti.model.Usuario;

@Data
public class NewUsuarioPayload extends Usuario implements Validable {
    private List<Area> areasList;
    private UUID areaid;
  
	
	public NewUsuarioPayload(List<Area> areasList) {
		 
		this.setAreasList(areasList);
	}
	public NewUsuarioPayload() {
		 
		this.setAreasList(new ArrayList<Area> ());
	}

	public Object[] validate(Model model) {
    	ArrayList<String> errors = new ArrayList<String>();
    	
    	if(getPass()== null || getPass().isEmpty()) {
    		errors.add("Complete la contraseña!");
    	}
    	if(getCorreo() == null || getCorreo().isEmpty()) {
    		errors.add("Complete el Correo!");
    	}
    	
    	if(getNombre() == null || getNombre().isEmpty()) {
    		errors.add("Complete el nombre!");
    	}
    	if(model.usuariosExisteNombre(getNombre())) {
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
	public UUID getAreaid() {
		return areaid;
	}
	public void setAreaId(UUID areaid) {
		this.areaid = areaid;
	}
}