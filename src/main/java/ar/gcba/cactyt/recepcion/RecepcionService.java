package ar.gcba.cactyt.recepcion;
 
import com.beust.jcommander.JCommander;

import ar.gcba.cactyt.common.CommandLineOptions;
import ar.gcba.cactyt.common.RouteConfigurator;
import ar.gcba.cactyt.recepcion.handlers.*;
import ar.gcba.cactyt.recepcion.models.Model;
import ar.gcba.cactyt.recepcion.models.Sql2oModel;
import freemarker.template.Configuration;

import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerEngine;

import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Logger;
import static spark.Spark.get;
import static spark.Spark.port;

public class RecepcionService 
{

    private static final Logger logger = Logger.getLogger(RecepcionService.class.getCanonicalName());
	public static final String PathWebLogin = "/login";

    public static void main( String[] args) {
    	//creamos el objeto que contiene las configuraciones por defecto
        CommandLineOptions options = new CommandLineOptions();
        //pisamos las configuraciones con lo que nos hayan pasado por linea de comandos
        new JCommander(options, args);
        
        //escribimos en el log los valores de como quedaron las variables de configuracion
        logger.finest("Options.debug = " + options.debug);
        logger.finest("Options.database = " + options.database);
        logger.finest("Options.dbHost = " + options.dbHost);
        logger.finest("Options.dbUsername = " + options.dbUsername);
        logger.finest("Options.dbPort = " + options.dbPort);
        logger.finest("Options.servicePort = " + options.servicePort);

        //iniciamos el servidor web
        port(options.servicePort);

        //configuramos el conector a base de datos
        Sql2o sql2o = new Sql2o("jdbc:postgresql://" + options.dbHost + ":" + options.dbPort + "/" + options.database,
                options.dbUsername, options.dbPassword, new PostgresQuirks() {
            {
                // make sure we use default UUID converter.
                converters.put(UUID.class, new UUIDConverter());
            }
        });

        //iniciamos el objeto principal de acceso a datos para nuestro sistema 
        Model sql2o_model = new Sql2oModel(sql2o);
        
        //configuramos el motor de plantillas/vistas html
        FreeMarkerEngine freeMarkerEngine = new FreeMarkerEngine();
        Configuration freeMarkerConfiguration = new Configuration();
        //freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(RecepcionService.class, "/"));
        try {
			String dirname = Paths.get(".").toAbsolutePath().normalize().toString() + "/src/main/resources";
			File dir = new File(dirname);
			freeMarkerConfiguration.setDirectoryForTemplateLoading(dir);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
        
        //configuramos la ruta para chequeo de salud
        get("/alive", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "ok";
            }
        });
        
        RouteConfigurator routes = new RouteConfigurator(sql2o_model, freeMarkerEngine, PathWebLogin);
        
        //INGRESO
        routes.get("/login", LoginGetFormHandler.class, false);
        routes.post("/login", LoginHandler.class, false);
        routes.get("/logout", LogoutHandler.class, false);
        routes.get("/errors", LogoutHandler.class, false);
        
        //crear VISITA
        routes.get("/visitas/create_form", VisitaCreateGetFormHandler.class);
        routes.post("/visitas", VisitaCreateHandler.class);
        routes.get("/visitas/ws_pendientes", VisitasPendientesWsHandler.class);
        routes.get("/visitas/atender", VisitasPendientesAtenderHandler.class);
        
       
        //listado
        routes.get("/visitas", VisitasSearchKendoHandler .class);
        routes.get("/visitas/kendo_search", VisitasSearchKendoFiltersHandler .class);

        
      //configuramos las rutas de USUARIOS
        //listado
        routes.get("/usuarios", UsuarioSearchHandler .class);
        
        //crear
        routes.get("/usuarios/create_form", UsuarioCreateGetFormHandler.class);
        routes.post("/usuarios", UsuarioCreateHandler.class);
        
