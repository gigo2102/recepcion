package ar.gcba.cactyt.common;

import ar.gcba.cactyt.recepcion.models.Model;
import spark.template.freemarker.FreeMarkerEngine;

public class RouteConfigurator {
    protected Model model;
    private FreeMarkerEngine freeMarkerEngine;
    private String pathWebLogin;

    public RouteConfigurator(Model model, FreeMarkerEngine freeMarkerEngine, String pathWebLogin) {
        this.model = model;
        this.freeMarkerEngine = freeMarkerEngine;
        this.pathWebLogin = pathWebLogin;
    }

    public void get(String path, Class<?> handlerClass, Boolean shouldBeLoggedIn) {
    	spark.Spark.get(path, new RouteProcessor(handlerClass, model, freeMarkerEngine, shouldBeLoggedIn, pathWebLogin));
    }
    
    public void get(String path, Class<?> handlerClass) {
    	this.get(path, handlerClass, true);
    }
    
    public void post(String path, Class<?> handlerClass, Boolean shouldBeLoggedIn) {
    	spark.Spark.post(path, new RouteProcessor(handlerClass, model, freeMarkerEngine, shouldBeLoggedIn, pathWebLogin));
    }
    
    public void post(String path, Class<?> handlerClass) {
    	this.post(path, handlerClass, true);
    }
}
