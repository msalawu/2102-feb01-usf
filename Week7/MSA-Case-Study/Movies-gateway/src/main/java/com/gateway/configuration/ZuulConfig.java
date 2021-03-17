package com.gateway.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.gateway.filter.PostFilter;
import com.gateway.filter.PreFilter;

@Configuration
public class ZuulConfig {

	@Bean
	public PreFilter makePre() {
		return new PreFilter();
	}
	@Bean
	public PostFilter makePost() {
		return new PostFilter();
	}
}
