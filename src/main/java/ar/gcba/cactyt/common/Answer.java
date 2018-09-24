package ar.gcba.cactyt.common;

import spark.Response;

public class Answer {

    private int code;

    public Answer(int code){
        this.code = code;
    }

    public int getCode() {
        return code;
    }

	public String generate(Response response) {
        response.status(getCode());
        return "";
	}
}
