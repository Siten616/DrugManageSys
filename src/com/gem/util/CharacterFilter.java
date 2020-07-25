package com.gem.util;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;

/**
 * Servlet Filter implementation class CharacterFilter
 * @WebFilter 拦截的路径  拦截所有的请求和响应
 */
@WebFilter("/*")
public class CharacterFilter implements Filter {

	/**
	 * Default constructor. 
	 */
	public CharacterFilter() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 * 拦下来后做的事情
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		// TODO Auto-generated method stub
		//解决中文乱码
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		//给响应的都是jsp，所以servlet给不了响应
		//response.setContentType("text/html;charset=utf-8");
		//css js image不做处理
		//放行  过滤器链(多个过滤器)
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
