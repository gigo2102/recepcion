package me.tomassetti.model;

import lombok.Data;
import java.util.UUID;

@Data
public class TipoDocumento {
    private UUID uuid;
    private String nombre;
    
	public UUID getUuid() {
		return uuid;
	}
	
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}
	
	public String getnombre() {
		return nombre;
	}
	
	public void setnombre(String nombre) {
		this.nombre = nombre;
	}
}
