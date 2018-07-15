package me.tomassetti.model;

import java.util.UUID;

public class Persona {
    private UUID uuid;
    private String valorDocumento;
    private TipoDocumento tipoDocumento; 
    
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
}
