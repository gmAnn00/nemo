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

import com.google.gson.Gson;

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
		String action = request.getPathInfo();
		String nextPage = "";
		session=request.getSession();
		
		String _section = request.getParameter("section");
		String _pageNum = request.getParameter("pageNum");
		
		int section = Integer.parseInt((_section == null) ? "1" : _section);
		int pageNum = Integer.parseInt((_section) == null ? "1" : _pageNum);
		
		String user_id = (String)session.getAttribute("user_id");
		String searchText = request.getParameter("searchText");
		String main_name = request.getParameter("bigCate") == null ? "none" : request.getParameter("bigCate");
		String sub_name = request.getParameter("smallCate") == null ? "none" : request.getParameter("smallCate");
		String joinAble = request.getParameter("joinAble") == null ? "none" : request.getParameter("joinAble");
		String sort = request.getParameter("sort") == null ? "sortByName" : request.getParameter("sort");
		int areaBar = request.getParameter("areaBar") == null ? -1 : Integer.parseInt(request.getParameter("areaBar"));
		String userLat = request.getParameter("userLat");
		String userLng = request.getParameter("userLng");
		
		//System.out.println(request.getParameter("userLat"));
		//System.out.println(request.getParameter("userLng"));
		
		System.out.println("searchText=" + searchText);
		System.out.println("main_name=" + main_name);
		System.out.println("sub_name=" + sub_name);
		System.out.println("joinAble=" + joinAble);
		System.out.println("sort=" + sort);
		System.out.println("areaBar=" + areaBar);
		System.out.println("userLat=" + userLat);
		System.out.println("userLng=" + userLng);
		
		Map searchMap = new HashMap();
		searchMap.put("user_id", user_id);
		searchMap.put("searchText", searchText);
		searchMap.put("main_name", main_name);
		searchMap.put("sub_name", sub_name);
		searchMap.put("joinAble", joinAble);
		searchMap.put("sort", sort);
		searchMap.put("areaBar", areaBar);
		searchMap.put("userLat", userLat);
		searchMap.put("userLng", userLng);
		searchMap.put("section", section);
		searchMap.put("pageNum", pageNum);
		
		int totGroup = searchService.findTotGroup(searchMap);
		searchMap.put("totGroup", totGroup);
		
		request.setAttribute("searchMap", searchMap);
		
		List<Map> resultList = new ArrayList<Map>();
		resultList = searchService.search(searchMap);
		
		
		//System.out.println(resultList.toString());
		
		request.setAttribute("resultList", resultList);
		
		/*
		if(resultList != null) {
			Gson gson = new Gson();
			String jsonResultList = gson.toJson(resultList);
			request.setAttribute("jsonResultList", jsonResultList);
			System.out.println(jsonResultList);
		}
		 */
		nextPage="/views/search.jsp";
		
		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		
	}
}
