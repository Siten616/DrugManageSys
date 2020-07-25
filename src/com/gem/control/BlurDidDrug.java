package com.gem.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gem.entity.Drug;
import com.gem.service.DrugService;
import com.gem.util.BeanFactory;

/**
 * Servlet implementation class BlurDidAddDrug
 */
@WebServlet("/blurDidDrug")
public class BlurDidDrug extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BlurDidDrug() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		DrugService service = (DrugService) BeanFactory.getBean("com.gem.service.DrugServiceImpl");
		String did = request.getParameter("did");
		if (did != null) {
			Drug drug = service.getDrugById(did);
			String drugJson = JSON.toJSONString(drug);
			response.getWriter().write(drugJson);
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
