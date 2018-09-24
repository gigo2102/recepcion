package ar.gcba.cactyt.recepcion.handlers;

import ar.gcba.cactyt.common.AbstractRequestHandler;
import ar.gcba.cactyt.common.Answer;

public class LogoutHandler extends AbstractRequestHandler {
	@Override
	public Answer process() {
		getSession().removeAttribute("currentUser"); 
        return redirect ("/login");
    }	    
}  
		        


