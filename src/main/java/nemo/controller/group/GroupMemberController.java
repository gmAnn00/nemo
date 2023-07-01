package nemo.controller.group;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.group.GroupMainService;
import nemo.service.group.GroupMemberService;
import nemo.vo.user.UserVO;
import nemo.vo.group.GroupVO;


@WebServlet("/group/member/*")
public class GroupMemberController extends HttpServlet {
	HttpSession session;
	GroupMemberService groupMemberService;
	GroupMainService groupMainService;
	
	@Override
	public void init() throws ServletException {
		groupMemberService = new GroupMemberService();
		groupMainService = new GroupMainService();
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
		PrintWriter out = response.getWriter();
		
		String nextPage = "";
		int group_id = Integer.parseInt(request.getParameter("group_id"));
		session=request.getSession();
		String user_id = (String) session.getAttribute("user_id");
		String action = request.getPathInfo();
		System.out.println("action="+action);
		
		if(action == null) {
			GroupVO groupVO = new GroupVO();
			
			groupVO = groupMainService.selectGroupById(group_id);
			request.setAttribute("groupVO", groupVO);
			
			int groupNum = groupMainService.selectGroupNumById(group_id);
			request.setAttribute("groupNum", groupNum);
			
			// 소모임 멤버 불러옴
			List<UserVO> usersList = null;
			usersList = groupMemberService.selectMemberById(group_id);
			request.setAttribute("usersList", usersList);
			
			nextPage = "/views/group/groupMember.jsp";

			RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
			dispatcher.forward(request, response);
		}else if(action.equals("/delete")) {
			groupMemberService.deleteMember(user_id, group_id);
			out.print("<script>");
			out.print("alert('소모임을 탈퇴했습니다.');");
			out.print("location.href='/nemo/index';");
			out.print("</script>");
			
		}
		
		
		
		
	}


}
