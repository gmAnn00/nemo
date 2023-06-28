package nemo.controller.member;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nemo.dao.member.MemberDAO;
import nemo.vo.member.MemberVO;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	MemberDAO dao;
	
	@Override
	public void init() throws ServletException {
		dao=new MemberDAO();
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
		String action=request.getPathInfo();
		System.out.println("요청 매핑 이름 : " + action);
		if (action == null || action.equals("/adminMember.do")) {
			List<MemberVO> membersList = dao.listMembers(); 
			request.setAttribute("membersList", membersList);
			nextPage="/admin/adminMember.jsp";
		}else if (action.equals("/delMember.do")) {
			String user_id=request.getParameter("user_id");
			dao.delMember(user_id);
			request.setAttribute("msg", "deleted");
			nextPage="/admin/adminMember.do";
			return;
		}
	}
}
