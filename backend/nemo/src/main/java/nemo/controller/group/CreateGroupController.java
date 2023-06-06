package nemo.controller.group;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.vo.group.GroupVO;


@WebServlet("/group/createGroup/*")
public class CreateGroupController extends HttpServlet {
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
		session=request.getSession();

		GroupVO groupVO = new GroupVO();
		String user_id = "";
		user_id = (String) session.getAttribute("user_id");
		
		String action = request.getPathInfo();
		System.out.println("요청 매핑이름 : " + action);
		
		if(action.equals("/form")) {
			String nextPage = "/group/createGroupForm.jsp";
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}else if (action.equals("/create")) {
			System.out.println(request.getParameter("app_st"));
			
			
			
			response.sendRedirect("/nemo/group/groupMain?group_id=1");

		}
		
	}

}
