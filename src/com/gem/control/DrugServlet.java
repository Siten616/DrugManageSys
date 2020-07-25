package com.gem.control;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSON;
import com.gem.entity.Drug;
import com.gem.entity.PageInfor;
import com.gem.service.DrugService;
import com.gem.util.BeanFactory;

/**
 * Servlet implementation class DrugServlet
 */
@WebServlet("/drugServlet")
public class DrugServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public DrugServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		//接受当前页面
		DrugService service = (DrugService) BeanFactory.getBean("com.gem.service.DrugServiceImpl");
		PageInfor<Drug> test = service.getAllDrug(1, 10);
		String lastpage = (test.getLastPage() + 1) + "";
		String curPages = request.getParameter("curPage");
		int curPage = 1;
		//没有页码,就是第一页
		if (curPages == null || curPages.trim().equals("") || curPages.equals("0")) {
			curPage = 1;
		} else if (curPages.equals(lastpage)) {
			curPage = Integer.parseInt(curPages) - 1;
		} else {
			//获得请求的页码
			curPage = Integer.parseInt(curPages);
		}

		PageInfor<Drug> drugs = service.getAllDrug(curPage, 10);
		String str = JSON.toJSONString(drugs);
		response.getWriter().write(str);
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
