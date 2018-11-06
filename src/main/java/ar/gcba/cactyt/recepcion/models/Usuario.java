package ar.gcba.cactyt.recepcion.models;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class Usuario {
    private UUID uuid;
    private String nombre;
    private String correo;
    private String pass;
    private Area area;
    private boolean esAdmin;
    private boolean esRecepcionista;
    
	public UUID getUuid() {
		return uuid;
	}
	
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public String getNombre() {
		return nombre;
	}
	
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Area getArea() {
		return area;
	}

	public void setArea(Area area) {
		this.area = area;
	}

	public boolean getEsAdmin() {
		return esAdmin;
	}

	public void setEsAdmin(boolean esAdmin) {
		this.esAdmin = esAdmin;
	}

	public boolean isEsRecepcionista() {
		return esRecepcionista;
	}

	public void setEsRecepcionista(boolean esRecepcionista) {
		this.esRecepcionista = esRecepcionista;
	}
}
