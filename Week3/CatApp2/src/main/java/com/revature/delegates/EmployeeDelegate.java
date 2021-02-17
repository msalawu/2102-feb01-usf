package com.revature.delegates;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.revature.beans.Breed;
import com.revature.beans.SpecialNeed;
import com.revature.beans.Status;
import com.revature.services.CatService;
import com.revature.services.CatServiceImpl;
import com.revature.services.PersonService;
import com.revature.services.PersonServiceImpl;

/*
 * Endpoints:
 *  /employee/status - (GET) returns all of the statuses that a cat can have.
 *  (for cat creation)
 *  /employee/breed - (GET) returns all of the breeds that a cat can have.
 *  (for cat creation)
 *         - (POST) creates a new breed
 *  /employee/specialneed - (GET) returns all of the special needs a cat can have.
 *               - (POST) creates a new special need
 *  /employee/role - (GET) returns all of the roles a person can have.
 *  
 */
public class EmployeeDelegate implements FrontControllerDelegate {
	private PersonService pServ = new PersonServiceImpl();
	private CatService cServ = new CatServiceImpl();
	private ObjectMapper om = new ObjectMapper();
	
	@Override
	public void process(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		String path = (String) req.getAttribute("path");
		
		if (path.contains("status")) {
			switch (req.getMethod()) {
				case "GET":
					Set<Status> statusset = cServ.getAllStatuses();
					resp.getWriter().write(om.writeValueAsString(statusset));
					break;
				default:
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
			}
		}
		else if (path.contains("breed")) {
			switch (req.getMethod()) {
				case "GET":
					Set<Breed> breedset = cServ.getAllBreeds();
					resp.getWriter().write(om.writeValueAsString(breedset));
					break;
				case "POST":
					Breed b = om.readValue(req.getInputStream(), Breed.class);
					cServ.addBreed(b);
					break;
				default:
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
			}
		} else if (path.contains("specialneed")) {
			switch (req.getMethod()) {
				case "GET":
					// TODO implement this functionality (all layers)
					// Set<SpecialNeed> specialneedset = cServ.getNeeds();
					// resp.getWriter().write(om.writeValueAsString(specialneedset));
					break;
				case "POST":
					// TODO implement this functionality (all layers)
					// SpecialNeed sn = om.readValue(req.getInputStream(), SpecialNeed.class);
					//cServ.addSpecialNeed(sn);
					break;
				default:
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
			}
		} else if (path.contains("role")) {
			switch (req.getMethod()) {
				case "GET":
					// TODO implement this functionality (all layers)
					// Set<Role> rolls = pServ.getRoles();
					// resp.getWriter().write(om.writeValueAsString(rolls));
					break;
				default:
					resp.sendError(HttpServletResponse.SC_METHOD_NOT_ALLOWED);
					break;
			}
		} else {
			resp.sendError(404);
		}
	}
}
