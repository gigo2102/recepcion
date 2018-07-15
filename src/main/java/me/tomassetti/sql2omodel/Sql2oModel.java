package me.tomassetti.sql2omodel;

import me.tomassetti.RandomUuidGenerator;
import me.tomassetti.UuidGenerator;
import me.tomassetti.handlers.EmptyPayload;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.model.Motivo;
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
	public List<Area> areasList() {
        try (Connection conn = sql2o.open()) {
        	List<Area> areas = conn.createQuery("select * from areas")
                    .executeAndFetch(Area.class);
            return areas;
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
	
	
 /*	@Override
	public void visitasCreate(Visita visita ) {
        try (Connection conn = sql2o.open()) {
            UUID uuid = uuidGenerator.generate();
            visita.setUuid(uuid);
            conn.createQuery("insert into areas(uuid, nombre) VALUES (:uuid, :nombre)")
                    .addParameter("uuid", uuid)
                    .addParameter("nombre", visita.getNombre())
                    .executeUpdate();
        }
	} */
	
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
}

