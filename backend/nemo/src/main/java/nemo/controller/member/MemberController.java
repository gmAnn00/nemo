package nemo.controller.member;

import java.io.IOException;
import java.io.PrintWriter;
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

import nemo.dao.member.MemberDAO;
import nemo.vo.member.MemberVO;

@WebServlet("/member/*")
public class MemberController extends HttpServlet {
	MemberDAO dao;
	MemberVO memberVo;
	
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
		PrintWriter out = response.getWriter();
		
		String action=request.getPathInfo();
		
		System.out.println("요청 매핑 이름 : " + action);
		if (action == null || action.equals("/adminMember")) {
			List<MemberVO> membersList = dao.listMembers(); 
			request.setAttribute("membersList", membersList);
			nextPage="/views/admin/adminMember.jsp";
			
			RequestDispatcher dispatcher =request.getRequestDispatcher(nextPage);// 디스패처로 자료를 넣음
		    dispatcher.forward(request, response); // 그 자료를 포워드 즉 넘겨준는 거임
		}else if (action.equals("/delMember.do")) {
			String user_id=request.getParameter("user_id");
			dao.delMember(user_id);
			request.setAttribute("msg", "deleted");
			nextPage="/views/admin/adminMember.do";
			out.print("<script>");
			out.print("location.href='/nemo/member';");
			out.print("</script>");
			
		}
	}
}
