package com.gem.control;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.entity.Car;
import com.gem.entity.User;
import com.gem.util.ParseCookie;

/**
 * Servlet implementation class AddCar
 */
@WebServlet("/addCar")
public class AddCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddCar() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		request.setCharacterEncoding("utf-8");
		String did = request.getParameter("did");
		int num = Integer.parseInt(request.getParameter("num"));
		//获得session
		Object object = request.getSession().getAttribute("user");
		if (object == null) {
			//跳转到登录页面
			request.getRequestDispatcher("/jsp/login.jsp").forward(request, response);
		} else {
			User user = (User) object;
			//存cookie中【字符串解析】
			//cookie->分割->cookie商品分割成List<Car>
			//获得购物车的商品
			Cookie[] cookies = request.getCookies();

			List<Car> cars = ParseCookie.getCookietoCars(cookies, user.getUid());

			String carLiString = ParseCookie.addDrugs(cars, user.getUid(), did, num);

			Cookie cookie = new Cookie("car", carLiString);//新的购物车

			cookie.setMaxAge(60 * 30);//半小时
			response.addCookie(cookie);
			//跳转到购物车[重定向]
			response.sendRedirect(getServletContext().getContextPath() + "/carList");
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
