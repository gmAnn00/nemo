package nemo.controller.group;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.group.GroupMainService;
import nemo.vo.group.GroupVO;

/**
 * Servlet implementation class GroupMainController
 */
@WebServlet("/group/groupMain")
public class GroupMainController extends HttpServlet {
	HttpSession session;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String nextPage = "";
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		session=request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		
		GroupVO groupVO = new GroupVO();
		GroupMainService groupMainService = new GroupMainService();
		
		groupVO = groupMainService.selectGroupById(group_id);
		
		nextPage = "/group/groupMain.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		
		
	}

}
