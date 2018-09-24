package ar.gcba.cactyt.common;

import spark.ModelAndView;
import spark.Response;
import spark.template.freemarker.FreeMarkerEngine;

public class AnswerTemplate extends Answer {
	private FreeMarkerEngine freeMarkerEngine;
    private Object model;

    public AnswerTemplate(FreeMarkerEngine freeMarkerEngine, Object model){
    	super(200);
    	this.freeMarkerEngine = freeMarkerEngine;
    	this.model = model;
    }
    
    public FreeMarkerEngine getFreeMarkerEngine() {
		return freeMarkerEngine;
	}

	public Object getModel() {
		return model;
	}
	
	@Override
	public String generate(Response response) {
		super.generate(response);
		String body = this.getFreeMarkerEngine().render(new ModelAndView(getModel(), "layout.ftl"));
        response.type("text/html");
        return body;
	}
}
