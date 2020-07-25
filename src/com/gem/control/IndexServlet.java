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
 * Servlet implementation class IndexServlet
 */
@WebServlet("/index")
public class IndexServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public IndexServlet() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
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

		//获得当前页的数据
		PageInfor<Drug> page = service.getAllDrug(curPage, 10);//获得所有的商品
		//第一个域对象:request[map],同一次请求中的页面
		//只要想把数据传给jsp,就把数据往域对象中放
		request.setAttribute("page", page);
		request.getRequestDispatcher("/WEB-INF/jsp/index.jsp").forward(request, response);
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
