package com.gem.util;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * Application Lifecycle Listener implementation class WebPathList
 *
 */
@WebListener
public class WebPathList implements ServletContextListener {

	/**
	 * Default constructor. 
	 */
	public WebPathList() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see ServletContextListener#contextDestroyed(ServletContextEvent)
	 */
	public void contextDestroyed(ServletContextEvent sce) {
		// TODO Auto-generated method stub
	}

	/**
	 * @see ServletContextListener#contextInitialized(ServletContextEvent)
	 */
	public void contextInitialized(ServletContextEvent sce) {
		ServletContext context = sce.getServletContext();
		String path = context.getContextPath();
		context.setAttribute("basepath", path);
	}

}
