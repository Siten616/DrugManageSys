package com.gem.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.entity.User;
import com.gem.service.UserService;
import com.gem.util.BeanFactory;

/**
 * Servlet implementation class AddUser
 */
@WebServlet("/addUser")
public class AddUser extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddUser() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserService service = (UserService) BeanFactory.getBean("com.gem.service.UserServiceImpl");
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		if (username != null && !username.trim().equals("") && password != null && !password.trim().equals("")) {
			User user = new User(null, username, password, type);
			if (service.register(user)) {
				response.getWriter().write("success");
			} else {
				response.getWriter().write("error");
			}
		} else {
			response.getWriter().write("error");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
