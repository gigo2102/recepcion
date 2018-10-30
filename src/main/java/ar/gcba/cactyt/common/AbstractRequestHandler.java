package ar.gcba.cactyt.common;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletException;
import javax.servlet.http.Part;

import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;

import com.fasterxml.jackson.databind.ObjectMapper;

import spark.Request;
import spark.Response;
import spark.Session;
import spark.template.freemarker.FreeMarkerEngine;

public abstract class AbstractRequestHandler {

    private static final int HTTP_BAD_REQUEST = 400;

    private Object model;
    private FreeMarkerEngine freeMarkerEngine;
    private Map<String, String> urlParams;
    private boolean shouldReturnHtml;
	private Session session;
    private Request request;
    private Response response;
    private String requestBody;

    public Part getPart(String name) {
		try {
			return request.raw().getPart(name);
		} catch (IOException | ServletException e) {
			e.printStackTrace();
			throw new RuntimeException("Error en getpart");
		}
    }
    
    public boolean isShouldReturnHtml() {
		return shouldReturnHtml;
	}
	public void setShouldReturnHtml(boolean shouldReturnHtml) {
		this.shouldReturnHtml = shouldReturnHtml;
	}
	
	public Session getSession() {
		return session;
	}
	public void setSession(Session session) {
		this.session = session;
	}
	
	public Request getRequest() {
		return request;
	}
	public void setRequest(Request request) {
		this.request = request;
	}
	
	public Response getResponse() {
		return response;
	}
	public void setResponse(Response response) {
		this.response = response;
	}

	public static int getHttpBadRequest() {
		return HTTP_BAD_REQUEST;
	}

    public <M> M getModel() {
    	return (M)model;
    }
    public Object setModel(Object model) {
    	return this.model = model;
    }

    public FreeMarkerEngine getFreeMarkerEngine() {
    	return freeMarkerEngine;
    }
    public FreeMarkerEngine setFreeMarkerEngine(FreeMarkerEngine freeMarkerEngine) {
    	return this.freeMarkerEngine = freeMarkerEngine;
    }

    public <V> V getValue(Class<V> clazz) {
		try {
	        ObjectMapper objectMapper = new ObjectMapper();
	        String auxBody = requestBody;
			if (!(request.contentType() != null && request.contentType().contains("json"))) {
				List<NameValuePair> pairs = URLEncodedUtils.parse(auxBody, Charset.defaultCharset());
				Map<String, String> map = new HashMap<>();
		        for(int i=0; i<pairs.size(); i++){
		            NameValuePair pair = pairs.get(i);
		            map.put(pair.getName(), pair.getValue());
		        }
		        auxBody = objectMapper.writeValueAsString(map);
			};
			V value = objectMapper.readValue(auxBody, clazz);
			return value;
		} catch (IOException e) {
			e.printStackTrace();
		}
		throw new RuntimeException();
    }

    public Map<String, String> getUrlParams() {
    	return urlParams;
    }
    public Map<String, String> setUrlParams(Map<String, String> urlParams) {
    	return this.urlParams = urlParams;
    }
    
    
    protected Answer view(String view, Object model) {
    	return view(view, model, null);
    }
    
    protected Answer view(String view, Object model, Object[] errors) {
    	Map<String, Object> viewModel = new HashMap<>();
    	viewModel.put("model", model);
    	viewModel.put("bodyTemplate", view);
    	viewModel.put("errors", errors);
		viewModel.put("isLoggedIn", isLoggedIn());
		viewModel.put("currentUser", session.attribute("currentUser"));
        return new AnswerTemplate(getFreeMarkerEngine(), viewModel);
    }
    
	protected Answer redirect(String location) {
        return new AnswerRedirect(location);    	
    }
    
    protected Answer json(Object data) {
        return new AnswerJson(data);    	
    }
    
    public abstract Answer process();
    
    public class LoggedUser
    {
		private String userUuid;
    	private String userName;
    	private List<String> roles;

    	public LoggedUser(String userUuid, String userName, String[] roles) {
			this.userUuid = userUuid;
			this.userName = userName;
			this.roles = Arrays.asList(roles);
		}
    	
		public String getUserUuid() {
			return userUuid;
		}
	
		public String getUserName() {
			return userName;
		}
	
		public List<String> getRoles() {
			return roles;
		}
    }
    
    protected void setLoggedUser(Session session, String userUuid, String userName, String[] roles) {
    	session.attribute("currentUser", new LoggedUser(userUuid, userName, roles));
    }
    
    protected boolean hasRole(String roleName) {
    	if (!isLoggedIn()) return false;
    	return ((LoggedUser)session.attribute("currentUser")).getRoles().contains(roleName);
    }
    
    protected LoggedUser getLoggedUser() {
    	if (!isLoggedIn()) return null;
    	return (LoggedUser)session.attribute("currentUser");
    }
    
    protected Boolean isLoggedIn() {
		return session.attribute("currentUser") != null;
	}
	public void setRequestBody(String body) {
		this.requestBody = body;
	}
}
