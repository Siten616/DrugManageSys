package com.gem.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.entity.Drug;
import com.gem.entity.PageInfor;
import com.gem.service.DrugService;
import com.gem.util.BeanFactory;

/**
 * Servlet implementation class OperatorIndexServlet
 */
@WebServlet("/operatorIndex")
public class OperatorIndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public OperatorIndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DrugService service = (DrugService) BeanFactory.getBean("com.gem.service.DrugServiceImpl");
		PageInfor<Drug> page = service.getAllDrug(1, 8);
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/jsp/operator.jsp").forward(request, response);
		;
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
