package ar.gcba.cactyt.common;

import java.io.IOException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import spark.Response;

public class AnswerJson extends Answer {
    private Object model;

    public AnswerJson(Object model){
    	super(200);
    	this.model = model;
    }

	public Object getModel() {
		return model;
	}
	
	@Override
	public String generate(Response response) {
		super.generate(response);
		String body = dataToJson(model);
        response.type("application/json");
        return body;
	}

    private static String dataToJson(Object data) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            mapper.enable(SerializationFeature.INDENT_OUTPUT);
            return mapper.writeValueAsString(data);
        } catch (IOException e){
            throw new RuntimeException("IOException from a StringWriter?");
        }
    }
}
