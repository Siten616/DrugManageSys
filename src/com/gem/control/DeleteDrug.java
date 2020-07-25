package com.gem.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.gem.service.DrugService;
import com.gem.util.BeanFactory;

/**
 * Servlet implementation class DeleteDrug
 */
@WebServlet("/deleteDrug")
public class DeleteDrug extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DeleteDrug() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String did = request.getParameter("did");
		DrugService service = (DrugService) BeanFactory.getBean("com.gem.service.DrugServiceImpl");
		if (service.deleteDrugById(did))
			response.getWriter().write("success");
		else
			response.getWriter().write("error");
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
