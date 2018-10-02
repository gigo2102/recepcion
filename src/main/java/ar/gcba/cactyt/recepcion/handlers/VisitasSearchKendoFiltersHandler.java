package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Persona;
import ar.gcba.cactyt.recepcion.models.Visita;

public class VisitasSearchKendoFiltersHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
		//para los que usan como webservice por ejemplo para autocompletes
        Model model = getModel();
	    String query = "select visitas.uuid, visitas.observaciones, areas.nombre as areaNombre, motivos.nombre as motivoNombre "
		+ " , personas.nombre as personaNombre, personas.apellido as personaApellido, personas.valorDocumento as personaValorDocumento"
		+ " , tipovisitas.nombre as tipoVisitaNombre"
		+ " from visitas"
		+ " inner join areas on areas.uuid = visitas.areaid"
		+ " inner join motivos on motivos.uuid = visitas.motivoid"
		+ " inner join personas on personas.uuid = visitas.personaid"
		+ " inner join tipovisitas on tipovisitas.uuid = visitas.tipovisitaid";
        return json(model.kendoSearch(VisitaSearchDto.class, query, getUrlParams()));
    }

    public class VisitaSearchDto {
		private String uuid;
    	private String observaciones;
    	private String areaNombre;
    	private String motivoNombre;
    	private String personaNombre;
    	private String personaApellido;
    	private String personaValorDocumento;
    	private String tipoVisitaNombre;

    	public String getUuid() {
			return uuid;
		}
		public void setUuid(String uuid) {
			this.uuid = uuid;
		}
		public String getObservaciones() {
			return observaciones;
		}
		public void setObservaciones(String observaciones) {
			this.observaciones = observaciones;
		}
		public String getAreaNombre() {
			return areaNombre;
		}
		public void setAreaNombre(String areaNombre) {
			this.areaNombre = areaNombre;
		}
		public String getMotivoNombre() {
			return motivoNombre;
		}
		public void setMotivoNombre(String motivoNombre) {
			this.motivoNombre = motivoNombre;
		}
		public String getPersonaNombre() {
			return personaNombre;
		}
		public void setPersonaNombre(String personaNombre) {
			this.personaNombre = personaNombre;
		}
		public String getPersonaApellido() {
			return personaApellido;
		}
		public void setPersonaApellido(String personaApellido) {
			this.personaApellido = personaApellido;
		}
		public String getPersonaValorDocumento() {
			return personaValorDocumento;
		}
		public void setPersonaValorDocumento(String personaValorDocumento) {
			this.personaValorDocumento = personaValorDocumento;
		}
		public String getTipoVisitaNombre() {
			return tipoVisitaNombre;
		}
		public void setTipoVisitaNombre(String tipoVisitaNombre) {
			this.tipoVisitaNombre = tipoVisitaNombre;
		}
    }
}

