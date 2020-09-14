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
		
		if(request instanceof HttpServletRequest) { //필터를 통해 charset을 한꺼번에 저장.. 
			//req은 메소드 내에서 처리하기 위한 변수고, 최종에는 다시 request를 넘겨야 한다. 
			HttpServletRequest req = (HttpServletRequest)request;
			
			uri = req.getRequestURI();
			
			if(uri.indexOf("abc.do")!=-1) {
				
				req.setCharacterEncoding("euc-kr");
				
			} else {
				
				req.setCharacterEncoding(charset); //charset = utf-8 
				
			}
		}
		
		chain.doFilter(request, response); //다음 필터/서버로 데이터를 넘겨주는 연결고리. 
		
	}

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
		charset = filterConfig.getInitParameter("charset");
		
		if(charset == null) {
			charset = "utf-8";
		}
		
	}
	

}
