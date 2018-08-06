package me.tomassetti.model;

import java.util.UUID;

public class Persona {
    private UUID uuid;
    private String valorDocumento;
    private TipoDocumento tipoDocumento; 
    private String nombre;
	private String apellido;
	private String correo;
	private String telefono;
    
	public UUID getUuid() {
		return uuid;
	}
	
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
    
	private String getValorDocumento() {
		return valorDocumento;
	}
	
	public void setNumero(String valorDocumento) {
		this.valorDocumento = valorDocumento;
	}
	
	public TipoDocumento getTipoDocumento() {
		return tipoDocumento;
	}
	
	public void setTipoDocumento(TipoDocumento tipoDocumento){
		this.tipoDocumento = tipoDocumento; 
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	
}
