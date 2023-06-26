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
import nemo.vo.board.BoardVO;
import nemo.vo.group.GroupVO;
import nemo.vo.schedule.ScheduleVO;
import nemo.vo.user.UserVO;

/**
 * Servlet implementation class GroupMainController
 */
@WebServlet("/group/groupMain")
public class GroupMainController extends HttpServlet {
	HttpSession session;
	GroupMainService groupMainService;
	
	@Override
	public void init() throws ServletException {
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
		
		GroupVO groupVO = new GroupVO();
		
		boolean isMember = groupMainService.isMember(user_id, group_id);
		request.setAttribute("isMember", isMember);
		
		groupVO = groupMainService.selectGroupById(group_id);
		request.setAttribute("groupVO", groupVO);
		
		int groupNum = groupMainService.selectGroupNumById(group_id);
		request.setAttribute("groupNum", groupNum);
		
		// 최근 일정 불러옴
		List<ScheduleVO> schdulesList = null;
		schdulesList = groupMainService.selectPrviewScheduleById(group_id);
		request.setAttribute("schdulesList", schdulesList);
		
		// 최근 게시글 불러옴
		List<BoardVO> boardsList = null;
		boardsList = groupMainService.selectPreviewBoardById(group_id);
		request.setAttribute("boardsList", boardsList);
		
		// 소모임 멤버 불러옴
		List<UserVO> usersList = null;
		usersList = groupMainService.selectMemberById(group_id);
		request.setAttribute("usersList", usersList);
		
		nextPage = "/views/group/groupMain.jsp";

		RequestDispatcher dispatcher = request.getRequestDispatcher(nextPage);
		dispatcher.forward(request, response);
		
		
	}

}
