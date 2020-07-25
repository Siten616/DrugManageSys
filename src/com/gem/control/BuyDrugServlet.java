package com.gem.control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.entity.Car;
import com.gem.entity.User;
import com.gem.service.OrderService;
import com.gem.util.BeanFactory;
import com.gem.util.ParseCookie;

/**
 * Servlet implementation class BuyDrugServlet
 */
@WebServlet("/buya")
public class BuyDrugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuyDrugServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String gidstring = request.getParameter("dids");
		String numsstring = request.getParameter("nums");
		//解析
		String[] dids = gidstring.split(";");
		String[] nums = numsstring.split(";");
		User user = (User) request.getSession().getAttribute("user");
		//获得需要结算的商品的购物车信息
		List<Car> cars = new ArrayList<Car>();
		for (int i = 0; i < nums.length; i++) {
			String did = dids[i];
			int num = Integer.parseInt(nums[i]);
			Car car = new Car(null, user.getUid(), did, num);
			cars.add(car);
		}
		//删除购物车中已经被结算的商品，更新到购物车cookie
		Cookie[] cookies = request.getCookies();
		List<Car> carAll = ParseCookie.getCookietoCars(cookies, user.getUid());
		for (int i = 0; i < carAll.size(); i++) {
			for (int j = 0; j < cars.size(); j++) {
				if (carAll.get(i).getDid().equals(cars.get(j).getDid())) {
					carAll.remove(i);
				}
			}
		}
		String newCar = ParseCookie.getCarToString(carAll);
		Cookie cookie = new Cookie("car", newCar);
		cookie.setMaxAge(60 * 30);
		response.addCookie(cookie);
		//执行购买操作，生成订单，添加订单，生成订单详情，添加订单详情到数据库中
		OrderService service = (OrderService) BeanFactory.getBean("com.gem.service.OrderServiceImpl");
		boolean flag = service.buy(user.getUid(), cars);

		//跳转到订单页面  (添加,重定向)
		response.sendRedirect(request.getServletContext().getContextPath() + "/orderList");
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
