package me.tomassetti.sql2omodel;

import me.tomassetti.RandomUuidGenerator;
import me.tomassetti.UuidGenerator;
import me.tomassetti.handlers.EmptyPayload;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
import me.tomassetti.model.Persona;
import me.tomassetti.model.TipoDocumento;
import me.tomassetti.model.TipoVisita;
import me.tomassetti.model.Usuario;
import me.tomassetti.model.Visita;

import org.sql2o.Connection;
import org.sql2o.Sql2o;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
            conn.createQuery("update tipodocumentos set nombre=:nombre where uuid=:tipodocumentos_uuid")
                    .addParameter("tipodocumento_uuid", tipodocumento.getUuid())
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
	public List<Area> areasList(String nombre) {
        try (Connection conn = sql2o.open()) {
        	List<Area> areas = conn.createQuery("select * from areas where :nombre is null or lower(nombre) like '%' || lower(:nombre) || '%'")
        			.addParameter("nombre", nombre)
                    .executeAndFetch(Area.class);
            return areas;
        }
	}
	
	
	@Override
	public List<Usuario> usuariosList(String nombre) {
        try (Connection conn = sql2o.open()) {
        	List<Usuario> usuarios = conn.createQuery("select * from usuarios where :nombre is null or lower(nombre) like '%' || lower(:nombre) || '%'")
        			.addParameter("nombre", nombre)
                    .executeAndFetch(Usuario.class);
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
        	List<Motivo> motivos = conn.createQuery("select uuid,nombre from motivos")
                    .executeAndFetch(Motivo.class);
        	for(Motivo motivo : motivos) {
        		mapAreaForMotivo(motivo, conn);
        	}
            return motivos;
        }
	}


	@Override
	public List<Persona> personasList() {
        try (Connection conn = sql2o.open()) {
        	List<Persona> personas = conn.createQuery("select uuid,nombre  from personas")
                    .executeAndFetch(Persona.class);
        	for(Persona persona : personas) {
        		mapTipoDocumentoForPersona(persona, conn);
        	}
            return personas;
        }
	}
	

	private void mapTipoDocumentoForPersona(Persona persona, Connection conn) {
		TipoDocumento tipodocumento = conn.createQuery("select * from tipodocumentos where uuid=(select tipodocumentoid from personas where uuid=:uuid)")
				.addParameter("uuid", persona.getUuid())
				.executeAndFetch(TipoDocumento.class).get(0);
		persona.setTipoDocumento(tipodocumento);
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
            conn.createQuery("insert into usuarios(uuid, nombre, correo , pass, areaid) VALUES (:uuid, :nombre ,:correo , :pass, :area_uuid)")
                    .addParameter("uuid", uuid)
                    .addParameter("nombre", usuario.getNombre())
                    .addParameter("correo", usuario.getCorreo())
                    .addParameter("pass", usuario.getPass())
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
	public void personasCreate(Persona persona) {
        try (Connection conn = sql2o.open()) {
            UUID uuid = uuidGenerator.generate();
            persona.setUuid(uuid);
            conn.createQuery("insert into personas(uuid, tipodocumentoid, nombre, apellido , correo , telefono) VALUES (:uuid, :tipodocumento_uuid ,:nombre , :apellido, :correo , :telefono")
                    .addParameter("uuid", uuid)
                    .addParameter("tipodocumento", persona.getTipoDocumento())
                    .addParameter("nombre", persona.getNombre())
                    .addParameter("apellido", persona.getApellido())
                    .addParameter("correo", persona.getCorreo())
                    .addParameter("telefono", persona.getTelefono())
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
	public void motivoDelete(UUID uuid) {
        try (Connection conn = sql2o.open()) {
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
	public void tipovisitasDelete(UUID uuid) {
        try (Connection conn = sql2o.open()) {
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
	public TipoDocumento tipodocumentoGetById(UUID uuid) {
        try (Connection conn = sql2o.open()) {
            List<TipoDocumento> tipodocumentos = conn.createQuery("select * from tipodocumento where uuid=:tipodocumento_uuid")
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
	public List<TipoDocumento> tipodocumentosList(String nombre) {
		 try (Connection conn = sql2o.open()) {
	        	List<TipoDocumento> tipodocumentos = conn.createQuery("select * from tipodocumentos where :nombre is null or lower(nombre) like '%' || lower(:nombre) || '%'")
	        			.addParameter("nombre", nombre)
	                    .executeAndFetch(TipoDocumento.class);
		return tipodocumentos;
	}
	
	}}

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


