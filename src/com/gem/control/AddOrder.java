package com.gem.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.entity.User;
import com.gem.service.OrderService;
import com.gem.util.BeanFactory;

/**
 * Servlet implementation class AddOrder
 */
@WebServlet("/addOrder")
public class AddOrder extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddOrder() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String did = request.getParameter("did");
		OrderService service = (OrderService) BeanFactory.getBean("com.gem.service.OrderServiceImpl");
		User user = (User) request.getSession().getAttribute("user");
		boolean flag = service.buyRight(user.getUid(), did);
		if (flag) {
			response.sendRedirect(request.getServletContext().getContextPath() + "/orderList");
		} else {
			response.getWriter().write("失败");
		}
		//添加操作,重定向 订单列表的servlet
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
