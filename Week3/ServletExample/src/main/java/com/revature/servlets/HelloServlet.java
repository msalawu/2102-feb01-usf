package com.revature.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {
	
	// GET request to /ServletExample/hello
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		PrintWriter pw = resp.getWriter();
		pw.write("<h1>Hello!</h1><p style=\"color:green\">welcome to the servlet example!</p>");
	}
	
	// POST request to /ServletExample/hello?firstname=something&lastname=something
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
		throws ServletException, IOException {
		String firstName = req.getParameter("firstname");
		String lastName = req.getParameter("lastname");
		
		PrintWriter pw = resp.getWriter();
		pw.write("Hello, " + firstName + " " + lastName + "!");
	}
}
