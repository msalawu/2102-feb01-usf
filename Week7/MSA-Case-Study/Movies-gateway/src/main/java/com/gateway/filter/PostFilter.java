package com.gateway.filter;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.exception.ZuulException;

public class PostFilter extends ZuulFilter{

	@Override
	public boolean shouldFilter() {
		System.out.println("in the post filter shouldFilter method");
		return true;
	}

	@Override
	public Object run() throws ZuulException {
		System.out.println("in the post's run method");
		return null;
	}

	@Override
	public String filterType() {
		System.out.println("in the filterType of the post filter");
		return "post";
	}

	@Override
	public int filterOrder() {
		System.out.println("in the post's filterOrder");
		return 0;
	}

}
