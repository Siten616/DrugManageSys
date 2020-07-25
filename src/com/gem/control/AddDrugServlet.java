package com.gem.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.entity.Drug;
import com.gem.service.DrugService;
import com.gem.util.BeanFactory;

/**
 * Servlet implementation class AddDrugServlet
 */
@WebServlet("/addDrugServlet")
public class AddDrugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddDrugServlet() {
		super();
		// TODO Auto-generated constructor stubv
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DrugService service = (DrugService) BeanFactory.getBean("com.gem.service.DrugServiceImpl");
		String did = request.getParameter("did");
		String dname = request.getParameter("dname");
		String address = request.getParameter("address");
		String production_date = request.getParameter("production_date");
		String deadline = request.getParameter("deadline");
		double price = request.getParameter("price").isEmpty() ? 0 : Double.parseDouble(request.getParameter("price"));
		int num = request.getParameter("num").isEmpty() ? 0 : Integer.parseInt(request.getParameter("num"));
		int stock = request.getParameter("stock").isEmpty() ? 0 : Integer.parseInt(request.getParameter("stock"));
		if (stock != 0) {
			boolean flag = service
					.addDrug(new Drug(did, dname, address, production_date, deadline, price, num + stock));
		}
		response.sendRedirect("/DrugManageSys/index");
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
