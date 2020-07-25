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
import com.gem.entity.CarDrugs;
import com.gem.entity.Drug;
import com.gem.entity.User;
import com.gem.service.DrugService;
import com.gem.util.BeanFactory;
import com.gem.util.ParseCookie;

/**
 * Servlet implementation class CarList
 * 查看购物车
 */
@WebServlet("/carList")
public class CarList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CarList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//获得session,获得用户
		Object object = request.getSession().getAttribute("user");
		if (object != null) {
			User user = (User) object;
			//获得cookie,变成List<CarGoods>集合
			Cookie[] cookies = request.getCookies();
			List<Car> cars = ParseCookie.getCookietoCars(cookies, user.getUid());
			//遍历cars集合,根据gid或商品详情,封装成CarGoods
			List<CarDrugs> drugs = new ArrayList<CarDrugs>();
			DrugService service = (DrugService) BeanFactory.getBean("com.gem.service.DrugServiceImpl");
			//获得数据

			for (int i = 0; i < cars.size(); i++) {
				Drug drug = service.getDrugById(cars.get(i).getDid());
				CarDrugs carGoods = new CarDrugs(drug.getDid(), drug.getDname(), drug.getAddress(), drug.getPrice(),
						drug.getProduction_date(), drug.getDeadline(), cars.get(i).getNum(),
						drug.getPrice() * cars.get(i).getNum());
				drugs.add(carGoods);
			}
			request.setAttribute("drugs", drugs);
			//跳转到登录页面
			request.getRequestDispatcher("WEB-INF/jsp/car.jsp").forward(request, response);
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
