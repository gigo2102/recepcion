package me.tomassetti;
 
import com.beust.jcommander.JCommander;
import freemarker.cache.ClassTemplateLoader;
import freemarker.template.Configuration;
import me.tomassetti.handlers.*;
import me.tomassetti.model.Area;
import me.tomassetti.model.Model;
import me.tomassetti.sql2omodel.Sql2oModel;
import org.sql2o.Sql2o;
import org.sql2o.converters.UUIDConverter;
import org.sql2o.quirks.PostgresQuirks;

import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;
import java.util.logging.Logger;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;
import static spark.Spark.delete;
import static spark.Spark.port;

public class RecepcionService 
{

    private static final Logger logger = Logger.getLogger(RecepcionService.class.getCanonicalName());

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
        freeMarkerConfiguration.setTemplateLoader(new ClassTemplateLoader(RecepcionService.class, "/"));
        freeMarkerEngine.setConfiguration(freeMarkerConfiguration);
        
        //configuramos la ruta para chequeo de salud
        get("/alive", new Route() {
            @Override
            public Object handle(Request request, Response response) throws Exception {
                return "ok";
            }
        });
        
        //crear VISITA
        get("/visitas/create_form", new VisitaCreateGetFormHandler(sql2o_model, freeMarkerEngine));
        post("/visitas", new VisitaCreateHandler(sql2o_model, freeMarkerEngine));

        
      //configuramos las rutas de USUARIOS
        //listado
        get("/usuarios", new UsuarioSearchHandler (sql2o_model, freeMarkerEngine));
        
        //crear
        get("/usuarios/create_form", new UsuarioCreateGetFormHandler(sql2o_model, freeMarkerEngine));
        post("/usuarios", new UsuarioCreateHandler(sql2o_model, freeMarkerEngine));
        
        get("/usuarios/:uuid/edit_form", new UsuarioEditGetFormHandler(sql2o_model, freeMarkerEngine));
        post("/usuarios/:uuid/", new UsuarioEditHandler(sql2o_model, freeMarkerEngine));
        
        //eliminar
        get("/usuarios/:uuid/delete", new UsuariosDeleteHandler(sql2o_model, freeMarkerEngine));
     

        //configuramos las rutas de PERSONA
        //listado
         get("/personas", new PersonasSearchHandler (sql2o_model, freeMarkerEngine));
     
        //crear
        get("/personas/create_form", new PersonaCreateGetFormHandler(sql2o_model, freeMarkerEngine));
        post("/personas", new PersonasCreateHandler(sql2o_model, freeMarkerEngine));
        
        get("/personas/:uuid/edit_form", new PersonaEditGetFormHandler(sql2o_model, freeMarkerEngine));
        post("/personas/:uuid/", new PersonaEditHandler(sql2o_model, freeMarkerEngine)); 
        
        
        //eliminar
       get("/personas/:uuid/delete", new PersonasDeleteHandler(sql2o_model, freeMarkerEngine));
               
       //buscar
       get("/personas/search", new PersonasSearchHandler(sql2o_model, freeMarkerEngine));
       
        
        //configuramos las rutas de TIPOS DE VISITAS
        //listado
        get("/tipovisitas", new TipoVisitasSearchHandler (sql2o_model, freeMarkerEngine));
     
        //crear
        get("/tipovisitas/create_form", new TipoVisitaCreateGetFormHandler(sql2o_model, freeMarkerEngine));
        post("/tipovisitas", new TipoVisitaCreateHandler(sql2o_model, freeMarkerEngine));
        
        get("/tipovisitas/:uuid/edit_form", new TipoVisitaEditGetFormHandler(sql2o_model, freeMarkerEngine));
        post("/tipovisitas/:uuid/", new TipoVisitaEditHandler(sql2o_model, freeMarkerEngine)); 
        
        
        
        //eliminar
       get("/tipovisitas/:uuid/delete", new TipoVisitasDeleteHandler(sql2o_model, freeMarkerEngine));
        
       //configuramos las rutas de TIPO DE DOCUMENTO
       //listado
        get("/tipodocumentos", new TipoDocumentosSearchHandler(sql2o_model, freeMarkerEngine));
    
       //crear
       get("/tipodocumentos/create_form", new TipoDocumentoCreateGetFormHandler(sql2o_model, freeMarkerEngine));
       post("/tipodocumentos", new TipoDocumentoCreateHandler(sql2o_model, freeMarkerEngine));
       
       get("/tipodocumentos/:uuid/edit_form", new TipoDocumentoEditGetFormHandler(sql2o_model, freeMarkerEngine));
       post("/tipodocumentos/:uuid/", new TipoDocumentoEditHandler(sql2o_model, freeMarkerEngine));
       
       get("/tipodocumentos/:uuid/delete", new TipoDocumentoDeleteHandler(sql2o_model, freeMarkerEngine));
    
        //configuramos las rutas de AREAS
        //listado
        get("/areas", new AreasSearchHandler(sql2o_model, freeMarkerEngine));
     
        //crear
        get("/areas/create_form", new AreaCreateGetFormHandler(sql2o_model, freeMarkerEngine));
        post("/areas", new AreaCreateHandler(sql2o_model, freeMarkerEngine));
        
        //eliminar
        get("/areas/:uuid/delete", new AreasDeleteHandler(sql2o_model, freeMarkerEngine));
        
        //editar
        get("/areas/:uuid/edit_form", new AreaEditGetFormHandler(sql2o_model, freeMarkerEngine));
        post("/areas/:uuid/", new AreaEditHandler(sql2o_model, freeMarkerEngine)); //AreaUpdatePayload
        /*
        //ver detalle
        get("/areas/:uuid", null);
      
       */
        //configuramos las rutas de MOTIVOS
        //listado
        get("/motivos", new MotivosSearchHandler(sql2o_model, freeMarkerEngine));
        
        //crear
        get("/motivos/create_form", new MotivoCreateGetFormHandler(sql2o_model, freeMarkerEngine));
        post("/motivos", new MotivoCreateHandler(sql2o_model, freeMarkerEngine));
        
        //eliminar
        get("/motivos/:uuid/delete", new MotivoDeleteHandler(sql2o_model, freeMarkerEngine));
        
        //editar
        get("/motivos/:uuid/edit_form", new MotivoEditGetFormHandler(sql2o_model, freeMarkerEngine));
        post("/motivos/:uuid/", new MotivoEditHandler(sql2o_model, freeMarkerEngine)); //AreaUpdatePayload
        

        
        
        
         }

}
