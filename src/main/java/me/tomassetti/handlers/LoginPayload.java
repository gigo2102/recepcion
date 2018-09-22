package me.tomassetti.handlers;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import lombok.Data;
import me.tomassetti.Validable;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import me.tomassetti.model.Persona;
import me.tomassetti.model.TipoDocumento;

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