        routes.get("/usuarios/:uuid/edit_form", UsuarioEditGetFormHandler.class);
        routes.post("/usuarios/:uuid/", UsuarioEditHandler.class);
        
        //eliminar
        routes.get("/usuarios/:uuid/delete", UsuariosDeleteHandler.class);
     

        //configuramos las rutas de PERSONA
        //listado
        routes.get("/personas", PersonasSearchKendoHandler .class);
        routes.get("/personas/kendo_search", PersonasSearchKendoFiltersHandler .class);
     
        //crear
        routes.get("/personas/create_form", PersonaCreateGetFormHandler.class);
        routes.post("/personas", PersonasCreateHandler.class);
        
        routes.get("/personas/:uuid/edit_form", PersonaEditGetFormHandler.class);
        routes.post("/personas/:uuid/", PersonaEditHandler.class); 
        
        
        //eliminar
       routes.get("/personas/:uuid/delete", PersonasDeleteHandler.class);
               
       //buscar
       routes.get("/personas/search", PersonasSearchHandler.class);
       
        
        //configuramos las rutas de TIPOS DE VISITAS
        //listado
        routes.get("/tipovisitas", TipoVisitasSearchHandler .class);
     
        //crear
        routes.get("/tipovisitas/create_form", TipoVisitaCreateGetFormHandler.class);
        routes.post("/tipovisitas", TipoVisitaCreateHandler.class);
        
        routes.get("/tipovisitas/:uuid/edit_form", TipoVisitaEditGetFormHandler.class);
        routes.post("/tipovisitas/:uuid/", TipoVisitaEditHandler.class); 
        
        
        
        //eliminar
       routes.get("/tipovisitas/:uuid/delete", TipoVisitasDeleteHandler.class);
        
       //configuramos las rutas de TIPO DE DOCUMENTO
       //listado
        routes.get("/tipodocumentos", TipoDocumentosSearchHandler.class);
    
       //crear
       routes.get("/tipodocumentos/create_form", TipoDocumentoCreateGetFormHandler.class);
       routes.post("/tipodocumentos", TipoDocumentoCreateHandler.class);
       
       routes.get("/tipodocumentos/:uuid/edit_form", TipoDocumentoEditGetFormHandler.class);
       routes.post("/tipodocumentos/:uuid/", TipoDocumentoEditHandler.class);
       
       routes.get("/tipodocumentos/:uuid/delete", TipoDocumentoDeleteHandler.class);
    
        //configuramos las rutas de AREAS
        //listado
        routes.get("/areas", AreasSearchHandler.class);
     
        //crear
        routes.get("/areas/create_form", AreaCreateGetFormHandler.class);
        routes.post("/areas", AreaCreateHandler.class);
        
        //eliminar
        routes.get("/areas/:uuid/delete", AreasDeleteHandler.class);
        
        //editar
        routes.get("/areas/:uuid/edit_form", AreaEditGetFormHandler.class);
        routes.post("/areas/:uuid/", AreaEditHandler.class); //AreaUpdatePayload
        /*
        //ver detalle
        routes.get("/areas/:uuid", null);
      
       */
        //configuramos las rutas de MOTIVOS
        //listado
        routes.get("/motivos", MotivosSearchHandler.class);
        
        //crear
        routes.get("/motivos/create_form", MotivoCreateGetFormHandler.class);
        routes.post("/motivos", MotivoCreateHandler.class);
        
        //eliminar
        routes.get("/motivos/:uuid/delete", MotivoDeleteHandler.class);
        
        //editar
        routes.get("/motivos/:uuid/edit_form", MotivoEditGetFormHandler.class);
        routes.post("/motivos/:uuid/", MotivoEditHandler.class); //AreaUpdatePayload
        
        //configuramos las rutas de MOTIVOS
        //listado
       
        routes.get("/personas/empleados/importacion", EmpleadosImportacionHandler.class);
        routes.post("/personas/empleados/importacion/upload", EmpleadosImportacionUploadHandler.class); //AreaUpdatePayload

        
        
        
         }

}
