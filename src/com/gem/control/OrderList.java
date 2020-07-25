package com.gem.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.entity.Order;
import com.gem.entity.PageInfor;
import com.gem.entity.User;
import com.gem.service.OrderService;
import com.gem.util.BeanFactory;

/**
 * Servlet implementation class OrderList
 */
@WebServlet("/orderList")
public class OrderList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OrderList() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String curPages = request.getParameter("curPage");
		int curPage = 1;
		//没有页码,就是第一页
		if (curPages == null || curPages.trim().equals("")) {
			curPage = 1;
		} else {
			//获得请求的页码
			curPage = Integer.parseInt(curPages);
		}
		User user = (User) request.getSession().getAttribute("user");
		OrderService service = (OrderService) BeanFactory.getBean("com.gem.service.OrderServiceImpl");
		//一页显示5个订单
		PageInfor<Order> page = service.getOrderList(user.getUid(), curPage, 5);

		//System.out.println(page);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/jsp/order.jsp").forward(request, response);
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
