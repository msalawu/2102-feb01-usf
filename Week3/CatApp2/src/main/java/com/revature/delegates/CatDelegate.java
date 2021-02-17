package com.revature.delegates;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Cat;
import com.revature.beans.Person;
import com.revature.exceptions.CatAlreadyAdoptedException;
import com.revature.services.CatService;
import com.revature.services.CatServiceImpl;

/*
 * Endpoints:
 *  /cat - (GET) retrieves all available cats
 *       - (POST) adds a cat
 *  /cat/:id - (GET) gets a cat by id
 *           - (PUT) updates a cat
 *           - (DELETE) deletes a cat
 *  /cat/adopt - (POST) adopts a cat
 *  
 */
public class CatDelegate implements FrontControllerDelegate {
	private CatService cs = new CatServiceImpl();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path"); // represents the identifier endpoint
		
		if (path == null || path.equals("")) { //default path ... could get all cats or specific one if you indicate in the code below
			switch (req.getMethod()) {
				case "POST":
					// add a cat
					Cat c = (Cat) om.readValue(req.getInputStream(), Cat.class); //create a cat based on parameters of request
					c.setId(cs.addCat(c)); //only thing need cuz new cat has no id
					resp.getWriter().write(om.writeValueAsString(c));
					resp.setStatus(HttpServletResponse.SC_CREATED);
					break;
				case "GET":
					resp.getWriter().write(
							om.writeValueAsString(cs.getAvailableCats()));
					break;
				default:
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
			}
		} else if (path.contains("adopt")) {
			if ("POST".equals(req.getMethod())) {
				Cat c = (Cat) om.readValue(req.getInputStream(), Cat.class);
				Person p = (Person) req.getSession().getAttribute("person");
				try {
					cs.adoptCat(p, c);
				} catch (CatAlreadyAdoptedException e) {
					resp.sendError(HttpServletResponse.SC_CONFLICT, "Cat already adopted");
				}
				resp.getWriter().write(om.writeValueAsString(c));
				resp.setStatus(HttpServletResponse.SC_ACCEPTED);
			} else {
				resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
			}
		} else {
			int catId = Integer.valueOf(path);
			Cat c = null;
			switch (req.getMethod()) {
				case "GET":
					c = cs.getCatById(catId);
					if (c != null) 
						resp.getWriter().write(om.writeValueAsString(c));
					else
						resp.sendError(404, "Cat not found.");
					break;
				case "PUT":
					if (isEmployee((Person) req.getSession().getAttribute("person"))) {
						c = om.readValue(req.getInputStream(), Cat.class);
						cs.updateCat(c);
						resp.getWriter().write(om.writeValueAsString(c));
					} else {
						resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					}
					break;
				case "DELETE":
					if (isEmployee((Person) req.getSession().getAttribute("person"))) {
						c = om.readValue(req.getInputStream(), Cat.class);
						cs.removeCat(c);
					} else {
						resp.sendError(HttpServletResponse.SC_UNAUTHORIZED);
					}
					break;
				default:
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
			}			
		}
	}
	
	private boolean isEmployee(Person p) {
		if (p != null && p.getRole().getName().equals("Employee"))
			return true;
		else
			return false;
	}
}
