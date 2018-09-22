package me.tomassetti.handlers;

import me.tomassetti.AbstractRequestHandler;
import me.tomassetti.Answer;
import me.tomassetti.model.Model;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

import java.util.Map;
import java.util.UUID;

public class LogoutHandler extends AbstractRequestHandler<LogoutPayload> {
    public LogoutHandler(Model model, FreeMarkerEngine freeMarkerEngine) {
		super(LogoutPayload.class, model, freeMarkerEngine);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected Answer processImpl(LogoutPayload value, Map<String, String> urlParams, boolean shouldReturnHtml,
			Session session) {
		session.removeAttribute("currentUser"); 
        return redirect ("/login");
    }	    
}  
		        


