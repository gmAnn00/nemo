package nemo.controller.groupSearch;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.groupSearch.GroupSearchService;




@WebServlet("/groupSearch")
public class GroupSearchController extends HttpServlet {
	HttpSession session;
	GroupSearchService searchService;
	
	@Override
	public void init() throws ServletException {
		searchService = new GroupSearchService();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String nextPage = "";
		session=request.getSession();
		
		String action = request.getPathInfo();
		
		String user_id = (String)session.getAttribute("user_id");
		String searchText = request.getParameter("searchText");
		String main_name = request.getParameter("bigCate");
		String sub_name = request.getParameter("smallCate");
		String joinAble = request.getParameter("joinAble");
		String sort = request.getParameter("sort");
		
		System.out.println("searchText=" + searchText);
		System.out.println("bigCate=" + main_name);
		System.out.println("sub_name=" + sub_name);
		System.out.println("joinAble=" + joinAble);
		System.out.println("sort=" + sort);
		
		Map searchMap = new HashMap();
		searchMap.put("user_id", user_id);
		searchMap.put("searchText", searchText);
		searchMap.put("main_name", main_name);
		searchMap.put("sub_name", sub_name);
		searchMap.put("joinAble", joinAble);
		searchMap.put("sort", sort);
		request.setAttribute("searchMap", searchMap);
		
		List<Map> resultList = new ArrayList<Map>();
		resultList = searchService.search(searchMap);
		
		//System.out.println(resultList.toString());
		
		request.setAttribute("resultList", resultList);
		
		
		nextPage="/views/search.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
	}
}
