package com.revature.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.delegates.CatDelegate;
import com.revature.delegates.EmployeeDelegate;
import com.revature.delegates.FrontControllerDelegate;
import com.revature.delegates.LoginDelegate;

public class RequestHandler {
	private Map<String, FrontControllerDelegate> delegateMap;
	
	{
		delegateMap = new HashMap<String, FrontControllerDelegate>();
		
		delegateMap.put("cat", new CatDelegate());
		delegateMap.put("user", new LoginDelegate());
		delegateMap.put("employee", new EmployeeDelegate());
	}
	
	public FrontControllerDelegate handle(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// if the request is an OPTIONS request,
		// we will return an empty delegate
		if ("OPTIONS".equals(req.getMethod())) {
			return (r1, r2) -> {};
		}
		
		// first, we get the URI from the request
		StringBuilder uriString = new StringBuilder(req.getRequestURI());
		// at this point, uriString = localhost:8080/CatApp/cat/4
		
		// next, we get rid of the first part of the URL
		uriString.replace(0, req.getContextPath().length()+1, "");
		// at this point, uriString = cat/4
		
		if (uriString.indexOf("/") != -1) {
			req.setAttribute("path", uriString.substring(uriString.indexOf("/")+1));
			uriString.replace(uriString.indexOf("/"), uriString.length(), "");
			// at this point, uriString = cat
		}
		
		return delegateMap.get(uriString.toString());
	}
}
