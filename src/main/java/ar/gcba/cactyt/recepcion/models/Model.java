package ar.gcba.cactyt.recepcion.models;

import java.util.List;
import java.util.Map;
import java.util.UUID;

public interface Model {
	Map<String, Object> kendoSearch(Class<?> clazz, String subQuery, Map<String, String> params);
	
    void areaDelete(UUID uuid);
    
    void personaDelete(UUID uuid);
    
    void usuarioDelete(UUID uuid);
    
	List<Area> areasList(String nombre);
	
	void areasCreate(Area area);
	
	boolean areasExisteNombre(String nombre);
	
	boolean motivosExisteNombre(String nombre);
	
    void updateArea(Area area);
    
    void updateTipoVisita(TipoVisita tipovisita);

	Area areasGetById(UUID uuid);
    
	List<Motivo> motivosList();
	
	List<Usuario> usuariosList();

	void motivosCreate(Motivo motivo);
	
	void visitasCreate(Visita visita);

	void motivoDelete(UUID uuid);

	void updateMotivo(Motivo motivo);
	
	void updateUsuario(Usuario usuario);
	
	void updatePersona(Persona persona);
	
    Usuario usuariosGetById(UUID uuid);
    
    Persona personasGetById(UUID uuid);
   
    
    TipoDocumento tipodocumentosGetById(UUID uuid);

	Motivo motivosGetById(UUID uuid);
	
	List<Persona> personasList(String searchTerm);

	void personasCreate(Persona persona);

	void tipovisitasCreate(TipoVisita tipovisita);

	List<TipoVisita> tipovisitaList();

    List<TipoDocumento> tipodocumentosList(String nombre);

	TipoVisita tipovisitasGetById(UUID uuid);

	TipoDocumento tipodocumentoGetById(UUID uuid);

	void tipodocumentoCreate(TipoDocumento tipodocumento);

	void tipovisitasDelete(UUID uuid);

    void usuariosCreate(Usuario usuario);
	
	void updateTipoDocumento(TipoDocumento tipodocumento);

	void TipoDocumentoDelete(UUID uuid);

	List<Visita> visitasList();

	Usuario usuariosGetByLogin(String usuario, String password);

	boolean usuariosExisteNombre(String nombre);

	boolean tipodocumentosExisteNombre(String getnombre);

	boolean tipovisitasExisteNombre(String nombre);

	boolean personaExisteDocumento(String valordocumento);
}
