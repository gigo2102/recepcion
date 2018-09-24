package ar.gcba.cactyt.common;

import spark.Response;

public class AnswerRedirect extends Answer {
	String location;
	
    public AnswerRedirect(String location){
    	super(303);
    	this.location = location;
    }
    
    public String getLocation() {
    	return location;
    }

    @Override
	public String generate(Response response) {
        super.generate(response);
    	response.redirect(getLocation(), getCode());
    	return "";
	}
}
