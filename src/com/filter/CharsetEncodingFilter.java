package com.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

public class CharsetEncodingFilter implements Filter {

	private String charset;
	
	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		

		String uri;
		
		if(request instanceof HttpServletRequest) { //���͸� ���� charset�� �Ѳ����� ����.. 
			//req�� �޼ҵ� ������ ó���ϱ� ���� ������, �������� �ٽ� request�� �Ѱܾ� �Ѵ�. 
			HttpServletRequest req = (HttpServletRequest)request;
			
			uri = req.getRequestURI();
			
			if(uri.indexOf("abc.do")!=-1) {
				
				req.setCharacterEncoding("euc-kr");
				
			} else {
				
				req.setCharacterEncoding(charset); //charset = utf-8 
				
			}
		}
		
		chain.doFilter(request, response); //���� ����/������ �����͸� �Ѱ��ִ� �����. 
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		charset = filterConfig.getInitParameter("charset");
		
		if(charset == null) {
			charset = "utf-8";
		}
		
	}
	

}
