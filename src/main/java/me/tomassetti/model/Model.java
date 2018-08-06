package me.tomassetti.model;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import me.tomassetti.handlers.EditAreaPayload;
import me.tomassetti.handlers.EditMotivoPayload;
import me.tomassetti.handlers.EditTipoDocumentoPayload;
import me.tomassetti.handlers.EditTipoVisitaPayload;
import me.tomassetti.handlers.EmptyPayload;
import me.tomassetti.handlers.NewAreaPayload;
import me.tomassetti.handlers.NewTipoDocumentoPayload;


public interface Model {
   /* UUID createPost(String title, String content, List<String> categories);
    UUID createComment(UUID post, String author, String content);
    List<Post> getAllPosts();
    List<Comment> getAllCommentsOn(UUID post);
    boolean existPost(UUID post);

 	Optional<Area> getArea(UUID uuid);

    void updatePost(Post post);
*/

    void areaDelete(UUID uuid);
    
	List<Area> areasList(String nombre);
	
	void areasCreate(Area area);
	
	boolean areasExisteNombre(String nombre);
	
    void updateArea(Area area);
    
    void updateTipoVisita(TipoVisita tipovisita);

	Area areasGetById(UUID uuid);
    
	List<Motivo> motivosList();

	void motivosCreate(Motivo motivo);

	void motivoDelete(UUID uuid);

	void updateMotivo(Motivo motivo);

	Motivo motivosGetById(UUID uuid);
	
	List<Persona> personasList();

	void personasCreate(Persona persona);

	void tipovisitasCreate(TipoVisita tipovisita);

	List<TipoVisita> tipovisitaList();

    List<TipoDocumento> tipodocumentosList(String nombre);

	TipoVisita tipovisitasGetById(UUID uuid);

	TipoDocumento tipodocumentoGetById(UUID tipoDocumentoId);

	void tipodocumentoCreate(TipoDocumento tipodocumento);

	void tipovisitasDelete(UUID uuid);

	Object usuariosList(String nombre);

	void usuariosCreate(Usuario usuario);
	
	void updateTipoDocumento(TipoDocumento tipodocumento);

	

	


	



}
