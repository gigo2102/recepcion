package me.tomassetti.model;

import java.util.UUID;

public class Visita {
	
	private UUID uuid;
	private String observaciones; 
	private TipoVisita tipovisita;
	private Persona persona;
	private Area area;
	private Motivo motivo;
	
	
	public UUID getUuid() {
		return uuid;
	}
	public void setUuid(UUID uuid) {
		this.uuid = uuid;
	}

	public String getObservaciones() {
		return observaciones;
	}
	public void setObservaciones(String observaciones) {
		this.observaciones = observaciones;
	}
	public Persona getPersona() {
		return persona;
	}
	public void setPersona(Persona persona) {
		this.persona = persona;
	}
	public Area getArea() {
		return area;
	}
	public void setArea(Area area) {
		this.area = area;
	}
	public Motivo getMotivo() {
		return motivo;
	}
	public void setMotivo(Motivo motivo) {
		this.motivo = motivo;
	}
	
	public TipoVisita getTipovisita() {
		return tipovisita;
	}
	public void setTipovisita(TipoVisita tipovisita) {
		this.tipovisita = tipovisita;
	}

	



}
