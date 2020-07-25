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
 * Servlet implementation class ChangeNumServlet
 */
@WebServlet("/ChangeNumServlet")
public class ChangeNumServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ChangeNumServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int num = Integer.parseInt(request.getParameter("num"));
		String did = request.getParameter("did");
		User user = (User) request.getSession().getAttribute("user");
		int uid = user.getUid();
		//修改购物车中商品的数量
		Cookie[] cookies = request.getCookies();
		List<Car> cars = ParseCookie.getCookietoCars(cookies, user.getUid());
		String carLiString = ParseCookie.addDrugs(cars, uid, did, num);
		Cookie cookie = new Cookie("car", carLiString);//新的购物车
		cookie.setMaxAge(60 * 60);//一小时
		response.addCookie(cookie);

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
