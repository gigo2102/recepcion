package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Persona;
import ar.gcba.cactyt.recepcion.models.Visita;

public class VisitaPendienteSearchKendoHandler extends AbstractRequestHandler {
    @Override
    public Answer process() {
		//para los que usan como webservice por ejemplo para autocompletes
        Model model = getModel();
	    String query = "select usuarios.uuid,areas.nombre as areaNombre , motivo.descripcion as Motivo "
		+ " , tipovisitas.nombre as tipoVisitaNombre"
		+ " from visitas"
//		+ " inner join usuarios on usuarios.uuid = visitas.usuarioid"
		+ " inner join areas on areas.uuid = visitas.areaid"
		+ " inner join motivos on motivos.uuid = visitas.motivoid"
		+ " inner join tipovisitas on tipovisitas.uuid = visitas.tipovisitaid";
        return json(model.kendoSearch(VisitaPendienteSearchDto.class, query, getUrlParams()));
    }

    public class VisitaPendienteSearchDto {
		private String uuid;
    	private String areaNombre;
    	private String motivoNombre;
    	private String tipoVisitaNombre;

    	public String getUuid() {
			return uuid;
		}
		public void setUuid(String uuid) {
			this.uuid = uuid;
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
		public String getTipoVisitaNombre() {
			return tipoVisitaNombre;
		}
		public void setTipoVisitaNombre(String tipoVisitaNombre) {
			this.tipoVisitaNombre = tipoVisitaNombre;
		}
	
    }
}

