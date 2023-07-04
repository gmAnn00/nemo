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

import nemo.service.group.JoinGroupService;


@WebServlet("/group/joinGroup")
public class JoinGroupController extends HttpServlet {
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
		
		JoinGroupService joinGroupService = new JoinGroupService();
		boolean isMember = joinGroupService.isMember(user_id, group_id);
		boolean isWait = joinGroupService.isWait(group_id);

		String group_id_str = Integer.toString(group_id);
		if(isMember) {
			out.print("<script>");
			out.print("alert('이미 가입한 소모임입니다.');");
			out.print("location.href='/nemo/group/groupMain?group_id="+group_id_str+"';");
			//out.print("location.href='/nemo/mypage/myGroupList';");
			out.print("</script>");
		} else if(!isMember && isWait) {
			joinGroupService.joinWaitList(user_id, group_id);
			out.print("<script>");
			out.print("alert('소모임 가입이 신청되었습니다.');");
			out.print("location.href='/nemo/mypage/myGroupList';");
			out.print("</script>");
		}else if(!isMember && !isWait) {
			joinGroupService.joinGroup(user_id, group_id);
			out.print("<script>");
			out.print("alert('소모임에 가입했습니다.');");
			out.print("location.href='/nemo/group/groupMain?group_id="+group_id_str+"';");
			out.print("</script>");
		}
		
		//nextPage="/group/groupMain?group_id=" + Integer.toString(group_id);
		//nextPage="/index";
		
		//RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		//dispatcher.forward(request, response);
		//response.sendRedirect("/nemo_agm/index");
		
	}
}
