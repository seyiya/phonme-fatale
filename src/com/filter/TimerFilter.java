package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class TimerFilter implements Filter{ //javax.Servlet ÀÇ Filter import 
	
	private FilterConfig config;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		long before = System.currentTimeMillis(); //start time
		
		chain.doFilter(request, response);
		
		long after = System.currentTimeMillis(); //end time
		
		String uri = "";
		
		if(request instanceof HttpServletRequest) {
			HttpServletRequest req = (HttpServletRequest)request;
			
			uri = req.getRequestURI();
			
			config.getServletContext().log(uri + ":" + (after-before) + "ms");
		}
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		this.config = filterConfig;
		
	}
	
}
