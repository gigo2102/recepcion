package ar.gcba.cactyt.common;

import java.io.IOException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import spark.Request;
import spark.Response;
import spark.Route;
import spark.template.freemarker.FreeMarkerEngine;

public class RouteProcessor<H extends AbstractRequestHandler> implements Route {
    protected Object model;
    private Class<H> handlerClass;
    private FreeMarkerEngine freeMarkerEngine;
    private boolean shouldBeLoggedIn;
    private String pathWebLogin;

    public RouteProcessor(Class<H> handlerClass, Object model,
    		FreeMarkerEngine freeMarkerEngine, Boolean shouldBeLoggedIn, String pathWebLogin) {
        this.handlerClass = handlerClass;
        this.model = model;
        this.freeMarkerEngine = freeMarkerEngine;
        this.shouldBeLoggedIn = shouldBeLoggedIn;
        this.pathWebLogin = pathWebLogin;
    }
    
    private static boolean shouldReturnHtml(Request request) {
        String accept = request.headers("Accept");
        return accept != null && accept.contains("text/html");
    }

    private boolean ensureUserIsLoggedIn(Request request, Response response) {
        if (request.session().attribute("currentUser") == null) {
            response.redirect(pathWebLogin);
            return false;
        }
        return true;
    }

    @Override
    public Object handle(Request request, Response response) throws Exception {
        if (shouldBeLoggedIn && !ensureUserIsLoggedIn(request, response)) {
			return null;
		}
        
		try {
	    	String location = "";  // the directory location where files will be stored
	    	long maxFileSize = 100000000;  // the maximum size allowed for uploaded files
	    	long maxRequestSize = 100000000;  // the maximum size allowed for multipart/form-data requests
	    	int fileSizeThreshold = 1024;  // the size threshold after which files will be written to disk
	    	MultipartConfigElement multipartConfigElement = new MultipartConfigElement(location, maxFileSize, maxRequestSize, fileSizeThreshold);
	    	request.raw().setAttribute("org.eclipse.jetty.multipartConfig", multipartConfigElement);
	    	Collection<Part> parts = request.raw().getParts();
		} catch (IOException | ServletException e) {
		}

        
        
        
        String body = request.body();

		Map<String, String> urlParams = new HashMap<String, String>();
		for (Entry<String, String[]> elem : request.queryMap().toMap().entrySet()) {
			urlParams.put(elem.getKey(), elem.getValue()[0]);
		}
		urlParams.putAll(request.params());
		
		AbstractRequestHandler handler = handlerClass.newInstance();
		handler.setRequestBody(body);
		handler.setFreeMarkerEngine(freeMarkerEngine);
		handler.setRequest(request);
		handler.setResponse(response);
		handler.setSession(request.session());
		handler.setShouldReturnHtml(shouldReturnHtml(request));
		handler.setModel(model);
		handler.setUrlParams(urlParams);
		
		Answer answer = handler.process();
		return answer.generate(response);//answer.getBody();
    }
}
