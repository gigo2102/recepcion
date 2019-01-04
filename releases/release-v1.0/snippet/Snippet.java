package snippet;

public class Snippet {
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
}

