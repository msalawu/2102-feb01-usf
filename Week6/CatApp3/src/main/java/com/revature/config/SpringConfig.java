package com.revature.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.DefaultServletHandlerConfigurer;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@EnableWebMvc
@ComponentScan(basePackages = "com.revature")
@Configuration
public class SpringConfig implements WebMvcConfigurer {
	// in the past with xml configuration, you would need the following tags:
	// <mvc:annotation-driven></mvc:annotation-driven>
	// <context:component-scan base-package="com.revature"></context:component-scan>
	
	@Override
	public void configureDefaultServletHandling(DefaultServletHandlerConfigurer configurer) {
		configurer.enable();
	}
}
