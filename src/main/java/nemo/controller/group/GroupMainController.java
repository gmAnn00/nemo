package nemo.controller.group;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nemo.service.board.BoardService;
import nemo.service.group.GroupInfoService;
import nemo.service.group.GroupMainService;
import nemo.vo.board.BoardVO;
import nemo.vo.group.GroupVO;
import nemo.vo.group.ScheduleVO;
import nemo.vo.user.UserVO;

/**
 * Servlet implementation class GroupMainController
 */
@WebServlet("/group/groupMain")
public class GroupMainController extends HttpServlet {
	HttpSession session;
	GroupMainService groupMainService;
	BoardService boardService;
	GroupInfoService groupInfoService;
	Map groupInfo;
	
	@Override
	public void init() throws ServletException {
		groupMainService = new GroupMainService();
		boardService  = new BoardService();
		groupInfoService=new GroupInfoService();
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
		groupInfo=groupInfoService.getGroupInfo(group_id);
		request.setAttribute("groupInfo", groupInfo);
		
		// 로그인 컨트롤러로 옮겨야 함
		// 유저가 소모임장인 소모임의 그룹넘버 저장
		List<String> grpMngList = groupMainService.grpMng(user_id);
		session.setAttribute("grpMngList", grpMngList);
		
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