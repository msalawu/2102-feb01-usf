package com.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class PreFilter extends ZuulFilter {

	@Override
	public boolean shouldFilter() {
		System.out.println("in the pre shouldFilter method");
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("in the run method of the pre filter");
		return null;
	}

	@Override
	public String filterType() {
		System.out.println("in the pre filter's filterType method");
		return "pre";
	}

	@Override
	public int filterOrder() {
		System.out.println("in the pre filter's filterOrder method");
		return 0;
	}

}
