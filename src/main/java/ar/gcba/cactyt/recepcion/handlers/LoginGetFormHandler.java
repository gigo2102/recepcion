package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;
import ar.gcba.cactyt.recepcion.payloads.LoginPayload;

public class LoginGetFormHandler extends AbstractRequestHandler {
	@Override
	public Answer process() {
    	return view("ingreso.ftl", new LoginPayload());
	}
}
