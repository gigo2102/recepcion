package ar.gcba.cactyt.recepcion.models;

import org.sql2o.Connection;
import org.sql2o.Query;
import org.sql2o.Sql2o;
import ar.gcba.cactyt.common.RandomUuidGenerator;
import ar.gcba.cactyt.common.UuidGenerator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class Sql2oModel implements Model {

    private Sql2o sql2o;
    private UuidGenerator uuidGenerator;

    public Sql2oModel(Sql2o sql2o) {
        this.sql2o = sql2o;
        uuidGenerator = new RandomUuidGenerator();
    }
    
    @Override
    public void updateArea(Area area) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("update areas set nombre=:nombre where uuid=:area_uuid")
                    .addParameter("area_uuid", area.getUuid())
                    .addParameter("nombre", area.getNombre())
                    .executeUpdate();
        }
    }
    
    @Override
    public void updateTipoDocumento(TipoDocumento tipodocumento) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("update tipodocumentos set nombre=:nombre where uuid=:tipodocumentoid")
                    .addParameter("tipodocumentoid", tipodocumento.getUuid())
                    .addParameter("nombre", tipodocumento.getnombre())
                    .executeUpdate();
        }
    }

    
   @Override
    public void updateTipoVisita(TipoVisita tipovisita) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("update tipovisitas set nombre=:nombre where uuid=:tipovisitas_uuid")
                    .addParameter("tipovisitas_uuid", tipovisita.getUuid())
                    .addParameter("nombre", tipovisita.getNombre())
                    .executeUpdate();
        }
    }
  
    @Override
    public void updateMotivo(Motivo motivo) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("update motivos set nombre=:nombre, areaid=:areaid where uuid=:motivo_uuid")
                    .addParameter("motivo_uuid", motivo.getUuid())
                    .addParameter("nombre", motivo.getNombre())
                    .addParameter("areaid", motivo.getArea().getUuid())
                    .executeUpdate();
        }
    }
    
    @Override
    public void updateUsuario(Usuario usuario) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("update usuarios set nombre=:nombre,correo=:correo , pass=:pass, areaid=:areaid, esAdmin=:esAdmin  where uuid=:usuario_uuid")
                    .addParameter("usuario_uuid", usuario.getUuid())
                    .addParameter("nombre", usuario.getNombre())
                    .addParameter("correo", usuario.getCorreo())
                    .addParameter("pass", usuario.getPass())
                    .addParameter("areaid", usuario.getArea().getUuid())
                    .addParameter("esAdmin", usuario.getEsAdmin())
                    .executeUpdate();
        }
    }
    
    @Override
    public void updatePersona(Persona persona) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("update personas set nombre=:nombre,apellido=:apellido ,telefono=:telefono ,correo=:correo,valordocumento=:valordocumento, tipodocumentoid=:tipodocumentoid  where uuid=:persona_uuid")
                    .addParameter("usuario_uuid", persona.getUuid())
                    .addParameter("nombre", persona.getNombre())
                    .addParameter("correo", persona.getCorreo())
                    .addParameter("apellido", persona.getApellido())
                    .addParameter("telefono", persona.getTelefono())
                    .addParameter("valordocumento", persona.getValorDocumento())
                    .addParameter("tipodocumentoid", persona.getTipoDocumento().getUuid())
                    .executeUpdate();
        }
    }



	@Override
	public List<Area> areasList(String nombre) {
        try (Connection conn = sql2o.open()) {
        	List<Area> areas = conn.createQuery("select * from areas where :nombre is null or lower(nombre) like '%' || lower(:nombre) || '%'")
        			.addParameter("nombre", nombre)
                    .executeAndFetch(Area.class);
            return areas;
        }
	}
	

	
	
	
	@Override
	public List<Usuario> usuariosList() {
	       try (Connection conn = sql2o.open()) {
	        	List<Usuario> usuarios = conn.createQuery("select uuid,nombre,correo,pass, esAdmin  from usuarios")
	                    .executeAndFetch(Usuario.class);
	        	for(Usuario usuario : usuarios) {
	        		mapAreaForUsuario(usuario, conn);
	        	}
	            return usuarios;
	        }
		}
	
		
	
	
	@Override
	public List<TipoVisita> tipovisitaList() {
        try (Connection conn = sql2o.open()) {
        	List<TipoVisita> tipovisitas = conn.createQuery("select * from tipovisitas")
                    .executeAndFetch(TipoVisita.class);
            return tipovisitas;
        }
	}
	


	@Override
	public List<Motivo> motivosList() {
        try (Connection conn = sql2o.open()) {
        	List<Motivo> motivos = conn.createQuery("select uuid,nombre from motivos order by areaid,nombre")
                    .executeAndFetch(Motivo.class);
        	for(Motivo motivo : motivos) {
        		mapAreaForMotivo(motivo, conn);
        	}
            return motivos;
        }
	}
	
	@Override
	public List<Visita> visitasList(String searchTerm, Boolean fueAtendido, UUID areaUuid) {
        try (Connection conn = sql2o.open()) {
        	List<Visita> visitas = conn.createQuery("select uuid,observaciones from visitas where (:fueAtendido is null or fueAtendido=:fueAtendido) and (:searchTerm is null or :searchTerm is not null) and (:areaUuid is null or areaid=:areaUuid)")
                	.addParameter("searchTerm", searchTerm)
                	.addParameter("fueAtendido", fueAtendido)
                	.addParameter("areaUuid", areaUuid)
            		.executeAndFetch(Visita.class);
        	for(Visita visita : visitas) {
        		mapPersonaForVisita(visita, conn);
        		mapAreaForVisita(visita , conn);
        		mapMotivoForVisita(visita , conn);
        		mapTipoVisitaForVisita(visita , conn);
        	}
            return visitas;
        }
	}

	private void mapPersonaForVisita(Visita visita, Connection conn) {
		Persona persona = conn.createQuery("select uuid,valorDocumento,nombre,apellido,correo,telefono from personas where uuid=(select personaid from visitas where uuid=:uuid)")
				.addParameter("uuid", visita.getUuid())
				.executeAndFetch(Persona.class).get(0);
		visita.setPersona(persona);
	}

	private void mapAreaForVisita(Visita visita, Connection conn) {
		Area area = conn.createQuery("select * from areas where uuid=(select areaid from visitas where uuid=:uuid)")
				.addParameter("uuid", visita.getUuid())
				.executeAndFetch(Area.class).get(0);
		visita.setArea(area);
	}
	
	private void mapTipoVisitaForVisita(Visita visita, Connection conn) {
		TipoVisita tipovisita = conn.createQuery("select * from tipovisitas where uuid=(select tipovisitaid from visitas where uuid=:uuid)")
				.addParameter("uuid", visita.getUuid())
				.executeAndFetch(TipoVisita.class).get(0);
		visita.setTipovisita(tipovisita);
	}
	
	private void mapMotivoForVisita(Visita visita, Connection conn) {
		Motivo motivo = conn.createQuery("select uuid,nombre from motivos where uuid=(select motivoid from visitas where uuid=:uuid)")
				.addParameter("uuid", visita.getUuid())
				.executeAndFetch(Motivo.class).get(0);
		visita.setMotivo(motivo);
	}

	@Override
	public List<Persona> personasList(String searchTerm) {
        try (Connection conn = sql2o.open()) {
        	List<Persona> personas = conn.createQuery("select uuid,nombre,apellido,correo,telefono,valordocumento from personas where (:searchTerm is null or lower(nombre) like '%' || lower(:searchTerm) || '%' or lower(apellido) like '%' || lower(:searchTerm) || '%' or lower(valorDocumento) like '%' || lower(:searchTerm) || '%')")
        			.addParameter("searchTerm", searchTerm)
        			.executeAndFetch(Persona.class);
        	for(Persona persona : personas) {
        		mapTipoDocumentoForPersona(persona, conn);
        	}
            return personas;
        }
	}
	

	
 /*	@Override
	public List<Visita> visitasList() {
        try (Connection conn = sql2o.open()) {
        	List<Visita> visitas = conn.createQuery("select uuid,persona,area,tipodocumento,valordocumento from personas")
                    .executeAndFetch(Visita.class);
        	for(Visita visita : visitas) {
        		mapTipoDocumentoForPersona(visita, conn);
        	}
            return visitas;
        }
	}*/
	

	private void mapTipoDocumentoForPersona(Persona persona, Connection conn) {
		TipoDocumento tipodocumento = conn.createQuery("select * from tipodocumentos where uuid=(select tipodocumentoid from personas where uuid=:uuid)")
				.addParameter("uuid", persona.getUuid())
				.executeAndFetch(TipoDocumento.class).get(0);
		persona.setTipoDocumento(tipodocumento);
	}
	
	private void mapAreaForUsuario(Usuario usuario, Connection conn) {
		Area area = conn.createQuery("select * from areas where uuid=(select areaid from usuarios where uuid=:uuid)")
				.addParameter("uuid", usuario.getUuid())
				.executeAndFetch(Area.class).get(0);
		usuario.setArea(area);
	}

	
	private void mapAreaForMotivo(Motivo motivo, Connection conn) {
		Area area = conn.createQuery("select * from areas where uuid=(select areaid from motivos where uuid=:uuid)")
				.addParameter("uuid", motivo.getUuid())
				.executeAndFetch(Area.class).get(0);
		motivo.setArea(area);
	}

	@Override
	public void areasCreate(Area area) {
        try (Connection conn = sql2o.open()) {
            UUID uuid = uuidGenerator.generate();
            area.setUuid(uuid);
            conn.createQuery("insert into areas(uuid, nombre) VALUES (:uuid, :nombre)")
                    .addParameter("uuid", uuid)
                    .addParameter("nombre", area.getNombre())
                    .executeUpdate();
        }
	}
	
	@Override
	public void usuariosCreate(Usuario usuario) {
        try (Connection conn = sql2o.open()) {
            UUID uuid = uuidGenerator.generate();
            usuario.setUuid(uuid);
            conn.createQuery("insert into usuarios(uuid, nombre, correo , pass, areaid,esAdmin) VALUES (:uuid, :nombre ,:correo , :pass, :areaid , :esAdmin)")
                    .addParameter("uuid", uuid)
                    .addParameter("nombre", usuario.getNombre())
                    .addParameter("correo", usuario.getCorreo())
                    .addParameter("pass", usuario.getPass())
                    .addParameter("esAdmin", usuario.getEsAdmin())
                    .addParameter("areaid", usuario.getArea().getUuid())
                    .executeUpdate();
        }
	}
	
	
	@Override
	public void tipovisitasCreate(TipoVisita tipovisita) {
        try (Connection conn = sql2o.open()) {
            UUID uuid = uuidGenerator.generate();
            tipovisita.setUuid(uuid);
            conn.createQuery("insert into tipovisitas(uuid, nombre) VALUES (:uuid, :nombre)")
                    .addParameter("uuid", uuid)
                    .addParameter("nombre", tipovisita.getNombre())
                    .executeUpdate();
        }
	}
	
	@Override
	public void tipodocumentoCreate(TipoDocumento tipodocumento) {
        try (Connection conn = sql2o.open()) {
            UUID uuid = uuidGenerator.generate();
            tipodocumento.setUuid(uuid);
            conn.createQuery("insert into tipodocumentos(uuid, nombre) VALUES (:uuid, :nombre)")
                    .addParameter("uuid", uuid)
                    .addParameter("nombre", tipodocumento.getnombre())
                    .executeUpdate();
        }
	}
	
	@Override
	public void empleadosUpdater(Empleados empleado) {
        try (Connection conn = sql2o.open()) {
            UUID uuid = uuidGenerator.generate();
            empleado.setUuid(uuid);
            conn.createQuery("insert into personas(uuid, nombre, apellido, cuil, tipodocumentoid, valorDocumento, correo, telefono,celular, ht, esempleado) VALUES (:uuid, :nombre , :apellido ,:cuil , (select uuid from public.tipodocumentos where lower(nombre)='dni'), :valorDocumento, :correo , :telefono , :celular , :ht, true)")
                    .addParameter("uuid", uuid)
                    .addParameter("nombre", empleado.getNombre())
                    .addParameter("apellido", empleado.getApellido())
                    .addParameter("cuil", empleado.getCuil())
                    .addParameter("valorDocumento", empleado.getDni())
                    .addParameter("correo", empleado.getCorreo())
                    .addParameter("telefono", empleado.getTelparticular())
                    .addParameter("celular", empleado.getCelular())
                    .addParameter("ht", empleado.getHt())
                    .executeUpdate();
        }
	}
	
	@Override
	public void personasCreate(Persona persona) {
        try (Connection conn = sql2o.open()) {
            UUID uuid = uuidGenerator.generate();
            persona.setUuid(uuid);
            conn.createQuery("insert into personas(uuid, tipodocumentoid, valordocumento, nombre, apellido , correo , telefono) VALUES (:uuid, :tipodocumentoid , :valordocumento ,:nombre , :apellido, :correo , :telefono)")
                    .addParameter("uuid", uuid)
                    .addParameter("tipodocumentoid", persona.getTipoDocumento().getUuid())
                    .addParameter("valordocumento", persona.getValorDocumento())
                    .addParameter("nombre", persona.getNombre())
                    .addParameter("apellido", persona.getApellido())
                    .addParameter("correo", persona.getCorreo())
                    .addParameter("telefono", persona.getTelefono())
                    .executeUpdate();
        }
	}
	
	
	

	@Override
	public void visitasCreate(Visita visita) {
        try (Connection conn = sql2o.open()) {
            UUID uuid = uuidGenerator.generate();
            visita.setUuid(uuid);
            conn.createQuery("insert into visitas(uuid, tipovisitaid, areaid, motivoid, personaid , observaciones) VALUES (:uuid, :tipovisitaid , :areaid ,:motivoid , :personaid, :observaciones )")
                    .addParameter("uuid", uuid)
                    .addParameter("tipovisitaid", visita.getTipovisita().getUuid())
                    .addParameter("areaid", visita.getArea().getUuid())
                    .addParameter("motivoid", visita.getMotivo().getUuid())
                    .addParameter("personaid", visita.getPersona().getUuid())
                    .addParameter("observaciones", visita.getObservaciones())
                    .executeUpdate();
        }
	}
	
	
	
	@Override
	public void motivosCreate(Motivo motivo) {
        try (Connection conn = sql2o.open()) {
            UUID uuid = uuidGenerator.generate();
            motivo.setUuid(uuid);
            conn.createQuery("insert into motivos(uuid, nombre, areaid) VALUES (:uuid, :nombre, :areaid)")
                    .addParameter("uuid", uuid)
                    .addParameter("nombre", motivo.getNombre())
                    .addParameter("areaid", motivo.getArea().getUuid())
                    .executeUpdate();
        }
	}

	@Override
	public boolean areasExisteNombre(String nombre) {
        try (Connection conn = sql2o.open()) {
            Long cant = (Long)conn.createQuery("select count(*) from areas where nombre like :nombre")
                    .addParameter("nombre", nombre)
                    .executeScalar();
            return cant > 0;
        }
	}
	
	@Override
	public boolean motivosExisteNombre(String nombre) {
        try (Connection conn = sql2o.open()) {
            Long cant = (Long)conn.createQuery("select count(*) from motivos where nombre like :nombre")
                    .addParameter("nombre", nombre)
                    .executeScalar();
            return cant > 0;
        }
	}
	
	@Override
	public boolean personaExisteDocumento(String valordocumento) {
        try (Connection conn = sql2o.open()) {
            Long cant = (Long)conn.createQuery("select count(*) from personas where valordocumento like :valordocumento")
                    .addParameter("valordocumento", valordocumento)
                    .executeScalar();
            return cant > 0;
        }
	}
	

	
	@Override
	public boolean tipovisitasExisteNombre(String nombre) {
        try (Connection conn = sql2o.open()) {
            Long cant = (Long)conn.createQuery("select count(*) from tipovisitas where nombre like :nombre")
                    .addParameter("nombre", nombre)
                    .executeScalar();
            return cant > 0;
        }
	}
	
	@Override
	public boolean tipodocumentosExisteNombre(String nombre) {
        try (Connection conn = sql2o.open()) {
            Long cant = (Long)conn.createQuery("select count(*) from motivos where nombre like :nombre")
                    .addParameter("nombre", nombre)
                    .executeScalar();
            return cant > 0;
        }
	}
	
	@Override
	public boolean usuariosExisteNombre(String nombre) {
        try (Connection conn = sql2o.open()) {
            Long cant = (Long)conn.createQuery("select count(*) from usuarios where nombre like :nombre")
                    .addParameter("nombre", nombre)
                    .executeScalar();
            return cant > 0;
        }
	}
	
	
	@Override
	public void motivoDelete(UUID uuid) {
        try (Connection conn = sql2o.open()) {
        	List<UUID> areasIds = conn
            .createQuery("select uuid from areas where areaid=:area_uuid")
            .addParameter("area_uuid", uuid)
            .executeAndFetch(UUID.class);
            if (!areasIds.isEmpty()) {
            	throw new RuntimeException("No se puede borrar el motivo porque es usado por " + areasIds.size() + " areas");
            }
            
    	conn.createQuery("delete from motivos where uuid=:motivo_uuid")
        .addParameter("motivo_uuid", uuid)
        .executeUpdate();
	}
	}
	
				
	@Override
	public void areaDelete(UUID uuid) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("delete from areas where uuid=:area_uuid")
                    .addParameter("area_uuid", uuid)
                    .executeUpdate();
        }
	}
	
	@Override
	public void usuarioDelete(UUID uuid) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("delete from usuarios where uuid=:usuario_uuid")
                    .addParameter("usuario_uuid", uuid)
                    .executeUpdate();
        }
	}
	
	@Override
	public void personaDelete(UUID uuid) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("delete from personas where uuid=:persona_uuid")
                    .addParameter("persona_uuid", uuid)
                    .executeUpdate();
        }
	}
	
	@Override
	public void TipoDocumentoDelete(UUID uuid) {
        try (Connection conn = sql2o.open()) {
        	List<UUID> personasIds = conn
    			.createQuery("select uuid from personas where tipodocumentoid=:tipodocumento_uuid")
            	.addParameter("tipodocumento_uuid", uuid)
            	.executeAndFetch(UUID.class);
            if (!personasIds.isEmpty()) {
            	throw new RuntimeException("No se puede borrar el tipo de documento porque es usado por " + personasIds.size() + " personas");
            }
        	
        	conn.createQuery("delete from tipodocumentos where uuid=:tipodocumento_uuid")
                    .addParameter("tipodocumento_uuid", uuid)
                    .executeUpdate();
        }
	}
	
	@Override
	public void tipovisitasDelete(UUID uuid) {
        try (Connection conn = sql2o.open()) {
        	List<UUID> visitasIds = conn
              .createQuery("select uuid from visitas where visitaid=:visita_uuid")
              .addParameter("visita_uuid", uuid)
              .executeAndFetch(UUID.class);
            if (!visitasIds.isEmpty()) {
              	throw new RuntimeException("No se puede borrar el tipo de visita porque es usado por " + visitasIds.size() + " areas");
            }
            conn.createQuery("delete from tipovisitas where uuid=:tipovisitas_uuid")
                    .addParameter("tipovisitas_uuid", uuid)
                    .executeUpdate();
        }
	}
	
	
        
	
	
	@Override
	public Area areasGetById(UUID uuid) {
        try (Connection conn = sql2o.open()) {
            List<Area> areas = conn.createQuery("select * from areas where uuid=:area_uuid")
                    .addParameter("area_uuid", uuid)
                    .executeAndFetch(Area.class);
            if (areas.size() == 1) {
                return areas.get(0);
            } else {
                throw new RuntimeException();
            }
        }
	}
	
	@Override
	public TipoDocumento tipodocumentosGetById(UUID uuid) {
        try (Connection conn = sql2o.open()) {
            List<TipoDocumento> tipodocumentos = conn.createQuery("select * from tipodocumentos where uuid=:tipodocumento_uuid")
                    .addParameter("tipodocumento_uuid", uuid)
                    .executeAndFetch(TipoDocumento.class);
            if (tipodocumentos.size() == 1) {
                return tipodocumentos.get(0);
            } else {
                throw new RuntimeException();
            }
        }
	}
	
	@Override
	public TipoDocumento tipodocumentoGetById(UUID uuid) {
        try (Connection conn = sql2o.open()) {
            List<TipoDocumento> tipodocumentos = conn.createQuery("select * from tipodocumentos where uuid=:tipodocumento_uuid")
                    .addParameter("tipodocumento_uuid", uuid)
                    .executeAndFetch(TipoDocumento.class);
            if (tipodocumentos.size() == 1) {
                return tipodocumentos.get(0);
            } else {
                throw new RuntimeException();
            }
        }
	}

	@Override
	public TipoVisita tipovisitasGetById(UUID uuid) {
        try (Connection conn = sql2o.open()) {
            List<TipoVisita> tipovisitas = conn.createQuery("select * from tipovisitas where uuid=:tipovisitas_uuid")
                    .addParameter("tipovisitas_uuid", uuid)
                    .executeAndFetch(TipoVisita.class);
            if (tipovisitas.size() == 1) {
                return tipovisitas.get(0);
            } else {
                throw new RuntimeException();
            }
        }
	}
	
	
	
	@Override
	public Motivo motivosGetById(UUID uuid) {
        try (Connection conn = sql2o.open()) {
            List<Motivo> motivos = conn.createQuery("select uuid,nombre from motivos where uuid=:motivo_uuid")
                    .addParameter("motivo_uuid", uuid)
                    .executeAndFetch(Motivo.class);
            if (motivos.size() == 1) {
        		Motivo motivo = motivos.get(0);
            	mapAreaForMotivo(motivo, conn);
                return motivo;
            } else {
                throw new RuntimeException();
            }
        }
	}
	
	@Override
	public Usuario usuariosGetById(UUID uuid) {
        try (Connection conn = sql2o.open()) {
            List<Usuario> usuarios = conn.createQuery("select uuid,nombre, correo, pass , esAdmin from usuarios where uuid=:usuario_uuid")
                    .addParameter("usuario_uuid", uuid)
                    .executeAndFetch(Usuario.class);
            if (usuarios.size() == 1) {
        		Usuario usuario = usuarios.get(0);
            	mapAreaForUsuario(usuario, conn);
                return usuario;
            } else {
                throw new RuntimeException();
            }
        }
	}
	
	@Override
	public Persona personasGetById(UUID uuid) {
        try (Connection conn = sql2o.open()) {
            List<Persona> personas = conn.createQuery("select uuid,nombre,apellido, correo, telefono, valordocumento from personas where uuid=:personaid")
                    .addParameter("personaid", uuid)
                    .executeAndFetch(Persona.class);
            if (personas.size() == 1) {
        		Persona persona = personas.get(0);
            	mapTipoDocumentoForPersona(persona, conn);
                return persona;
            } else {
                throw new RuntimeException();
            }
        }
	}

	@Override
	public List<TipoDocumento> tipodocumentosList(String nombre) {
		 try (Connection conn = sql2o.open()) {
	        	List<TipoDocumento> tipodocumentos = conn.createQuery("select * from tipodocumentos where :nombre is null or lower(nombre) like '%' || lower(:nombre) || '%'")
	        			.addParameter("nombre", nombre)
	                    .executeAndFetch(TipoDocumento.class);
		return tipodocumentos;
	}
	
	}

	@Override
	public Usuario usuariosGetByLogin(String nombre, String password) {
        try (Connection conn = sql2o.open()) {
            List<Usuario> usuarios = conn.createQuery("select uuid,nombre, correo, pass, esAdmin from usuarios where nombre=:usuario_nombre and pass=:usuario_password")
                    .addParameter("usuario_nombre", nombre)
                    .addParameter("usuario_password", password)
                    .executeAndFetch(Usuario.class);
            if (usuarios.size() == 1) {
        		Usuario usuario = usuarios.get(0);
            	mapAreaForUsuario(usuario, conn);
                return usuario;
            } else if (usuarios.size() == 0) {
        		return null;
            } else {
                throw new RuntimeException();
            }
        }
	}

	@Override
	public Map<String, Object> kendoSearch(Class<?> clazz, String subQuery, Map<String, String> params) {
        try (Connection conn = sql2o.open()) {
        	String q = "select * from (" + subQuery + ") as x ";
        	if (params.containsKey("filterField")) {
    			q = q + " where lower(cast(" + params.get("filterField") + " as varchar)) like '%' || lower(:" + params.get("filterField") + ") || '%'";
        	}
        	
        	Query builderTotal = conn.createQuery("select count(1) from (" + q + ") as x2");
        	if (params.containsKey("filterField")) {
        		builderTotal = builderTotal.addParameter(params.get("filterField"), params.get("filterValue"));
        	}
        	long total = (long) builderTotal.executeScalar();
        	
        	if (params.containsKey("sortField")) {
    			q = q + " order by " + params.get("sortField") + " " + params.get("sortDir");
        	}
        	q = q + " limit " + params.get("take");
        	q = q + " offset " + params.get("skip");
        	
        	Query builderData = conn.createQuery(q);
        	if (params.containsKey("filterField")) {
        		builderData = builderData.addParameter(params.get("filterField"), params.get("filterValue"));
        	}
            List<?> l = builderData.executeAndFetch(clazz);
            
            Map<String, Object> result = new HashMap<String, Object>();
            result.put("total", total);
            result.put("data", l);
            return result;
        }
	}

	@Override
	public void visitaAtender(UUID uuid) {
        try (Connection conn = sql2o.open()) {
            conn.createQuery("update visitas set fueAtendido=true where uuid=:uuid")
                    .addParameter("uuid", uuid)
                    .executeUpdate();
        }
	}
}


	

	/*
	@Override
	public List<Motivo> motivoList() {
        try (Connection conn = sql2o.open()) {
        	List<Motivo> motivos = conn.createQuery("select * from motivos")
                    .executeAndFetch(Motivo.class);
        	
            return motivos;
        }
	}

	@Override
	public void motivosCreate(Motivo motivo) {
        try (Connection conn = sql2o.open()) {
            UUID uuid = uuidGenerator.generate();
            motivo.setUuid(uuid);
            conn.createQuery("insert into motivos(uuid, nombre) VALUES (:uuid, :nombre)")
                    .addParameter("uuid", uuid)
                    .addParameter("nombre", motivo.getNombre())
                    .executeUpdate();
	}
    */


