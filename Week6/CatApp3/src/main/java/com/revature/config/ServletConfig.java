package com.revature.config;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration;

import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

public class ServletConfig implements WebApplicationInitializer {
	// this class replaces the deployment descriptor (web.xml) that maps servlets
	
	@Override
	public void onStartup(ServletContext servletContext) throws ServletException {
		AnnotationConfigWebApplicationContext appCtx = new AnnotationConfigWebApplicationContext();
		appCtx.register(SpringConfig.class);
		appCtx.setServletContext(servletContext);
		ServletRegistration.Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(appCtx));
		servlet.setLoadOnStartup(1);
		servlet.addMapping("/");
	}
}
