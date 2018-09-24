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
import lombok.Data;

@Data
public class LoginPayload implements Validable {
    private String usuario;
    private String password;
    
	public Boolean getIsLoginPage() {
		return true;
	}
	
	public String getUsuario() {
		return usuario;
	}
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	@Override
	public Object[] validate(Model model) {
		return null;
	}
	
}