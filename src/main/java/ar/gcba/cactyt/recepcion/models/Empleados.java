package ar.gcba.cactyt.recepcion.models;

import lombok.Data;
import java.util.List;
import java.util.UUID;

@Data
public class Empleados {
    private UUID uuid;
    private String nombre;
    private String apellido;
    private String cuil;
    private String dni;
    private String correo;
    private String telparticular;
    private String celular;
    private String ht;
    
    
    public Empleados(String nombre, String apellido, String cuil ,String dni , String correo , String telparticular , 
    		String celular , String ht) { 
    	this.nombre = nombre;
    	this.apellido = apellido; 
    	this.cuil = cuil;
    	this.dni = dni;
    	this.correo = correo;
    	this.telparticular = telparticular;
    	this.celular = celular;
    	this.ht = ht;
    }



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

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getCuil() {
		return cuil;
	}

	public void setCuil(String cuil) {
		this.cuil = cuil;
	}

	public String getDni() {
		return dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getTelparticular() {
		return telparticular;
	}

	public void setTelparticular(String telparticular) {
		this.telparticular = telparticular;
	}

	public String getCelular() {
		return celular;
	}

	public void setCelular(String celular) {
		this.celular = celular;
	}

	public String getHt() {
		return ht;
	}

	public void setHt(String ht) {
		this.ht = ht;
	}
	


}
