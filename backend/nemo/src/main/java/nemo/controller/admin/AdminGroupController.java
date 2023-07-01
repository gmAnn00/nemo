package nemo.controller.admin;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nemo.dao.admin.AdminGroupDAO;
import nemo.vo.group.GroupVO;


@WebServlet("/admingroup/*")
public class AdminGroupController extends HttpServlet {
	AdminGroupDAO dao;
	GroupVO groupVo;
	
	
	@Override
	public void init() throws ServletException {
		dao = new AdminGroupDAO();
		groupVo = new GroupVO();
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doHandle(request, response);
	}
	
	private void doHandle(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nextPage=null;
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		String action=request.getPathInfo();
		
		System.out.println("요청 매핑 이름 : " + action);
		if(action == null) {
			List<GroupVO> groupsList = dao.listGroups();
			request.setAttribute("groupsList", groupsList);
			nextPage="views/admin/adminGroup.jsp";
			
			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);// 디스패처로 자료를 넣음
		    dispatcher.forward(request, response); // 그 자료를 포워드 즉 넘겨준는 거임
		    
		} else if (action.equals("/delGroup.do")) {			
			int grp_id=Integer.parseInt(request.getParameter("grp_id"));
			//System.out.println("grp_id" + grp_id);
			dao.delGroup(grp_id);
			request.setAttribute("msg", "deleted");
			
			nextPage="/views/admin/admingroup.do";			
			out.print("<script>");
			out.print("location.href='/nemo/admingroup';");
			out.print("</script>");
			
		}
	}

}
