package com.gem.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.entity.User;
import com.gem.service.UserService;
import com.gem.util.BeanFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public LoginServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		String type = request.getParameter("type");
		UserService service = (UserService) BeanFactory.getBean("com.gem.service.UserServiceImpl");
		User user = service.login(username, password, type);
		if (user != null) {//登录成功
			request.getSession().setAttribute("user", user);
			Cookie cookie = new Cookie("JSESSIONID", request.getSession().getId());
			//持久化
			cookie.setPath("/");
			cookie.setMaxAge(60 * 24 * 30);//一个月
			response.addCookie(cookie);
			if (user.getType().equals("admin"))
				//转发，跳转到主页index
				request.getRequestDispatcher("/index").forward(request, response);
			else {
				request.getRequestDispatcher("/operatorIndex").forward(request, response);
			}
		} else {
			request.setAttribute("error", "用户名或者密码错误");
			request.getRequestDispatcher("/path/toLogin").forward(request, response);
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
