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
 * Servlet implementation class SearchGoods
 */
@WebServlet("/searchDrug")
public class SearchDrug extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public SearchDrug() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String dname = request.getParameter("dname");
		//传递给业务
		//接受当前页面
		String curPages = request.getParameter("curPage");
		int curPage = 1;
		//没有页码,就是第一页
		if (curPages == null || curPages.trim().equals("")) {
			curPage = 1;
		} else {
			//获得请求的页码
			curPage = Integer.parseInt(curPages);
		}

		//调用业务岑
		DrugService service = (DrugService) BeanFactory.getBean("com.gem.service.DrugServiceImpl");

		PageInfor<Drug> pageInfor = service.getDrugByName(dname, curPage, 4);
		request.setAttribute("page", pageInfor);
		//搜索条件回显
		request.setAttribute("dname", dname);
		//给jsp(同一个项目)  搜索页面
		request.getRequestDispatcher("/WEB-INF/jsp/search.jsp").forward(request, response);
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
